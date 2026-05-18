package com.petbox.pagos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagoDetalleDTO {
    private Long id;
    private String transaccionId;
    private Double monto;
    private String metodoPago;
    private String estado;
    private Object usuarioComprador; // Almacenará el JSON del cliente consultado al puerto 8081
}