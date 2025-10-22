package com.blue.apartamentos.controllers;

import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.DisponibilidadModel;
import com.blue.apartamentos.services.DisponibilidadService;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadController {
  private final DisponibilidadService service;
  public DisponibilidadController(DisponibilidadService service){ this.service = service; }

  @GetMapping public List<DisponibilidadModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<DisponibilidadModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @GetMapping("/propiedad/{idPropiedad}") public List<DisponibilidadModel> byProp(@PathVariable Long idPropiedad){
    return service.byPropiedad(idPropiedad);
  }
  @GetMapping("/propiedad/{idPropiedad}/fecha/{fecha}")
  public List<DisponibilidadModel> byPropFecha(@PathVariable Long idPropiedad,
    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha){
    return service.byPropiedadFecha(idPropiedad, fecha);
  }
  @PostMapping("/propiedad/{idPropiedad}") public DisponibilidadModel create(@PathVariable Long idPropiedad, @RequestBody DisponibilidadModel d){
    return service.create(idPropiedad, d);
  }
  @PutMapping("/{id}") public DisponibilidadModel update(@PathVariable Long id, @RequestBody DisponibilidadModel d){
    return service.update(id, d);
  }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}

