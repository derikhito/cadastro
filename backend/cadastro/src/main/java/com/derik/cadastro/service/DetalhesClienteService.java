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
        Optional<Cliente> clienteOpt = clienteRepository.findByCpf(detalhesCliente.getCpfCliente());

        if (clienteOpt.isEmpty()) {
            throw new EntityNotFoundException("Cliente com o CPF:" + detalhesCliente.getCpfCliente() + " não existe");
        }

        return detalhesClienteRepository.save(detalhesCliente);
    }
}