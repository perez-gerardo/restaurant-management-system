package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Factura;
import com.tecsup.restaurantmanagementsystem.service.FacturaService;
import com.tecsup.restaurantmanagementsystem.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/ventas")
public class VentaController {
    
    @Autowired
    private FacturaService facturaService;
    
    @Autowired
    private PedidoService pedidoService;
    
    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("facturas", facturaService.findAll());
        return "ventas/list";
    }
    
    @GetMapping("/nuevo/{idPedido}")
    public String crearFactura(@PathVariable Long idPedido, Model model) {
        var pedido = pedidoService.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        Factura factura = new Factura();
        factura.setPedido(pedido);
        factura.setTotal(BigDecimal.valueOf(pedido.getTotal()));
        factura.setFechaEmision(LocalDateTime.now());
        
        model.addAttribute("factura", factura);
        return "ventas/form";
    }
    
    @PostMapping("/guardar")
    public String guardarFactura(@ModelAttribute Factura factura,
                                RedirectAttributes redirectAttributes) {
        facturaService.save(factura);
        
        // Actualizar estado del pedido a cerrado
        var pedido = factura.getPedido();
        pedido.setEstado("cerrado");
        pedidoService.save(pedido);
        
        redirectAttributes.addFlashAttribute("mensaje", "Factura generada exitosamente");
        return "redirect:/ventas";
    }
    
    @GetMapping("/pagar/{id}")
    public String pagarFactura(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Factura factura = facturaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        factura.setEstado("pagado");
        facturaService.save(factura);
        redirectAttributes.addFlashAttribute("mensaje", "Factura marcada como pagada");
        return "redirect:/ventas";
    }
}



