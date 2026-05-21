package com.petbox.notificaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del usuario destinatario es requerido")
    private Long usuarioId; // Relación lógica externa

    @NotBlank(message = "El tipo de notificación (EMAIL/SMS) es obligatorio")
    private String tipo;

    @NotBlank(message = "El asunto no puede estar vacío")
    private String asunto;

    @NotBlank(message = "El contenido del mensaje es obligatorio")
    private String mensajeContenido;
}