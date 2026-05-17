package com.petbox.pedidos.controller;

import com.petbox.pedidos.dto.PedidoDetalleDTO;
import com.petbox.pedidos.dto.PedidoListadoDTO;
import com.petbox.pedidos.dto.PedidoSimpleDTO;
import com.petbox.pedidos.model.Pedido;
import com.petbox.pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PedidoSimpleDTO> crear(@Valid @RequestBody Pedido pedido) {
        PedidoSimpleDTO resultado = service.registrar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<PedidoListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}