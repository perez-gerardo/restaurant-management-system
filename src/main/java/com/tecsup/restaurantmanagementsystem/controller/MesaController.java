package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Mesa;
import com.tecsup.restaurantmanagementsystem.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/mesas")
public class MesaController {
    
    @Autowired
    private MesaService mesaService;
    
    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaService.findAll());
        return "mesas/list";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesas/form";
    }
    
    @PostMapping("/guardar")
    public String guardarMesa(@Valid @ModelAttribute Mesa mesa,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "mesas/form";
        }
        
        if (mesa.getIdMesa() == null && mesaService.existsByNumero(mesa.getNumero())) {
            result.rejectValue("numero", "error.numero", "Ya existe una mesa con este número");
            return "mesas/form";
        }
        
        mesaService.save(mesa);
        redirectAttributes.addFlashAttribute("mensaje", "Mesa guardada exitosamente");
        return "redirect:/admin/mesas";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Mesa mesa = mesaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        model.addAttribute("mesa", mesa);
        return "mesas/form";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarMesa(@PathVariable Long id,
                                @Valid @ModelAttribute Mesa mesa,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "mesas/form";
        }
        
        mesaService.update(id, mesa);
        redirectAttributes.addFlashAttribute("mensaje", "Mesa actualizada exitosamente");
        return "redirect:/admin/mesas";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarMesa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        mesaService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Mesa eliminada exitosamente");
        return "redirect:/admin/mesas";
    }
}



