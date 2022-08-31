package com.alex.vendedores.dominio;

import java.util.Collection;
import java.util.Set;

/**
 * Interface para realizar operaciones de persistencia
 * con objetos de la clase modelo Vendedor
 *
 * <p> Esta interface presta un servicio reutilizable por cualquier tipo de app,
 * ya sea una REST API, desktop app, CLI app o mobile app
 *
 * @author Alex Angulo
 */
public interface VendedorRepositorio {
    /**
     * Returna todos los vendedores del proveedor de persistencia elegido,
     * garantiza que no habrá dos objetos Vendedor idénticos repetidos
     *
     * @return todos los vendedores en nuestra fuente de persistencia, sin duplicados
     */
    Set<Vendedor> encontrarTodos();

    /**
     * Persiste un objeto Vendedor
     *
     * @param vendedor el vendedor a persistir
     */
    void guardar(Vendedor vendedor);
}
