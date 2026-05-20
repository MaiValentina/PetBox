package com.petbox.envios.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnvioListadoDTO {
    private Long id;
    private String trackingNumber;
    private String estado;
}