package com.alex.vendedores.cli;

import static java.lang.String.format;

/**
 * Clase utilitaria para guardar los mensajes de la CLI
 */
class Mensajes {
    public static final String ERROR_ABORTANDO_APP = "Error. Cerrando app... app finalizada";
    public static final String ERROR_INGRESE_SOLAMENTE_UN_ENTERO = "Error. Esta opcion solo acepta un numero entero.";
    public static final String INGRESE_ID = "Ingrese el id";
    public static final String INGRESE_NOMBRE = "Ingrese el nombre";

    public static final String INGRESE_ESTADO = "Ingrese un estado";
    public  static final String VENDEDOR_FUE_GUARDADO = "Se ha guardado con exito el Vendedor \\n";

    private static final String INGRESE_FECHA = "Ingrese la fecha de nacimiento en formato %s";

    private static final String ERROR_FECHA_FORMATO_INVALIDO = "Error. La fecha %s no tiene el formato %s";
    private static final String ERROR_FECHA_IMPOSIBLE =
            "Error. La cadena %s no puede interpretarse con el patron de fecha %s porque representa una fecha imposible";

    public static String ingreseFecha(String formatoFechaRequerido) {
        return format(INGRESE_FECHA, formatoFechaRequerido);
    }

    public static String errorFechaFormatoInvalido(String fechaIncorrecta, String formatoFechaRequerido) {
        return format(ERROR_FECHA_FORMATO_INVALIDO, fechaIncorrecta, formatoFechaRequerido);
    }

    public static String errorFechaImposible(String posibleFecha, String formatoFechaRequerido) {
        return format(ERROR_FECHA_IMPOSIBLE, posibleFecha, formatoFechaRequerido);
    }
}
