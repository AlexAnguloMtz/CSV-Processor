package com.alex.vendedores.dominio;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;

/**
 * Clase que representa el reporte general con todos los vendedores
 *
 * @author Alex Angulo
 */
public final class ReporteGeneral {

    /**
     * Estructura de datos para almacenar los vendedores de este reporte
     */
    private final Set<Vendedor> vendedores;

    /**
     * Inicializa este objeto con una copia defensiva de los vendedores recibidos
     *
     * @param vendedores los vendedores para este reporte
     */
    public ReporteGeneral(Collection<? extends Vendedor> vendedores) {
        requireNonNull(vendedores);
        this.vendedores = new LinkedHashSet<>(vendedores);
    }

    /**
     * Returna una copia Inmutable de los vendedores de este reporte
     *
     * @return copia Inmutable de los vendedores de este reporte
     */
    public Collection<Vendedor> datos() {
        return unmodifiableSet(vendedores);
    }
}
