package com.petbox.personalizacion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalizacionDetalleDTO {
    private Long id;
    private String tipoPreferencia;
    private String detallesExclusiones;
    private String nivelCuidado;
    
    // Objeto traído dinámicamente mediante RestTemplate desde el MS Mascotas
    private Object mascotaDetalle; 
}