package com.blue.apartamentos.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "mensajes")
public class MensajeModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_mensaje")
  private Long idMensaje;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_remitente", nullable = false)
  private ClienteModel remitente;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_destinatario", nullable = false)
  private ClienteModel destinatario;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_reservacion", nullable = false)
  private ReservacionModel reservacion;

  @Column(name = "asunto", length = 120)
  private String asunto;

  @Lob
  @Column(name = "contenido")
  private String contenido;

  @Column(name = "leido")
  private Boolean leido = Boolean.FALSE;

  @Column(name = "fecha_envio", nullable = false)
  private LocalDateTime fechaEnvio;

  @PrePersist
  void prePersist() { if (fechaEnvio == null) fechaEnvio = LocalDateTime.now(); }


  public Long getIdMensaje() { return idMensaje; }
  public void setIdMensaje(Long idMensaje) { this.idMensaje = idMensaje; }
  public ClienteModel getRemitente() { return remitente; }
  public void setRemitente(ClienteModel remitente) { this.remitente = remitente; }
  public ClienteModel getDestinatario() { return destinatario; }
  public void setDestinatario(ClienteModel destinatario) { this.destinatario = destinatario; }
  public ReservacionModel getReservacion() { return reservacion; }
  public void setReservacion(ReservacionModel reservacion) { this.reservacion = reservacion; }
  public String getAsunto() { return asunto; }
  public void setAsunto(String asunto) { this.asunto = asunto; }
  public String getContenido() { return contenido; }
  public void setContenido(String contenido) { this.contenido = contenido; }
  public Boolean getLeido() { return leido; }
  public void setLeido(Boolean leido) { this.leido = leido; }
  public LocalDateTime getFechaEnvio() { return fechaEnvio; }
  public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
}
