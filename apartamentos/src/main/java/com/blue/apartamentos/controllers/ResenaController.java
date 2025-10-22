package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.ResenaModel;
import com.blue.apartamentos.services.ResenaService;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {
  private final ResenaService service;
  public ResenaController(ResenaService service){ this.service = service; }

  @GetMapping public List<ResenaModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<ResenaModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @GetMapping("/reservacion/{idReservacion}") public List<ResenaModel> byReservacion(@PathVariable Long idReservacion){
    return service.byReservacion(idReservacion);
  }
  @PostMapping public ResenaModel create(@RequestBody ResenaModel r){ return service.create(r); }
  @PutMapping("/{id}") public ResenaModel update(@PathVariable Long id, @RequestBody ResenaModel r){ return service.update(id, r); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
