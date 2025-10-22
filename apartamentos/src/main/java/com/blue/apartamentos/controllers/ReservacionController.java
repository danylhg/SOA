package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.models.ReservacionModel.EstadoReservacion;
import com.blue.apartamentos.services.ReservacionService;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservacionController {
  private final ReservacionService service;
  public ReservacionController(ReservacionService service){ this.service = service; }

  @GetMapping public List<ReservacionModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<ReservacionModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @PostMapping public ReservacionModel create(@RequestBody ReservacionModel r){ return service.create(r); }
  @PutMapping("/{id}") public ReservacionModel update(@PathVariable Long id, @RequestBody ReservacionModel r){ return service.update(id, r); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }

  @GetMapping("/cliente/{idUsuario}") public List<ReservacionModel> byCliente(@PathVariable Long idUsuario){ return service.byCliente(idUsuario); }
  @GetMapping("/propiedad/{idPropiedad}") public List<ReservacionModel> byPropiedad(@PathVariable Long idPropiedad){ return service.byPropiedad(idPropiedad); }
  @GetMapping("/estado/{estado}") public List<ReservacionModel> byEstado(@PathVariable String estado){
    return service.byEstado(EstadoReservacion.valueOf(estado.toUpperCase()));
  }
}
