
package cl.gonzalo.prueba.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class PNinnos implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ids;
    
    private String nombre;
    
    private int edad;
    
    private int noHistoriaClinica;
    
    private int relacionPeso;

    public PNinnos() {
    }

    public PNinnos(int ids, String nombre, int edad, int noHistoriaClinica, int relacionPeso) {
        this.ids = ids;
        this.nombre = nombre;
        this.edad = edad;
        this.noHistoriaClinica = noHistoriaClinica;
        this.relacionPeso = relacionPeso;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
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

    public int getRelacionPeso() {
        return relacionPeso;
    }

    public void setRelacionPeso(int relacionPeso) {
        this.relacionPeso = relacionPeso;
    }
    
    
    private static final int serialVersionUID = (int) 1L;

    
    
    

    
    
    
}
