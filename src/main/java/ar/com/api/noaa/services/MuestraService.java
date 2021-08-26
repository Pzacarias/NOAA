package ar.com.api.noaa.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.noaa.entities.Boya;
import ar.com.api.noaa.entities.Muestra;
import ar.com.api.noaa.repos.MuestraRepository;

@Service
public class MuestraService {
    
    @Autowired
    MuestraRepository repo;
        
    @Autowired
    BoyaService boyaService;

    public Muestra crear(Double alturaNivelDelMar, Integer boyaId, Date horario, Double latitud, Double longitud,
            String matricula) {

        Muestra muestra = new Muestra();
        muestra.setAlturaAlNivelDelMar(alturaNivelDelMar);
        Boya boya = boyaService.buscarPorBoyaId(boyaId);
        muestra.setBoya(boyaService.calcularColor(boya, alturaNivelDelMar));
        muestra.setHorarioMuestra(horario);
        muestra.setLatitud(latitud);
        muestra.setLongitud(longitud);
        muestra.setMatriculaEmbarcacion(matricula);
        
        return repo.save(muestra);
    }

    public List<Muestra> buscarMuestrasPorBoyaId(Integer idBoya) {
        return repo.findByBoyaId(idBoya);

    }

    public boolean validarMuestraExiste(Integer muestraId) {
        Muestra muestra = repo.findByMuestraId(muestraId);
        if (muestra != null) {
            return true;
        } else
            return false;

    }

    public void eliminarMuestraPorId(Integer id) {
        repo.deleteById(id);
    }
    
 
    

}
