package com.alex.vendedores.csv;

import com.alex.vendedores.dominio.Vendedor;
import com.alex.vendedores.dominio.VendedorRepositorio;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

/**
 *  Implementacion de la interfaz VendedorRepositorio.
 *  Esta implementacion trabaja con archivos CSV como proveedor de persistencia.
 *
 * @author Alex Angulo
 */

public final class VendedorRepositorioCSV implements VendedorRepositorio {

    /**
     * El mapper que encapsula el algoritmo para mapear
     * una String en formato CSV a un objeto Vendedor.
     */
    private final VendedorMapper mapper;

    /**
     * La Function que encapsula el algoritmo para leer
     * las lineas de un archivo
     */
    private final Function<Path, List<String>> lector;

    /**
     * El Supplier que provee el archivo
     */
    private final Supplier<Path> proveedorPath;

    /**
     * El Consumer que encapsula el algoritmo para escribir
     * objetos Vendedor en un archivo
     */

    private final Consumer<String> escritor;

    /**
     * Collection para almacenar en cache los objetos Vendedor,
     * para leer solo una vez el archivo CSV durante la ejecucion del programa.
     *
     * <p> Es un 'implementation detail' para optimizacion.
     * Su inicializacion es de tipo 'lazy', por ello la variable no es 'final'.
     */
    private Set<Vendedor> cache;

    public VendedorRepositorioCSV(VendedorMapper mapper,
                                  Supplier<Path> proveedorPath,
                                  Function<Path, List<String>> lectorArchivo,
                                  Consumer<String> escritorArchivo) {
        this.mapper = mapper;
        this.proveedorPath = proveedorPath;
        this.lector = lectorArchivo;
        this.escritor = escritorArchivo;
    }

    /**
     * Returna una Collection INMUTABLE con todos los objetos Vendedor encontrados en el archivo CSV,
     * descartando las duplicaciones de objetos Vendedor identicos.
     *
     * @return Todos los objetos Vendedor recuperados,
     *         sin duplicados, en una Collection INMUTABLE
     */
    @Override
    public Set<Vendedor> encontrarTodos() {
        if(cache == null) {
            cache = cargarVendedoresEnMemoria();
        }
        return Collections.unmodifiableSet(cache);
    }

    /**
     * Persiste un objeto Vendedor
     *
     * @param vendedor el vendedor a persistir
     */
    @Override
    public void guardar(Vendedor vendedor) {
        String vendedorConFormatoCSV = mapper.mapearAFormatoCSV(vendedor);
        escritor.accept(vendedorConFormatoCSV);
    }

    /**
     * Metodo private helper para inicializar la cache de objetos Vendedor
     *
     * @return Collection con todos los objetos Vendedor, sin duplicados.
     */
    private Set<Vendedor> cargarVendedoresEnMemoria() {
        return lineasArchivo().stream()
                .filter(mapper::esVendedor)
                .map(mapper::mapearAVendedor)
                .collect(toCollection(LinkedHashSet::new));
    }

    /**
     * Lee el archivo y returna las lineas contenidas en el mismo
     *
     * @return las lineas de texto contenidas en el archivo
     */
    private List<String> lineasArchivo() {
        return lector.apply(proveedorPath.get());
    }

}
