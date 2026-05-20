package com.petbox.envios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnvioDetalleDTO {
    private Long id;
    private String trackingNumber;
    private String direccionDestino;
    private String estado;
    private Object usuarioDetalle; // Almacenará el JSON completo del usuario dueño del envío
}