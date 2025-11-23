package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Insumo;
import com.tecsup.restaurantmanagementsystem.service.InsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inventario")
public class InventarioController {
    
    @Autowired
    private InsumoService insumoService;
    
    @GetMapping
    public String listarInsumos(Model model) {
        model.addAttribute("insumos", insumoService.findAll());
        model.addAttribute("insumosStockBajo", insumoService.findInsumosConStockBajo());
        return "inventario/list";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("insumo", new Insumo());
        return "inventario/form";
    }
    
    @PostMapping("/guardar")
    public String guardarInsumo(@Valid @ModelAttribute Insumo insumo,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "inventario/form";
        }
        
        insumoService.save(insumo);
        redirectAttributes.addFlashAttribute("mensaje", "Insumo guardado exitosamente");
        return "redirect:/inventario";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Insumo insumo = insumoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        model.addAttribute("insumo", insumo);
        return "inventario/form";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarInsumo(@PathVariable Long id,
                                  @Valid @ModelAttribute Insumo insumo,
                                  BindingResult result,
                                  RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "inventario/form";
        }
        
        insumoService.update(id, insumo);
        redirectAttributes.addFlashAttribute("mensaje", "Insumo actualizado exitosamente");
        return "redirect:/inventario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarInsumo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        insumoService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Insumo eliminado exitosamente");
        return "redirect:/inventario";
    }
}



