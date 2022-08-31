package com.alex.vendedores.servicio;

import com.alex.vendedores.dominio.Vendedor;
import com.alex.vendedores.dominio.VendedorRepositorio;

import java.util.Collection;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Una implementacion simple de nuestra interface VendedorServicio
 */
public final class SimpleVendedorServicio implements VendedorServicio {

    /**
     * El proveedor de persistencia del cual obtenemos los objetos tipo Vendedor
     */
    private final VendedorRepositorio vendedorRepositorio;

    public SimpleVendedorServicio(VendedorRepositorio repositorio) {
        this.vendedorRepositorio = requireNonNull(repositorio);
    }

    /**
     * Returna todos los objetos Vendedor almacenados
     * en el proveedor de persistencia elegido
     *
     * @return todos los objetos Vendedor almacenados
     * en el proveedor de persistencia elegido
     */
    @Override
    public Set<Vendedor> encontrarTodos() {
        return vendedorRepositorio.encontrarTodos();
    }

    /**
     * Guarda un objeto vendedor
     *
     * @param vendedor el vendedor a guardar.
     */
    @Override
    public void guardar(Vendedor vendedor) {
        vendedorRepositorio.guardar(vendedor);
    }

}
