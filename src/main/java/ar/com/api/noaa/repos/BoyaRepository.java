package ar.com.api.noaa.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.api.noaa.entities.Boya;

@Repository
public interface BoyaRepository  extends JpaRepository<Boya, Integer> {

    Boya findByBoyaId(Integer boyaId);
}
