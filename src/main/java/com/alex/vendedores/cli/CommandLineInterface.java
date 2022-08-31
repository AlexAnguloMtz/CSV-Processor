package com.alex.vendedores.cli;

import com.alex.vendedores.applicacion.InterfazUsuario;
import com.alex.vendedores.dominio.ReporteEdadPromedioPorZona;
import com.alex.vendedores.dominio.ReporteGeneral;
import com.alex.vendedores.dominio.Vendedor;
import com.alex.vendedores.fecha.FechaParser;
import com.alex.vendedores.fecha.FormatoFechaException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import static java.lang.String.format;

/**
 * Clase para abstraer la salida mediante la linea de comandos
 *
 * @author Alex Angulo
 */
public final class CommandLineInterface implements InterfazUsuario {

    /**
     * El formatter para dar formato a los objetos Vendedor
     */
    private final Function<Vendedor, String> formatter;

    /**
     * Scanner para leer entrada estándar del teclado
     */
    private final Scanner entrada;

    public CommandLineInterface(Function<Vendedor, String> formatter) {
        this.formatter = formatter;
        this.entrada = new Scanner(System.in);
    }

    /**
     * Muestra una tabla con el reporte general de todos los vendedores,
     * con la siguiente informacion de cada uno:
     * -- id
     * -- nombre
     * -- estado
     * -- fecha de nacimiento
     * -- edad
     *
     * @param reporte El reporte a mostrar
     */
    @Override
    public void aceptar(ReporteGeneral reporte) {
        String encabezadosTabla = format("%3s %20s %15s %15s %10s %n",
                "Id", "Nombre", "Fecha Nac", "Estado", "Edad");

        mostrarConFormato(encabezadosTabla);

        reporte.datos().stream()
                .map(formatter)
                .forEach(this::mostrar);
    }

    /**
     * Muestra una tabla con el reporte del promedio de edad
     * de los vendedores en cada zona geografica
     *
     * @param reporte El reporte a mostrar
     */
    @Override
    public void aceptar(ReporteEdadPromedioPorZona reporte) {
        String encabezadosTabla = format("%20s %15s %n", "Estado", "Edad Promedio");

        mostrarConFormato(encabezadosTabla);

        reporte.datos().stream()
                .map(this::formatearPromedioEdadPorZona)
                .forEach(this::mostrar);
    }

    /**
     * Solicita los datos de un vendedor al usuario
     * y returna el objeto Vendedor correspondiente
     *
     * @return el vendedor obtenido
     */
    @Override
    public Vendedor solicitarVendedor(String formatoFecha) {
        return Vendedor.builder()
                .id(solicitarId())
                .nombre(solicitarNombre())
                .fechaDeNacimiento(solicitarFechaNacimiento(formatoFecha))
                .estado(solicitarEstado())
                .build();
    }

    /**
     * Notifica que el Vendedor fue guardado de manera exitosa
     */
    public void notificarQueElVendedorHaSidoGuardado() {
        mostrar(Mensajes.VENDEDOR_FUE_GUARDADO);
    }

    /**
     * Solicita un id
     *
     * @return id del Vendedor
     */
    private int solicitarId() {
        mostrar(Mensajes.INGRESE_ID);
        return leerEntero();
    }

    /**
     * Solicita un nombre
     *
     * @return un nombre
     */
    private String solicitarNombre() {
        mostrar(Mensajes.INGRESE_NOMBRE);
        return leerLinea();
    }

    /**
     * Solicita una fecha de nacimiento
     *
     * @return una fecha de nacimiento
     */
    private LocalDate solicitarFechaNacimiento(String formatoFechaRequerido) throws FormatoFechaException {
        mostrar(Mensajes.ingreseFecha(formatoFechaRequerido));
        String posibleFecha = leerLinea();
        LocalDate fechaNacimiento = null;
        try {
            fechaNacimiento = new FechaParser().parsear(posibleFecha, formatoFechaRequerido);
        } catch (FormatoFechaException exception) {
            mostrar(Mensajes.errorFechaFormatoInvalido(posibleFecha, formatoFechaRequerido));
            abortarApp();
        } catch (DateTimeException exception) {
            mostrar(Mensajes.errorFechaImposible(posibleFecha, formatoFechaRequerido));
            abortarApp();
        }
        return fechaNacimiento;
    }

    /**
     * Solicita un estado
     *
     * @return estado solicitado
     */
    private String solicitarEstado() {
        mostrar(Mensajes.INGRESE_ESTADO);
        return leerLinea();
    }

    /**
     * Da formato de salida a la zona geografica con su promedio de edad
     *
     * @param promedioEdadPorZona Contiene el nombre de la zona geografica y su promedio de edad
     * @return String que representa el formato de salida del promedio de edad por zona geografica
     */
    private String formatearPromedioEdadPorZona(Map.Entry<String, Double> promedioEdadPorZona) {
        return format("%20s %15.2f",
                promedioEdadPorZona.getKey(),
                promedioEdadPorZona.getValue()
        );
    }

    /**
     * Muestra un mensaje
     *
     * @param mensaje el mensaje a mostrar
     */
    private void mostrar(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra un mensaje con formato
     *
     * @param mensajeConFormato el mensaje a mostrar con formato
     */
    private void mostrarConFormato(String mensajeConFormato) {
        System.out.printf(mensajeConFormato);
    }

    /**
     * Lee una linea
     *
     * @return la linea leida
     */
    private String leerLinea() {
        return entrada.nextLine();
    }

    /**
     * Lee un entero
     *
     * @return el entero leido
     */
    private int leerEntero() {
        int entero = 0;
        try {
            entero = entrada.nextInt();
        } catch (InputMismatchException exception) {
            mostrar(Mensajes.ERROR_INGRESE_SOLAMENTE_UN_ENTERO);
            abortarApp();
        }
        // Descartamos la linea que ya no nos sirve
        leerLinea();
        return entero;
    }

    /**
     * Aborta la app
     */
    private void abortarApp() {
        mostrar(Mensajes.ERROR_ABORTANDO_APP);
        System.exit(1);
    }

}
