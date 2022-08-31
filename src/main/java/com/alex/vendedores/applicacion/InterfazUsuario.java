package com.alex.vendedores.applicacion;

import com.alex.vendedores.dominio.ReporteEdadPromedioPorZona;
import com.alex.vendedores.dominio.ReporteGeneral;
import com.alex.vendedores.dominio.Vendedor;

/**
 * Interface que abstrae el medio de Output del programa
 *
 * @author Alex Angulo
 */
public interface InterfazUsuario {

    /**
     * Returna un reporte general de los vendedores
     *
     * @param reporte el reporte general de los vendedores
     */
    public void aceptar(ReporteGeneral reporte);

    /**
     * Returna un reporte de la edad promedio de los vendedores por zona geografica
     *
     * @param reporte Reporte de la edad promedio de los vendedores por zona geografica
     */

    void aceptar(ReporteEdadPromedioPorZona reporte);

    /**
     * Returna un nuevo Vendedor
     *
     * @return un nuevo Vendedor
     */
    Vendedor solicitarVendedor(String formatoFecha);

}
