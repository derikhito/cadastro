package com.derik.cadastro.repository;

import com.derik.cadastro.model.DetalhesCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalhesClienteRepository extends JpaRepository<DetalhesCliente, Long> {
}
