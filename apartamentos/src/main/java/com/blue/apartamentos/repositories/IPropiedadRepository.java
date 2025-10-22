package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.PropiedadModel.EstadoPropiedad;
import com.blue.apartamentos.models.PropiedadModel.TipoPropiedad;

public interface IPropiedadRepository extends JpaRepository<PropiedadModel, Long> {
  List<PropiedadModel> findByTipo(TipoPropiedad tipo);
  List<PropiedadModel> findByEstado(EstadoPropiedad estado);
  List<PropiedadModel> findByPropietario_IdUsuario(Long idUsuario);
  List<PropiedadModel> findByCiudadIgnoreCase(String ciudad);
}
