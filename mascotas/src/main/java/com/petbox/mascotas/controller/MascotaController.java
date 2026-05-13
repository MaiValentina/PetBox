package com.petbox.mascotas.controller;

import com.petbox.mascotas.dto.MascotaDetalleDTO;
import com.petbox.mascotas.dto.MascotaListadoDTO;
import com.petbox.mascotas.dto.MascotaSimpleDTO;
import com.petbox.mascotas.model.Mascota;
import com.petbox.mascotas.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaService service;

    public MascotaController(MascotaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MascotaSimpleDTO> crear(@Valid @RequestBody Mascota mascota) {
        MascotaSimpleDTO resultado = service.registrar(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<MascotaListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }

    // Endpoint expuesto exclusivamente para que el Microservicio de Usuarios haga su consulta inversa
    @GetMapping("/dueno/{duenoId}")
    public ResponseEntity<List<MascotaSimpleDTO>> obtenerPorDueno(@PathVariable Long duenoId) {
        return ResponseEntity.ok(service.listarPorDueno(duenoId));
    }
}