package com.blue.apartamentos.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blue.apartamentos.models.ReservacionModel;

@Repository
public interface IReservacionRepository extends JpaRepository<ReservacionModel, Long> {

   
    @Query(value = """
        SELECT COUNT(*) FROM reservaciones
         WHERE id_propiedad = :idPropiedad
           AND estado IN ('PENDIENTE','CONFIRMADA')
           AND (fecha_entrada < :salida AND fecha_salida > :entrada)
    """, nativeQuery = true)
    int contarTraslapesActivos(@Param("idPropiedad") Long idPropiedad,
                               @Param("entrada") LocalDate entrada,
                               @Param("salida") LocalDate salida);

    
    @Query(value = "SELECT * FROM reservaciones WHERE id_cliente = :idUsuario ORDER BY fecha_reservacion DESC", nativeQuery = true)
    List<ReservacionModel> findAllByUsuario(@Param("idUsuario") Long idUsuario);

    
    @Query(value = "SELECT * FROM reservaciones WHERE id_propiedad = :idPropiedad ORDER BY fecha_reservacion DESC", nativeQuery = true)
    List<ReservacionModel> findAllByPropiedad(@Param("idPropiedad") Long idPropiedad);

    
    @Query(value = """
        SELECT * FROM reservaciones
         WHERE fecha_entrada >= :desde AND fecha_salida <= :hasta
         ORDER BY fecha_entrada
    """, nativeQuery = true)
    List<ReservacionModel> findAllByRango(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

    
    @Query(value = "SELECT * FROM reservaciones WHERE estado = :estado ORDER BY fecha_reservacion DESC", nativeQuery = true)
    List<ReservacionModel> findAllByEstado(@Param("estado") String estado);

    
    @Query(value = """
        SELECT * FROM reservaciones
         WHERE id_cliente = :idUsuario AND estado = :estado
         ORDER BY fecha_reservacion DESC
    """, nativeQuery = true)
    List<ReservacionModel> findAllByUsuarioAndEstado(@Param("idUsuario") Long idUsuario,
                                                     @Param("estado") String estado);

    
    @Query(value = """
        SELECT * FROM reservaciones
         WHERE id_propiedad = :idPropiedad AND estado = :estado
         ORDER BY fecha_reservacion DESC
    """, nativeQuery = true)
    List<ReservacionModel> findAllByPropiedadAndEstado(@Param("idPropiedad") Long idPropiedad,
                                                       @Param("estado") String estado);

 
    @Query(value = """
        SELECT * FROM reservaciones
         WHERE id_cliente = :idUsuario
           AND (fecha_entrada >= :desde AND fecha_salida <= :hasta)
         ORDER BY fecha_entrada
    """, nativeQuery = true)
    List<ReservacionModel> findAllByUsuarioAndRango(@Param("idUsuario") Long idUsuario,
                                                     @Param("desde") LocalDate desde,
                                                     @Param("hasta") LocalDate hasta);

    
    @Query(value = """
        SELECT * FROM reservaciones
         WHERE id_propiedad = :idPropiedad
           AND (fecha_entrada >= :desde AND fecha_salida <= :hasta)
         ORDER BY fecha_entrada
    """, nativeQuery = true)
    List<ReservacionModel> findAllByPropiedadAndRango(@Param("idPropiedad") Long idPropiedad,
                                                       @Param("desde") LocalDate desde,
                                                       @Param("hasta") LocalDate hasta);

    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        UPDATE reservaciones
           SET estado = 'CANCELADA'
         WHERE id_cliente = :idUsuario
           AND estado IN ('PENDIENTE','CONFIRMADA')
    """, nativeQuery = true)
    int cancelarActivasPorUsuario(@Param("idUsuario") Long idUsuario);

    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        UPDATE reservaciones
           SET estado = 'CANCELADA'
         WHERE id_propiedad = :idPropiedad
           AND estado IN ('PENDIENTE','CONFIRMADA')
    """, nativeQuery = true)
    int cancelarActivasPorPropiedad(@Param("idPropiedad") Long idPropiedad);

 
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        UPDATE reservaciones
           SET estado = 'CANCELADA'
         WHERE estado IN ('PENDIENTE','CONFIRMADA')
           AND fecha_entrada >= :desde
           AND fecha_salida  <= :hasta
    """, nativeQuery = true)
    int cancelarActivasPorRango(@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}
