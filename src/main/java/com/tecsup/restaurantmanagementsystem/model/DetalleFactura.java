package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetalleFactura")
    private Long idDetalleFactura;
    
    @NotNull(message = "La factura es obligatoria")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFactura", nullable = false)
    private Factura factura;
    
    @NotBlank(message = "El concepto es obligatorio")
    @Column(nullable = false, length = 200)
    private String concepto;
    
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    
    // Constructores
    public DetalleFactura() {
    }
    
    public DetalleFactura(Factura factura, String concepto, BigDecimal monto) {
        this.factura = factura;
        this.concepto = concepto;
        this.monto = monto;
    }
    
    // Getters y Setters
    public Long getIdDetalleFactura() {
        return idDetalleFactura;
    }
    
    public void setIdDetalleFactura(Long idDetalleFactura) {
        this.idDetalleFactura = idDetalleFactura;
    }
    
    public Factura getFactura() {
        return factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    public String getConcepto() {
        return concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    
    public BigDecimal getMonto() {
        return monto;
    }
    
    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}


