package com.petbox.pagos.controller;

import com.petbox.pagos.dto.PagoDetalleDTO;
import com.petbox.pagos.dto.PagoListadoDTO;
import com.petbox.pagos.dto.PagoSimpleDTO;
import com.petbox.pagos.model.Pago;
import com.petbox.pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService service;

    public PagoController(PagoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PagoSimpleDTO> crear(@Valid @RequestBody Pago pago) {
        PagoSimpleDTO resultado = service.registrar(pago);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<PagoListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}