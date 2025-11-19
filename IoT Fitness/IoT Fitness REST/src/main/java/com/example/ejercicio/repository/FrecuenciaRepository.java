package com.example.ejercicio.repository;

import com.example.ejercicio.model.Frecuencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FrecuenciaRepository extends JpaRepository<Frecuencia, Long> {
    List<Frecuencia> findByUserId(Long userId);
}
