package com.alex.vendedores.archivo;

import static java.lang.String.format;

/**
 * Excepcion que representa una ruta que no pudo ser resuelta
 *
 * @author Alex Angulo
 */
public class RutaNoResueltaException extends RuntimeException {

    /**
     * El mensaje de error
     */
    private static final String MENSAJE = "No se pudo resolver la ruta del archivo con nombre %s";

    public RutaNoResueltaException(String nombreArchivo) {
        super(format(MENSAJE, nombreArchivo));
    }

}