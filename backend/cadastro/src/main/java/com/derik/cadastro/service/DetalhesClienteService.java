package com.derik.cadastro.service;

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
       validarClienteExistente(detalhesCliente);

        return detalhesClienteRepository.save(detalhesCliente);
    }

    @Transactional
    public void deletarDetalhesCliente(Long id) {
        if (!detalhesClienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Detalhes do cliente com o ID fornecido não existem.");
        }
        detalhesClienteRepository.deleteById(id);
    }

    @Transactional
    public DetalhesCliente atualizarDetalhesCliente(Long id, DetalhesCliente detalhesAtualizados) {
        DetalhesCliente detalhesExistentes = detalhesClienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalhes do cliente com o ID fornecido não existem."));
        detalhesExistentes.setEndereco(detalhesAtualizados.getEndereco());
        detalhesExistentes.setTelefone(detalhesAtualizados.getTelefone());
        return detalhesClienteRepository.save(detalhesExistentes);
    }

    private void validarClienteExistente(DetalhesCliente detalhesCliente) {
        Optional.ofNullable(detalhesCliente.getCpf())
                .flatMap(clienteRepository::findByCpf)
                .orElseThrow(() -> new EntityNotFoundException("Cliente com o CPF fornecido não existe."));
    }

}