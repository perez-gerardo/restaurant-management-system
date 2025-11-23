package com.tecsup.restaurantmanagementsystem.repository;

import com.tecsup.restaurantmanagementsystem.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByEstado(String estado);
    List<Factura> findByPedidoIdPedido(Long idPedido);
    List<Factura> findByFechaEmisionBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Factura> findByMetodoPago(String metodoPago);
}



