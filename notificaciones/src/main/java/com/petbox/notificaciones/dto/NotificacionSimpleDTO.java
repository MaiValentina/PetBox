package com.petbox.notificaciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacionSimpleDTO {
    private Long id;
    private Long usuarioId; // Conexión lógica con Usuarios
    private String tipo;
    private String asunto;
    private String mensajeContenido;
}