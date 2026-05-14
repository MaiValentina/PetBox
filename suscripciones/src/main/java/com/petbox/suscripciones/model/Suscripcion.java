package com.petbox.suscripciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "suscripciones")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del usuario suscriptor es obligatorio")
    @Column(unique = true) // Un usuario solo tiene una suscripción activa a la vez
    private Long usuarioId; 

    @NotBlank(message = "El tipo de plan no puede estar vacío")
    private String tipoPlan;

    @NotNull(message = "El precio mensual es requerido")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precioMensual;

    private LocalDate fechaInicio;

    @NotBlank(message = "El estado (ACTIVA, PAUSADA, CANCELADA) es obligatorio")
    private String estado;

    @PrePersist
    public void asignarFecha() {
        this.fechaInicio = LocalDate.now();
    }
}