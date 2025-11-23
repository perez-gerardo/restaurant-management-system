package com.tecsup.restaurantmanagementsystem.repository;

import com.tecsup.restaurantmanagementsystem.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByMesaIdMesa(Long idMesa);
    List<Pedido> findByClienteIdCliente(Long idCliente);
    List<Pedido> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}



