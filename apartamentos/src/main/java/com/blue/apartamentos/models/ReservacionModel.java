package com.blue.apartamentos.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "reservaciones")
public class ReservacionModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_reservacion")
  private Long idReservacion;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_propiedad", nullable = false)
  private PropiedadModel propiedad;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_cliente", nullable = false)
  private ClienteModel cliente;

  @Column(name = "fecha_entrada", nullable = false)
  private LocalDate fechaEntrada;

  @Column(name = "fecha_salida", nullable = false)
  private LocalDate fechaSalida;

  @Column(name = "numero_huespedes", nullable = false)
  private Integer numeroHuespedes;

  @Column(name = "precio_total", precision = 12, scale = 2, nullable = false)
  private BigDecimal precioTotal;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado", nullable = false, length = 20)
  private EstadoReservacion estado;

  @Column(name = "fecha_reservacion", nullable = false)
  private LocalDateTime fechaReservacion;

  @Lob
  @Column(name = "notas")
  private String notas;

  @Column(name = "codigo_reserva", length = 40)
  private String codigoReserva;

  @Column(name = "fecha_checkin")
  private LocalDateTime fechaCheckin;

  @Column(name = "fecha_checkout")
  private LocalDateTime fechaCheckout;

  @PrePersist
  void prePersist() {
    if (fechaReservacion == null) fechaReservacion = LocalDateTime.now();
    if (estado == null) estado = EstadoReservacion.PENDIENTE;
  }

  public enum EstadoReservacion { PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA }

  public Long getIdReservacion() { return idReservacion; }
  public void setIdReservacion(Long idReservacion) { this.idReservacion = idReservacion; }
  public PropiedadModel getPropiedad() { return propiedad; }
  public void setPropiedad(PropiedadModel propiedad) { this.propiedad = propiedad; }
  public ClienteModel getCliente() { return cliente; }
  public void setCliente(ClienteModel cliente) { this.cliente = cliente; }
  public LocalDate getFechaEntrada() { return fechaEntrada; }
  public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }
  public LocalDate getFechaSalida() { return fechaSalida; }
  public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
  public Integer getNumeroHuespedes() { return numeroHuespedes; }
  public void setNumeroHuespedes(Integer numeroHuespedes) { this.numeroHuespedes = numeroHuespedes; }
  public BigDecimal getPrecioTotal() { return precioTotal; }
  public void setPrecioTotal(BigDecimal precioTotal) { this.precioTotal = precioTotal; }
  public EstadoReservacion getEstado() { return estado; }
  public void setEstado(EstadoReservacion estado) { this.estado = estado; }
  public LocalDateTime getFechaReservacion() { return fechaReservacion; }
  public void setFechaReservacion(LocalDateTime fechaReservacion) { this.fechaReservacion = fechaReservacion; }
  public String getNotas() { return notas; }
  public void setNotas(String notas) { this.notas = notas; }
  public String getCodigoReserva() { return codigoReserva; }
  public void setCodigoReserva(String codigoReserva) { this.codigoReserva = codigoReserva; }
  public LocalDateTime getFechaCheckin() { return fechaCheckin; }
  public void setFechaCheckin(LocalDateTime fechaCheckin) { this.fechaCheckin = fechaCheckin; }
  public LocalDateTime getFechaCheckout() { return fechaCheckout; }
  public void setFechaCheckout(LocalDateTime fechaCheckout) { this.fechaCheckout = fechaCheckout; }
}
