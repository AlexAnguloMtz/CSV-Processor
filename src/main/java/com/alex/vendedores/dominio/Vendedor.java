package com.alex.vendedores.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Clase modelo para representar un Vendedor.
 * Se evita la implementacion de metodos setter, y todos sus campos son inmutables.
 * Por tanto, es una clase inmutable.
 *
 * @author Alex Angulo
 */

public final class Vendedor {

    /***
     * id del Vendedor
     */
    private final int id;

    /**
     * nombre del Vendedor
     */
    private final String nombre;

    /**
     * fecha de nacimiento del Vendedor
     */
    private final LocalDate fechaDeNacimiento;

    /**
     * estado de residencia del Vendedor
     */
    private final String estado;

    /**
     * Aseguramos la no instanciabilidad directa, con un constructor privado.
     * Esto es para forzar el uso de la clase anidada Builder para construir
     * instancias de una manera mas ordenada.
     *
     * @param id id del Vendedor
     * @param nombre nombre del Vendedor
     * @param fechaDeNacimiento fecha de nacimiento del Vendedor
     * @param estado estado de residencia del Vendedor
     */
    private Vendedor(int id,
                     String nombre,
                     LocalDate fechaDeNacimiento,
                     String estado) {
        this.id = id;
        this.nombre = requireNonNull(nombre);
        this.fechaDeNacimiento = requireNonNull(fechaDeNacimiento);
        this.estado = requireNonNull(estado);
    }

    /**
     * Returna el id del Vendedor
     *
     * @return id del Vendedor
     */
    public int getId() {
        return id;
    }

    /**
     * Returna el nombre del Vendedor
     *
     * @return nombre del Vendedor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Returna la fecha de nacimiento del Vendedor
     *
     * @return fecha de nacimiento del Vendedor
     */
    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    /**
     * Returna el estado de residencia del Vendedor
     *
     * @return estado de residencia del Vendedor
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Returna la edad en años
     *
     * @return Edad en años
     */
    public int getEdad() {
        return Period.between(fechaDeNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Compara este Vendedor con el objeto recibido.
     * Devuelve true unicamente si todos sus campos son identicos.
     *
     * @param otro El objeto a comparar con este Vendedor
     * @return true si el objeto comparado equivale a este Vendedor, falso en caso contrario
     */
    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (otro == null || getClass() != otro.getClass()) return false;
        Vendedor vendedor = (Vendedor) otro;
        return     id == vendedor.id
                && nombre.equals(vendedor.nombre)
                && fechaDeNacimiento.equals(vendedor.fechaDeNacimiento)
                && estado.equals(vendedor.estado);
    }

    /**
     * Returna el codigo hash para este Vendedor.
     * Es calculado en base al codigo hash de todos los campos de este Vendedor.
     *
     * @return codigo hash para este Vendedor
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fechaDeNacimiento, estado);
    }

    /**
     * Returna un objeto String que representa a este Vendedor.
     * El resultado es una String que contiene representaciones String de todos
     * los campos de este vendedor
     *
     * @return objeto String que representa a este Vendedor
     */
    @Override
    public String toString() {
        return "Vendedor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fechaDeNacimiento=" + fechaDeNacimiento +
                ", estado='" + estado + '\'' +
                '}';
    }

    /**
     * Returna un objeto de la clase anidada Builder para
     * ayudar en la construccion de objetos Vendedor
     *
     * @return una instancia de la clase anidada Builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     *  Patrón de diseno Builder (Design Patterns, Gang of Four).
     *
     *  <p>Clase anidada estática para facilitar la
     *  construcción de objetos Vendedor.
     */
    public static final class Builder {

        /**
         * el id que tendrá el Vendedor construido
         */
        private int id;

        /**
         * el nombre que tendrá el Vendedor construido
         */
        private String nombre;

        /**
         * la fecha de nacimiento que tendrá el Vendedor construido
         */
        private LocalDate fechaDeNacimiento;

        /**
         * el estado de residencia que tendrá el Vendedor construido
         */
        private String estado;

        /**
         * Establece el id que tendrá el Vendedor construido
         *
         * @param id id que tendrá el vendedor construido
         * @return este objeto Builder
         */
        public Builder id(int id) {
            this.id = id;
            return this;
        }

        /**
         * Establece el nombre que tendrá el Vendedor construido
         *
         * @param nombre nombre que tendrá el Vendedor construido
         * @return este objeto Builder
         */
        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        /**
         * Establece la fecha de nacimiento que tendrá el Vendedor construido
         *
         * @param fechaDeNacimiento fecha de nacimiento que tendrá el Vendedor construido
         * @return este objeto Builder
         */
        public Builder fechaDeNacimiento(LocalDate fechaDeNacimiento) {
            this.fechaDeNacimiento = fechaDeNacimiento;
            return this;
        }

        /**
         * Establece el estado de residencia que tendrá el Vendedor construido
         *
         * @param estado estado de residencia que tendrá el Vendedor construido
         * @return este objeto Builder
         */
        public Builder estado(String estado) {
            this.estado = estado;
            return this;
        }

        /**
         * Utiliza los campos de este Builder para construir
         * un nuevo objeto Vendedor y lo returna
         *
         * @return el Vendedor construido
         */
        public Vendedor build() {
            return new Vendedor(id, nombre, fechaDeNacimiento, estado);
        }

    }

}
