package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    /**
     * R01
     */
    @Test
    void testSetNumeroValido(){
        final Conta instance = new Conta();
        final String esperado = "12345-6";
        instance.setNumero(esperado);
        final String obtido = instance.getNumero();
        assertEquals(esperado, obtido);
    }
    
    @Test
    void testSetNumeroValidoNaoArmazena(){
        final Conta instance = new Conta();
        final String invalido = "123";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(invalido));        
        final String obtido = instance.getNumero();
        assertNotEquals(invalido, obtido);
    }
    @Test 
    void testSetNotNumeroNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getNumero());
    }

    /**
     * R02
     */
    @Test
    void testInstanciaPadraoPoupanca(){
        final Conta instance = new Conta();
        assertFalse(instance.isPoupanca());
    }

    /**
     * R03
     */
    @Test 
    void testSetLimiteContaEspecial(){
        final Conta instance = new Conta();
        instance.setEspecial(true);
        final double esperado = 100;
        instance.setLimite(esperado);
        final double obtido = instance.getLimite();
        assertEquals(esperado, obtido);
    }

    @Test 
    void testSetLimiteContaNaoEspecial(){
        final Conta instance = new Conta();
        final double limite = 1000;
        assertThrows(IllegalStateException.class, () -> instance.setLimite(limite));        
    }

    /**
     * R04
     */
    @Test
    void testHistoricoNotNull(){
        final Conta instance = new Conta();
        assertNotNull(instance.getMovimentacoes());
    }

    /**
     * R06
     */
    @Test
    void testGetSaldoTotal() {
        final double limite = 500;
        final double esperado = limite;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido);
    }       

    @Test
    void testDepositoDinheiro() {
        final double limite = 500.6, deposito = 500.8, esperado = 1001.4;
        final Conta instance = new Conta();
        instance.setEspecial(true);
        instance.setLimite(limite);
        instance.depositoDinheiro(deposito);
        
        final double obtido = instance.getSaldoTotal();
        assertEquals(esperado, obtido, 0.001);
    }
    
    
}
