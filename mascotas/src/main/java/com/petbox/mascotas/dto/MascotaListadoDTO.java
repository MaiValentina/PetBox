package com.petbox.mascotas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotaListadoDTO {
    private Long id;
    private String nombre;
    private String especie;
}