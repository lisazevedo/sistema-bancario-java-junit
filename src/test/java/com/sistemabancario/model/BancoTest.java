package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BancoTest {
    /**
     * (R01) Deve ter exatamente 3 algarismos. Senão, uma exceção deve ser 
     * lançada informando o problema.
     */
    @Test
    void testSetNumero() {
        final Banco instance = new Banco();
        final String number = "333";
        instance.setNumero(number);
        assertEquals(instance.getNumero(), number);
    }

    /**
     * (R01) Número deve ter exatamente 3 algarismos númericos
     */
    @Test
    void testSetNumeroQuantidadeAlgarismos() {
        final Banco instance = new Banco();
        final String number = "4344";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(number));        
    }

    /**
     * (R01) Número deve ter exatamente 3 algarismos númericos
     */
    @Test
    void testSetNumeroAlgarismosAlfaNumericos() {
        final Banco instance = new Banco();
        final String number = "AAA";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(number));        
    }

    /**
     * (R02) Número não pode ser nulo nem vazio
     */
    @Test
    void testSetNumeroVazio() {
        final Banco instance = new Banco();
        final String number = "";
        assertThrows(IllegalArgumentException.class, () -> instance.setNumero(number));        
    }
}
