package com.petbox.notificaciones.service;

import com.petbox.notificaciones.dto.NotificacionDetalleDTO;
import com.petbox.notificaciones.dto.NotificacionListadoDTO;
import com.petbox.notificaciones.dto.NotificacionSimpleDTO;
import com.petbox.notificaciones.model.Notificacion;
import com.petbox.notificaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacionService {

    private final NotificacionRepository repository;
    private final RestTemplate restTemplate;

    public NotificacionService(NotificacionRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public NotificacionSimpleDTO registrar(Notificacion n) {
        Notificacion guardada = repository.save(n);
        
        NotificacionSimpleDTO dto = new NotificacionSimpleDTO();
        dto.setId(guardada.getId());
        dto.setUsuarioId(guardada.getUsuarioId());
        dto.setTipo(guardada.getTipo());
        dto.setAsunto(guardada.getAsunto());
        dto.setMensajeContenido(guardada.getMensajeContenido());
        return dto;
    }

    public List<NotificacionListadoDTO> listarTodas() {
        return repository.findAll().stream().map(n -> {
            NotificacionListadoDTO dto = new NotificacionListadoDTO();
            dto.setId(n.getId());
            dto.setTipo(n.getTipo());
            dto.setAsunto(n.getAsunto());
            return dto;
        }).collect(Collectors.toList());
    }

    public NotificacionDetalleDTO buscarDetallePorId(Long id) {
        Notificacion n = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notificación no encontrada con ID: " + id));

        NotificacionDetalleDTO dto = new NotificacionDetalleDTO();
        dto.setId(n.getId());
        dto.setTipo(n.getTipo());
        dto.setAsunto(n.getAsunto());
        dto.setMensajeContenido(n.getMensajeContenido());

        try {
            // Conexión síncrona al Microservicio de Usuarios (Puerto 8081)
            Object usuario = restTemplate.getForObject("http://localhost:8081/usuarios/" + n.getUsuarioId(), Object.class);
            dto.setUsuarioDestinatario(usuario);
        } catch (Exception ex) {
            dto.setUsuarioDestinatario(null); // Tolerancia a fallos si Usuarios está caído
        }

        return dto;
    }
}