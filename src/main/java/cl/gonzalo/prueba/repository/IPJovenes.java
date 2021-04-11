/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.gonzalo.prueba.repository;

import cl.gonzalo.prueba.model.PAncianos;
import cl.gonzalo.prueba.model.PJovenes;
import cl.gonzalo.prueba.model.PNinnos;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author gonza
 */
public interface IPJovenes extends CrudRepository <PJovenes,Integer> {
    
    
    
    
    
    
                                        
    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes   WHERE gravedad = (SELECT MAX(gravedad) FROM pjovenes) and no_historia_clinica =:no_historia_clinica  order by id;")
    Iterable<PJovenes> findMayorRiesgo(@Param("no_historia_clinica") Integer no_historia_clinica);
    
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes WHERE gravedad <=4 order by gravedad  desc")
    Iterable<PJovenes> findgravedad();
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes WHERE gravedad >4  order by gravedad  desc")
    Iterable<PJovenes> findgravedadmax();
    
    
    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes WHERE gravedad >4 and fumador=true order by gravedad  desc")
    Iterable<PJovenes> findfumador();
    
    
    
    PJovenes findById(int id);
    
    
    
         @Query(nativeQuery = true,value = "select COALESCE(max(gravedad),0) from pjovenes where id=(select min(id) from pjovenes) and gravedad <= 4;")
	  Integer max();
          
         
          
          
          @Query(nativeQuery = true,value = "select min(id) from pjovenes where gravedad=(select max(gravedad) from pjovenes) and gravedad <= 4 ")
	  public Integer maxid();
          
          
          @Query(nativeQuery = true,value = "select COALESCE(max(gravedad),0) from pjovenes where id=(select min(id) from pjovenes) and gravedad > 4;")
	  Integer maxgrave();
          
         
          
          
          @Query(nativeQuery = true,value = "select min(id) from pjovenes where gravedad=(select max(gravedad) from pjovenes) and gravedad > 4 ")
	  public Integer maxidgrave();
    
}
