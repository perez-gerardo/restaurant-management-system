package com.tecsup.restaurantmanagementsystem.repository;

import com.tecsup.restaurantmanagementsystem.model.Bitacora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BitacoraRepository extends JpaRepository<Bitacora, Long> {
    List<Bitacora> findByUsuarioIdUsuario(Long idUsuario);
    List<Bitacora> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Bitacora> findByUsuarioIdUsuarioAndFechaHoraBetween(Long idUsuario, LocalDateTime inicio, LocalDateTime fin);
}



