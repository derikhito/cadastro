CREATE DATABASE IF NOT EXISTS sankhyatest;

USE sankhyatest;

CREATE TABLE IF NOT EXISTS cliente (
    cpf VARCHAR(11) PRIMARY KEY, 
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS detalhes_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,  
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    FOREIGN KEY (cpf) REFERENCES cliente(cpf) ON DELETE CASCADE
);