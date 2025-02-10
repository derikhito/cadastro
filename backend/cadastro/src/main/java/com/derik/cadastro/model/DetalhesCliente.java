package com.derik.cadastro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DetalhesCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cpf_cliente", nullable = false)
    private String cpfCliente;

    @ManyToOne
    @JoinColumn(name = "cpf_cliente", insertable = false, updatable = false)
    @JsonBackReference
    private Cliente cliente;

    private String endereco;

    private String telefone;
}
