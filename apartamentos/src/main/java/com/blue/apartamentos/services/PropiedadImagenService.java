package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.PropiedadImagenModel;
import com.blue.apartamentos.repositories.IPropiedadImagenRepository;

@Service
public class PropiedadImagenService {
  @Autowired private IPropiedadImagenRepository repo;

  public List<PropiedadImagenModel> byPropiedad(Long idPropiedad){
    return repo.findByPropiedad_IdPropiedadOrderByOrdenAsc(idPropiedad);
  }
  public List<PropiedadImagenModel> getAll(){ return repo.findAll(); }
  public Optional<PropiedadImagenModel> getById(Long id){ return repo.findById(id); }
  public PropiedadImagenModel create(PropiedadImagenModel img){ return repo.save(img); }
  public PropiedadImagenModel update(Long id, PropiedadImagenModel in){
    PropiedadImagenModel i = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Imagen no encontrada"));
    i.setPropiedad(in.getPropiedad()); i.setUrlImagen(in.getUrlImagen());
    i.setOrden(in.getOrden()); i.setEsPrincipal(in.getEsPrincipal()); i.setFechaSubida(in.getFechaSubida());
    return repo.save(i);
  }
  public void delete(Long id){ repo.deleteById(id); }
}
