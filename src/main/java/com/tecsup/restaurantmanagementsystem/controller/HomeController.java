package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.service.ClienteService;
import com.tecsup.restaurantmanagementsystem.service.FacturaService;
import com.tecsup.restaurantmanagementsystem.service.PedidoService;
import com.tecsup.restaurantmanagementsystem.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private FacturaService facturaService;
    
    @Autowired
    private PlatoService platoService;
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        try {
            model.addAttribute("totalClientes", clienteService.findAll().size());
            model.addAttribute("totalVentas", facturaService.findAll().size());
            model.addAttribute("totalPlatos", platoService.findAll().size());
            model.addAttribute("pedidosPendientes", pedidoService.findByEstado("pendiente").size() + 
                                                      pedidoService.findByEstado("en preparación").size());
        } catch (Exception e) {
            model.addAttribute("totalClientes", 0);
            model.addAttribute("totalVentas", 0);
            model.addAttribute("totalPlatos", 0);
            model.addAttribute("pedidosPendientes", 0);
        }
        return "dashboard";
    }
    
    @GetMapping("/login")
    public String login() {
        // Redirigir al login del frontend de Astro
        return "redirect:http://localhost:4321/login";
    }
}

