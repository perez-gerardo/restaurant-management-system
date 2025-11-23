package com.tecsup.restaurantmanagementsystem.controller.api;

import com.tecsup.restaurantmanagementsystem.model.Plato;
import com.tecsup.restaurantmanagementsystem.service.PlatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platos")
@CrossOrigin(origins = "*")
public class PlatoRestController {
    
    @Autowired
    private PlatoService platoService;
    
    @GetMapping
    public ResponseEntity<List<Plato>> getAllPlatos() {
        return ResponseEntity.ok(platoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable Long id) {
        return platoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/activos")
    public ResponseEntity<List<Plato>> getPlatosActivos() {
        return ResponseEntity.ok(platoService.findByEstado("activo"));
    }
    
    @PostMapping
    public ResponseEntity<Plato> createPlato(@Valid @RequestBody Plato plato) {
        Plato savedPlato = platoService.save(plato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPlato);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Plato> updatePlato(@PathVariable Long id, @Valid @RequestBody Plato plato) {
        Plato updatedPlato = platoService.update(id, plato);
        return ResponseEntity.ok(updatedPlato);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlato(@PathVariable Long id) {
        platoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



