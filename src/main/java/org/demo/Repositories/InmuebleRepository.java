package org.demo.Repositories;

import org.demo.Models.Inmueble;
import org.demo.Models.TipoInmueble;

import java.util.ArrayList;

public class InmuebleRepository {
    private static InmuebleRepository instance;
    private ArrayList<Inmueble> inmuebles;

    private InmuebleRepository() {
        inmuebles = new ArrayList<>();
        cargarDatosEjemplo();
    }

    public static InmuebleRepository getInstance() {
        if (instance == null) {
            instance = new InmuebleRepository();
        }
        return instance;
    }

    private void cargarDatosEjemplo() {
        inmuebles.add(new Inmueble("Calarcá", "3", "2", 200000, TipoInmueble.APARTAMENTO));
        inmuebles.add(new Inmueble("Armenia", "2", "1", 100000, TipoInmueble.APARTAMENTO));
        inmuebles.add(new Inmueble("Calarcá", "3", "2", 200000, TipoInmueble.APARTAMENTO));

    }

    public ArrayList<Inmueble> getInmuebles() {
        return inmuebles;
    }

    public void agregarInmueble(Inmueble inmueble) {
        inmuebles.add(inmueble);
    }

    public void eliminarInmueble(Inmueble inmueble) {
        inmuebles.remove(inmueble);
    }

//    public Inmueble buscarPorNombreYApellido(String nombre, String apellido){
//        return inmuebles.stream()
//                .filter(e -> e.getNombreCompleto().equals(nombre + " " + apellido))
//                .findFirst()
//                .orElse(null);
//    }
}
