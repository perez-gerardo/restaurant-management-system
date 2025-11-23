package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Pedido;
import com.tecsup.restaurantmanagementsystem.service.ClienteService;
import com.tecsup.restaurantmanagementsystem.service.MesaService;
import com.tecsup.restaurantmanagementsystem.service.PedidoService;
import com.tecsup.restaurantmanagementsystem.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private MesaService mesaService;
    
    @Autowired
    private PlatoService platoService;
    
    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.findAll());
        return "pedidos/list";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("mesas", mesaService.findByEstado("disponible"));
        model.addAttribute("platos", platoService.findByEstado("activo"));
        return "pedidos/form";
    }
    
    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido,
                               RedirectAttributes redirectAttributes) {
        pedidoService.save(pedido);
        redirectAttributes.addFlashAttribute("mensaje", "Pedido creado exitosamente");
        return "redirect:/pedidos";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("mesas", mesaService.findAll());
        return "pedidos/form";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarPedido(@PathVariable Long id,
                                  @ModelAttribute Pedido pedido,
                                  RedirectAttributes redirectAttributes) {
        pedidoService.update(id, pedido);
        redirectAttributes.addFlashAttribute("mensaje", "Pedido actualizado exitosamente");
        return "redirect:/pedidos";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        pedidoService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Pedido eliminado exitosamente");
        return "redirect:/pedidos";
    }
    
    @GetMapping("/cocina")
    public String verPedidosCocina(Model model) {
        model.addAttribute("pedidos", pedidoService.findByEstado("en preparación"));
        return "pedidos/cocina";
    }
}



