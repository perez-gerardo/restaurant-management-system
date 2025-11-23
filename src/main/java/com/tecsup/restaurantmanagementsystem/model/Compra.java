package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compra")
public class Compra {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompra")
    private Long idCompra;
    
    @NotNull(message = "El proveedor es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    @Column(nullable = false)
    private LocalDateTime fechaCompra = LocalDateTime.now();
    
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> detalleCompras;
    
    // Constructores
    public Compra() {
    }
    
    public Compra(Proveedor proveedor, LocalDateTime fechaCompra, BigDecimal total) {
        this.proveedor = proveedor;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }
    
    // Getters y Setters
    public Long getIdCompra() {
        return idCompra;
    }
    
    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }
    
    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    
    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }
    
    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    public List<DetalleCompra> getDetalleCompras() {
        return detalleCompras;
    }
    
    public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }
}


