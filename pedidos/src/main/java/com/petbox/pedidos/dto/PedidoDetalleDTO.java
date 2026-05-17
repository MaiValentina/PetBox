package com.petbox.pedidos.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PedidoDetalleDTO {
    private Long id;
    private String numeroOrden;
    private Integer cantidad;
    private LocalDate fecha;
    private Double total;
    
    // Objetos enriquecidos dinámicamente mediante RestTemplate
    private Object usuarioComprador; 
    private Object productoDetalle;  
}