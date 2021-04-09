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

/**
 *
 * @author gonza
 */
public interface IPNinnos extends CrudRepository<PNinnos, Integer> {

   

   

    @Query(nativeQuery = true, value = "SELECT * FROM pninnos WHERE relacion_peso <=4 order by relacion_peso  desc")
    List<PNinnos> findgravedad();
    
    PNinnos findByIds(int id);
    
   
    
}
