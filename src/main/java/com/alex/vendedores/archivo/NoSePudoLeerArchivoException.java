package com.alex.vendedores.archivo;

import static java.lang.String.format;

/**
 * Excepcion para indicar que hubo un error al leer un archivo
 *
 * @author Alex Angulo
 */
public class NoSePudoLeerArchivoException extends RuntimeException {

    /**
     * La plantilla para el mensaje de error
     */
    private static final String MENSAJE = "Error al leer el archivo con ruta %s";

    public NoSePudoLeerArchivoException(String ruta, Exception exception) {
        super(format(MENSAJE, ruta), exception);
    }
}
