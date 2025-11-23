package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "plato_insumo")
public class PlatoInsumo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlatoInsumo")
    private Long idPlatoInsumo;
    
    @NotNull(message = "El plato es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPlato", nullable = false)
    private Plato plato;
    
    @NotNull(message = "El insumo es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idInsumo", nullable = false)
    private Insumo insumo;
    
    @NotNull(message = "La cantidad usada es obligatoria")
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal cantidadUsada;
    
    // Constructores
    public PlatoInsumo() {
    }
    
    public PlatoInsumo(Plato plato, Insumo insumo, BigDecimal cantidadUsada) {
        this.plato = plato;
        this.insumo = insumo;
        this.cantidadUsada = cantidadUsada;
    }
    
    // Getters y Setters
    public Long getIdPlatoInsumo() {
        return idPlatoInsumo;
    }
    
    public void setIdPlatoInsumo(Long idPlatoInsumo) {
        this.idPlatoInsumo = idPlatoInsumo;
    }
    
    public Plato getPlato() {
        return plato;
    }
    
    public void setPlato(Plato plato) {
        this.plato = plato;
    }
    
    public Insumo getInsumo() {
        return insumo;
    }
    
    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }
    
    public BigDecimal getCantidadUsada() {
        return cantidadUsada;
    }
    
    public void setCantidadUsada(BigDecimal cantidadUsada) {
        this.cantidadUsada = cantidadUsada;
    }
}

