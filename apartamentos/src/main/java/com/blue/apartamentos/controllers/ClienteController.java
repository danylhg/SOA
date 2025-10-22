package com.blue.apartamentos.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.blue.apartamentos.models.ClienteModel;
import com.blue.apartamentos.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
  private final ClienteService service;
  public ClienteController(ClienteService service){ this.service = service; }

  @GetMapping public List<ClienteModel> all(){ return service.getAll(); }
  @GetMapping("/{id}") public ResponseEntity<ClienteModel> byId(@PathVariable Long id){
    return service.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  @PostMapping public ClienteModel create(@RequestBody ClienteModel c){ return service.create(c); }
  @PutMapping("/{id}") public ClienteModel update(@PathVariable Long id, @RequestBody ClienteModel c){ return service.update(id,c); }
  @DeleteMapping("/{id}") public ResponseEntity<Void> delete(@PathVariable Long id){ service.delete(id); return ResponseEntity.noContent().build(); }
}
