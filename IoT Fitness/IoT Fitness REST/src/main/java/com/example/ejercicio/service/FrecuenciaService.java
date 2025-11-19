package com.example.ejercicio.service;

import com.example.ejercicio.model.Frecuencia;
import com.example.ejercicio.repository.FrecuenciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrecuenciaService {

    private final FrecuenciaRepository repo;

    public FrecuenciaService(FrecuenciaRepository repo) {
        this.repo = repo;
    }

    public Frecuencia crear(Frecuencia f) {
        return repo.save(f);
    }

    public List<Frecuencia> listar() {
        return repo.findAll();
    }

    public List<Frecuencia> listarPorUsuario(Long userId) {
        return repo.findByUserId(userId);
    }
}
