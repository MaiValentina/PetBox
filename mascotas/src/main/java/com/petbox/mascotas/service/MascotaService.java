package com.petbox.mascotas.service;

import com.petbox.mascotas.dto.MascotaDetalleDTO;
import com.petbox.mascotas.dto.MascotaListadoDTO;
import com.petbox.mascotas.dto.MascotaSimpleDTO;
import com.petbox.mascotas.model.Mascota;
import com.petbox.mascotas.repository.MascotaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    private final MascotaRepository repository;
    private final RestTemplate restTemplate;

    public MascotaService(MascotaRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public MascotaSimpleDTO registrar(Mascota m) {
        Mascota guardada = repository.save(m);
        
        MascotaSimpleDTO dto = new MascotaSimpleDTO();
        dto.setId(guardada.getId());
        dto.setDuenoId(guardada.getDuenoId());
        dto.setNombre(guardada.getNombre());
        dto.setEspecie(guardada.getEspecie());
        dto.setRaza(guardada.getRaza());
        dto.setAlergias(guardada.getAlergias());
        return dto;
    }

    public List<MascotaListadoDTO> listarTodas() {
        return repository.findAll().stream().map(m -> {
            MascotaListadoDTO dto = new MascotaListadoDTO();
            dto.setId(m.getId());
            dto.setNombre(m.getNombre());
            dto.setEspecie(m.getEspecie());
            return dto;
        }).collect(Collectors.toList());
    }

    // Endpoint auxiliar de comunicación inter-servicio (Usado por el MS Usuarios para buscar sus mascotas)
    public List<MascotaSimpleDTO> listarPorDueno(Long duenoId) {
        return repository.findByDuenoId(duenoId).stream().map(m -> {
            MascotaSimpleDTO dto = new MascotaSimpleDTO();
            dto.setId(m.getId());
            dto.setDuenoId(m.getDuenoId());
            dto.setNombre(m.getNombre());
            dto.setEspecie(m.getEspecie());
            dto.setRaza(m.getRaza());
            dto.setAlergias(m.getAlergias());
            return dto;
        }).collect(Collectors.toList());
    }

    // Consumo síncrono con RestTemplate apuntando al MS Usuarios (Puerto 8081)
    public MascotaDetalleDTO buscarDetallePorId(Long id) {
        Mascota m = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la mascota con ID: " + id));

        MascotaDetalleDTO dto = new MascotaDetalleDTO();
        dto.setId(m.getId());
        dto.setNombre(m.getNombre());
        dto.setEspecie(m.getEspecie());
        dto.setRaza(m.getRaza());
        dto.setAlergias(m.getAlergias());

        try {
            // Llamamos dinámicamente al MS de Usuarios para obtener el JSON completo del dueño
            Object dueno = restTemplate.getForObject("http://localhost:8081/usuarios/" + m.getDuenoId(), Object.class);
            dto.setDuenoDetalle(dueno);
        } catch (Exception e) {
            dto.setDuenoDetalle(null); // Respaldo tolerante a fallos si Usuarios está apagado
        }

        return dto;
    }
}