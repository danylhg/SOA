package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.repositories.IResenaRepository;

@Service
public class ResenaService {
  @Autowired private IResenaRepository repo;

  public List<ResenaModel> getAll(){ return repo.findAll(); }
  public Optional<ResenaModel> getById(Long id){ return repo.findById(id); }
  public List<ResenaModel> byReservacion(Long idReservacion){ return repo.findByReservacion_IdReservacion(idReservacion); }
  public ResenaModel create(ResenaModel r){ return repo.save(r); }
  public ResenaModel update(Long id, ResenaModel in){
    ResenaModel r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reseña no encontrada"));
    r.setReservacion(in.getReservacion()); r.setCalificacionLimpieza(in.getCalificacionLimpieza());
    r.setCalificacionUbicacion(in.getCalificacionUbicacion()); r.setCalificacionComunicacion(in.getCalificacionComunicacion());
    r.setCalificacionGeneral(in.getCalificacionGeneral()); r.setComentario(in.getComentario());
    r.setFechaResena(in.getFechaResena()); r.setRespuestaPropietario(in.getRespuestaPropietario());
    r.setFechaRespuesta(in.getFechaRespuesta());
    return repo.save(r);
  }
  public void delete(Long id){ repo.deleteById(id); }
}
