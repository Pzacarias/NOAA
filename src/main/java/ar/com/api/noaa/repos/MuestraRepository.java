package ar.com.api.noaa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.noaa.entities.Muestra;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Integer>{
    
    Muestra findByMuestraId(Integer muestraId);
    List<Muestra> findByBoyaId(Integer BoyaId);

}
