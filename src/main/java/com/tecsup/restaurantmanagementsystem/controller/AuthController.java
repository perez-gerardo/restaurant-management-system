package com.tecsup.restaurantmanagementsystem.controller;

import com.tecsup.restaurantmanagementsystem.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {
    
    @GetMapping("/auth/success")
    public String loginSuccess() {
        // Redirigir al frontend de Astro después del login exitoso
        return "redirect:http://localhost:4321";
    }
}

@RestController
@RequestMapping("/api/auth")
class AuthRestController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/user")
    public org.springframework.http.ResponseEntity<?> getCurrentUser(
            @RequestParam(required = false) String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Si hay un usuario autenticado en la sesión
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("username", auth.getName());
            userInfo.put("roles", auth.getAuthorities().stream()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .toList());
            return org.springframework.http.ResponseEntity.ok(userInfo);
        }
        
        // Si se pasa username como parámetro (para desarrollo/testing cuando las cookies no funcionan)
        if (username != null && !username.isEmpty()) {
            try {
                List<com.tecsup.restaurantmanagementsystem.model.Usuario> usuarios = usuarioService.findAll();
                for (com.tecsup.restaurantmanagementsystem.model.Usuario u : usuarios) {
                    if (u.getNombreUsuario().equals(username)) {
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("username", u.getNombreUsuario());
                        List<String> roles = new ArrayList<>();
                        roles.add(u.getRol());
                        userInfo.put("roles", roles);
                        return org.springframework.http.ResponseEntity.ok(userInfo);
                    }
                }
            } catch (Exception e) {
                // Si hay error, continuar
            }
        }
        
        return org.springframework.http.ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED).build();
    }
}

