package cl.gonzalo.prueba.repository;

import cl.gonzalo.prueba.model.PAncianos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author gonza
 */
public interface IPAncianos extends CrudRepository<PAncianos, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM pancianos WHERE gravedad <=4 order by gravedad  desc")
    Iterable<PAncianos> findgravedad();

    @Query(nativeQuery = true, value = "SELECT * FROM pancianos WHERE gravedad >4 order by gravedad  desc")
    Iterable<PAncianos> findgravedadmax();

    @Query(nativeQuery = true, value = "SELECT * FROM pancianos   WHERE gravedad = (SELECT MAX(gravedad) FROM pancianos) and no_historia_clinica =:no_historia_clinica  order by id;")
    Iterable<PAncianos> findMayorRiesgo(@Param("no_historia_clinica") Integer no_historia_clinica);

    @Query(nativeQuery = true, value = "SELECT * FROM pancianos   WHERE edad = (SELECT MAX(edad) FROM pancianos)  order by id;")
    Iterable<PAncianos> findMasAnciano();

    @Query(nativeQuery = true, value = "select  COALESCE(max(gravedad),0)  from pancianos where  gravedad <= 4 limit 1")
    Integer max();

    @Query(nativeQuery = true, value = "SELECT COALESCE(min(id),0) FROM pancianos   WHERE gravedad = (SELECT MAX(gravedad)  FROM pancianos where  gravedad  <5 )")
    public Integer maxid();

    @Query(nativeQuery = true, value = "select  COALESCE(max(gravedad),0)  from pancianos where  gravedad > 4 limit 1")
    Integer maxgrave();

    @Query(nativeQuery = true, value = "SELECT COALESCE(min(id),0) FROM pancianos   WHERE gravedad = (SELECT MAX(gravedad)  FROM pancianos where  gravedad  >4 )")
    public Integer maxidgrave();

}
