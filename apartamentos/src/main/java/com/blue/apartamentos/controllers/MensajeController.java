package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.MensajeModel;
import com.blue.apartamentos.services.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {
  private final MensajeService service;
  public MensajeController(MensajeService service){ this.service = service; }

  @GetMapping public List<MensajeModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<MensajeModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @GetMapping("/reservacion/{idReservacion}") public List<MensajeModel> byReservacion(@PathVariable Long idReservacion){
    return service.byReservacion(idReservacion);
  }
  @GetMapping("/conversacion")
  public List<MensajeModel> conversacion(@RequestParam Long remitente, @RequestParam Long destinatario){
    return service.conversacion(remitente, destinatario);
  }
  @PostMapping public MensajeModel create(@RequestBody MensajeModel m){ return service.create(m); }
  @PutMapping("/{id}") public MensajeModel update(@PathVariable Long id, @RequestBody MensajeModel m){ return service.update(id, m); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
