package com.tecsup.restaurantmanagementsystem.config;

import com.tecsup.restaurantmanagementsystem.model.Usuario;
import com.tecsup.restaurantmanagementsystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Crear usuarios iniciales si no existen
        if (usuarioRepository.count() == 0) {
            crearUsuario("admin", "admin123", "ADMIN", "activo");
            crearUsuario("mozo", "mozo123", "MOZO", "activo");
            crearUsuario("cocinero", "cocinero123", "COCINERO", "activo");
            crearUsuario("cajero", "cajero123", "CAJERO", "activo");
        }
    }
    
    private void crearUsuario(String nombreUsuario, String contraseña, String rol, String estado) {
        if (!usuarioRepository.existsByNombreUsuario(nombreUsuario)) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContraseña(passwordEncoder.encode(contraseña));
            usuario.setRol(rol);
            usuario.setEstado(estado);
            usuarioRepository.save(usuario);
        }
    }
}



