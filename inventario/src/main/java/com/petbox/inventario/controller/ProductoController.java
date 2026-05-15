package com.petbox.inventario.controller;

import com.petbox.inventario.dto.ProductoDetalleDTO;
import com.petbox.inventario.dto.ProductoListadoDTO;
import com.petbox.inventario.dto.ProductoSimpleDTO;
import com.petbox.inventario.model.Producto;
import com.petbox.inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductoSimpleDTO> crear(@Valid @RequestBody Producto producto) {
        ProductoSimpleDTO resultado = service.registrar(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    @GetMapping
    public ResponseEntity<List<ProductoListadoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDetalleDTO> obtenerDetalle(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarDetallePorId(id));
    }
}