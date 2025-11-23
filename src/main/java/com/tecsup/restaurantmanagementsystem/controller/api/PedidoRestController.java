package com.tecsup.restaurantmanagementsystem.controller.api;

import com.tecsup.restaurantmanagementsystem.model.Pedido;
import com.tecsup.restaurantmanagementsystem.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoRestController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Pedido>> getPedidosByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pedidoService.findByEstado(estado));
    }
    
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@Valid @RequestBody Pedido pedido) {
        Pedido savedPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPedido);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        Pedido updatedPedido = pedidoService.update(id, pedido);
        return ResponseEntity.ok(updatedPedido);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



