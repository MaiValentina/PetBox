package com.petbox.inventario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoListadoDTO {
    private Long id;
    private String nombre;
    private Integer stockActual;
}