package com.petbox.personalizacion.service;

import com.petbox.personalizacion.dto.PersonalizacionDetalleDTO;
import com.petbox.personalizacion.dto.PersonalizacionListadoDTO;
import com.petbox.personalizacion.dto.PersonalizacionSimpleDTO;
import com.petbox.personalizacion.model.Personalizacion;
import com.petbox.personalizacion.repository.PersonalizacionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalizacionService {

    private final PersonalizacionRepository repository;
    private final RestTemplate restTemplate;

    public PersonalizacionService(PersonalizacionRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public PersonalizacionSimpleDTO registrar(Personalizacion p) {
        if (repository.findByMascotaId(p.getMascotaId()).isPresent()) {
            throw new DataIntegrityViolationException("Esta mascota ya cuenta con un perfil de personalización activo.");
        }
        
        Personalizacion guardada = repository.save(p);
        
        PersonalizacionSimpleDTO dto = new PersonalizacionSimpleDTO();
        dto.setId(guardada.getId());
        dto.setMascotaId(guardada.getMascotaId());
        dto.setTipoPreferencia(guardada.getTipoPreferencia());
        dto.setDetallesExclusiones(guardada.getDetallesExclusiones());
        dto.setNivelCuidado(guardada.getNivelCuidado());
        return dto;
    }

    public List<PersonalizacionListadoDTO> listarTodas() {
        return repository.findAll().stream().map(p -> {
            PersonalizacionListadoDTO dto = new PersonalizacionListadoDTO();
            dto.setId(p.getId());
            dto.setTipoPreferencia(p.getTipoPreferencia());
            dto.setNivelCuidado(p.getNivelCuidado());
            return dto;
        }).collect(Collectors.toList());
    }

    public PersonalizacionDetalleDTO buscarDetallePorId(Long id) {
        Personalizacion p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró personalización con ID: " + id));

        PersonalizacionDetalleDTO dto = new PersonalizacionDetalleDTO();
        dto.setId(p.getId());
        dto.setTipoPreferencia(p.getTipoPreferencia());
        dto.setDetallesExclusiones(p.getDetallesExclusiones());
        dto.setNivelCuidado(p.getNivelCuidado());

        try {
            // Consumo síncrono al Microservicio de Mascotas (Puerto 8082)
            Object mascota = restTemplate.getForObject("http://localhost:8082/mascotas/" + p.getMascotaId(), Object.class);
            dto.setMascotaDetalle(mascota);
        } catch (Exception ex) {
            dto.setMascotaDetalle(null); // Respaldo tolerante a fallos si Mascotas está apagado
        }

        return dto;
    }
}