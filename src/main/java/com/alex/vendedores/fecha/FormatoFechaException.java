package com.alex.vendedores.fecha;

import static java.lang.String.format;

/**
 * Exception que representa que una String no pudo ser parseada a una fecha
 *
 * @author Alex Angulo
 */
public class FormatoFechaException extends RuntimeException {

    /**
     * El mensaje de error
     */
    private static final String MENSAJE = "La cadena %s no pudo reconocerse como una fecha valida";

    FormatoFechaException(String cadena) {
        super(format(MENSAJE, cadena));
    }

}
