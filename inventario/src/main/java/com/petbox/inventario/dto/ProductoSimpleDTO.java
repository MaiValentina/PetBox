package com.petbox.inventario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoSimpleDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stockActual;
}