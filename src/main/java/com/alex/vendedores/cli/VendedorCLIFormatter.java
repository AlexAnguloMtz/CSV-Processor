package com.alex.vendedores.cli;

import com.alex.vendedores.dominio.Vendedor;

import static java.lang.String.format;

/**
 * Clase para dar formato a un objeto Vendedor
 * para output mediante la linea de comandos
 *
 * @author Alex Angulo
 */
public class VendedorCLIFormatter {
    public String darFormato(Vendedor vendedor) {
        return format(
                "%3d %20s %15s %15s %5d años",
                vendedor.getId(),
                vendedor.getNombre(),
                vendedor.getFechaDeNacimiento(),
                vendedor.getEstado(),
                vendedor.getEdad()
        );
    }
}
