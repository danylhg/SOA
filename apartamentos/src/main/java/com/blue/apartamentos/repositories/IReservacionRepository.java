package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;

public interface IReservacionRepository extends JpaRepository<ReservacionModel, Long> {
  List<ReservacionModel> findByCliente_IdUsuario(Long idUsuario);
  List<ReservacionModel> findByPropiedad_IdPropiedad(Long idPropiedad);
  List<ReservacionModel> findByEstado(EstadoReservacion estado);
}
