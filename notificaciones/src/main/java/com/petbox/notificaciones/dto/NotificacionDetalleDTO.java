package com.petbox.notificaciones.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificacionDetalleDTO {
    private Long id;
    private String tipo;
    private String asunto;
    private String mensajeContenido;
    private Object usuarioDestinatario; // Almacenará el JSON del usuario traído desde el puerto 8081
}