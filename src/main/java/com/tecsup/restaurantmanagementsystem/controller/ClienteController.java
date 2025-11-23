package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Cliente;
import com.tecsup.restaurantmanagementsystem.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
        return "clientes/list";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute Cliente cliente, 
                                BindingResult result, 
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "clientes/form";
        }
        
        if (cliente.getIdCliente() == null && clienteService.existsByDni(cliente.getDni())) {
            result.rejectValue("dni", "error.dni", "Ya existe un cliente con este DNI");
            return "clientes/form";
        }
        
        clienteService.save(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente guardado exitosamente");
        return "redirect:/admin/clientes";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Long id, 
                                   @Valid @ModelAttribute Cliente cliente,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "clientes/form";
        }
        
        clienteService.update(id, cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado exitosamente");
        return "redirect:/admin/clientes";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clienteService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado exitosamente");
        return "redirect:/admin/clientes";
    }
}



