package com.petbox.resenas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResenaSimpleDTO {
    private Long id;
    private Long usuarioId;
    private Long productoId;
    private Integer estrellas;
    private String comentario;
}
