package com.petbox.pagos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoSimpleDTO {
    private Long id;
    private Long usuarioId; // Vínculo lógico con Usuarios (8081)
    private String transaccionId;
    private Double monto;
    private String metodoPago; // Ej: "TARJETA", "TRANSFERENCIA"
    private String estado;
}