package ar.com.api.noaa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.api.noaa.entities.Boya;
import ar.com.api.noaa.models.request.InfoBoyaNueva;
import ar.com.api.noaa.models.request.InfoColorNuevo;
import ar.com.api.noaa.models.response.GenericResponse;
import ar.com.api.noaa.services.BoyaService;

@RestController
public class BoyaController {

    @Autowired
    BoyaService service;

    @PostMapping("/boyas")
    public ResponseEntity<GenericResponse> crear(@RequestBody InfoBoyaNueva infoBoya) {

        GenericResponse respuesta = new GenericResponse();

        Boya boya = service.crear(infoBoya.latitudInstalacion, infoBoya.longitudInstalacion);

        respuesta.isOk = true;
        respuesta.message = "Se ha creado la boya correctamente";
        respuesta.id = boya.getBoyaId();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/boyas")
    public ResponseEntity<List<Boya>> traerTodo() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/boyas/{id}")
    public ResponseEntity<?> traerPorId(@PathVariable Integer id) {
        GenericResponse respuesta = new GenericResponse();
        if (!service.validarBoyaExiste(id)) {
            respuesta.isOk = false;
            respuesta.message = "El número de Id ingresado no es correcto.";
            return ResponseEntity.badRequest().body(respuesta);
        }
        return ResponseEntity.ok(service.buscarPorBoyaId(id));
    }

    @PutMapping("/boyas/{id}")
    public ResponseEntity<GenericResponse> modificar(@PathVariable Integer id,
            @RequestBody InfoColorNuevo infoColor) {

        GenericResponse respuesta = new GenericResponse();

            service.modificar(id, infoColor.color);

            respuesta.isOk = true;
            respuesta.message = "Se actualizó el aeropuerto correctamente.";

            return ResponseEntity.ok(respuesta);
            
        }
}
