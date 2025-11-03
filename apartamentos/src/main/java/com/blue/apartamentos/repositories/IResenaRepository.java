package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.ResenaModel;

public interface IResenaRepository extends JpaRepository<ResenaModel, Long> {
  List<ResenaModel> findByReservacion_IdReservacion(Long idReservacion);
}

