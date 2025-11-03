package com.blue.apartamentos.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class ClienteModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_usuario")
  private Long idUsuario;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false, length = 20)
  private TipoUsuario tipo; 

  @Column(name = "nombre", nullable = false, length = 50)
  private String nombre;

  @Column(name = "apellido", nullable = false, length = 50)
  private String apellido;

  @Column(name = "email", nullable = false, unique = true, length = 120)
  private String email;

  @Column(name = "telefono", length = 30)
  private String telefono;

  @Column(name = "fecha_nacimiento")
  private LocalDate fechaNacimiento;

  @Column(name = "documento_identidad", length = 40)
  private String documentoIdentidad;

  @Lob
  @Column(name = "direccion")
  private String direccion;

  @Column(name = "fecha_registro", nullable = false)
  private LocalDateTime fechaRegistro;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado", nullable = false, length = 20)
  private EstadoUsuario estado;

  @Column(name = "contrasena_hash", nullable = false, length = 255)
  private String contrasenaHash;

  @Column(name = "ultimo_login")
  private LocalDateTime ultimoLogin;

  @PrePersist
  void prePersist() {
    if (fechaRegistro == null) fechaRegistro = LocalDateTime.now();
    if (estado == null) estado = EstadoUsuario.ACTIVO;
  }

  public enum TipoUsuario { PROPIETARIO, CLIENTE, EMPRESA }
  public enum EstadoUsuario { ACTIVO, INACTIVO, SUSPENDIDO }

  public Long getIdUsuario() { return idUsuario; }
  public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
  public TipoUsuario getTipo() { return tipo; }
  public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }
  public String getApellido() { return apellido; }
  public void setApellido(String apellido) { this.apellido = apellido; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public String getTelefono() { return telefono; }
  public void setTelefono(String telefono) { this.telefono = telefono; }
  public LocalDate getFechaNacimiento() { return fechaNacimiento; }
  public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
  public String getDocumentoIdentidad() { return documentoIdentidad; }
  public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }
  public String getDireccion() { return direccion; }
  public void setDireccion(String direccion) { this.direccion = direccion; }
  public LocalDateTime getFechaRegistro() { return fechaRegistro; }
  public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
  public EstadoUsuario getEstado() { return estado; }
  public void setEstado(EstadoUsuario estado) { this.estado = estado; }
  public String getContrasenaHash() { return contrasenaHash; }
  public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }
  public LocalDateTime getUltimoLogin() { return ultimoLogin; }
  public void setUltimoLogin(LocalDateTime ultimoLogin) { this.ultimoLogin = ultimoLogin; }
}
