package com.tecsup.restaurantmanagementsystem.repository;

import com.tecsup.restaurantmanagementsystem.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByEstado(String estado);
    
    @Query("SELECT i FROM Insumo i WHERE i.stock <= i.stockMinimo AND i.estado = 'activo'")
    List<Insumo> findInsumosConStockBajo();
}



