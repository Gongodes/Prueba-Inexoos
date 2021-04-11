/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.gonzalo.prueba.repository;

import cl.gonzalo.prueba.model.Consultas;
import cl.gonzalo.prueba.model.PJovenes;
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
    Iterable<PNinnos> findgravedad();
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM pninnos WHERE relacion_peso >4 order by relacion_peso  desc")
    Iterable<PNinnos> findgravedadmax();
   

    
    
        @Query(nativeQuery = true,value = "select COALESCE(max(relacion_peso),0) from pninnos where ids=(select min(ids) from pninnos) and relacion_peso <= 4;")
	  Integer max();
          
         
          
          
          @Query(nativeQuery = true,value = "select min(ids) from pninnos where relacion_peso=(select max(relacion_peso) from pninnos) and relacion_peso <= 4 ")
	  public Integer maxid();
          
          
          @Query(nativeQuery = true,value = "select COALESCE(max(relacion_peso),0) from pninnos where ids=(select min(ids) from pninnos) and relacion_peso > 4;")
	  Integer maxgrave();
          
         
          
          
          @Query(nativeQuery = true,value = "select min(ids) from pninnos where relacion_peso=(select max(relacion_peso) from pninnos) and relacion_peso > 4 ")
	  public Integer maxidgrave();
    
}
