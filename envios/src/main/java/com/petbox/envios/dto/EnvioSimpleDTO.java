package com.petbox.envios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnvioSimpleDTO {
    private Long id;
    private Long usuarioId; // ID que conecta lógicamente con Usuarios
    private String trackingNumber;
    private String direccionDestino;
    private String estado;
}