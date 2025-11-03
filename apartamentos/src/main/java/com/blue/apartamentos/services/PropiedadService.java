package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.PropiedadModel.EstadoPropiedad;
import com.blue.apartamentos.models.PropiedadModel.TipoPropiedad;
import com.blue.apartamentos.repositories.IPropiedadRepository;

@Service
public class PropiedadService {
  @Autowired private IPropiedadRepository repo;

  public List<PropiedadModel> getAll(){ return repo.findAll(); }
  public Optional<PropiedadModel> getById(Long id){ return repo.findById(id); }
  public PropiedadModel create(PropiedadModel p){ return repo.save(p); }
  public PropiedadModel update(Long id, PropiedadModel in){
    PropiedadModel p = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
    p.setPropietario(in.getPropietario()); p.setTipo(in.getTipo()); p.setTitulo(in.getTitulo());
    p.setDescripcion(in.getDescripcion()); p.setDireccion(in.getDireccion()); p.setCiudad(in.getCiudad());
    p.setCodigoPostal(in.getCodigoPostal()); p.setPais(in.getPais()); p.setLatitud(in.getLatitud());
    p.setLongitud(in.getLongitud()); p.setPrecioNoche(in.getPrecioNoche()); p.setCapacidadPersonas(in.getCapacidadPersonas());
    p.setNumeroHabitaciones(in.getNumeroHabitaciones()); p.setNumeroBanos(in.getNumeroBanos());
    p.setMetrosCuadrados(in.getMetrosCuadrados()); p.setComodidades(in.getComodidades());
    p.setNormasCasa(in.getNormasCasa()); p.setEstado(in.getEstado());
    return repo.save(p);
  }
  public void delete(Long id){ repo.deleteById(id); }

  public List<PropiedadModel> byTipo(TipoPropiedad t){ return repo.findByTipo(t); }
  public List<PropiedadModel> byEstado(EstadoPropiedad e){ return repo.findByEstado(e); }
  public List<PropiedadModel> byPropietario(Long idUsuario){ return repo.findByPropietario_IdUsuario(idUsuario); }
  public List<PropiedadModel> byCiudad(String ciudad){ return repo.findByCiudadIgnoreCase(ciudad); }
}
