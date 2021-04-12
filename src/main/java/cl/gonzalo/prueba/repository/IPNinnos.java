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

    @Query(nativeQuery = true, value = "select  COALESCE(max(relacion_peso),0)  from pninnos where  relacion_peso <= 4 limit 1")
    Integer max();

    @Query(nativeQuery = true, value = "select  COALESCE(min(ids),0)  from pninnos where  relacion_peso <= 4 limit 1")
    public Integer maxid();

    @Query(nativeQuery = true, value = "select  COALESCE(max(relacion_peso),0)  from pninnos where  relacion_peso > 4 limit 1")
    Integer maxgrave();

    @Query(nativeQuery = true, value = "select  COALESCE(min(ids),0)  from pninnos where  relacion_peso > 4 limit 1")
    public Integer maxidgrave();

    @Query(nativeQuery = true, value = "SELECT * FROM pninnos   WHERE relacion_peso = (SELECT MAX(relacion_peso) FROM pninnos) and no_historia_clinica =:no_historia_clinica  order by ids;")
    Iterable<PNinnos> findMayorRiesgo(@Param("no_historia_clinica") Integer no_historia_clinica);

}
