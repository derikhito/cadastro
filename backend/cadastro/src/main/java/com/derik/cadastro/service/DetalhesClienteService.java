package com.derik.cadastro.service;

import com.derik.cadastro.model.Cliente;
import com.derik.cadastro.model.DetalhesCliente;
import com.derik.cadastro.repository.ClienteRepository;
import com.derik.cadastro.repository.DetalhesClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Service
public class DetalhesClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DetalhesClienteRepository detalhesClienteRepository;

    @Transactional
    public DetalhesCliente salvarDetalhesCliente(DetalhesCliente detalhesCliente) {
        // Check if the cliente exists
        Optional<Cliente> clienteOpt = clienteRepository.findById(detalhesCliente.getIdCliente());

        if (!clienteOpt.isPresent()) {
            // Handle the case where the cliente does not exist
            throw new EntityNotFoundException("Cliente with id " + detalhesCliente.getIdCliente() + " does not exist");
        }
        // Save the detalhes_cliente
        return detalhesClienteRepository.save(detalhesCliente);
    }
}