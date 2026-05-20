package com.petbox.envios.service;

import com.petbox.envios.dto.EnvioDetalleDTO;
import com.petbox.envios.dto.EnvioListadoDTO;
import com.petbox.envios.dto.EnvioSimpleDTO;
import com.petbox.envios.model.Envio;
import com.petbox.envios.repository.EnvioRepository;
import org.springframework.dao.DataIntegrityViolationException; // IMPORT SOLUCIONADO
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnvioService {

    private final EnvioRepository repository;
    private final RestTemplate restTemplate;

    public EnvioService(EnvioRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public EnvioSimpleDTO registrar(Envio e) {
        if (repository.findByTrackingNumber(e.getTrackingNumber()).isPresent()) {
            throw new DataIntegrityViolationException("El número de tracking ya existe.");
        }
        
        Envio guardado = repository.save(e);
        
        EnvioSimpleDTO dto = new EnvioSimpleDTO();
        dto.setId(guardado.getId());
        dto.setUsuarioId(guardado.getUsuarioId());
        dto.setTrackingNumber(guardado.getTrackingNumber());
        dto.setDireccionDestino(guardado.getDireccionDestino());
        dto.setEstado(guardado.getEstado());
        return dto;
    }

    public List<EnvioListadoDTO> listarTodos() {
        return repository.findAll().stream().map(e -> {
            EnvioListadoDTO dto = new EnvioListadoDTO();
            dto.setId(e.getId());
            dto.setTrackingNumber(e.getTrackingNumber());
            dto.setEstado(e.getEstado());
            return dto;
        }).collect(Collectors.toList());
    }

    public EnvioDetalleDTO buscarDetallePorId(Long id) {
        Envio e = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el envío con ID: " + id));

        EnvioDetalleDTO dto = new EnvioDetalleDTO();
        dto.setId(e.getId());
        dto.setTrackingNumber(e.getTrackingNumber());
        dto.setDireccionDestino(e.getDireccionDestino());
        dto.setEstado(e.getEstado());

        try {
            Object usuario = restTemplate.getForObject("http://localhost:8081/usuarios/" + e.getUsuarioId(), Object.class);
            dto.setUsuarioDetalle(usuario);
        } catch (Exception ex) {
            dto.setUsuarioDetalle(null);
        }

        return dto;
    }
}