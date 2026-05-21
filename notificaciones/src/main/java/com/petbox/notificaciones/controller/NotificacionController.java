package com.petbox.notificaciones.controller;

import com.petbox.notificaciones.dto.NotificacionDetalleDTO;
import com.petbox.notificaciones.dto.NotificacionListadoDTO;
import com.petbox.notificaciones.dto.NotificacionSimpleDTO;
import com.petbox.notificaciones.model.Notificacion;
import com.petbox.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<NotificacionSimpleDTO> crear(@Valid @RequestBody Notificacion notificacion) {
        NotificacionSimpleDTO resultado = service.registrar(notificacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<NotificacionListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificacionDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}