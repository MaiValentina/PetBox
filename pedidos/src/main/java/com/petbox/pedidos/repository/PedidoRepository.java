package com.petbox.pedidos.repository;

import com.petbox.pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByNumeroOrden(String numeroOrden);
}