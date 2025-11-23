package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "plato")
public class Plato {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlato")
    private Long idPlato;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @NotBlank(message = "El tipo es obligatorio")
    @Column(nullable = false, length = 20)
    private String tipo; // entrada, fondo, postre, bebida
    
    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Column(length = 500)
    private String descripcion;
    
    @Column(nullable = false, length = 20)
    private String estado = "activo"; // activo/inactivo
    
    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlatoInsumo> platoInsumos;
    
    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL)
    private List<DetallePedido> detallePedidos;
    
    // Constructores
    public Plato() {
    }
    
    public Plato(String nombre, String tipo, BigDecimal precio, String descripcion, String estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
    }
    
    // Getters y Setters
    public Long getIdPlato() {
        return idPlato;
    }
    
    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
    
    public List<DetallePedido> getDetallePedidos() {
        return detallePedidos;
    }
    
    public void setDetallePedidos(List<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}


