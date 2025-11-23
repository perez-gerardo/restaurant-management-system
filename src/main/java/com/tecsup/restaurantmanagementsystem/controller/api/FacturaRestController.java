package com.tecsup.restaurantmanagementsystem.controller.api;

import com.tecsup.restaurantmanagementsystem.model.Factura;
import com.tecsup.restaurantmanagementsystem.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
@CrossOrigin(origins = "*")
public class FacturaRestController {
    
    @Autowired
    private FacturaService facturaService;
    
    @GetMapping
    public ResponseEntity<List<Factura>> getAllFacturas() {
        return ResponseEntity.ok(facturaService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        return facturaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Factura>> getFacturasByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(facturaService.findByEstado(estado));
    }
    
    @PostMapping
    public ResponseEntity<Factura> createFactura(@Valid @RequestBody Factura factura) {
        Factura savedFactura = facturaService.save(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFactura);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @Valid @RequestBody Factura factura) {
        Factura updatedFactura = facturaService.update(id, factura);
        return ResponseEntity.ok(updatedFactura);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        facturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



