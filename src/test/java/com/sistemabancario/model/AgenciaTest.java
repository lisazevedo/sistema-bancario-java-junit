package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AgenciaTest {  
    /**
     * (R01)  O banco deve ser fornecido como um parâmetro no construtor,
     * pois não pode haver agência sem banco.
     */
    @Test
    void testSetBancoAgencia() {
        final Agencia instance = new Agencia(new Banco());
        assertNotNull(instance.getBanco());
    }

    /**
     * (R02) O valor número é obrigatório, não podendo ser nulo nem vazio
     */
    @Test
    void testSetNumero() {
        final Agencia instance = new Agencia(new Banco());
        final String number = "0638-6";
        instance.setNumero(number);
        assertEquals(number, instance.getNumero());
    }

    /**
     * (R02) O valor número é obrigatório, não podendo ser nulo nem vazio
     */
    @Test
    void testSetNumeroVazio() {
        final Agencia instance = new Agencia(new Banco());
        final String number = "";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(number));
    }

    /**
     * (R03) Deve ter exatamente 4 algarismos, seguido de um hífen e mais um dígito verificador.
     * o hífen é pra deixar clara a existência de tal dígito). 
     * Exemplos de números de agência
     * válidos inclui: 0638-6, 1886-4, 1867-8.
     */
    @Test
    void testSetNumeroAgencia() {
        final Agencia instance = new Agencia(new Banco());
        final String number = "06385-6";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(number));
    }

    /**
     * (R04) Verificar se o dígito informado em numero é igual ao 
     * dígito calculado pelo método e armazenado na variável 
     * digitoCalculado
     */
    @Test
    void testSetNumeroModulo11() {
        final Agencia instance = new Agencia(new Banco());
        final String number = "1867-8";
        String digitoCalculado = Util.calculaDigitoModulo11(number);
        instance.setNumero(number);
        assertTrue(number.endsWith(digitoCalculado));
        assertEquals(number, instance.getNumero());
    }

    /**
     * (R04) Verificar se o dígito informado em numero é igual ao 
     * dígito calculado pelo método e armazenado na variável 
     * digitoCalculado
     */
    @Test
    void testSetInvalidoNumeroModulo11() {
        final Agencia instance = new Agencia(new Banco());
        final String number = "1867-7";
        String digitoCalculado = Util.calculaDigitoModulo11(number);
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(number));
        assertFalse(number.endsWith(digitoCalculado));
    }
}
