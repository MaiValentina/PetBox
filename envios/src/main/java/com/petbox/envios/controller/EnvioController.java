package com.petbox.envios.controller;

import com.petbox.envios.dto.EnvioDetalleDTO;
import com.petbox.envios.dto.EnvioListadoDTO;
import com.petbox.envios.dto.EnvioSimpleDTO;
import com.petbox.envios.model.Envio;
import com.petbox.envios.service.EnvioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private final EnvioService service;

    public EnvioController(EnvioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnvioSimpleDTO> crear(@Valid @RequestBody Envio envio) {
        EnvioSimpleDTO resultado = service.registrar(envio);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<EnvioListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}