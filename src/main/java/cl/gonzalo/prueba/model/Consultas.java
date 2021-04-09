
package cl.gonzalo.prueba.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Consultas {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int ids;
    
        
    private String nombreEspecialista;
    
    
    private String tipoConsulta;
    
    
     private String estado;
     
     
     private int paciente;

    public Consultas() {
    }

    public Consultas(int ids, String nombreEspecialista, String tipoConsulta, String estado, int paciente) {
        this.ids = ids;
        this.nombreEspecialista = nombreEspecialista;
        this.tipoConsulta = tipoConsulta;
        this.estado = estado;
        this.paciente = paciente;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getNombreEspecialista() {
        return nombreEspecialista;
    }

    public void setNombreEspecialista(String nombreEspecialista) {
        this.nombreEspecialista = nombreEspecialista;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPaciente() {
        return paciente;
    }

    public void setPaciente(int paciente) {
        this.paciente = paciente;
    }

   
    private static final int serialVersionUID = (int) 1L;

    
}
