package com.petbox.resenas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResenaListadoDTO {
    private Long id;
    private Integer estrellas;
    private String comentarioCorto;
}
