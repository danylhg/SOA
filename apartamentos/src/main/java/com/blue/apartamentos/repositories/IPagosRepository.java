package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.PagoModel;

public interface IPagosRepository extends JpaRepository<PagoModel, Long> {
  List<PagoModel> findByReservacion_IdReservacion(Long idReservacion);
}
