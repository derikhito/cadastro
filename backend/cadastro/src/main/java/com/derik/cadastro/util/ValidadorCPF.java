package com.derik.cadastro.util;

public class ValidadorCPF {

    public static boolean cpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int[] digitos = new int[11];
            for (int i = 0; i < 11; i++) {
                digitos[i] = Integer.parseInt(cpf.substring(i, i + 1));
            }

            int calculoPrimeiroDigito = 0, calculoSegundoDigito = 0;
            for (int i = 0; i < 9; i++) {
                calculoPrimeiroDigito += digitos[i] * (10 - i);
                calculoSegundoDigito += digitos[i] * (11 - i);
            }

            int primeiroDigitoVerificador = (calculoPrimeiroDigito * 10) % 11;
            if (primeiroDigitoVerificador == 10) {
                primeiroDigitoVerificador = 0;
            }

            calculoSegundoDigito += primeiroDigitoVerificador * 2;
            int segundoDigitoVerificador = (calculoSegundoDigito * 10) % 11;
            if (segundoDigitoVerificador == 10) {
                segundoDigitoVerificador = 0;
            }

            return primeiroDigitoVerificador == digitos[9] && segundoDigitoVerificador == digitos[10];

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String limparCPF(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

}
