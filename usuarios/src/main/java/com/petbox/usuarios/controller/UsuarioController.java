package com.petbox.usuarios.controller;

import com.petbox.usuarios.dto.UsuarioDetalleDTO;
import com.petbox.usuarios.dto.UsuarioListadoDTO;
import com.petbox.usuarios.dto.UsuarioSimpleDTO;
import com.petbox.usuarios.model.Usuario;
import com.petbox.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioSimpleDTO> crear(@Valid @RequestBody Usuario usuario) {
        UsuarioSimpleDTO resultado = service.registrar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}