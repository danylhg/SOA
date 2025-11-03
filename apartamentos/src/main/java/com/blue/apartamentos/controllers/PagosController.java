package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.PagoModel;
import com.blue.apartamentos.services.PagosService;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {
  private final PagosService service;
  public PagosController(PagosService service){ this.service = service; }

  @GetMapping public List<PagoModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<PagoModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @GetMapping("/reservacion/{idReservacion}") public List<PagoModel> byReservacion(@PathVariable Long idReservacion){
    return service.byReservacion(idReservacion);
  }
  @PostMapping public PagoModel create(@RequestBody PagoModel p){ return service.create(p); }
  @PutMapping("/{id}") public PagoModel update(@PathVariable Long id, @RequestBody PagoModel p){ return service.update(id,p); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}

