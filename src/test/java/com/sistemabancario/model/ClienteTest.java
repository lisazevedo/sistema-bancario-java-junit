package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    /**
     * (R01) Não pode ser vazio nem nulo, 
     */
    @Test
    void testSetCpfNulo() {
        final Cliente instance = new Cliente();
        final String cpf = "";
        assertThrows(IllegalArgumentException.class, () -> instance.setCpf(cpf));
    }

    /**
     * (R01) Não pode ter todos os dígitos iguais, 
     */
    @Test
    void testSetCpfDigitosIguais() {
        final Cliente instance = new Cliente();
        final String cpf = "99999999999";
        assertThrows(IllegalArgumentException.class, () -> instance.setCpf(cpf));
    }

    /**
     * (R01) Não pode ter conter hífens e traços 
     */
    @Test
    void testSetCpfHifen() {
        final Cliente instance = new Cliente();
        final String cpf = "9999999999-9";
        assertThrows(IllegalArgumentException.class, () -> instance.setCpf(cpf));
    }
    
    /**
     * (R01) Deve conter 11 dígitos
     */
    @Test
    void testSetCpfQuantidade() {
        final Cliente instance = new Cliente();
        final String cpf = "444444444448";
        assertThrows(IllegalArgumentException.class, () -> instance.setCpf(cpf));
    }

    /**
     * (R02) Testa se cpf é valido de acordo com o módulo 11
     */
    @Test
    void testSetCpfInvalidoModulo11() {
        final Cliente instance = new Cliente();
        final String cpf = "10710410065";
        assertThrows(IllegalArgumentException.class, () -> instance.setCpf(cpf));
    }

    /**
     * (R02) Testa se cpf é valido de acordo com o módulo 11
     */
    @Test
    void testSetCpfModulo11() {
        final Cliente instance = new Cliente();
        final String cpf = "07514033424";
        instance.setCpf(cpf);
        assertEquals(instance.getCpf(), cpf);
    }

    /**
     * (R03) Nome do cliente não pode ser nulo, vazio nem uma String contendo apenas espaços.
     */
    @Test
    void testSetNomeCliente() {
        final Cliente instance = new Cliente();
        final String nome = "  ";
        assertThrows(IllegalArgumentException.class, () -> instance.setNome(nome));
    }

    /**
     * (R03) Nome do cliente deve conter no mínimo um sobrenome
     */
    @Test
    void testSetNomeClienteSobrenomeInvalido() {
        final Cliente instance = new Cliente();
        final String nome = "LisAzevedo";
        assertThrows(IllegalArgumentException.class, () -> instance.setNome(nome));
    }

    /**
     * (R03) Nome do cliente deve conter no mínimo um sobrenome
     */
    @Test
    void testSetNomeClienteSobrenome() {
        final Cliente instance = new Cliente();
        final String nome = "Lis Azevedo";
        instance.setNome(nome);
        assertEquals(instance.getNome(), nome);
    }
}
