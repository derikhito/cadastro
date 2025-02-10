package com.derik.cadastro.service;

import com.derik.cadastro.model.Cliente;
import com.derik.cadastro.repository.ClienteRepository;
import com.derik.cadastro.util.ValidadorCPF;
import jakarta.persistence.EntityNotFoundException;
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


    @Transactional
    public void deletarCliente(String cpf) {
        String cpfLimpo = limparEValidarCPF(cpf);
        if (!clienteRepository.existsByCpf(cpfLimpo)) {
            throw new EntityNotFoundException("Cliente com o CPF fornecido não existe.");
        }
        clienteRepository.deleteByCpf(cpfLimpo);
    }

    @Transactional
    public Cliente atualizarCliente(String cpf, Cliente clienteAtualizado) {
        String cpfLimpo = limparEValidarCPF(cpf);
        Cliente clienteExistente = clienteRepository.findByCpf(cpfLimpo)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com o CPF fornecido não existe."));
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());
        return clienteRepository.save(clienteExistente);
    }

    private String limparEValidarCPF(String cpf) {
        String cpfLimpo = ValidadorCPF.limparCPF(cpf);
        if (!ValidadorCPF.cpfValido(cpfLimpo)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        return cpfLimpo;
    }

}
