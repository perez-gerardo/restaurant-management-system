package com.tecsup.restaurantmanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor")
    private Long idProveedor;
    
    @NotBlank(message = "El RUC es obligatorio")
    @Size(min = 11, max = 11, message = "El RUC debe tener 11 dígitos")
    @Column(unique = true, nullable = false, length = 11)
    private String ruc;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Size(max = 15, message = "El teléfono debe tener máximo 15 caracteres")
    @Column(length = 15)
    private String telefono;
    
    @Email(message = "El correo debe ser válido")
    @Column(length = 100)
    private String correo;
    
    @Column(length = 200)
    private String direccion;
    
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<Compra> compras;
    
    // Constructores
    public Proveedor() {
    }
    
    public Proveedor(String ruc, String nombre, String telefono, String correo, String direccion) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }
    
    // Getters y Setters
    public Long getIdProveedor() {
        return idProveedor;
    }
    
    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }
    
    public String getRuc() {
        return ruc;
    }
    
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public List<Compra> getCompras() {
        return compras;
    }
    
    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}


