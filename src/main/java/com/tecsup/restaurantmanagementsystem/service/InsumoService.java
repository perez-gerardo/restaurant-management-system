package com.tecsup.restaurantmanagementsystem.service;

import com.tecsup.restaurantmanagementsystem.model.Insumo;
import com.tecsup.restaurantmanagementsystem.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InsumoService {
    
    @Autowired
    private InsumoRepository insumoRepository;
    
    public List<Insumo> findAll() {
        return insumoRepository.findAll();
    }
    
    public Optional<Insumo> findById(Long id) {
        return insumoRepository.findById(id);
    }
    
    public Insumo save(Insumo insumo) {
        return insumoRepository.save(insumo);
    }
    
    public Insumo update(Long id, Insumo insumoActualizado) {
        Insumo insumo = insumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        
        insumo.setNombre(insumoActualizado.getNombre());
        insumo.setUnidadMedida(insumoActualizado.getUnidadMedida());
        insumo.setStock(insumoActualizado.getStock());
        insumo.setStockMinimo(insumoActualizado.getStockMinimo());
        insumo.setPrecioCompra(insumoActualizado.getPrecioCompra());
        insumo.setEstado(insumoActualizado.getEstado());
        
        return insumoRepository.save(insumo);
    }
    
    public void deleteById(Long id) {
        insumoRepository.deleteById(id);
    }
    
    public List<Insumo> findInsumosConStockBajo() {
        return insumoRepository.findInsumosConStockBajo();
    }
}



