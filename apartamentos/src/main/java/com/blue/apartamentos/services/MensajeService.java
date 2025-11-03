package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.repositories.IMensajeRepository;

@Service
public class MensajeService {
  @Autowired private IMensajeRepository repo;

  public List<MensajeModel> getAll(){ return repo.findAll(); }
  public Optional<MensajeModel> getById(Long id){ return repo.findById(id); }
  public List<MensajeModel> byReservacion(Long idReservacion){
    return repo.findByReservacion_IdReservacionOrderByFechaEnvioAsc(idReservacion);
  }
  public List<MensajeModel> conversacion(Long idRem, Long idDest){
    return repo.findByRemitente_IdUsuarioAndDestinatario_IdUsuarioOrderByFechaEnvioAsc(idRem, idDest);
  }
  public MensajeModel create(MensajeModel m){ return repo.save(m); }
  public MensajeModel update(Long id, MensajeModel in){
    MensajeModel m = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado"));
    m.setRemitente(in.getRemitente()); m.setDestinatario(in.getDestinatario());
    m.setReservacion(in.getReservacion()); m.setAsunto(in.getAsunto());
    m.setContenido(in.getContenido()); m.setLeido(in.getLeido()); m.setFechaEnvio(in.getFechaEnvio());
    return repo.save(m);
  }
  public void delete(Long id){ repo.deleteById(id); }
}
