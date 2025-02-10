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

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<Cliente> buscarClienteComDetalhes(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarClienteComDetalhes(id);
        return cliente.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

//    @DeleteMapping("/cliente")
//    public ResponseEntity<Cliente> removerCliente(@RequestParam(name ="clientId") long clientId) {
//        Cliente removerCliente = clienteService.removerCliente(clientId);
//        return new ResponseEntity<>(removerCliente, HttpStatus.OK);
//    }
}
