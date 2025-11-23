package com.tecsup.restaurantmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/auth/**").permitAll() // Permitir acceso a endpoints de autenticación
                .requestMatchers("/api/auth/user").permitAll() // Permitir acceso para obtener usuario (se validará en el código)
                .requestMatchers("/api/**").permitAll() // Permitir acceso a APIs para frontend Astro
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/pedidos/**").hasAnyRole("MOZO", "COCINERO")
                .requestMatchers("/ventas/**").hasAnyRole("CAJERO", "ADMIN")
                .requestMatchers("/inventario/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") // Redirigirá al frontend de Astro
                .loginProcessingUrl("/login") // El formulario del frontend enviará aquí
                .defaultSuccessUrl("/auth/success", true) // Redirigir al endpoint que redirige a Astro
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("http://localhost:4321/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}

