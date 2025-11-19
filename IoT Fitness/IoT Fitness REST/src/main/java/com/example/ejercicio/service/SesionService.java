package com.example.ejercicio.service;

import com.example.ejercicio.model.Sesion;
import com.example.ejercicio.repository.SesionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionService {

    private final SesionRepository repo;

    public SesionService(SesionRepository repo) {
        this.repo = repo;
    }

    public List<Sesion> getAll() {
        return repo.findAll();
    }

    public List<Sesion> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Sesion create(Sesion s) {
        return repo.save(s);
    }

    public boolean delete(Long id) {
        if(!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
