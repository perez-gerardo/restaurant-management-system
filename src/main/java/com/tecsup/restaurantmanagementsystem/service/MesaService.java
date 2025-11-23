package com.tecsup.restaurantmanagementsystem.service;

import com.tecsup.restaurantmanagementsystem.model.Mesa;
import com.tecsup.restaurantmanagementsystem.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MesaService {
    
    @Autowired
    private MesaRepository mesaRepository;
    
    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }
    
    public Optional<Mesa> findById(Long id) {
        return mesaRepository.findById(id);
    }
    
    public Mesa save(Mesa mesa) {
        return mesaRepository.save(mesa);
    }
    
    public Mesa update(Long id, Mesa mesaActualizada) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        
        mesa.setNumero(mesaActualizada.getNumero());
        mesa.setCapacidad(mesaActualizada.getCapacidad());
        mesa.setEstado(mesaActualizada.getEstado());
        
        return mesaRepository.save(mesa);
    }
    
    public void deleteById(Long id) {
        mesaRepository.deleteById(id);
    }
    
    public List<Mesa> findByEstado(String estado) {
        return mesaRepository.findByEstado(estado);
    }
    
    public boolean existsByNumero(String numero) {
        return mesaRepository.existsByNumero(numero);
    }
}



