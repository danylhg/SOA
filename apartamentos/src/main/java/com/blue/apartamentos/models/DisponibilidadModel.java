package com.blue.apartamentos.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "disponibilidad")
public class DisponibilidadModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_disponibilidad")
  private Long idDisponibilidad;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_propiedad", nullable = false)
  private PropiedadModel propiedad;

  @Column(name = "fecha", nullable = false)
  private LocalDate fecha;

  @Column(name = "disponible", nullable = false)
  private Boolean disponible = Boolean.TRUE;

  @Column(name = "precio_especial", precision = 12, scale = 2)
  private BigDecimal precioEspecial;


  public Long getIdDisponibilidad() { return idDisponibilidad; }
  public void setIdDisponibilidad(Long idDisponibilidad) { this.idDisponibilidad = idDisponibilidad; }
  public PropiedadModel getPropiedad() { return propiedad; }
  public void setPropiedad(PropiedadModel propiedad) { this.propiedad = propiedad; }
  public LocalDate getFecha() { return fecha; }
  public void setFecha(LocalDate fecha) { this.fecha = fecha; }
  public Boolean getDisponible() { return disponible; }
  public void setDisponible(Boolean disponible) { this.disponible = disponible; }
  public BigDecimal getPrecioEspecial() { return precioEspecial; }
  public void setPrecioEspecial(BigDecimal precioEspecial) { this.precioEspecial = precioEspecial; }
}


