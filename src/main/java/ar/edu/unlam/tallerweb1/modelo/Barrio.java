package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Barrio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne
    private Comuna comuna;

    public Barrio() {}

    public Barrio(String nombre, String comuna){
        setNombre(nombre);
        setComuna(new Comuna(nombre));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }
}
