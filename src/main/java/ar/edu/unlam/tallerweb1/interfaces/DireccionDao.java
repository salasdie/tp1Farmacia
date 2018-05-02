package ar.edu.unlam.tallerweb1.interfaces;

import ar.edu.unlam.tallerweb1.modelo.Direccion;

import java.util.List;

public interface DireccionDao {
    boolean addDireccion(Direccion direccion);
    List<Direccion> getDireccionByContent(String nombre, Number numero);
    Direccion getUbicacionById(Long id);
}
