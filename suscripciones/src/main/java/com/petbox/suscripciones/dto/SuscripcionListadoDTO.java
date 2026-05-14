package com.petbox.suscripciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuscripcionListadoDTO {
    private Long id;
    private String tipoPlan; // Ej: "PREMIUM", "BASICO"
    private String estado;   // Ej: "ACTIVA", "VENCIDA"
}