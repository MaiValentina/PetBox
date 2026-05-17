package com.petbox.pedidos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PedidoListadoDTO {
    private Long id;
    private String numeroOrden;
    private LocalDate fecha;
    private Double total;
}