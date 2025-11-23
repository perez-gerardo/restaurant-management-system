package com.tecsup.restaurantmanagementsystem.service;

import com.tecsup.restaurantmanagementsystem.model.Pedido;
import com.tecsup.restaurantmanagementsystem.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }
    
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    
    public Pedido update(Long id, Pedido pedidoActualizado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        pedido.setCliente(pedidoActualizado.getCliente());
        pedido.setMesa(pedidoActualizado.getMesa());
        pedido.setEstado(pedidoActualizado.getEstado());
        
        return pedidoRepository.save(pedido);
    }
    
    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }
    
    public List<Pedido> findByEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }
}



