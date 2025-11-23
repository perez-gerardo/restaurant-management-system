package com.tecsup.restaurantmanagementsystem.controller.api;

import com.tecsup.restaurantmanagementsystem.service.ClienteService;
import com.tecsup.restaurantmanagementsystem.service.FacturaService;
import com.tecsup.restaurantmanagementsystem.service.PedidoService;
import com.tecsup.restaurantmanagementsystem.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardRestController {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private FacturaService facturaService;
    
    @Autowired
    private PlatoService platoService;
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        try {
            stats.put("totalClientes", clienteService.findAll().size());
            stats.put("totalVentas", facturaService.findAll().size());
            stats.put("totalPlatos", platoService.findAll().size());
            stats.put("pedidosPendientes", 
                pedidoService.findByEstado("pendiente").size() + 
                pedidoService.findByEstado("en preparación").size());
        } catch (Exception e) {
            stats.put("totalClientes", 0);
            stats.put("totalVentas", 0);
            stats.put("totalPlatos", 0);
            stats.put("pedidosPendientes", 0);
        }
        
        return ResponseEntity.ok(stats);
    }
}



