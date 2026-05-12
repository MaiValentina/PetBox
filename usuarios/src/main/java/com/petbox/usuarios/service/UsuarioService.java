package com.petbox.usuarios.service;

import com.petbox.usuarios.dto.UsuarioDetalleDTO;
import com.petbox.usuarios.dto.UsuarioListadoDTO;
import com.petbox.usuarios.dto.UsuarioSimpleDTO;
import com.petbox.usuarios.model.Usuario;
import com.petbox.usuarios.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final RestTemplate restTemplate;

    public UsuarioService(UsuarioRepository repository, BCryptPasswordEncoder encoder, RestTemplate restTemplate) {
        this.repository = repository;
        this.encoder = encoder;
        this.restTemplate = restTemplate;
    }

    public UsuarioSimpleDTO registrar(Usuario usuario) {
        if (repository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ingresado ya se encuentra registrado.");
        }
        
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        Usuario guardado = repository.save(usuario);
        
        UsuarioSimpleDTO dto = new UsuarioSimpleDTO();
        dto.setId(guardado.getId());
        dto.setNombre(guardado.getNombre());
        dto.setEmail(guardado.getEmail());
        dto.setMascotasIds(new ArrayList<>()); 
        return dto;
    }

    public List<UsuarioListadoDTO> listarTodos() {
        return repository.findAll().stream().map(u -> {
            UsuarioListadoDTO dto = new UsuarioListadoDTO();
            dto.setId(u.getId());
            dto.setNombre(u.getNombre());
            dto.setEmail(u.getEmail());
            return dto;
        }).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public UsuarioDetalleDTO buscarDetallePorId(Long id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con ID: " + id));
        
        UsuarioDetalleDTO dto = new UsuarioDetalleDTO();
        dto.setId(u.getId());
        dto.setNombre(u.getNombre());
        dto.setEmail(u.getEmail());
        
        try {
            List<Object> mascotas = restTemplate.getForObject("http://localhost:8082/mascotas/dueno/" + id, List.class);
            dto.setMascotasDetalle(mascotas);
        } catch (Exception e) {
            dto.setMascotasDetalle(new ArrayList<>());
        }
        
        return dto;
    }
}