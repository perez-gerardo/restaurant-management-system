package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "insumo")
public class Insumo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInsumo")
    private Long idInsumo;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @NotBlank(message = "La unidad de medida es obligatoria")
    @Column(nullable = false, length = 20)
    private String unidadMedida; // kg, litros, unidades, etc.
    
    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;
    
    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    @Column(nullable = false)
    private Integer stockMinimo;
    
    @NotNull(message = "El precio de compra es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio de compra debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;
    
    @Column(nullable = false, length = 20)
    private String estado = "activo"; // activo/inactivo
    
    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlatoInsumo> platoInsumos;
    
    @OneToMany(mappedBy = "insumo", cascade = CascadeType.ALL)
    private List<DetalleCompra> detalleCompras;
    
    // Constructores
    public Insumo() {
    }
    
    public Insumo(String nombre, String unidadMedida, Integer stock, Integer stockMinimo, BigDecimal precioCompra, String estado) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.precioCompra = precioCompra;
        this.estado = estado;
    }
    
    // Getters y Setters
    public Long getIdInsumo() {
        return idInsumo;
    }
    
    public void setIdInsumo(Long idInsumo) {
        this.idInsumo = idInsumo;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getUnidadMedida() {
        return unidadMedida;
    }
    
    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    
    public Integer getStock() {
        return stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
    public Integer getStockMinimo() {
        return stockMinimo;
    }
    
    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }
    
    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<PlatoInsumo> getPlatoInsumos() {
        return platoInsumos;
    }
    
    public void setPlatoInsumos(List<PlatoInsumo> platoInsumos) {
        this.platoInsumos = platoInsumos;
    }
    
    public List<DetalleCompra> getDetalleCompras() {
        return detalleCompras;
    }
    
    public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }
    
    public boolean isStockBajo() {
        return stock <= stockMinimo;
    }
}


