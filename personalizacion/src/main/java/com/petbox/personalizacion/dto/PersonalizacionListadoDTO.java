package com.petbox.personalizacion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalizacionListadoDTO {
    private Long id;
    private String tipoPreferencia; // Ej: "ALIMENTACION", "JUGUETES"
    private String nivelCuidado;    // Ej: "ALTO", "ESTÁNDAR"
}