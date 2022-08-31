package com.alex.vendedores.main;

import com.alex.vendedores.applicacion.AplicacionVendedores;

/**
 * Clase main para ejecutar el Ejercicio Tres
 * NOTA: El archivo CSV tendra el nombre 'vendedores-guardados.csv'
 *
 * Requerimientos del formato en el archivo CSV:
 * - id
 * - Nombre, maximo 35 caracteres
 * - Fecha de nacimiento en formato DD/MM/AAAA
 * - Estado, maximo 15 caracteres
 *
 * @author Alex Angulo
 */
public class EjercicioTresMain {
    public static void main(String[] args) {
        var configuracion = new ConfiguracionApp();
        AplicacionVendedores app = configuracion.montarAplicacion();
        app.solicitarVendedorParaGuardarlo();
        System.out.printf
                ("Se guardo la informacion del vendedor en el archivo %s", VariablesDeEntorno.RUTA_ARCHIVO_OUTPUT);
    }

}