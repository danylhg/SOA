package com.blue.apartamentos.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "pagos")
public class PagoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_pago")
  private Long idPago;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_reservacion", nullable = false)
  private ReservacionModel reservacion;

  @Column(name = "monto", precision = 12, scale = 2, nullable = false)
  private BigDecimal monto;

  @Enumerated(EnumType.STRING)
  @Column(name = "metodo_pago", nullable = false, length = 20)
  private MetodoPago metodoPago;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado", nullable = false, length = 20)
  private EstadoPago estado;

  @Column(name = "fecha_pago", nullable = false)
  private LocalDateTime fechaPago;

  @Column(name = "referencia_pago", length = 100)
  private String referenciaPago;

  @Lob
  @Column(name = "datos_pago")
  private String datosPago;

  @Column(name = "fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion;

  @PrePersist
  void prePersist() { if (fechaCreacion == null) fechaCreacion = LocalDateTime.now(); }

  public enum MetodoPago { TARJETA_CREDITO, TARJETA_DEBITO, EFECTIVO }
  public enum EstadoPago { PENDIENTE, COMPLETADO, FALLIDO, REEMBOLSADO }

  public Long getIdPago() { return idPago; }
  public void setIdPago(Long idPago) { this.idPago = idPago; }
  public ReservacionModel getReservacion() { return reservacion; }
  public void setReservacion(ReservacionModel reservacion) { this.reservacion = reservacion; }
  public BigDecimal getMonto() { return monto; }
  public void setMonto(BigDecimal monto) { this.monto = monto; }
  public MetodoPago getMetodoPago() { return metodoPago; }
  public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }
  public EstadoPago getEstado() { return estado; }
  public void setEstado(EstadoPago estado) { this.estado = estado; }
  public LocalDateTime getFechaPago() { return fechaPago; }
  public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }
  public String getReferenciaPago() { return referenciaPago; }
  public void setReferenciaPago(String referenciaPago) { this.referenciaPago = referenciaPago; }
  public String getDatosPago() { return datosPago; }
  public void setDatosPago(String datosPago) { this.datosPago = datosPago; }
  public LocalDateTime getFechaCreacion() { return fechaCreacion; }
  public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
