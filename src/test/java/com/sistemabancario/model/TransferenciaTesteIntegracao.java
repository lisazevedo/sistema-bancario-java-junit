package com.sistemabancario.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TransferenciaTesteIntegracao {
    private static ColecaoClientes clientes;
    private static ColecaoContas contas;
    private static Cliente cliente;
    
    @BeforeAll
    static void inicializa() {
        clientes = ColecaoClientes.inicializaClientes();
        contas = ColecaoContas.inicializaContas();
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
    void deveEfetuarTransferenciaBemSucedida() {
        String numeroContaParaDeposito = "67332-3";
        Double valorParaDeposito = 100.00;

        Double saldoContaOrigem = cliente.getConta().getSaldoTotal();
        cliente.getConta().saque(valorParaDeposito);
        
        Conta contaParaDeposito = contas.getConta(numeroContaParaDeposito);
        Double saldoContaDestino = contaParaDeposito.getSaldoTotal();
        contaParaDeposito.depositoDinheiro(valorParaDeposito);

        assertEquals(cliente.getConta().getSaldoTotal(), saldoContaOrigem - valorParaDeposito);
        assertEquals(contaParaDeposito.getSaldoTotal(), saldoContaDestino + valorParaDeposito);
    }

    /**
     * CT2
     */
    @Test
    void deveFalharTransferenciaComContaInvalida() {
        String numeroContaParaDeposito = "673323";
        Double valorParaDeposito = 100.00;

        Conta contaParaDeposito = contas.getConta(numeroContaParaDeposito);
        assertThrows(IllegalArgumentException.class, () -> contaParaDeposito.depositoDinheiro(valorParaDeposito)); 
    }
}
