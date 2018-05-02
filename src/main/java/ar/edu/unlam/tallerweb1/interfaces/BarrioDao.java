package ar.edu.unlam.tallerweb1.interfaces;

import ar.edu.unlam.tallerweb1.modelo.Barrio;

import java.util.List;

public interface BarrioDao {
    boolean addBarrio(Barrio comuna);

    Barrio getBarrioById(Long id);

    List<Barrio> getBarrioByName(String name);
}
