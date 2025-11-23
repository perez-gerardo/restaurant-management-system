package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.model.Usuario;
import com.tecsup.restaurantmanagementsystem.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/list";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/form";
        }
        
        if (usuario.getIdUsuario() == null && usuarioService.existsByNombreUsuario(usuario.getNombreUsuario())) {
            result.rejectValue("nombreUsuario", "error.nombreUsuario", "Ya existe un usuario con este nombre");
            return "usuarios/form";
        }
        
        usuarioService.save(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario guardado exitosamente");
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }
    
    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Long id,
                                   @Valid @ModelAttribute Usuario usuario,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "usuarios/form";
        }
        
        // Si la contraseña está vacía, mantener la actual
        Usuario usuarioExistente = usuarioService.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (usuario.getContraseña() == null || usuario.getContraseña().isEmpty()) {
            usuario.setContraseña(usuarioExistente.getContraseña());
        }
        
        usuarioService.save(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario actualizado exitosamente");
        return "redirect:/admin/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        usuarioService.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario eliminado exitosamente");
        return "redirect:/admin/usuarios";
    }
}



