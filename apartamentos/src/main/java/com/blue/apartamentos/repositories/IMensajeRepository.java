package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.MensajeModel;

public interface IMensajeRepository extends JpaRepository<MensajeModel, Long> {
  List<MensajeModel> findByReservacion_IdReservacionOrderByFechaEnvioAsc(Long idReservacion);
  List<MensajeModel> findByRemitente_IdUsuarioAndDestinatario_IdUsuarioOrderByFechaEnvioAsc(Long idRem, Long idDest);
}

