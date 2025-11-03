package com.blue.apartamentos.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blue.apartamentos.models.ReservacionModel;
import com.blue.apartamentos.services.ReservacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservaciones")
@RequiredArgsConstructor
public class ReservacionController {

    private final ReservacionService service;

    
    @GetMapping("/disponibilidad")
    public ResponseEntity<String> disponibilidad(
            @RequestParam Long propiedadId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salida) {
        boolean libre = service.estaDisponible(propiedadId, entrada, salida);
        return ResponseEntity.ok(libre ? "DISPONIBLE" : "NO_DISPONIBLE");
    }


    @PostMapping
    public ReservacionModel crear(@RequestBody ReservacionModel body){
        return service.crear(body);
    }

    
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelar(@PathVariable Long id){
        return service.cancelarPorId(id)
            ? ResponseEntity.ok("Reservación " + id + " CANCELADA")
            : ResponseEntity.notFound().build();
    }

    
    @GetMapping("/usuario/{idUsuario}")
    public List<ReservacionModel> porUsuario(@PathVariable Long idUsuario){
        return service.porUsuario(idUsuario);
    }

    
    @GetMapping("/apartamento/{idPropiedad}")
    public List<ReservacionModel> porPropiedad(@PathVariable Long idPropiedad){
        return service.porPropiedad(idPropiedad);
    }

   
    @GetMapping
    public List<ReservacionModel> todas(){ return service.todas(); }

   
    @PutMapping("/{id}")
    public ResponseEntity<ReservacionModel> actualizar(@PathVariable Long id, @RequestBody ReservacionModel body){
        var r = service.actualizar(id, body);
        return (r == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionModel> porId(@PathVariable Long id){
        var r = service.porId(id);
        return (r == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(r);
    }

  
    @GetMapping("/fecha")
    public List<ReservacionModel> porRango(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta){
        return service.porRango(desde, hasta);
    }

    @GetMapping("/estado/{estado}")
    public List<ReservacionModel> porEstado(@PathVariable String estado){
        return service.porEstado(estado);
    }

  
    @GetMapping("/usuario/{idUsuario}/estado/{estado}")
    public List<ReservacionModel> porUsuarioEstado(@PathVariable Long idUsuario, @PathVariable String estado){
        return service.porUsuarioEstado(idUsuario, estado);
    }

    @GetMapping("/apartamento/{idPropiedad}/estado/{estado}")
    public List<ReservacionModel> porPropEstado(@PathVariable Long idPropiedad, @PathVariable String estado){
        return service.porPropEstado(idPropiedad, estado);
    }

    
    @GetMapping("/usuario/{idUsuario}/rango")
    public List<ReservacionModel> porUsuarioRango(@PathVariable Long idUsuario,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta){
        return service.porUsuarioRango(idUsuario, desde, hasta);
    }

    @GetMapping("/apartamento/{idPropiedad}/rango")
    public List<ReservacionModel> porPropRango(@PathVariable Long idPropiedad,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta){
        return service.porPropRango(idPropiedad, desde, hasta);
    }

 
    @PutMapping("/cancelar/usuario/{idUsuario}")
    public ResponseEntity<String> cancelarUsuario(@PathVariable Long idUsuario){
        int n = service.cancelarActivasUsuario(idUsuario);
        return ResponseEntity.ok("Se cancelaron " + n + (n==1 ? " reservación" : " reservaciones") + " del usuario " + idUsuario);
    }

  
    @PutMapping("/cancelar/apartamento/{idPropiedad}")
    public ResponseEntity<String> cancelarProp(@PathVariable Long idPropiedad){
        int n = service.cancelarActivasProp(idPropiedad);
        return ResponseEntity.ok("Se cancelaron " + n + (n==1 ? " reservación" : " reservaciones") + " del apartamento " + idPropiedad);
    }

   
    @PutMapping("/cancelar/fecha")
    public ResponseEntity<String> cancelarRango(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta){
        int n = service.cancelarActivasRango(desde, hasta);
        return ResponseEntity.ok("Se cancelaron " + n + (n==1 ? " reservación" : " reservaciones") +
                " entre " + desde + " y " + hasta);
    }
}
