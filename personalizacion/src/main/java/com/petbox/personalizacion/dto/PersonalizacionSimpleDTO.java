package com.petbox.personalizacion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonalizacionSimpleDTO {
    private Long id;
    private Long mascotaId; // Vínculo lógico con Mascotas (8082)
    private String tipoPreferencia;
    private String detallesExclusiones; // Ej: "Sin pollo", "No peluches hilos"
    private String nivelCuidado;
}