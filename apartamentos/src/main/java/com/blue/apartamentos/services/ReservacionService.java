package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;
import com.blue.apartamentos.repositories.IReservacionRepository;

@Service
public class ReservacionService {
  @Autowired private IReservacionRepository repo;

  public List<ReservacionModel> getAll(){ return repo.findAll(); }
  public Optional<ReservacionModel> getById(Long id){ return repo.findById(id); }
  public ReservacionModel create(ReservacionModel r){ return repo.save(r); }
  public ReservacionModel update(Long id, ReservacionModel in){
    ReservacionModel r = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservación no encontrada"));
    r.setPropiedad(in.getPropiedad()); r.setCliente(in.getCliente());
    r.setFechaEntrada(in.getFechaEntrada()); r.setFechaSalida(in.getFechaSalida());
    r.setNumeroHuespedes(in.getNumeroHuespedes()); r.setPrecioTotal(in.getPrecioTotal());
    r.setEstado(in.getEstado()); r.setFechaReservacion(in.getFechaReservacion());
    r.setNotas(in.getNotas()); r.setCodigoReserva(in.getCodigoReserva());
    r.setFechaCheckin(in.getFechaCheckin()); r.setFechaCheckout(in.getFechaCheckout());
    return repo.save(r);
  }
  public void delete(Long id){ repo.deleteById(id); }

  public List<ReservacionModel> byCliente(Long idUsuario){ return repo.findByCliente_IdUsuario(idUsuario); }
  public List<ReservacionModel> byPropiedad(Long idPropiedad){ return repo.findByPropiedad_IdPropiedad(idPropiedad); }
  public List<ReservacionModel> byEstado(EstadoReservacion e){ return repo.findByEstado(e); }
}

