package com.alex.vendedores.servicio;

import com.alex.vendedores.dominio.Vendedor;

import java.util.Collection;
import java.util.Set;

/**
 * Servicio para proporcionar las
 * operaciones necesarias con la clase Vendedor.
 *
 * <p> Esta interfaz presta un servicio reutilizable por cualquier tipo de app, ya sea
 * una REST API, desktop app, CLI app o mobile app
 *
 * @author Alex Angulo
 */
public interface VendedorServicio {

    /**
     * Returna todos los objetos Vendedor
     *
     * @return todos los objetos Vendedor
     */
    Set<Vendedor> encontrarTodos();

    /**
     * Guarda un objeto Vendedor.
     *
     * @param vendedor el vendedor a guardar.
     */
    void guardar(Vendedor vendedor);
}
