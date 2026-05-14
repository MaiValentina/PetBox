package com.petbox.suscripciones.repository;

import com.petbox.suscripciones.model.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    Optional<Suscripcion> findByUsuarioId(Long usuarioId);
}