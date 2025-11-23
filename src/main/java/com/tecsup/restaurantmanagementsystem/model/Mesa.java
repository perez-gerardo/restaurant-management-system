package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesa")
    private Long idMesa;
    
    @NotBlank(message = "El número de mesa es obligatorio")
    @Column(unique = true, nullable = false, length = 10)
    private String numero;
    
    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer capacidad;
    
    @Column(nullable = false, length = 20)
    private String estado = "disponible"; // disponible, ocupada, reservada, mantenimiento
    
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;
    
    // Constructores
    public Mesa() {
    }
    
    public Mesa(String numero, Integer capacidad, String estado) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
    }
    
    // Getters y Setters
    public Long getIdMesa() {
        return idMesa;
    }
    
    public void setIdMesa(Long idMesa) {
        this.idMesa = idMesa;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public Integer getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}


