package com.petbox.mascotas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MascotaDetalleDTO {
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private String alergias;
    private Object duenoDetalle; // Almacenará el JSON completo del dueño consultado al MS Usuarios
}