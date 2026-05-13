package com.petbox.mascotas.repository;

import com.petbox.mascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByDuenoId(Long duenoId); // Nos servirá para la consulta inversa desde Usuarios
}