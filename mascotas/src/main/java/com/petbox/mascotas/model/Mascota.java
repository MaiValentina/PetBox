package com.petbox.mascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del dueño es un dato obligatorio")
    private Long duenoId; // Relación lógica hacia el Microservicio 1 (Usuarios)

    @NotBlank(message = "El nombre de la mascota es obligatorio")
    private String nombre;

    @NotBlank(message = "La especie (Perro, Gato, etc.) es obligatoria")
    private String especie;

    private String raza;
    private String alergias;
}