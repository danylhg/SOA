package com.blue.apartamentos.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.repositories.IClienteRepository;

@Service
public class ClienteService {
  @Autowired private IClienteRepository repo;
  public List<ClienteModel> getAll(){ return repo.findAll(); }
  public Optional<ClienteModel> getById(Long id){ return repo.findById(id); }
  public ClienteModel create(ClienteModel c){ return repo.save(c); }
  public ClienteModel update(Long id, ClienteModel in){
    ClienteModel c = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    c.setTipo(in.getTipo()); c.setNombre(in.getNombre()); c.setApellido(in.getApellido());
    c.setEmail(in.getEmail()); c.setTelefono(in.getTelefono()); c.setFechaNacimiento(in.getFechaNacimiento());
    c.setDocumentoIdentidad(in.getDocumentoIdentidad()); c.setDireccion(in.getDireccion());
    c.setEstado(in.getEstado()); c.setContrasenaHash(in.getContrasenaHash()); c.setUltimoLogin(in.getUltimoLogin());
    return repo.save(c);
  }
  public void delete(Long id){ repo.deleteById(id); }
}
