package com.petbox.suscripciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SuscripcionSimpleDTO {
    private Long id;
    private Long usuarioId; // Conexión lógica con Usuarios (8081)
    private String tipoPlan;
    private Double precioMensual;
    private LocalDate fechaInicio;
    private String estado;
}