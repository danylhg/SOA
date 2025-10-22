package com.blue.apartamentos.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.DisponibilidadModel;

public interface IDisponibilidadRepository extends JpaRepository<DisponibilidadModel, Long> {
  List<DisponibilidadModel> findByPropiedad_IdPropiedad(Long idPropiedad);
  List<DisponibilidadModel> findByPropiedad_IdPropiedadAndFecha(Long idPropiedad, LocalDate fecha);
}
