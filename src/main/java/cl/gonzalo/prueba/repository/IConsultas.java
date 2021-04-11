/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.gonzalo.prueba.repository;

import cl.gonzalo.prueba.model.Consultas;
import cl.gonzalo.prueba.model.PAncianos;
import cl.gonzalo.prueba.model.PNinnos;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gonza
 */
public interface IConsultas extends CrudRepository<Consultas, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE consultas SET estado='En Espera' WHERE estado = 'Ocupada'")
    void findOcupados();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE consultas SET estado='Ocupada' , paciente=paciente+1  WHERE tipo_consulta = 'CGI' AND estado='En Espera' LIMIT 1")
    void findCGIOcupado();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE consultas SET estado='Ocupada' , paciente=paciente+1  WHERE tipo_consulta = 'Urgencia' AND estado='En Espera' LIMIT 1")
    void findURGcupado();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE consultas SET estado='Ocupada' , paciente=paciente+1  WHERE tipo_consulta = 'Pediatria' AND estado='En Espera' LIMIT 1")
    void findPEDcupado();

    @Query(nativeQuery = true, value = "SELECT COUNT(estado)FROM consultas WHERE estado='En Espera' and tipo_consulta= 'CGI';")
    Integer findbycantidadCGE();

    @Query(nativeQuery = true, value = "SELECT COUNT(estado)FROM consultas WHERE estado='En Espera' and tipo_consulta= 'Pediatria';")
    Integer findbycantidadPED();

    @Query(nativeQuery = true, value = "SELECT COUNT(estado)FROM consultas WHERE estado='En Espera' and tipo_consulta= 'Urgencia';")
    Integer findbycantidadURG();

    List<Consultas> findAllByEstado(String estado);

    @Query(nativeQuery = true, value = "SELECT * FROM consultas   WHERE paciente = (SELECT MAX(paciente) FROM consultas)  order by ids;")
    Iterable<Consultas> findMassol();

    
}
