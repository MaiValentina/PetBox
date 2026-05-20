package com.petbox.envios.repository;

import com.petbox.envios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    Optional<Envio> findByTrackingNumber(String trackingNumber);
}