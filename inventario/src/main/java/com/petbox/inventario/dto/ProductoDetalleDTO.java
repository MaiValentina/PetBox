package com.petbox.inventario.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductoDetalleDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stockActual;
    private String estadoStock; // Mostrará si está: STOCK_ALTO, STOCK_CRITICO o SIN_STOCK
}