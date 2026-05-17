package com.petbox.pedidos.service;

import com.petbox.pedidos.dto.PedidoDetalleDTO;
import com.petbox.pedidos.dto.PedidoListadoDTO;
import com.petbox.pedidos.dto.PedidoSimpleDTO;
import com.petbox.pedidos.model.Pedido;
import com.petbox.pedidos.repository.PedidoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final RestTemplate restTemplate;

    public PedidoService(PedidoRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public PedidoSimpleDTO registrar(Pedido p) {
        if (repository.findByNumeroOrden(p.getNumeroOrden()).isPresent()) {
            throw new DataIntegrityViolationException("Ya existe una orden de pedido con ese identificador.");
        }
        
        Pedido guardado = repository.save(p);
        
        PedidoSimpleDTO dto = new PedidoSimpleDTO();
        dto.setId(guardado.getId());
        dto.setUsuarioId(guardado.getUsuarioId());
        dto.setProductoId(guardado.getProductoId());
        dto.setNumeroOrden(guardado.getNumeroOrden());
        dto.setCantidad(guardado.getCantidad());
        dto.setFecha(guardado.getFecha());
        dto.setTotal(guardado.getTotal());
        return dto;
    }

    public List<PedidoListadoDTO> listarTodos() {
        return repository.findAll().stream().map(p -> {
            PedidoListadoDTO dto = new PedidoListadoDTO();
            dto.setId(p.getId());
            dto.setNumeroOrden(p.getNumeroOrden());
            dto.setFecha(p.getFecha());
            dto.setTotal(p.getTotal());
            return dto;
        }).collect(Collectors.toList());
    }

    public PedidoDetalleDTO buscarDetallePorId(Long id) {
        Pedido p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el pedido con el ID: " + id));

        PedidoDetalleDTO dto = new PedidoDetalleDTO();
        dto.setId(p.getId());
        dto.setNumeroOrden(p.getNumeroOrden());
        dto.setCantidad(p.getCantidad());
        dto.setFecha(p.getFecha());
        dto.setTotal(p.getTotal());

        // CONSUMO 1: Obtener datos del Usuario Comprador (Puerto 8081)
        try {
            Object usuario = restTemplate.getForObject("http://localhost:8081/usuarios/" + p.getUsuarioId(), Object.class);
            dto.setUsuarioComprador(usuario);
        } catch (Exception ex) {
            dto.setUsuarioComprador(null);
        }

        // CONSUMO 2: Obtener datos del Producto comprado (Puerto 8084)
        try {
            Object producto = restTemplate.getForObject("http://localhost:8084/productos/" + p.getProductoId(), Object.class);
            dto.setProductoDetalle(producto);
        } catch (Exception ex) {
            dto.setProductoDetalle(null);
        }

        return dto;
    }
}