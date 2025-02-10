package com.derik.cadastro.service;

import com.derik.cadastro.model.Cliente;
import com.derik.cadastro.repository.ClienteRepository;
import com.derik.cadastro.util.ValidadorCPF;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        String cpfLimpo = limparEValidarCPF(cliente.getCpf());
        cliente.setCpf(cpfLimpo);
        if(clienteRepository.findByCpf(cpfLimpo).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        return clienteRepository.save(cliente);

    }

    public Optional<Cliente> buscarClienteComDetalhes(String cpf) {
        String cpfLimpo = limparEValidarCPF(cpf);
        return clienteRepository.findByCpf(cpfLimpo);
    }

    private String limparEValidarCPF(String cpf) {
        String cpfLimpo = ValidadorCPF.limparCPF(cpf);
        if (!ValidadorCPF.cpfValido(cpfLimpo)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        return cpfLimpo;
    }

}
