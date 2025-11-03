package com.blue.apartamentos.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.repositories.IReservacionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservacionService {

    private final IReservacionRepository repo;

  
    public boolean estaDisponible(Long idPropiedad, LocalDate entrada, LocalDate salida) {
        return repo.contarTraslapesActivos(idPropiedad, entrada, salida) == 0;
    }

  
    @Transactional
    public ReservacionModel crear(ReservacionModel body) {
        return repo.save(body);
    }

 
    @Transactional
    public boolean cancelarPorId(Long id) {
        return repo.findById(id).map(r -> {
            r.setEstado(ReservacionModel.Estado.CANCELADA.name());
            repo.save(r);
            return true;
        }).orElse(false);
    }

  
    public List<ReservacionModel> todas() { return repo.findAll(); }
    public List<ReservacionModel> porUsuario(Long u){ return repo.findAllByUsuario(u); }
    public List<ReservacionModel> porPropiedad(Long p){ return repo.findAllByPropiedad(p); }
    public ReservacionModel porId(Long id){ return repo.findById(id).orElse(null); }

    @Transactional
    public ReservacionModel actualizar(Long id, ReservacionModel body){
        return repo.findById(id).map(r -> {
            r.setIdPropiedad(body.getIdPropiedad());
            r.setIdCliente(body.getIdCliente());
            r.setFechaEntrada(body.getFechaEntrada());
            r.setFechaSalida(body.getFechaSalida());
            r.setNumeroHuespedes(body.getNumeroHuespedes());
            r.setPrecioTotal(body.getPrecioTotal());
            r.setEstado(body.getEstado());
            r.setNotas(body.getNotas());
            r.setCodigoReserva(body.getCodigoReserva());
            r.setFechaCheckin(body.getFechaCheckin());
            r.setFechaCheckout(body.getFechaCheckout());
            return repo.save(r);
        }).orElse(null);
    }

    public List<ReservacionModel> porRango(LocalDate d, LocalDate h){ return repo.findAllByRango(d,h); }
    public List<ReservacionModel> porEstado(String e){ return repo.findAllByEstado(e); }
    public List<ReservacionModel> porUsuarioEstado(Long u,String e){ return repo.findAllByUsuarioAndEstado(u,e); }
    public List<ReservacionModel> porPropEstado(Long p,String e){ return repo.findAllByPropiedadAndEstado(p,e); }
    public List<ReservacionModel> porUsuarioRango(Long u, LocalDate d, LocalDate h){ return repo.findAllByUsuarioAndRango(u,d,h); }
    public List<ReservacionModel> porPropRango(Long p, LocalDate d, LocalDate h){ return repo.findAllByPropiedadAndRango(p,d,h); }

    
    @Transactional public int cancelarActivasUsuario(Long u){ return repo.cancelarActivasPorUsuario(u); }
    @Transactional public int cancelarActivasProp(Long p){ return repo.cancelarActivasPorPropiedad(p); }
    @Transactional public int cancelarActivasRango(LocalDate d, LocalDate h){ return repo.cancelarActivasPorRango(d,h); }
}
