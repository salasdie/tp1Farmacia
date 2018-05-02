package ar.edu.unlam.tallerweb1.interfaces;

import ar.edu.unlam.tallerweb1.modelo.Punto;

import java.util.List;

public interface PuntoDao {
    Punto getPuntoById(Long id);

    List<Punto> getPuntoByName(String name);

    void addPunto(Punto punto);
}
