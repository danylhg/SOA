package com.blue.apartamentos.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "resenas")
public class ResenaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_resena")
  private Long idResena;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_reservacion", nullable = false)
  private ReservacionModel reservacion;

  @Column(name = "calificacion_limpieza")
  private Integer calificacionLimpieza;

  @Column(name = "calificacion_ubicacion")
  private Integer calificacionUbicacion;

  @Column(name = "calificacion_comunicacion")
  private Integer calificacionComunicacion;

  @Column(name = "calificacion_general")
  private Integer calificacionGeneral;

  @Lob
  @Column(name = "comentario")
  private String comentario;

  @Column(name = "fecha_resena")
  private LocalDateTime fechaResena;

  @Lob
  @Column(name = "respuesta_propietario")
  private String respuestaPropietario;

  @Column(name = "fecha_respuesta")
  private LocalDateTime fechaRespuesta;

  
  public Long getIdResena() { return idResena; }
  public void setIdResena(Long idResena) { this.idResena = idResena; }
  public ReservacionModel getReservacion() { return reservacion; }
  public void setReservacion(ReservacionModel reservacion) { this.reservacion = reservacion; }
  public Integer getCalificacionLimpieza() { return calificacionLimpieza; }
  public void setCalificacionLimpieza(Integer calificacionLimpieza) { this.calificacionLimpieza = calificacionLimpieza; }
  public Integer getCalificacionUbicacion() { return calificacionUbicacion; }
  public void setCalificacionUbicacion(Integer calificacionUbicacion) { this.calificacionUbicacion = calificacionUbicacion; }
  public Integer getCalificacionComunicacion() { return calificacionComunicacion; }
  public void setCalificacionComunicacion(Integer calificacionComunicacion) { this.calificacionComunicacion = calificacionComunicacion; }
  public Integer getCalificacionGeneral() { return calificacionGeneral; }
  public void setCalificacionGeneral(Integer calificacionGeneral) { this.calificacionGeneral = calificacionGeneral; }
  public String getComentario() { return comentario; }
  public void setComentario(String comentario) { this.comentario = comentario; }
  public LocalDateTime getFechaResena() { return fechaResena; }
  public void setFechaResena(LocalDateTime fechaResena) { this.fechaResena = fechaResena; }
  public String getRespuestaPropietario() { return respuestaPropietario; }
  public void setRespuestaPropietario(String respuestaPropietario) { this.respuestaPropietario = respuestaPropietario; }
  public LocalDateTime getFechaRespuesta() { return fechaRespuesta; }
  public void setFechaRespuesta(LocalDateTime fechaRespuesta) { this.fechaRespuesta = fechaRespuesta; }
}
