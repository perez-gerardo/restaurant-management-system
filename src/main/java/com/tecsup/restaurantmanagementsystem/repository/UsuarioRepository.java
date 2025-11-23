package com.tecsup.restaurantmanagementsystem.repository;

import com.tecsup.restaurantmanagementsystem.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByNombreUsuarioAndEstado(String nombreUsuario, String estado);
}



