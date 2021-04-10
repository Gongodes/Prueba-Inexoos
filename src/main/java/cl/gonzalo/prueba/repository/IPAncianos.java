/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.gonzalo.prueba.repository;

import cl.gonzalo.prueba.model.PAncianos;
import cl.gonzalo.prueba.model.PJovenes;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author gonza
 */
public interface IPAncianos extends CrudRepository<PAncianos,Integer> {
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM pancianos WHERE gravedad <=4 order by gravedad  desc")
    List<PAncianos> findgravedad();
    
    
    PAncianos findById(int id);
    
         
    
         @Query(nativeQuery = true,value = "SELECT COALESCE(MAX(gravedad),0) FROM pancianos order by id asc")
	  Integer max();
    
         @Query(nativeQuery = true,value = "select min(id) from pancianos where gravedad=(select max(gravedad) from pancianos) ")
	  public Integer maxid();
    
}
