package com.petbox.suscripciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SuscripcionDetalleDTO {
    private Long id;
    private String tipoPlan;
    private Double precioMensual;
    private LocalDate fechaInicio;
    private String estado;
    private Object usuarioTitular; // Guardará el JSON del cliente consultado al puerto 8081
}