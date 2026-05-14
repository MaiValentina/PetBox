package com.petbox.suscripciones.controller;

import com.petbox.suscripciones.dto.SuscripcionDetalleDTO;
import com.petbox.suscripciones.dto.SuscripcionListadoDTO;
import com.petbox.suscripciones.dto.SuscripcionSimpleDTO;
import com.petbox.suscripciones.model.Suscripcion;
import com.petbox.suscripciones.service.SuscripcionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suscripciones")
public class SuscripcionController {

    private final SuscripcionService service;

    public SuscripcionController(SuscripcionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SuscripcionSimpleDTO> crear(@Valid @RequestBody Suscripcion suscripcion) {
        SuscripcionSimpleDTO resultado = service.registrar(suscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<SuscripcionListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}