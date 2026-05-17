package com.petbox.pedidos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PedidoSimpleDTO {
    private Long id;
    private Long usuarioId;   // Vínculo lógico con Usuarios (8081)
    private Long productoId;  // Vínculo lógico con Inventario (8084)
    private String numeroOrden;
    private Integer cantidad;
    private LocalDate fecha;
    private Double total;
}