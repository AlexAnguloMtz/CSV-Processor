package com.alex.vendedores.applicacion;

import com.alex.vendedores.dominio.ReporteEdadPromedioPorZona;
import com.alex.vendedores.dominio.ReporteGeneral;
import com.alex.vendedores.dominio.Vendedor;
import com.alex.vendedores.dominio.VendedorRepositorio;
import com.alex.vendedores.fecha.FechaParser;
import com.alex.vendedores.servicio.VendedorServicio;

import java.util.Set;

/**
 * Clase que representa nuestra Aplicacion.
 *
 * <p>Su tarea es obtener los objetos Vendedor del
 * input elegido y enviarlos al dispositivo de output elegido.
 *
 * <p>La AplicacionVendedores se configura mediante Inyeccion de Dependencias,
 * es totalmente agnostica a la interfaz de usuario y al
 * dispositivo de output elegidos, depende unicamente de interfaces.
 *
 * <p>Por ejemplo, podriamos inyectar una interfaz CLI, Java Swing o JavaFX,
 * y como proveedor de persistencia podriamos inyectar un proveedor apoyado
 * por una base de datos, un archivo CSV, un archivo JSON, etc
 *
 * @author Alex Angulo
 */
public final class AplicacionVendedores {

    /**
     * El objeto VendedorServicio que nos
     * proporcionara los objetos Vendedor y tambien
     * nos permitira guardarlos		
     */
    private final VendedorServicio vendedorServicio;

    /**
     * La InterfazUsuario para consumir los resultados del programa
     * y mostrarlos en la implementacion de output deseada
     */
    private final InterfazUsuario interfazUsuario;

    /**
     * Constructor para inyectar las dependencias
     *
     * @param vendedorServicio el VendedorService a utilizar
     * @param  interfazUsuario la implementacion del output que consume los resultados del programa
     */
    public AplicacionVendedores(VendedorServicio vendedorServicio,
                                InterfazUsuario interfazUsuario) {
        this.vendedorServicio = vendedorServicio;
        this.interfazUsuario = interfazUsuario;
    }

    /**
     * Recupera todos los objetos Vendedor, genera un reporte general
     * y lo envia a la interfaz de usuario seleccionada
     */
    public void procesarReporteGeneral() {
        interfazUsuario.aceptar(new ReporteGeneral(vendedorServicio.encontrarTodos()));
    }

    /**
     * Recupera todos los objetos Vendedor,
     * genera un reporte de promedio de edades por zona geografica
     * y envia dicho reporte a ser aceptado por el medio de output seleccionado.
     */
    public void reportarPromedioDeEdadesPorZona() {
        Set<Vendedor> vendedores = vendedorServicio.encontrarTodos();
        interfazUsuario.aceptar(new ReporteEdadPromedioPorZona(vendedores));
    }

    /**
     * Solicita los datos de un Vendedor y lo envia a ser guardado
     */
    public void solicitarVendedorParaGuardarlo() {
        vendedorServicio.guardar(interfazUsuario.solicitarVendedor("dd/mm/yyyy"));
    }
}