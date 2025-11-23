package com.tecsup.restaurantmanagementsystem.controller.api;

import com.tecsup.restaurantmanagementsystem.model.Insumo;
import com.tecsup.restaurantmanagementsystem.service.InsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin(origins = "*")
public class InsumoRestController {
    
    @Autowired
    private InsumoService insumoService;
    
    @GetMapping
    public ResponseEntity<List<Insumo>> getAllInsumos() {
        return ResponseEntity.ok(insumoService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Insumo> getInsumoById(@PathVariable Long id) {
        return insumoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Insumo>> getInsumosStockBajo() {
        return ResponseEntity.ok(insumoService.findInsumosConStockBajo());
    }
    
    @PostMapping
    public ResponseEntity<Insumo> createInsumo(@Valid @RequestBody Insumo insumo) {
        return ResponseEntity.ok(insumoService.save(insumo));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Insumo> updateInsumo(@PathVariable Long id, @Valid @RequestBody Insumo insumo) {
        return ResponseEntity.ok(insumoService.update(id, insumo));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsumo(@PathVariable Long id) {
        insumoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



