package com.example.ejercicio.controller;

import com.example.ejercicio.model.Frecuencia;
import com.example.ejercicio.service.FrecuenciaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frecuencia")
@CrossOrigin("*")
public class FrecuenciaController {

    private final FrecuenciaService service;

    public FrecuenciaController(FrecuenciaService service) {
        this.service = service;
    }

    @PostMapping
    public Frecuencia crear(@RequestBody Frecuencia f) {
        return service.crear(f);
    }

    @GetMapping
    public List<Frecuencia> listar() {
        return service.listar();
    }

    @GetMapping("/usuario/{id}")
    public List<Frecuencia> listarPorUsuario(@PathVariable Long id) {
        return service.listarPorUsuario(id);
    }
}
