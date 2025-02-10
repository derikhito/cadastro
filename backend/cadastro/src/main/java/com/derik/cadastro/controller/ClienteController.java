package com.derik.cadastro.controller;

import com.derik.cadastro.model.Cliente;
import com.derik.cadastro.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente salvarCliente = clienteService.salvarCliente(cliente);
        return new ResponseEntity<>(salvarCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{cpf}/detalhes")
    public ResponseEntity<Cliente> buscarClienteComDetalhes(@PathVariable String cpf) {
        Optional<Cliente> cliente = clienteService.buscarClienteComDetalhes(cpf);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        try {
            clienteService.deletarCliente(cpf);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String cpf, @RequestBody Cliente clienteAtualizado) {
        try {
            Cliente cliente = clienteService.atualizarCliente(cpf, clienteAtualizado);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
