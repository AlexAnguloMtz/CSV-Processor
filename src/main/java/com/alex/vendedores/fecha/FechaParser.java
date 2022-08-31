package com.alex.vendedores.fecha;

import java.time.LocalDate;
import java.util.Map;
import java.util.function.Function;

import static java.lang.Integer.parseInt;

/**
 * Clase para parsear objetos String que representan fechas
 *
 * @author Alex Angulo
 */

public final class FechaParser {

    /**
     * REGEXs para testear si las String a parsear son un formato valido
     */

    private static final String REGEX_DIA_BARRA_MES_BARRA_ANIO = "^\\d{2}/\\d{2}/\\d{4}$";
    private static final String REGEX_DIA_GUION_MES_GUION_ANIO = "^\\d{2}-\\d{2}-\\d{4}$";
    private static final String REGEX_MES_GUION_DIA_GUION_ANIO = "^\\d{2}-\\d{2}-\\d{4}$";
    private static final String REGEX_MES_BARRA_DIA_BARRA_ANIO = "^\\d{2}/\\d{2}/\\d{4}$";


    /**
     * Estructura de datos para relacionar patrones de fecha
     * con algoritmos parsers. Esto permite que la clase encapsule
     * varios algoritmos de parsing y los relacione con diversos patrones de fecha.
     */
    private final Map<String, Function<String, LocalDate>> parsers;

    public FechaParser() {
        this.parsers = crearParsers();
    }

    /**
     * Returna un Map que relaciona patrones de fechas con sus respectivos algoritmos parsers
     *
     * @return Map cuyas keys son patrones de fechas y los values son algoritmos de parsing de fecha
     */
    private Map<String, Function<String, LocalDate>> crearParsers() {
        return Map.of(
                "mm/dd/yyyy", this::mesBarraDiaBarraAnio,
                "dd/mm/yyyy", this::diaBarraMesBarraAnio,
                "mm-dd-yyyy", this::mesGuionDiaGuionAnio,
                "dd-mm-yyyy", this::diaGuionMesGuionAnio
        );
    }

    /**
     * Parsea una cadena y returna su fecha correspondiente
     *
     * @param fechaCadena la cadena a parsear
     * @param patron el patron en el cual esta formateada la fecha
     * @return la fecha parseada
     * @throws FormatoFechaException si la String no coincide
     *         con el formato especificado
     */
    public LocalDate parsear(String fechaCadena, String patron) {
        Function<String, LocalDate> parser = parsers.get(patron);
        return parser.apply(fechaCadena);
    }

    /**
     * Parsea una fecha en formato "dd/mm/yyyy"
     *
     * @param fecha fecha a parsear
     * @return la fecha parseada
     * @throws FormatoFechaException si la String no coincide
     *         con el formato especificado
     */
    private LocalDate diaBarraMesBarraAnio(String fecha) {
        assertFormato(fecha, REGEX_DIA_BARRA_MES_BARRA_ANIO);
        String[] partesFecha = fecha.split("/");
        int dia = parseInt(partesFecha[0]);
        int mes = parseInt(partesFecha[1]);
        int anio = parseInt(partesFecha[2]);
        return LocalDate.of(anio, mes, dia);
    }

    /**
     * Parsea una fecha en formato "dd-mm-yyyy"
     *
     * @param fecha fecha a parsear
     * @return la fecha parseada
     * @throws FormatoFechaException si la String no coincide
     *         con el formato especificado
     */
    private LocalDate diaGuionMesGuionAnio(String fecha) {
        assertFormato(fecha, REGEX_DIA_GUION_MES_GUION_ANIO);
        String[] partesFecha = fecha.split("-");
        int dia = parseInt(partesFecha[0]);
        int mes = parseInt(partesFecha[1]);
        int anio = parseInt(partesFecha[2]);
        return LocalDate.of(anio, mes, dia);
    }

    /**
     * Parsea una fecha en formato "mm-dd-yyyy"
     *
     * @param fecha fecha a parsear
     * @return la fecha parseada
     * @throws FormatoFechaException si la String no coincide
     *         con el formato especificado
     */
    private LocalDate mesGuionDiaGuionAnio(String fecha) {
        assertFormato(fecha, REGEX_MES_GUION_DIA_GUION_ANIO);
        String[] partesFecha = fecha.split("-");
        int mes = parseInt(partesFecha[0]);
        int dia = parseInt(partesFecha[1]);
        int anio = parseInt(partesFecha[2]);
        return LocalDate.of(anio, mes, dia);
    }

    /**
     * Parsea una fecha en formato "mm/dd/yyyy"
     *
     * @param fecha fecha a parsear
     * @return la fecha parseada
     * @throws FormatoFechaException si la String no coincide
     *         con el formato especificado
     */
    private LocalDate mesBarraDiaBarraAnio(String fecha) {
        assertFormato(fecha, REGEX_MES_BARRA_DIA_BARRA_ANIO);
        String[] partesFecha = fecha.split("/");
        int mes = parseInt(partesFecha[0]);
        int dia = parseInt(partesFecha[1]);
        int anio = parseInt(partesFecha[2]);
        return LocalDate.of(anio, mes, dia);
    }

    /**
     * Assert que la cadena a parsear es un formato de fecha reconocido por esta clase
     *
     * @param cadena la String a testear
     * @param regex la regex contra la cual se comparara la String
     * @throws FormatoFechaException si la String no coincide
     *         con el formato especificado
     */
    private void assertFormato(String cadena, String regex) {
        if(!cadena.matches(regex)) {
            throw new FormatoFechaException(cadena);
        }
    }

}
