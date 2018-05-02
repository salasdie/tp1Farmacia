package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String calle;
    private Number numero;
    @OneToOne
    private Barrio barrio;

    public Direccion(){}

    public Direccion(String calle, Number numero, Barrio barrio) {
        setCalle(calle);
        setNumero(numero);
        setBarrio(barrio);
    }

    public Direccion(String calle, Number numero, String barrio, String comuna) {
        setCalle(calle);
        setNumero(numero);
        setBarrio(new Barrio(barrio, comuna));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Number getNumero() {
        return numero;
    }

    public void setNumero(Number numero) {
        this.numero = numero;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }
}
