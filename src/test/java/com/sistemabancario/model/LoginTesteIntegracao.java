package com.sistemabancario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Testes de Integração para o Sistema Bancário
 * 
 */
public class LoginTesteIntegracao {

    private static ColecaoClientes clientes;

    @BeforeAll
    static void inicializa() {
        clientes = ColecaoClientes.inicializaClientes();
    }

    /**
     * CT1
     */
    @Test
    void deveFalharComLoginEmBranco() {
        String cpf = " ";
        String senha = " ";
        Cliente cliente = clientes.getCliente(cpf);
        Boolean autenticado = Cliente.autentica(cliente, cpf, senha);
        assertEquals(false, autenticado);
    }

    /**
     * CT2
     */
    @Test
    void deveFalharComUsuarioSenhaInvalido() {
        String cpf = "123456789123";
        String senha = "888888";
        Cliente cliente = clientes.getCliente(cpf);
        Boolean autenticado = Cliente.autentica(cliente, cpf, senha);
        assertEquals(false, autenticado);
    }
    /**
     * CT3
     */
    @Test
    void deveFalharComUsuarioValidoSenhaInvalida() {
        String cpf = "3800358557";
        String senha = "1231";
        Cliente cliente = clientes.getCliente(cpf);
        Boolean autenticado = Cliente.autentica(cliente, cpf, senha);
        assertEquals(false, autenticado);
    }
    
    /**
     * CT4
     */
    @Test
    void deveAutenticarUsuarioeSenhaValido() {
        String cpf = "36601046043";
        String senha = "1234";
        Cliente cliente = clientes.getCliente(cpf);
        Boolean autenticado = Cliente.autentica(cliente, cpf, senha);
        assertEquals(true, autenticado);
    }    
}
