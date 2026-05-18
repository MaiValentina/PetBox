package com.petbox.pagos.service;

import com.petbox.pagos.dto.PagoDetalleDTO;
import com.petbox.pagos.dto.PagoListadoDTO;
import com.petbox.pagos.dto.PagoSimpleDTO;
import com.petbox.pagos.model.Pago;
import com.petbox.pagos.repository.PagoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PagoService {

    private final PagoRepository repository;
    private final RestTemplate restTemplate;

    public PagoService(PagoRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public PagoSimpleDTO registrar(Pago p) {
        if (repository.findByTransaccionId(p.getTransaccionId()).isPresent()) {
            throw new DataIntegrityViolationException("Esta transacción ya fue procesada anteriormente.");
        }
        
        Pago guardado = repository.save(p);
        
        PagoSimpleDTO dto = new PagoSimpleDTO();
        dto.setId(guardado.getId());
        dto.setUsuarioId(guardado.getUsuarioId());
        dto.setTransaccionId(guardado.getTransaccionId());
        dto.setMonto(guardado.getMonto());
        dto.setMetodoPago(guardado.getMetodoPago());
        dto.setEstado(guardado.getEstado());
        return dto;
    }

    public List<PagoListadoDTO> listarTodos() {
        return repository.findAll().stream().map(p -> {
            PagoListadoDTO dto = new PagoListadoDTO();
            dto.setId(p.getId());
            dto.setTransaccionId(p.getTransaccionId());
            dto.setMonto(p.getMonto());
            dto.setEstado(p.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public PagoDetalleDTO buscarDetallePorId(Long id) {
        Pago p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el registro de pago con ID: " + id));

        PagoDetalleDTO dto = new PagoDetalleDTO();
        dto.setId(p.getId());
        dto.setTransaccionId(p.getTransaccionId());
        dto.setMonto(p.getMonto());
        dto.setMetodoPago(p.getMetodoPago());
        dto.setEstado(p.getEstado());

        try {
            // Consumo síncrono al Microservicio de Usuarios (Puerto 8081)
            Object usuario = restTemplate.getForObject("http://localhost:8081/usuarios/" + p.getUsuarioId(), Object.class);
            dto.setUsuarioComprador(usuario);
        } catch (Exception ex) {
            dto.setUsuarioComprador(null); // Respaldo tolerante si Usuarios no responde
        }

        return dto;
    }
}