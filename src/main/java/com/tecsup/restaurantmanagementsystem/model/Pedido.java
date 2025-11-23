package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private Long idPedido;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    
    @NotNull(message = "La mesa es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMesa", nullable = false)
    private Mesa mesa;
    
    @Column(nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();
    
    @Column(nullable = false, length = 20)
    private String estado = "pendiente"; // pendiente, en preparación, servido, cerrado
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detallePedidos;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<Factura> facturas;
    
    // Constructores
    public Pedido() {
    }
    
    public Pedido(Cliente cliente, Mesa mesa, LocalDateTime fechaHora, String estado) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }
    
    // Getters y Setters
    public Long getIdPedido() {
        return idPedido;
    }
    
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Mesa getMesa() {
        return mesa;
    }
    
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
    
    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
    
    public List<Factura> getFacturas() {
        return facturas;
    }
    
    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public Double getTotal() {
        if (detallePedidos == null || detallePedidos.isEmpty()) {
            return 0.0;
        }
        return detallePedidos.stream()
                .map(detalle -> detalle.getSubtotal().doubleValue())
                .reduce(0.0, Double::sum);
    }
}

