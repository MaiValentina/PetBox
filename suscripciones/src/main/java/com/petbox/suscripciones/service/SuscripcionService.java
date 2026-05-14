package com.petbox.suscripciones.service;

import com.petbox.suscripciones.dto.SuscripcionDetalleDTO;
import com.petbox.suscripciones.dto.SuscripcionListadoDTO;
import com.petbox.suscripciones.dto.SuscripcionSimpleDTO;
import com.petbox.suscripciones.model.Suscripcion;
import com.petbox.suscripciones.repository.SuscripcionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuscripcionService {

    private final SuscripcionRepository repository;
    private final RestTemplate restTemplate;

    public SuscripcionService(SuscripcionRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public SuscripcionSimpleDTO registrar(Suscripcion s) {
        if (repository.findByUsuarioId(s.getUsuarioId()).isPresent()) {
            throw new DataIntegrityViolationException("El usuario ya se encuentra suscrito a un plan.");
        }
        
        Suscripcion guardada = repository.save(s);
        
        SuscripcionSimpleDTO dto = new SuscripcionSimpleDTO();
        dto.setId(guardada.getId());
        dto.setUsuarioId(guardada.getUsuarioId());
        dto.setTipoPlan(guardada.getTipoPlan());
        dto.setPrecioMensual(guardada.getPrecioMensual());
        dto.setFechaInicio(guardada.getFechaInicio());
        dto.setEstado(guardada.getEstado());
        return dto;
    }

    public List<SuscripcionListadoDTO> listarTodas() {
        return repository.findAll().stream().map(s -> {
            SuscripcionListadoDTO dto = new SuscripcionListadoDTO();
            dto.setId(s.getId());
            dto.setTipoPlan(s.getTipoPlan());
            dto.setEstado(s.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public SuscripcionDetalleDTO buscarDetallePorId(Long id) {
        Suscripcion s = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se ubicó la suscripción con ID: " + id));

        SuscripcionDetalleDTO dto = new SuscripcionDetalleDTO();
        dto.setId(s.getId());
        dto.setTipoPlan(s.getTipoPlan());
        dto.setPrecioMensual(s.getPrecioMensual());
        dto.setFechaInicio(s.getFechaInicio());
        dto.setEstado(s.getEstado());

        try {
            // Consumo síncrono al Microservicio de Usuarios (Puerto 8081)
            Object usuario = restTemplate.getForObject("http://localhost:8081/usuarios/" + s.getUsuarioId(), Object.class);
            dto.setUsuarioTitular(usuario);
        } catch (Exception ex) {
            dto.setUsuarioTitular(null); // Respaldo tolerante si Usuarios está offline
        }

        return dto;
    }
}