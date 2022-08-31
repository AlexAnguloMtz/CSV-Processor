package com.alex.vendedores.main;

import com.alex.vendedores.applicacion.AplicacionVendedores;

/**
 * Clase main para ejecutar el Ejercicio Uno
 *
 * @author Alex Angulo
 */
public class EjercicioUnoMain {

    public static void main(String[] args) {
        var configuracion = new ConfiguracionApp();
        AplicacionVendedores app = configuracion.montarAplicacion();
        app.procesarReporteGeneral();
    }

}
