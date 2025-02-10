-- 1. Criar o banco de dados
CREATE DATABASE IF NOT EXISTS sankhyatest;

-- 2. Usar o banco de dados
USE sankhyatest;

-- 3. Criar a tabela Cliente com CPF como chave primária
CREATE TABLE IF NOT EXISTS cliente (
    cpf VARCHAR(11) PRIMARY KEY,  -- CPF como chave primária
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL
);

-- 4. Criar a tabela Detalhes_Cliente referenciando CPF
CREATE TABLE IF NOT EXISTS detalhes_cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(11) NOT NULL,  -- Agora referência CPF
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    FOREIGN KEY (cpf) REFERENCES cliente(cpf) ON DELETE CASCADE
);