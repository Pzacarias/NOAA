package ar.com.api.noaa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import ar.com.api.noaa.entities.Muestra;
import ar.com.api.noaa.models.request.InfoMuestraNueva;
import ar.com.api.noaa.models.response.GenericResponse;
import ar.com.api.noaa.models.response.MuestraResponse;
import ar.com.api.noaa.services.BoyaService;
import ar.com.api.noaa.services.MuestraService;

@RestController
public class MuestraController {

    @Autowired
    MuestraService service;

    @Autowired
    BoyaService boyaService;

    @PostMapping("/muestras")
    public ResponseEntity<?> crear(@RequestBody InfoMuestraNueva infoMuestra) {

        GenericResponse respuesta = new GenericResponse();
        MuestraResponse respuestaEsperada = new MuestraResponse();

        Muestra muestra = service.crear(infoMuestra.alturaNivelDelMar, infoMuestra.boyaId, infoMuestra.horario,
                infoMuestra.latitud, infoMuestra.longitud, infoMuestra.matricula);

        respuestaEsperada.id = muestra.getMuestraId();
        respuestaEsperada.color = muestra.getBoya().getColorLuz();


        return ResponseEntity.ok(respuestaEsperada);
    }

    @GetMapping("/muestras/boyas/{idBoya}")
    public ResponseEntity<List<Muestra>> traerPorId(@PathVariable Integer idBoya) {
        return ResponseEntity.ok(service.buscarMuestrasPorBoyaId(idBoya));
    }

    
    @DeleteMapping ("/muestras/{id}")
    public ResponseEntity<GenericResponse> eliminar(@PathVariable Integer id){
       
        GenericResponse respuesta = new GenericResponse();
        if (service.validarMuestraExiste(id)) {
            service.eliminarMuestraPorId(id);
            respuesta.isOk = true;
            respuesta.message = "La muesta ha sido eliminada correctamente.";
            return ResponseEntity.ok(respuesta);
            
        }
        else {
            respuesta.isOk = false;
            respuesta.message = "El número de Id ingresado no es correcto.";
            return ResponseEntity.badRequest().body(respuesta);
          
        }
        
        
    }

    
}
