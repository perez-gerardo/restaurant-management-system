package com.tecsup.restaurantmanagementsystem.aspect;

import com.tecsup.restaurantmanagementsystem.model.Bitacora;
import com.tecsup.restaurantmanagementsystem.model.Usuario;
import com.tecsup.restaurantmanagementsystem.repository.BitacoraRepository;
import com.tecsup.restaurantmanagementsystem.repository.UsuarioRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class AuditoriaAspect {
    
    @Autowired
    private BitacoraRepository bitacoraRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @After("execution(* com.tecsup.restaurantmanagementsystem.service.*.save(..)) || " +
           "execution(* com.tecsup.restaurantmanagementsystem.service.*.update(..)) || " +
           "execution(* com.tecsup.restaurantmanagementsystem.service.*.delete*(..))")
    public void registrarAccion(JoinPoint joinPoint) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && 
                !authentication.getName().equals("anonymousUser")) {
                
                String nombreUsuario = authentication.getName();
                Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                        .orElse(null);
                
                if (usuario != null) {
                    String metodo = joinPoint.getSignature().getName();
                    String clase = joinPoint.getTarget().getClass().getSimpleName();
                    String accion = String.format("%s.%s - %s", clase, metodo, obtenerDescripcionAccion(metodo));
                    
                    Bitacora bitacora = new Bitacora();
                    bitacora.setUsuario(usuario);
                    bitacora.setAccion(accion);
                    bitacora.setFechaHora(LocalDateTime.now());
                    
                    bitacoraRepository.save(bitacora);
                }
            }
        } catch (Exception e) {
            // Log error pero no interrumpir la ejecución
            System.err.println("Error al registrar en bitácora: " + e.getMessage());
        }
    }
    
    private String obtenerDescripcionAccion(String metodo) {
        if (metodo.startsWith("save")) {
            return "CREAR";
        } else if (metodo.startsWith("update")) {
            return "ACTUALIZAR";
        } else if (metodo.startsWith("delete")) {
            return "ELIMINAR";
        }
        return "ACCION";
    }
}



