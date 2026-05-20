package com.petbox.personalizacion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "personalizaciones")
public class Personalizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId; // Relación lógica externa hacia el Puerto 8082 (Mascotas)

    @NotBlank(message = "El tipo de preferencia (ALIMENTACION, JUGUETES, MIXTO) es obligatorio")
    private String tipoPreferencia;

    @NotBlank(message = "Debe detallar los requerimientos o exclusiones de la mascota")
    private String detallesExclusiones;

    @NotBlank(message = "El nivel de cuidado/fragilidad es obligatorio")
    private String nivelCuidado;
}