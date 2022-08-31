package com.alex.vendedores.main;

import com.alex.vendedores.applicacion.AplicacionVendedores;
import com.alex.vendedores.applicacion.InterfazUsuario;
import com.alex.vendedores.archivo.EscritorArchivo;
import com.alex.vendedores.archivo.LectorArchivo;
import com.alex.vendedores.archivo.Rutas;
import com.alex.vendedores.cli.CommandLineInterface;
import com.alex.vendedores.cli.VendedorCLIFormatter;
import com.alex.vendedores.dominio.Vendedor;
import com.alex.vendedores.dominio.VendedorRepositorio;
import com.alex.vendedores.csv.SimpleVendedorMapper;
import com.alex.vendedores.csv.VendedorMapper;
import com.alex.vendedores.csv.VendedorRepositorioCSV;
import com.alex.vendedores.servicio.SimpleVendedorServicio;
import com.alex.vendedores.servicio.VendedorServicio;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *  Clase para configurar la aplicacion
 *
 *  <p> Proporciona las implementaciones de todas
 *  las interfaces necesarias para la
 *  Inyeccion de Dependencias en la app.
 *
 *  <p> Esta clase es una implementacion manual de un ´Inversion Of Control Container´
 *
 * @author Alex Angulo
 */
final class ConfiguracionApp {

    /**
     * Genera una nueva aplicacion, inyectando las dependencias
     * previamente configuradas en esta clase
     *
     * @return Una nueva AplicacionVendedores con sus dependencias inyectadas
     */
    public AplicacionVendedores montarAplicacion() {
        return new AplicacionVendedores(vendedorServicio(),output());
    }

    /**
     * Returna la implementacion de la interface VendedorServicio a utilizar
     *
     * @return la implementacion de la interface VendedorServicio a utilizar
     */
    public VendedorServicio vendedorServicio() {
        return new SimpleVendedorServicio(vendedorRepositorio());
    }

    /**
     * Returna la implementacion de la interface VendedorRepositorio a utilizar
     *
     * @return la implementacion de la interface VendedorRepositorio a utilizar
     */
    VendedorRepositorio vendedorRepositorio() {
        return new VendedorRepositorioCSV(mapper(), proveedorPath(), lectorArchivo(), escritorArchivo());
    }

    /**
     * Returna la implementacion del Consumer que encapsula 
     * el algoritmo para persistir los objetos Vendedor en un archivo
     * 
     * @return el Consumer que persiste los objetos Vendedor en un archivo
     */
    private Consumer<String> escritorArchivo() {
        return lineas -> new EscritorArchivo().escribirLinea(lineas, VariablesDeEntorno.RUTA_ARCHIVO_OUTPUT);
    }

    /**
     * Returna la implementacion de la interface VendedorMapper a utilizar
     *
     * @return la implementacion de la interface VendedorMapper a utilizar
     */
    private VendedorMapper mapper() {
        return new SimpleVendedorMapper();
    }

    /**
     * Returna la implementacion del Supplier que encapsula la fuente
     * de la cual proviene la abstraccion del archivo requerido
     *
     * @return El Supplier que provee la abstraccion del archivo requerido
     */
    private Supplier<Path> proveedorPath() {
        return () -> new Rutas().resolverRuta(VariablesDeEntorno.NOMBRE_ARCHIVO_INPUT);
    }

    /**
     * Returna la implementacion de la Function que encapsula el algoritmo para
     * leer un archivo y returnar las lineas que contiene el mismo
     *
     * @return La Function que toma la abstraccion de un archivo y returna las lineas que contiene
     */
    private Function<Path, List<String>> lectorArchivo() {
        return new LectorArchivo()::leer;
    }

    /**
     * Returna la implementacion que va a dirigir el output del programa al dispositivo de salida deseado
     *
     * @return El objeto que abstrae el output del programa
     */
    public InterfazUsuario output() {
        return new CommandLineInterface(formatter());
    }

    /**
     * Returna la implementacion de la Function que encapsula el algoritmo
     * para dar formato de output a un objeto Vendedor
     *
     * @return el formatter para dar formato a un objeto Vendedor
     */
    public Function<Vendedor, String> formatter() {
        return new VendedorCLIFormatter()::darFormato;
    }

}