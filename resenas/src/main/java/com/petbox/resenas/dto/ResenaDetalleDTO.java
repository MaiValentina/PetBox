package com.petbox.resenas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResenaDetalleDTO {
    private Long id;
    private Integer estrellas;
    private String comentario;

    private Object usuarioAutor;
    private Object productoResenado;
}
