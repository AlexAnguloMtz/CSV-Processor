package com.alex.vendedores.archivo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Clase cuya unica responsabilidad es leer el
 * contenido de un archivo y returnar las lineas leidas
 *
 * @author Alex Angulo
 */
public final class LectorArchivo {

    /**
     * Lee el contenido de la abstraccion que representa un archivo y returna las lineas leidas
     *
     * <p>La lectura se realiza en un bloque 'try-with-resources', por lo
     * que no se necesita un bloque 'finally'
     *
     * @param path La abstraccion que representa el archivo a leer
     * @return Las lineas contenidas en el archivo
     * @throws NoSePudoLeerArchivoException si el archivo no se pudo leer correctamente
     */
    public List<String> leer(Path path) {
        try(Stream<String> lineas = Files.lines(path)) {
            return lineas.collect(toList());
        } catch (IOException exception) {
            throw new NoSePudoLeerArchivoException(path.toString(), exception);
        }
    }


}
