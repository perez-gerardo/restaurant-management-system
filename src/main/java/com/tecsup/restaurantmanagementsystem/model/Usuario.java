package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(unique = true, nullable = false, length = 50)
    private String nombreUsuario;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Column(nullable = false, length = 255)
    private String contraseña;
    
    @NotBlank(message = "El rol es obligatorio")
    @Column(nullable = false, length = 20)
    private String rol; // ADMIN, MOZO, COCINERO, CAJERO
    
    @Column(nullable = false, length = 20)
    private String estado = "activo"; // activo/inactivo
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Bitacora> bitacoras;
    
    // Constructores
    public Usuario() {
    }
    
    public Usuario(String nombreUsuario, String contraseña, String rol, String estado) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.estado = estado;
    }
    
    // Getters y Setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public List<Bitacora> getBitacoras() {
        return bitacoras;
    }
    
    public void setBitacoras(List<Bitacora> bitacoras) {
        this.bitacoras = bitacoras;
    }
}


