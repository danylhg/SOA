package com.example.ejercicio.repository;

import com.example.ejercicio.model.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SesionRepository extends JpaRepository<Sesion, Long> {
    List<Sesion> findByUserId(Long userId);
}
