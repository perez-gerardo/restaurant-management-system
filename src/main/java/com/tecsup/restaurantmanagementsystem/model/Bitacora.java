package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "bitacora")
public class Bitacora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBitacora")
    private Long idBitacora;
    
    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    @NotBlank(message = "La acción es obligatoria")
    @Column(nullable = false, length = 500)
    private String accion;
    
    @Column(nullable = false)
    private LocalDateTime fechaHora = LocalDateTime.now();
    
    // Constructores
    public Bitacora() {
    }
    
    public Bitacora(Usuario usuario, String accion, LocalDateTime fechaHora) {
        this.usuario = usuario;
        this.accion = accion;
        this.fechaHora = fechaHora;
    }
    
    // Getters y Setters
    public Long getIdBitacora() {
        return idBitacora;
    }
    
    public void setIdBitacora(Long idBitacora) {
        this.idBitacora = idBitacora;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String getAccion() {
        return accion;
    }
    
    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
}


