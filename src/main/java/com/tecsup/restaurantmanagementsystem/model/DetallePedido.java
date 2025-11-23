package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetallePedido")
    private Long idDetallePedido;
    
    @NotNull(message = "El pedido es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;
    
    @NotNull(message = "El plato es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlato", nullable = false)
    private Plato plato;
    
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer cantidad;
    
    @NotNull(message = "El subtotal es obligatorio")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;
    
    // Constructores
    public DetallePedido() {
    }
    
    public DetallePedido(Pedido pedido, Plato plato, Integer cantidad, BigDecimal subtotal) {
        this.pedido = pedido;
        this.plato = plato;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }
    
    // Getters y Setters
    public Long getIdDetallePedido() {
        return idDetallePedido;
    }
    
    public void setIdDetallePedido(Long idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public Plato getPlato() {
        return plato;
    }
    
    public void setPlato(Plato plato) {
        this.plato = plato;
    }
    
    public Integer getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public void calcularSubtotal() {
        if (plato != null && cantidad != null) {
            this.subtotal = plato.getPrecio().multiply(BigDecimal.valueOf(cantidad));
        }
    }
}


