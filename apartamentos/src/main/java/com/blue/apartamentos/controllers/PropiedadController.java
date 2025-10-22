package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.PropiedadModel;
import com.blue.apartamentos.models.PropiedadModel.EstadoPropiedad;
import com.blue.apartamentos.models.PropiedadModel.TipoPropiedad;
import com.blue.apartamentos.services.PropiedadService;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {
  private final PropiedadService service;
  public PropiedadController(PropiedadService service){ this.service = service; }

  @GetMapping public List<PropiedadModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<PropiedadModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @PostMapping public PropiedadModel create(@RequestBody PropiedadModel p){ return service.create(p); }
  @PutMapping("/{id}") public PropiedadModel update(@PathVariable Long id, @RequestBody PropiedadModel p){ return service.update(id,p); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }

  
  @GetMapping("/tipo/{tipo}") public List<PropiedadModel> byTipo(@PathVariable String tipo){
    return service.byTipo(TipoPropiedad.valueOf(tipo.toUpperCase()));
  }
  @GetMapping("/estado/{estado}") public List<PropiedadModel> byEstado(@PathVariable String estado){
    return service.byEstado(EstadoPropiedad.valueOf(estado.toUpperCase()));
  }
  @GetMapping("/propietario/{idUsuario}") public List<PropiedadModel> byProp(@PathVariable Long idUsuario){
    return service.byPropietario(idUsuario);
  }
  @GetMapping("/ciudad/{ciudad}") public List<PropiedadModel> byCiudad(@PathVariable String ciudad){
    return service.byCiudad(ciudad);
  }
}
