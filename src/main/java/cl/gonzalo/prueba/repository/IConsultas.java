/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.gonzalo.prueba.repository;

import cl.gonzalo.prueba.model.Consultas;
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

   
   

   
   

List<Consultas> findAllByEstado(String estado);

Consultas findByIds(int id);


   
 


   @Modifying 
   @Transactional
   @Query(nativeQuery = true, value = "UPDATE consultas SET paciente=paciente+1, estado='Ocupada' WHERE ids =:ids ")
   void aumentar(@Param("ids") int ids);


}
