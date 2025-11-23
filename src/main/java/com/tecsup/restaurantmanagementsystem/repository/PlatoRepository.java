package com.tecsup.restaurantmanagementsystem.repository;

import com.tecsup.restaurantmanagementsystem.model.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {
    List<Plato> findByEstado(String estado);
    List<Plato> findByTipo(String tipo);
    List<Plato> findByTipoAndEstado(String tipo, String estado);
}



