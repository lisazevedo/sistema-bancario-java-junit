package com.sistemabancario.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de teste base para implementação dos testes
 * unitários para a classe {@link Movimentacao}.
 * Os testes foram gerados pelo IDE apenas como modelo
 * (fiz apenas algumas melhorias de boas práticas 
 * como tirar visibilidade public e definir variáveis como final).
 * 
 * Assim, NENHUM DELES FUNCIONA E O CÓDIGO PRECISA SER ALTERADO
 * de acordo com as regras de negócio dos métodos da classe {@link Movimentacao}.
 * Ao iniciar a alteração de um teste aqui,
 * a primeira coisa a fazer é remover a chamada de fail(),
 * que indica que o teste é apenas um protótipo.
 * 
 * @author Manoel Campos da Silva Filho
 */
class MovimentacaoTest {
    /**
     * Verifica se o valor passado para o ID está sendo realmente armazenado.
     */
    @Test
    void testGetId() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final long esperado = 1;
        instance.setId(esperado);
        final long obtido = instance.getId();
        assertEquals(esperado, obtido);
    }

    /**
     * R00 - Verifica se ocorre erro ao tentar inserir uma descrição nula.
     *
     * <p>O nome dos testes para os requisitos indicados na classe {@link Movimentacao}
     * deve seguir este padrão de nome, onde o número do requisito é incluído no nome do teste.
     * </p>
     */
    @Test
    void testR00SetDescricaoNula() {
        final Movimentacao instance = new Movimentacao(new Conta());
        assertThrows(NullPointerException.class, () -> instance.setDescricao(null));
    }

    /**
     * R00 - Verifica se ocorre erro ao tentar inserir uma descrição vazia.
     */
    @Test
    void testR00SetDescricaoVazia() {
        final Movimentacao instance = new Movimentacao(new Conta());
        assertThrows(IllegalArgumentException.class, () -> instance.setDescricao(""));
    }

    /**
     * R00 - Verifica se ocorre erro ao tentar inserir uma descrição somente com espaços em branco.
     */
    @Test
    void testR00SetDescricaoBranco() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final String espacosEmBranco = "           ";
        assertThrows(IllegalArgumentException.class, () -> instance.setDescricao(espacosEmBranco));
    }

    /**
     * Verificar se o valor passado para o tipo está realmente sendo setado
     */
    @Test
    void tetGetTipo() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final char esperado = 'C';
        instance.setTipo(esperado);
        final long obtido = instance.getTipo();
        assertEquals(esperado, obtido);
    }

    /**
     * R01 - Verifica se ocorre erro ao tentar inserir um valor nulo em tipo
     */
    @Test
    void testR01SetTipoVazio() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final char valorErrado = ' ';
        assertThrows(IllegalArgumentException.class, () -> instance.setTipo(valorErrado));
    }
    /**
     * R01 - Verifica se ocorre erro ao tentar inserir um valor diferente de 'C' ou 'D' para tipo
     * Tipo da movimentação deve ser 'C' para crédito (entrada de dinheiro)
     * ou 'D' para débito (saída de dinheiro) 
     */
    @Test
    void testR01SetTipo() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final char valorErrado = 'A';
        assertThrows(IllegalArgumentException.class, () -> instance.setTipo(valorErrado));
    }

    /**
     * Testa se o valor foi realmente setado 
     */
    @Test
    void testValor() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final double valorEsperado = 1000;
        instance.setValor(valorEsperado);
        assertEquals(valorEsperado, instance.getValor());
    }

    /**
     * R02 - O valor não deve ser negativo, uma vez que existe o atributo {@link #tipo} 
     */
    @Test
    void testR02SetValorNegativo() {
        final Movimentacao instance = new Movimentacao(new Conta());
        final char tipo = 'C';
        final double valorNegativo = -4.5;
        instance.setTipo(tipo);
        assertThrows(IllegalArgumentException.class, () -> instance.setValor(valorNegativo));
    }

    /**
     * R03 - Se o tipo for débito, o valor da movimentação não pode ser superior ao saldo total da {@link Conta}
     */
    @Test
    void testR03SetValorNoDebito() {
        final Conta conta = new Conta();    
        final double valorDeposito = 300;
        conta.depositoDinheiro(valorDeposito);

        final Movimentacao instance = new Movimentacao(conta);
        instance.setTipo('D');

        final double valorMovimentacao = 400;
        assertThrows(IllegalArgumentException.class, () -> instance.setValor(valorMovimentacao));
    }

    /**
     * R04 - Todas as Movimentações devem ser instanciadas como "confirmadas" por padrão
     */
    @Test
    void testR04SetValorConfirmadaPadrao() {
        final Movimentacao instance = new Movimentacao(new Conta());
        assertEquals(true, instance.isConfirmada());
    }
}
