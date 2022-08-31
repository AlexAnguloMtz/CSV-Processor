package com.alex.vendedores.archivo;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase utilitaria para obtener un Path que representa a un archivo
 *
 * @author Alex Angulo
 */
public final class Rutas {

    /**
     * Returna el Path del archivo solicitado
     *
     * @param nombreArchivo El nombre del archivo
     * @return El Path que representa la abstraccion del archivo solicitado
     * @throws RutaNoResueltaException Si el archivo solicitado no pudo ser encontrado
     */
    public Path resolverRuta(String nombreArchivo) {
        URI uri;
        try {
            uri = ClassLoader.getSystemResource(nombreArchivo).toURI();
        } catch (URISyntaxException exception) {
            throw new RutaNoResueltaException(nombreArchivo);
        }
        return Paths.get(uri);
    }
}
