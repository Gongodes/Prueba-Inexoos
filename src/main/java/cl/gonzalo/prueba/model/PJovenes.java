
package cl.gonzalo.prueba.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class PJovenes implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
     private String nombre;
    
    private int edad;
    
    private int noHistoriaClinica;
    
    private boolean fumador;
    
    private int gravedad;
    

    

    public PJovenes() {
    }

    public PJovenes(int id, String nombre, int edad, int noHistoriaClinica, boolean fumador, int gravedad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.noHistoriaClinica = noHistoriaClinica;
        this.fumador = fumador;
        this.gravedad = gravedad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNoHistoriaClinica() {
        return noHistoriaClinica;
    }

    public void setNoHistoriaClinica(int noHistoriaClinica) {
        this.noHistoriaClinica = noHistoriaClinica;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    

   private static final int serialVersionUID = (int) 1L;
    
    
    }
    
    

