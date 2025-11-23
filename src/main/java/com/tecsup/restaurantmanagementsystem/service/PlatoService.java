package com.tecsup.restaurantmanagementsystem.service;

import com.tecsup.restaurantmanagementsystem.model.Plato;
import com.tecsup.restaurantmanagementsystem.repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlatoService {
    
    @Autowired
    private PlatoRepository platoRepository;
    
    public List<Plato> findAll() {
        return platoRepository.findAll();
    }
    
    public Optional<Plato> findById(Long id) {
        return platoRepository.findById(id);
    }
    
    public Plato save(Plato plato) {
        return platoRepository.save(plato);
    }
    
    public Plato update(Long id, Plato platoActualizado) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        
        plato.setNombre(platoActualizado.getNombre());
        plato.setTipo(platoActualizado.getTipo());
        plato.setPrecio(platoActualizado.getPrecio());
        plato.setDescripcion(platoActualizado.getDescripcion());
        plato.setEstado(platoActualizado.getEstado());
        
        return platoRepository.save(plato);
    }
    
    public void deleteById(Long id) {
        platoRepository.deleteById(id);
    }
    
    public List<Plato> findByEstado(String estado) {
        return platoRepository.findByEstado(estado);
    }
    
    public List<Plato> findByTipo(String tipo) {
        return platoRepository.findByTipo(tipo);
    }
}



