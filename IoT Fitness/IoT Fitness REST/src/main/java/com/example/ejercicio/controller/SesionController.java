package com.example.ejercicio.controller;

import com.example.ejercicio.model.Sesion;
import com.example.ejercicio.service.SesionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
@CrossOrigin("*")
public class SesionController {

    private final SesionService service;

    public SesionController(SesionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sesion> listar() {
        return service.getAll();
    }

    @GetMapping("/usuario/{id}")
    public List<Sesion> listarPorUsuario(@PathVariable Long id) {
        return service.getByUser(id);
    }

    @PostMapping
    public Sesion crear(@RequestBody Sesion s) {
        return service.create(s);
    }

    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable Long id) {
        return service.delete(id);
    }
}
