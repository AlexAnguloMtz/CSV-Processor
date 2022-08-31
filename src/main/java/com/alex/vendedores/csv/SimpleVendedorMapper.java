package com.alex.vendedores.csv;

import com.alex.vendedores.fecha.FechaParser;
import com.alex.vendedores.dominio.Vendedor;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

/**
 * Implementacion de la interfaz VendedorMapper para mapear
 * los datos de un vendedor de su formato de archivo CSV
 * a su correspondiente objeto de la clase Vendedor del dominio.
 *
 * @author Alex Angulo
 */

public final class SimpleVendedorMapper implements VendedorMapper {

    private static final int INDICE_ID = 0;
    private static final int INDICE_NOMBRE = 1;
    private static final int INDICE_FECHA_DE_NACIMIENTO = 2;
    private static final int INDICE_ESTADO = 3;

    /**
     * Regex que coincide con una fila de datos de un Vendedor en formato CSV
     */
    private static final String REGEX_VENDEDOR_CSV = "^\\d+,[\\w ]+,\\d+/\\d+/\\d+,[\\w ]+$";

    /**
     *  Mapea una String de un vendedor en formato CSV
     *  a su equivalente objeto modelo Vendedor
     *
     * @param filaVendedor los datos del vendedor en formato CSV
     * @return El objeto Vendedor mapeado
     */
    @Override
    public Vendedor mapearAVendedor(String filaVendedor) {
        String[] datosVendedor = separarPorComas(filaVendedor);
        return Vendedor.builder()
                .id(extraerId(datosVendedor))
                .nombre(extraerNombre(datosVendedor))
                .fechaDeNacimiento(extraerFechaDeNacimiento(datosVendedor))
                .estado(extraerEstado(datosVendedor))
                .build();
    }

    /**
     * Extrae el id del vendedor
     *
     * @param datosVendedor los datos del vendedor
     * @return id del vendedor
     */
    private int extraerId(String[] datosVendedor) {
        return parseInt(datosVendedor[INDICE_ID]);
    }

    /**
     * Extrae el nombre del vendedor
     *
     * @param datosVendedor los datos del vendedor
     * @return nombre del vendedor
     */
    private String extraerNombre(String[] datosVendedor) {
        return datosVendedor[INDICE_NOMBRE];
    }

    /**
     * Extrae la fecha de nacimiento del vendedor
     *
     * @param datosVendedor los datos del vendedor
     * @return fecha de nacimiento del vendedor
     */
    private LocalDate extraerFechaDeNacimiento(String[] datosVendedor) {
        String fecha = datosVendedor[INDICE_FECHA_DE_NACIMIENTO];
        return new FechaParser().parsear(fecha, "mm/dd/yyyy");
    }

    /**
     * Extrae el estado donde reside el vendedor
     *
     * @param datosVendedor los datos del vendedor
     * @return estado donde reside el vendedor
     */
    private String extraerEstado(String[] datosVendedor) {
        return datosVendedor[INDICE_ESTADO];
    }

    /**
     * Separa los datos del vendedor por comas
     *
     * @param filaVendedor los datos del vendedor en formato CSV
     * @return datos del vendedor ya separados por comas
     */
    private String[] separarPorComas(String filaVendedor) {
        return filaVendedor.split(",");
    }

    /**
     * Returna si la linea de texto corresponde o no
     * con el formato esperado para un Vendedor.
     *
     * <p>Su utilidad es la de ignorar lineas no deseadas.
     *
     * @param filaVendedor
     * @return true si la linea de texto corresponde
     *         con el patron esperado para un Vendedor,
     *         false en caso contrario
     */
    public boolean esVendedor(String filaVendedor) {
        return filaVendedor.matches(REGEX_VENDEDOR_CSV);
    }

    /**
     * Mapea un Vendedor a su formato CSV.
     * El formato sera el siguiente:
     *
     * - id
     * - Nombre, maximo 35 caracteres
     * - Fecha de nacimiento exactamente en formato DD/MM/AAAA
     * - Estado, maximo 15 caracteres
     *
     *
     * @param vendedor el vendedor a mapear
     * @return String en formato CSV que representa al Vendedor
     */
    @Override
    public String mapearAFormatoCSV(Vendedor vendedor) {
        return format("%d,%.35s,%s/%s/%d,%.15s",
                vendedor.getId(),
                vendedor.getNombre(),
                padding(vendedor.getFechaDeNacimiento().getDayOfMonth()),
                padding(vendedor.getFechaDeNacimiento().getMonth().getValue()),
                vendedor.getFechaDeNacimiento().getYear(),
                vendedor.getEstado()
        );
    }

    /**
     * Recibe un numero y returna la representacion String del numero
     * con un padding extra de un digito 0 de ser necesario,
     * para un total de dos digitos
     *
     * @param numero el numero a transformar en String
     *               con padding de un 0 a la izquierda
     * @return la String que incluye al numero original y
     * un padding izquierdo de un 0 en caso necesario
     */
    private String padding(int numero) {
        return format("%02d", numero);
    }
}
