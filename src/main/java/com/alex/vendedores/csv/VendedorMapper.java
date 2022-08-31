package com.alex.vendedores.csv;

import com.alex.vendedores.dominio.Vendedor;

/**
 * Interface para mapear una String en formato CSV a un objeto Vendedor
 *
 * @author Alex Angulo
 */
public interface VendedorMapper {
    Vendedor mapearAVendedor(String filaVendedor);
    boolean esVendedor(String filaVendedor);

    String mapearAFormatoCSV(Vendedor vendedor);

}
