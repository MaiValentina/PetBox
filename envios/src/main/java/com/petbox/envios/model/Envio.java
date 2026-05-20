package com.petbox.envios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del usuario destinatario es obligatorio")
    private Long usuarioId; // Relación lógica hacia el Puerto 8081 (Usuarios)

    @NotBlank(message = "El número de tracking no puede estar vacío")
    @Column(unique = true)
    private String trackingNumber;

    @NotBlank(message = "La dirección de destino es obligatoria")
    private String direccionDestino;

    @NotBlank(message = "El estado del envío (PREPARACIÓN, EN_CAMINO, ENTREGADO) es obligatorio")
    private String estado;
}