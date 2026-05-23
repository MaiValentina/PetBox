package com.petbox.resenas.service;

import com.petbox.resenas.dto.ResenaDetalleDTO;
import com.petbox.resenas.dto.ResenaListadoDTO;
import com.petbox.resenas.dto.ResenaSimpleDTO;
import com.petbox.resenas.model.Resena;
import com.petbox.resenas.repository.ResenaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResenaService {

    private final ResenaRepository repository;
    private final RestTemplate restTemplate;

    public ResenaService(ResenaRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public ResenaSimpleDTO registrar(Resena r) {
        Resena guardada = repository.save(r);

        ResenaSimpleDTO dto = new ResenaSimpleDTO();
        dto.setId(guardada.getId());
        dto.setUsuarioId(guardada.getUsuarioId());
        dto.setProductoId(guardada.getProductoId());
        dto.setEstrellas(guardada.getEstrellas());
        dto.setComentario(guardada.getComentario());
        return dto;
    }

    public List<ResenaListadoDTO> listarTodas() {
        return repository.findAll().stream().map(r -> {
            ResenaListadoDTO dto = new ResenaListadoDTO();
            dto.setId(r.getId());
            dto.setEstrellas(r.getEstrellas());
            String corto = r.getComentario().length() > 30 ? r.getComentario().substring(0, 27) + "..." : r.getComentario();
            dto.setComentarioCorto(corto);
            return dto;
        }).collect(Collectors.toList());
    }

    public ResenaDetalleDTO buscarDetallePorId(Long id) {
        Resena r = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la reseña con ID: " + id));

        ResenaDetalleDTO dto = new ResenaDetalleDTO();
        dto.setId(r.getId());
        dto.setEstrellas(r.getEstrellas());
        dto.setComentario(r.getComentario());

        try {
            Object usuario = restTemplate.getForObject("http://localhost:8090/usuarios/" + r.getUsuarioId(), Object.class);
            dto.setUsuarioAutor(usuario);
        } catch (Exception ex) {
            dto.setUsuarioAutor(null);
        }

        try {
            Object producto = restTemplate.getForObject("http://localhost:8084/productos/" + r.getProductoId(), Object.class);
            dto.setProductoResenado(producto);
        } catch (Exception ex) {
            dto.setProductoResenado(null);
        }

        return dto;
    }
}
