package com.derik.cadastro.controller;

import com.derik.cadastro.model.DetalhesCliente;
import com.derik.cadastro.service.DetalhesClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DetalhesClienteController {

    @Autowired
    private DetalhesClienteService detalhesClienteService;

    @PostMapping("/detalhes-cliente")
    public ResponseEntity<DetalhesCliente> criarDetalhesCliente(@RequestBody DetalhesCliente detalhesCliente) {
        DetalhesCliente salvarDetalhesCliente = detalhesClienteService.salvarDetalhesCliente(detalhesCliente);
        return new ResponseEntity<>(salvarDetalhesCliente, HttpStatus.CREATED);
    }
}
