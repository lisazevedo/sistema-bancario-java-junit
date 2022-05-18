package com.sistemabancario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DepositoTesteIntegracao {
    private static ColecaoClientes clientes;
    private static Cliente cliente;
    
    @BeforeAll
    static void inicializa() {
        clientes = ColecaoClientes.inicializaClientes();
    }

    @BeforeAll
    static void login() {
        String cpf = "23906078027";
        String senha = "1234";
        Cliente clienteAux = clientes.getCliente(cpf);
        if (Cliente.autentica(clienteAux, cpf, senha)) {
            cliente = clienteAux;
        }
    }

    /**
     * CT1
     */
    @Test
    void deveEfetuarDepositoBemSucedido() {
        Double valor = 100.00;
        Double valorEmConta = cliente.getConta().getSaldoTotal();
        cliente.getConta().depositoDinheiro(valor);
        assertEquals(cliente.getConta().getSaldo(), valorEmConta + valor);
    }

    /**
     * CT2
     */
    @Test
    void deveFalharDepositoComValorNegativo() {
        Double valor = -10.00;
        assertThrows(IllegalArgumentException.class, () -> cliente.getConta().depositoDinheiro(valor)); 
    }
}
