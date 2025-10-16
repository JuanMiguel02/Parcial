package org.demo.Models;

import javafx.scene.image.Image;

public class Inmueble {
    private String ciudad;
    private String numHabitaciones;
    private double precio;
    private String numPisos;
    private TipoInmueble tipoInmueble;

    public Inmueble(String ciudad, String numHabitaciones, String numPisos, double precio, TipoInmueble tipoInmueble) {
        this.ciudad = ciudad;
        this.numHabitaciones = numHabitaciones;
        this.numPisos = numPisos;
        this.precio = precio;
        this.tipoInmueble = tipoInmueble;
    }

    public String getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(String numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }



    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNumPisos() {
        return numPisos;
    }

    public void setNumPisos(String numPisos) {
        this.numPisos = numPisos;
    }

    public TipoInmueble getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(TipoInmueble tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    @Override
    public String toString() {
        return "Inmueble{" +
                "ciudad='" + ciudad + '\'' +
                ", numHabitaciones='" + numHabitaciones + '\'' +
                ", precio=" + precio +
                ", numPisos='" + numPisos + '\'' +
                ", tipoInmueble=" + tipoInmueble +
                '}';
    }
}
