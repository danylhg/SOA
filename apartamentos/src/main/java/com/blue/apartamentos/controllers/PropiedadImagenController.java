package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.PropiedadImagenModel;
import com.blue.apartamentos.services.PropiedadImagenService;

@RestController
@RequestMapping("/api/propiedad-imagenes")
public class PropiedadImagenController {
  private final PropiedadImagenService service;
  public PropiedadImagenController(PropiedadImagenService service){ this.service = service; }

  @GetMapping public List<PropiedadImagenModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<PropiedadImagenModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @GetMapping("/propiedad/{idPropiedad}") public List<PropiedadImagenModel> byPropiedad(@PathVariable Long idPropiedad){
    return service.byPropiedad(idPropiedad);
  }
  @PostMapping public PropiedadImagenModel create(@RequestBody PropiedadImagenModel img){ return service.create(img); }
  @PutMapping("/{id}") public PropiedadImagenModel update(@PathVariable Long id, @RequestBody PropiedadImagenModel img){ return service.update(id, img); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
