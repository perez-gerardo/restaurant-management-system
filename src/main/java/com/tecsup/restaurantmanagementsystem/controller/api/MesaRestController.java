package com.tecsup.restaurantmanagementsystem.controller.api;

import com.tecsup.restaurantmanagementsystem.model.Mesa;
import com.tecsup.restaurantmanagementsystem.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@CrossOrigin(origins = "*")
public class MesaRestController {
    
    @Autowired
    private MesaService mesaService;
    
    @GetMapping
    public ResponseEntity<List<Mesa>> getAllMesas() {
        return ResponseEntity.ok(mesaService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable Long id) {
        return mesaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Mesa>> getMesasByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(mesaService.findByEstado(estado));
    }
    
    @PostMapping
    public ResponseEntity<Mesa> createMesa(@Valid @RequestBody Mesa mesa) {
        Mesa savedMesa = mesaService.save(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMesa);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Mesa> updateMesa(@PathVariable Long id, @Valid @RequestBody Mesa mesa) {
        Mesa updatedMesa = mesaService.update(id, mesa);
        return ResponseEntity.ok(updatedMesa);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable Long id) {
        mesaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



