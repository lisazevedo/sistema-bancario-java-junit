package com.sistemabancario.model;

import java.util.Objects;

/**
 * Representa uma movimentação em uma {@link Conta} bancária, que pode indicar entrada ou saída.
 *
 * <p>
 * <b>NOTA</b>: Mesmo sendo possível obter o saldo somando-se todas as movimentações,
 * à medida que os dados no sistema aumentarem ao longo do tempo, 
 * calcular o saldo pode se tornar uma operação extremamente lenta.
 * Isto normalmente ocorrer quando o histórico de movimentações se torna longo
 * (principalmente depois de alguns anos).
 * </p>
 * 
 * @author Manoel Campos da Silva Filho
 */
public class Movimentacao implements Cadastro {
    private long id;

    /**
     * Descrição não pode ser nula, vazia nem conter apenas espaços em branco (R00).
     */
    private String descricao;

    /**
     * Conta bancária a qual a movimentação está vinculada.
     */
    private Conta conta;

    /**
     * Tipo da movimentação deve ser 'C' para crédito (entrada de dinheiro)
     * ou 'D' para débito (saída de dinheiro) (R01).
     */
    private char tipo;

    /**
     * Valor monetário da movimentação.
     * O valor não deve ser negativo, uma vez que existe o atributo {@link #tipo} (R02).
     * Se o tipo for débito, o valor da movimentação não pode ser superior ao saldo total da {@link Conta} (R03).
     */
    private double valor;

    /**
     * Indica se a movimentação foi confirmada, neste caso, devendo ser registrada no saldo da
     * conta, quando for adicionada à lista de movimentações usando
     * {@link Conta#addMovimentacao(Movimentacao)}.
     *
     * <ul>
     *  <li>Movimentacoes devem ser instanciadas como "confirmadas" por padrão (R04).</li>
     *  <li>
     *      Somente operações como depósito em cheque devem ser
     *      registradas inicialmente como não confirmadas. Após uma operação ser
     *      confirmada, deve-se atualizar o saldo da conta (R99).
     *  <li>
     * </ul>
     *
     * @see Conta#depositoDinheiro(double)
     * @see Conta#depositoCheque(double)
     */
    private boolean confirmada;

    /**
     * Instancia uma movimentação para uma determinada {@link Conta} bancária,
     * onde a conta passada deve ser armazenada no atributo {@link #conta} (R05).
     * @param conta a {@link Conta} para vincular a movimentação.
     */
    public Movimentacao(Conta conta){
        this.conta = conta;
        this.confirmada = true;
    }

    public Movimentacao(Conta conta, boolean confirmada){
        this.conta = conta;
        this.confirmada = confirmada;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public char getTipo(){
        return tipo;
    }

    public void setTipo(char tipo){
        Objects.requireNonNull(tipo, "Tipo não pode ser nulo");

        if (tipo != 'C' && tipo != 'D') {
            throw new IllegalArgumentException("Tipo só pode ter os valores 'C' (crédito) ou 'D' (débito).");
        }

        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        Objects.requireNonNull(descricao, "Descrição não pode ser nula.");

        if(descricao.trim().isEmpty()){
            throw new IllegalArgumentException("Descrição não pode ser vazia.");
        }

        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (!Objects.isNull(tipo) && valor < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
        
        if (!Objects.isNull(conta) && !Objects.isNull(tipo) && tipo == 'D' && valor > conta.getSaldoTotal()) {
            throw new IllegalArgumentException("Valor não pode ser maior que o saldo total no débito.");
        }

        this.valor = valor;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        this.confirmada = confirmada;
    }

}
