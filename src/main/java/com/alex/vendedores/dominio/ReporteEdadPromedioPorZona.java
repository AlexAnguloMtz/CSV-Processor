package com.alex.vendedores.dominio;

import java.util.*;

import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.*;

/**
 * Clase que representa la abstraccion de un reporte
 * del promedio de edades de todos los vendedores por cada zona geografica
 *
 * @author Alex Angulo
 */
public final class ReporteEdadPromedioPorZona {

    /**
     * Estructura de datos para almacenar los datos del reporte generado
     */
    private final Map<String, Double> reporte;

    public ReporteEdadPromedioPorZona(Set<Vendedor> vendedores) {
        requireNonNull(vendedores);
        this.reporte = calcularPromedioEdadesPorZona(vendedores);
    }

    /**
     * Genera una estructura de datos que contiene la informacion del
     * promedio de edad de todos los vendedores en cada zona geografica
     *
     * @param vendedores Los vendedores a procesar
     * @return Una estructura de datos apropiada para almacenar el promedio
     *         de edades de todos los vendedores por cada zona geografica
     */
    private Map<String, Double> calcularPromedioEdadesPorZona(Set<Vendedor> vendedores) {
        return vendedores.stream()
                .collect(groupingBy(
                        Vendedor::getEstado,
                        averagingInt(Vendedor::getEdad))
                );
    }

    /**
     * Returna una copia inmutable de la estructura de datos
     * con la informacion del reporte generado
     *
     * @return estructura de datos inmutable con que contiene
     * la informacion del reporte generado
     */
    public Set<Map.Entry<String, Double>> datos() {
        return unmodifiableSet(reporte.entrySet());
    }

}
