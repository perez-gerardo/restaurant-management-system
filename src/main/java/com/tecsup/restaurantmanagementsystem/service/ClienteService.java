package com.tecsup.restaurantmanagementsystem.service;

import com.tecsup.restaurantmanagementsystem.model.Cliente;
import com.tecsup.restaurantmanagementsystem.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public Cliente update(Long id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
        cliente.setDni(clienteActualizado.getDni());
        cliente.setNombres(clienteActualizado.getNombres());
        cliente.setApellidos(clienteActualizado.getApellidos());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setCorreo(clienteActualizado.getCorreo());
        cliente.setEstado(clienteActualizado.getEstado());
        
        return clienteRepository.save(cliente);
    }
    
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public boolean existsByDni(String dni) {
        return clienteRepository.existsByDni(dni);
    }
    
    public Optional<Cliente> findByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }
}



