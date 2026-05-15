package com.petbox.inventario.service;

import com.petbox.inventario.dto.ProductoDetalleDTO;
import com.petbox.inventario.dto.ProductoListadoDTO;
import com.petbox.inventario.dto.ProductoSimpleDTO;
import com.petbox.inventario.model.Producto;
import com.petbox.inventario.repository.ProductoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public ProductoSimpleDTO registrar(Producto p) {
        if (repository.findByNombre(p.getNombre()).isPresent()) {
            throw new DataIntegrityViolationException("Ya existe un artículo con ese nombre.");
        }
        
        Producto guardado = repository.save(p);
        
        ProductoSimpleDTO dto = new ProductoSimpleDTO();
        dto.setId(guardado.getId());
        dto.setNombre(guardado.getNombre());
        dto.setDescripcion(guardado.getDescripcion());
        dto.setPrecio(guardado.getPrecio());
        dto.setStockActual(guardado.getStockActual());
        return dto;
    }

    public List<ProductoListadoDTO> listarTodos() {
        return repository.findAll().stream().map(p -> {
            ProductoListadoDTO dto = new ProductoListadoDTO();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setStockActual(p.getStockActual());
            return dto;
        }).collect(Collectors.toList());
    }

    public ProductoDetalleDTO buscarDetallePorId(Long id) {
        Producto p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el producto con ID: " + id));

        ProductoDetalleDTO dto = new ProductoDetalleDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setStockActual(p.getStockActual());

        // Lógica de semáforo de inventario
        if (p.getStockActual() == 0) {
            dto.setEstadoStock("SIN_STOCK");
        } else if (p.getStockActual() <= 5) {
            dto.setEstadoStock("STOCK_CRITICO");
        } else {
            dto.setEstadoStock("STOCK_ALTO");
        }

        return dto;
    }
}