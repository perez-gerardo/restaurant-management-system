package com.tecsup.restaurantmanagementsystem.service;

import com.tecsup.restaurantmanagementsystem.model.Factura;
import com.tecsup.restaurantmanagementsystem.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;
    
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }
    
    public Optional<Factura> findById(Long id) {
        return facturaRepository.findById(id);
    }
    
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }
    
    public Factura update(Long id, Factura facturaActualizada) {
        Factura factura = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        
        factura.setMetodoPago(facturaActualizada.getMetodoPago());
        factura.setEstado(facturaActualizada.getEstado());
        
        return facturaRepository.save(factura);
    }
    
    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }
    
    public List<Factura> findByEstado(String estado) {
        return facturaRepository.findByEstado(estado);
    }
}



