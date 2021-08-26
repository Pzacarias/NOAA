package ar.com.api.noaa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.api.noaa.entities.Boya;
import ar.com.api.noaa.entities.Boya.ColorLuzEnum;
import ar.com.api.noaa.repos.BoyaRepository;

@Service
public class BoyaService {

    @Autowired
    BoyaRepository repo;

    public Boya crear(Double longitudInstalacion, Double latitudInstalacion) {
        Boya boya = new Boya();
        boya.setLatitudInstalacion(latitudInstalacion);
        boya.setLongitudInstalacion(longitudInstalacion);
        boya.setColorLuz(null);
        return repo.save(boya);
    }

    public List<Boya> obtenerTodos() {
        return repo.findAll();

    }

    public boolean validarBoyaExiste(Integer boyaId) {
        Boya boya = repo.findByBoyaId(boyaId);
        if (boya != null) {
            return true;
        } else
            return false;

    }

    public Boya buscarPorBoyaId(Integer id) {
        return repo.findByBoyaId(id);
    }

    public void modificar(Integer id, ColorLuzEnum color) {
        Boya boya = repo.findByBoyaId(id);
        switch (color) {

            case AZUL:
                boya.setColorLuz(color);
                repo.save(boya);
                break;

            case VERDE:

                boya.setColorLuz(color);
                repo.save(boya);
                break;

            case AMARILLO:

                boya.setColorLuz(color);
                repo.save(boya);
                break;

            case ROJO:

                boya.setColorLuz(color);
                repo.save(boya);
                break;

            default:
                break;
        }

        repo.save(boya);
    }

    public ValidacionModificacionColor validar(Integer id, ColorLuzEnum color) {

        if (!validarBoyaExiste(id))
            return ValidacionModificacionColor.ERROR_ID_INVALIDA;

        return ValidacionModificacionColor.OK;
    }

    public enum ValidacionModificacionColor {
        ERROR_ID_INVALIDA, OK
    }

    public Boya calcularColor(Boya boya, Double alturaNivelDelMar){
        
        if (alturaNivelDelMar <= -50 || alturaNivelDelMar >= 50){
            boya.setColorLuz(ColorLuzEnum.AMARILLO);
        }
        else if(alturaNivelDelMar <=-100 || alturaNivelDelMar >= 100){
            boya.setColorLuz(ColorLuzEnum.ROJO);
        }
        else boya.setColorLuz(ColorLuzEnum.VERDE);

        return boya;
    }
}
