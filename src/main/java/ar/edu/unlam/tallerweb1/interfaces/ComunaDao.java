package ar.edu.unlam.tallerweb1.interfaces;
import ar.edu.unlam.tallerweb1.modelo.Comuna;

import java.util.List;

public interface ComunaDao {
    boolean addComuna(Comuna comuna);
    Comuna getComunaById(Long id);
    List<Comuna> getComunaByName(String name);
}
