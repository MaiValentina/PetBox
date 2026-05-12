package com.petbox.usuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    
    @NotBlank(message = "El email es un campo obligatorio")
    @Email(message = "Debe proporcionar un email con formato correcto")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "La clave de acceso es obligatoria")
    private String password;
}