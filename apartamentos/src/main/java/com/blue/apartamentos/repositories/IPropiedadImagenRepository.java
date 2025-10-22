package com.blue.apartamentos.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blue.apartamentos.models.PropiedadImagenModel;

public interface IPropiedadImagenRepository extends JpaRepository<PropiedadImagenModel, Long> {
  List<PropiedadImagenModel> findByPropiedad_IdPropiedadOrderByOrdenAsc(Long idPropiedad);
}
