package com.petbox.usuarios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioListadoDTO {
    private Long id;
    private String nombre;
    private String email;
}