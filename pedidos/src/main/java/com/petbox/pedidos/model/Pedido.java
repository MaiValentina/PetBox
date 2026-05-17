package com.petbox.pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del comprador es obligatorio")
    private Long usuarioId; // Relación lógica externa (8081)

    @NotNull(message = "El ID del producto comprado es obligatorio")
    private Long productoId; // Relación lógica externa (8084)

    @NotBlank(message = "El número de orden no puede estar vacío")
    @Column(unique = true)
    private String numeroOrden;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "Debe ordenar al menos 1 producto")
    private Integer cantidad;

    private LocalDate fecha;

    @NotNull(message = "El total del pedido es requerido")
    @Min(value = 0, message = "El total no puede ser negativo")
    private Double total;

    @PrePersist
    public void asignarFecha() {
        this.fecha = LocalDate.now();
    }
}