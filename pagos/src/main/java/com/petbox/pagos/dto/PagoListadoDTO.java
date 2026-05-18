package com.petbox.pagos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoListadoDTO {
    private Long id;
    private String transaccionId;
    private Double monto;
    private String estado;
}