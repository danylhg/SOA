package com.blue.apartamentos.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservaciones")
@Getter @Setter @NoArgsConstructor
public class ReservacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservacion")
    private Long idReservacion;

    @Column(name = "id_propiedad", nullable = false)
    private Long idPropiedad;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "numero_huespedes")
    private Integer numeroHuespedes;

    @Column(name = "precio_total", precision = 12, scale = 2)
    private BigDecimal precioTotal;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado; 

    @Column(name = "fecha_reservacion", nullable = false)
    private LocalDateTime fechaReservacion;

    @Lob
    @Column(name = "notas")
    private String notas;

    @Column(name = "codigo_reserva", length = 50)
    private String codigoReserva;

    @Column(name = "fecha_checkin")
    private LocalDateTime fechaCheckin;

    @Column(name = "fecha_checkout")
    private LocalDateTime fechaCheckout;

    @PrePersist
    void prePersist() {
        if (fechaReservacion == null) fechaReservacion = LocalDateTime.now();
        if (estado == null) estado = Estado.PENDIENTE.name();
    }

    public enum Estado { PENDIENTE, CONFIRMADA, CANCELADA }
}
