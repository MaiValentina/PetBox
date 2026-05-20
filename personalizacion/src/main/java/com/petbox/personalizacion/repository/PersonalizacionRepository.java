package com.petbox.personalizacion.repository;

import com.petbox.personalizacion.model.Personalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PersonalizacionRepository extends JpaRepository<Personalizacion, Long> {
    Optional<Personalizacion> findByMascotaId(Long mascotaId);
}