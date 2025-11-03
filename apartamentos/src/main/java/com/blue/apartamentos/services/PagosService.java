package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.PagoModel;
import com.blue.apartamentos.repositories.IPagosRepository;

@Service
public class PagosService {
  @Autowired private IPagosRepository repo;

  public List<PagoModel> getAll(){ return repo.findAll(); }
  public Optional<PagoModel> getById(Long id){ return repo.findById(id); }
  public List<PagoModel> byReservacion(Long idReservacion){ return repo.findByReservacion_IdReservacion(idReservacion); }
  public PagoModel create(PagoModel p){ return repo.save(p); }
  public PagoModel update(Long id, PagoModel in){
    PagoModel p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Pago no encontrado"));
    p.setReservacion(in.getReservacion()); p.setMonto(in.getMonto()); p.setMetodoPago(in.getMetodoPago());
    p.setEstado(in.getEstado()); p.setFechaPago(in.getFechaPago()); p.setReferenciaPago(in.getReferenciaPago());
    p.setDatosPago(in.getDatosPago());
    return repo.save(p);
  }
  public void delete(Long id){ repo.deleteById(id); }
}
