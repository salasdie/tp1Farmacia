package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;
import java.util.Random;

@Entity
public class Farmacia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String telefono;
    private String diaDeTurno;
    @OneToOne
    private Punto geoLocalizacion;

    @OneToOne
    private Direccion direccion;

    public Farmacia() {
    }

    public Farmacia (String nombre, String telefono, String diaDeTurno) {
        this.setNombre(nombre);
        this.setTelefono(telefono);
        this.setDiaDeTurno(diaDeTurno);
    }

    public Farmacia(String nombre, String telefono, String diaDeTurno, Direccion direccion) {
        Random randomGenerator = new Random();
        this.setNombre(nombre);
        this.setTelefono(telefono);
        this.setDiaDeTurno(diaDeTurno);
        this.setDireccion(direccion);
        this.setGeoLocalizacion(new Punto(String.valueOf(randomGenerator.nextInt(9999999)), String.valueOf(randomGenerator.nextInt(9999999))));
    }

    public Farmacia(String nombre, String telefono, String diaDeTurno, Direccion direccion, Punto coordenadas) {
        this.setNombre(nombre);
        this.setTelefono(telefono);
        this.setDiaDeTurno(diaDeTurno);
        this.setDireccion(direccion);
        this.setGeoLocalizacion(coordenadas);
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDiaDeTurno() {
        return diaDeTurno;
    }

    public void setDiaDeTurno(String diaDeTurno) {
        this.diaDeTurno = diaDeTurno;
    }

    public Punto getGeoLocalizacion() {
        return geoLocalizacion;
    }

    public void setGeoLocalizacion(Punto geoLocalizacion) {
        this.geoLocalizacion = geoLocalizacion;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
