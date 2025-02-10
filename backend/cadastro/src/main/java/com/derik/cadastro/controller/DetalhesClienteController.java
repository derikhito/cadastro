package com.derik.cadastro.controller;

import com.derik.cadastro.model.DetalhesCliente;
import com.derik.cadastro.service.DetalhesClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalhes-cliente")
public class DetalhesClienteController {

    @Autowired
    private DetalhesClienteService detalhesClienteService;

    @PostMapping
    public ResponseEntity<DetalhesCliente> criarDetalhesCliente(@RequestBody DetalhesCliente detalhesCliente) {
        if (detalhesCliente.getCpf() == null || detalhesCliente.getCpf().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DetalhesCliente salvarDetalhesCliente = detalhesClienteService.salvarDetalhesCliente(detalhesCliente);
        return new ResponseEntity<>(salvarDetalhesCliente, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDetalhesCliente(@PathVariable Long id) {
        try {
            detalhesClienteService.deletarDetalhesCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalhesCliente> atualizarDetalhesCliente(@PathVariable Long id, @RequestBody DetalhesCliente detalhesAtualizados) {
        try {
            DetalhesCliente detalhes = detalhesClienteService.atualizarDetalhesCliente(id, detalhesAtualizados);
            return new ResponseEntity<>(detalhes, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
