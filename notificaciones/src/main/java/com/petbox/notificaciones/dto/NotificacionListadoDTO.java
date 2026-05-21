package com.petbox.notificaciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacionListadoDTO {
    private Long id;
    private String tipo; // Ej: "EMAIL", "SMS"
    private String asunto;
}