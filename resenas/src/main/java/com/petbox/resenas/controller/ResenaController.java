package com.petbox.resenas.controller;

import com.petbox.resenas.dto.ResenaDetalleDTO;
import com.petbox.resenas.dto.ResenaListadoDTO;
import com.petbox.resenas.dto.ResenaSimpleDTO;
import com.petbox.resenas.model.Resena;
import com.petbox.resenas.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    private final ResenaService service;

    public ResenaController(ResenaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResenaSimpleDTO> crear(@Valid @RequestBody Resena resena) {
        ResenaSimpleDTO resultado = service.registrar(resena);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ResenaListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}
