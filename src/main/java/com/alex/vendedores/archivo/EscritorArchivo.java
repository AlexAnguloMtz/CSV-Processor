package com.alex.vendedores.archivo;

import java.io.*;

import static java.lang.String.format;

/**
 * Clase para escribir en un archivo
 *
 * @author Alex Angulo
 */
public class EscritorArchivo {

    /**
     * Escribe una nueva linea en el archivo especificado.
     *
     * @param linea la linea a escribir
     * @param nombreYExtensionArchivo el nombre y extension del archivo
     */
    public void escribirLinea(String linea, String nombreYExtensionArchivo) {
        //Bloque try-with-resources. No se necesita llamar al metodo 'close()' de BufferedWriter
        try (var out = new BufferedWriter(new FileWriter(nombreYExtensionArchivo, true))) {
            out.write(format("%s%n", linea));
        } catch (IOException exception) {
            throw new NoSePudoEscribirEnArchivoException(nombreYExtensionArchivo);
        }
    }
}
