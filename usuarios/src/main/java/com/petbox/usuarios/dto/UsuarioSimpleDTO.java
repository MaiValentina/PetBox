package com.petbox.usuarios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class UsuarioSimpleDTO {
    private Long id;
    private String nombre;
    private String email;
    private List<Long> mascotasIds; 
}