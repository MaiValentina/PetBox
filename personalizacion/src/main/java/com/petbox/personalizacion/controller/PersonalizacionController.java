package com.petbox.personalizacion.controller;

import com.petbox.personalizacion.dto.PersonalizacionDetalleDTO;
import com.petbox.personalizacion.dto.PersonalizacionListadoDTO;
import com.petbox.personalizacion.dto.PersonalizacionSimpleDTO;
import com.petbox.personalizacion.model.Personalizacion;
import com.petbox.personalizacion.service.PersonalizacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personalizaciones")
public class PersonalizacionController {

    private final PersonalizacionService service;

    public PersonalizacionController(PersonalizacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PersonalizacionSimpleDTO> crear(@Valid @RequestBody Personalizacion personalizacion) {
        PersonalizacionSimpleDTO resultado = service.registrar(personalizacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<PersonalizacionListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalizacionDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}