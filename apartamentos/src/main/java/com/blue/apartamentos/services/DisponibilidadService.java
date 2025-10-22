package com.blue.apartamentos.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.repositories.IDisponibilidadRepository;
import com.blue.apartamentos.repositories.IPropiedadRepository;

@Service
public class DisponibilidadService {
  @Autowired private IDisponibilidadRepository repo;
  @Autowired private IPropiedadRepository propiedadRepo;

  public List<DisponibilidadModel> getAll(){ return repo.findAll(); }
  public Optional<DisponibilidadModel> getById(Long id){ return repo.findById(id); }
  public List<DisponibilidadModel> byPropiedad(Long idProp){ return repo.findByPropiedad_IdPropiedad(idProp); }
  public List<DisponibilidadModel> byPropiedadFecha(Long idProp, LocalDate fecha){
    return repo.findByPropiedad_IdPropiedadAndFecha(idProp, fecha);
  }
  public DisponibilidadModel create(Long idPropiedad, DisponibilidadModel d){
    PropiedadModel p = propiedadRepo.findById(idPropiedad)
      .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
    if(!repo.findByPropiedad_IdPropiedadAndFecha(idPropiedad, d.getFecha()).isEmpty())
      throw new IllegalArgumentException("Ya existe disponibilidad para esa fecha");
    d.setPropiedad(p);
    return repo.save(d);
  }
  public DisponibilidadModel update(Long id, DisponibilidadModel in){
    DisponibilidadModel d = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Disponibilidad no encontrada"));
    if(!d.getFecha().equals(in.getFecha())){
      Long idProp = d.getPropiedad().getIdPropiedad();
      if(!repo.findByPropiedad_IdPropiedadAndFecha(idProp, in.getFecha()).isEmpty())
        throw new IllegalArgumentException("Duplicado de fecha para esa propiedad");
      d.setFecha(in.getFecha());
    }
    d.setDisponible(in.getDisponible());
    d.setPrecioEspecial(in.getPrecioEspecial());
    return repo.save(d);
  }
  public void delete(Long id){ repo.deleteById(id); }
}

