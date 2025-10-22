package com.blue.apartamentos.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "propiedades")
public class PropiedadModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_propiedad")
  private Long idPropiedad;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "id_propietario", nullable = false)
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private ClienteModel propietario;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo", nullable = false, length = 30)
  private TipoPropiedad tipo;

  @Column(name = "titulo", nullable = false, length = 120)
  private String titulo;

  @Lob
  @Column(name = "descripcion", nullable = false)
  private String descripcion;

  @Lob
  @Column(name = "direccion", nullable = false)
  private String direccion;

  @Column(name = "ciudad", length = 60)
  private String ciudad;

  @Column(name = "codigo_postal", length = 20)
  private String codigoPostal;

  @Column(name = "pais", length = 60)
  private String pais;

  @Column(name = "latitud", precision = 10, scale = 6)
  private BigDecimal latitud;

  @Column(name = "longitud", precision = 10, scale = 6)
  private BigDecimal longitud;

  @Column(name = "precio_noche", precision = 12, scale = 2)
  private BigDecimal precioNoche;

  @Column(name = "capacidad_personas")
  private Integer capacidadPersonas;

  @Column(name = "numero_habitaciones")
  private Integer numeroHabitaciones;

  @Column(name = "numero_banos")
  private Integer numeroBanos;

  @Column(name = "metros_cuadrados")
  private Integer metrosCuadrados;

  @Lob
  @Column(name = "comodidades")
  private String comodidades;

  @Lob
  @Column(name = "normas_casa")
  private String normasCasa;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado", nullable = false, length = 20)
  private EstadoPropiedad estado;

  @Column(name = "fecha_creacion", nullable = false)
  private LocalDateTime fechaCreacion;

  @Column(name = "fecha_actualizacion", nullable = false)
  private LocalDateTime fechaActualizacion;

  @PrePersist
  void prePersist() {
    if (fechaCreacion == null) fechaCreacion = LocalDateTime.now();
    fechaActualizacion = LocalDateTime.now();
    if (estado == null) estado = EstadoPropiedad.DISPONIBLE;
  }

  @PreUpdate
  void preUpdate() { fechaActualizacion = LocalDateTime.now(); }

  public enum TipoPropiedad { CASA, DEPARTAMENTO, CABAÑA, ESTUDIO, LOFT, OTRO }
  public enum EstadoPropiedad { DISPONIBLE, NO_DISPONIBLE, MANTENIMIENTO }

  public Long getIdPropiedad() { return idPropiedad; }
  public void setIdPropiedad(Long idPropiedad) { this.idPropiedad = idPropiedad; }
  public ClienteModel getPropietario() { return propietario; }
  public void setPropietario(ClienteModel propietario) { this.propietario = propietario; }
  public TipoPropiedad getTipo() { return tipo; }
  public void setTipo(TipoPropiedad tipo) { this.tipo = tipo; }
  public String getTitulo() { return titulo; }
  public void setTitulo(String titulo) { this.titulo = titulo; }
  public String getDescripcion() { return descripcion; }
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
  public String getDireccion() { return direccion; }
  public void setDireccion(String direccion) { this.direccion = direccion; }
  public String getCiudad() { return ciudad; }
  public void setCiudad(String ciudad) { this.ciudad = ciudad; }
  public String getCodigoPostal() { return codigoPostal; }
  public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
  public String getPais() { return pais; }
  public void setPais(String pais) { this.pais = pais; }
  public BigDecimal getLatitud() { return latitud; }
  public void setLatitud(BigDecimal latitud) { this.latitud = latitud; }
  public BigDecimal getLongitud() { return longitud; }
  public void setLongitud(BigDecimal longitud) { this.longitud = longitud; }
  public BigDecimal getPrecioNoche() { return precioNoche; }
  public void setPrecioNoche(BigDecimal precioNoche) { this.precioNoche = precioNoche; }
  public Integer getCapacidadPersonas() { return capacidadPersonas; }
  public void setCapacidadPersonas(Integer capacidadPersonas) { this.capacidadPersonas = capacidadPersonas; }
  public Integer getNumeroHabitaciones() { return numeroHabitaciones; }
  public void setNumeroHabitaciones(Integer numeroHabitaciones) { this.numeroHabitaciones = numeroHabitaciones; }
  public Integer getNumeroBanos() { return numeroBanos; }
  public void setNumeroBanos(Integer numeroBanos) { this.numeroBanos = numeroBanos; }
  public Integer getMetrosCuadrados() { return metrosCuadrados; }
  public void setMetrosCuadrados(Integer metrosCuadrados) { this.metrosCuadrados = metrosCuadrados; }
  public String getComodidades() { return comodidades; }
  public void setComodidades(String comodidades) { this.comodidades = comodidades; }
  public String getNormasCasa() { return normasCasa; }
  public void setNormasCasa(String normasCasa) { this.normasCasa = normasCasa; }
  public EstadoPropiedad getEstado() { return estado; }
  public void setEstado(EstadoPropiedad estado) { this.estado = estado; }
  public LocalDateTime getFechaCreacion() { return fechaCreacion; }
  public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
  public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
  public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}

