package com.example.ejercicio.service;

import com.example.ejercicio.model.Usuario;
import com.example.ejercicio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> getAll() {
        return repo.findAll();
    }

    public Usuario getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Usuario create(Usuario usuario) {
        return repo.save(usuario);
    }

    public Usuario update(Long id, Usuario datos) {
        Usuario u = repo.findById(id).orElse(null);
        if (u == null) return null;

        u.setNombres(datos.getNombres());
        u.setApellidos(datos.getApellidos());
        u.setEmail(datos.getEmail());
        u.setTelefono(datos.getTelefono());
        u.setDireccion(datos.getDireccion());
        u.setCiudad(datos.getCiudad());
        u.setPais(datos.getPais());
        u.setFechaNacimiento(datos.getFechaNacimiento());
        u.setGenero(datos.getGenero());
        u.setAlturaCm(datos.getAlturaCm());
        u.setPesoKg(datos.getPesoKg());
        u.setNivelActividad(datos.getNivelActividad());
        u.setMetaFitness(datos.getMetaFitness());
        u.setEstado(datos.getEstado());

        return repo.save(u);
    }

    public boolean delete(Long id) {
        Usuario u = repo.findById(id).orElse(null);
        if (u == null) return false;

        u.setEstado("INACTIVO");
        repo.save(u);
        return true;
    }
}
