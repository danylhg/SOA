package com.blue.apartamentos.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "propiedad_imagenes")
public class PropiedadImagenModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_imagen")
  private Long idImagen;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_propiedad", nullable = false)
  private PropiedadModel propiedad;

  @Column(name = "url_imagen", nullable = false, length = 255)
  private String urlImagen;

  @Column(name = "orden")
  private Integer orden;

  @Column(name = "es_principal")
  private Boolean esPrincipal = Boolean.FALSE;

  @Column(name = "fecha_subida", nullable = false)
  private LocalDateTime fechaSubida;

  @PrePersist
  void prePersist() { if (fechaSubida == null) fechaSubida = LocalDateTime.now(); }

 
  public Long getIdImagen() { return idImagen; }
  public void setIdImagen(Long idImagen) { this.idImagen = idImagen; }
  public PropiedadModel getPropiedad() { return propiedad; }
  public void setPropiedad(PropiedadModel propiedad) { this.propiedad = propiedad; }
  public String getUrlImagen() { return urlImagen; }
  public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
  public Integer getOrden() { return orden; }
  public void setOrden(Integer orden) { this.orden = orden; }
  public Boolean getEsPrincipal() { return esPrincipal; }
  public void setEsPrincipal(Boolean esPrincipal) { this.esPrincipal = esPrincipal; }
  public LocalDateTime getFechaSubida() { return fechaSubida; }
  public void setFechaSubida(LocalDateTime fechaSubida) { this.fechaSubida = fechaSubida; }
}
