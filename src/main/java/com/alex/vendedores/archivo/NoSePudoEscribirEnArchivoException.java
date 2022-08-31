package com.alex.vendedores.archivo;

import static java.lang.String.format;

public class NoSePudoEscribirEnArchivoException extends RuntimeException {
    private static final String MENSAJE = "Error. No se pudo escribir en el archivo %s";

    public NoSePudoEscribirEnArchivoException(String nombreYExtensionArchivo) {
        super(format(MENSAJE, nombreYExtensionArchivo));
    }
}
