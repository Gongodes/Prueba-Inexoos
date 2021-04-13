package cl.gonzalo.prueba.repository;
import cl.gonzalo.prueba.model.PJovenes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface IPJovenes extends CrudRepository<PJovenes, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes   WHERE gravedad = (SELECT MAX(gravedad) FROM pjovenes) and no_historia_clinica =:no_historia_clinica  order by id;")
    Iterable<PJovenes> findMayorRiesgo(@Param("no_historia_clinica") Integer no_historia_clinica);

    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes WHERE gravedad <=4 order by gravedad  desc")
    Iterable<PJovenes> findgravedad();

    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes WHERE gravedad >4  order by gravedad  desc")
    Iterable<PJovenes> findgravedadmax();

    @Query(nativeQuery = true, value = "SELECT * FROM pjovenes WHERE gravedad >4 and fumador=true order by gravedad  desc")
    Iterable<PJovenes> findfumador();

    

    @Query(nativeQuery = true, value = "select  COALESCE(max(gravedad),0)  from pjovenes where  gravedad <= 4 limit 1")
    Integer max();

    @Query(nativeQuery = true, value = "SELECT COALESCE(min(id),0) FROM pjovenes   WHERE gravedad = (SELECT MAX(gravedad)  FROM pjovenes where  gravedad  <5 )")
    public Integer maxid();

    @Query(nativeQuery = true, value = "select  COALESCE(max(gravedad),0)  from pjovenes where  gravedad > 4 limit 1")
    Integer maxgrave();

    @Query(nativeQuery = true, value = "SELECT COALESCE(min(id),0) FROM pjovenes   WHERE gravedad = (SELECT MAX(gravedad)  FROM pjovenes where  gravedad  >4 )")
    public Integer maxidgrave();

}