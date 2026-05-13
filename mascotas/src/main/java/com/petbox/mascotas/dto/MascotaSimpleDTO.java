package com.petbox.mascotas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotaSimpleDTO {
    private Long id;
    private Long duenoId; // ID primitivo que conecta de forma lógica con el MS de Usuarios
    private String nombre;
    private String especie;
    private String raza;
    private String alergias;
}