package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Plato;
import com.tecsup.restaurantmanagementsystem.service.PlatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/platos")
public class PlatoController {
    
    @Autowired
    private PlatoService platoService;
    
    @GetMapping
    public String listarPlatos(Model model) {
        model.addAttribute("platos", platoService.findAll());
        return "platos/list";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("plato", new Plato());
        return "platos/form";
    }
    
    @PostMapping("/guardar")
    public String guardarPlato(@Valid @ModelAttribute Plato plato,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "platos/form";
        }
        
        platoService.save(plato);
        redirectAttributes.addFlashAttribute("mensaje", "Plato guardado exitosamente");
        return "redirect:/admin/platos";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Plato plato = platoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Plato no encontrado"));
        model.addAttribute("plato", plato);
        return "platos/form";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarPlato(@PathVariable Long id,
                                  @Valid @ModelAttribute Plato plato,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "platos/form";
        }
        
        platoService.update(id, plato);
        redirectAttributes.addFlashAttribute("mensaje", "Plato actualizado exitosamente");
        return "redirect:/admin/platos";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarPlato(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        platoService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Plato eliminado exitosamente");
        return "redirect:/admin/platos";
    }
}



