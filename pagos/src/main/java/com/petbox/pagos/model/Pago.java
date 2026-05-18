package com.petbox.pagos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El ID del usuario que paga es obligatorio")
    private Long usuarioId; // Relación lógica externa

    @NotBlank(message = "El ID de transacción de la pasarela es obligatorio")
    @Column(unique = true)
    private String transaccionId;

    @NotNull(message = "El monto del pago es requerido")
    @Min(value = 1, message = "El monto a pagar debe ser mayor a cero")
    private Double monto;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @NotBlank(message = "El estado del pago (APROBADO, RECHAZADO, PENDIENTE) es obligatorio")
    private String estado;
}