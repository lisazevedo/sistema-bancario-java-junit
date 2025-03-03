package com.sistemabancario.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa uma conta bancária de um determinado {@link Cliente}, tendo os
 * seguintes requisitos:
 *
 * <ul>
 * <li>Uma conta não pode ser excluída se existirem movimentações.</li>
 * </ul>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Conta implements Cadastro {

    private long id;

    /**
     * Número que identifica unicamente uma conta em uma determinada agência,
     * devendo estar no formato 99999-9. Se o número não estiver no formato
     * indicado, o valor não pode ser armazenado e uma exceção deve ser lançada
     * (R01). O número da agência tem um dígito verificador como no CPF, mas
     * isto é outro requisito não definido aqui.
     */
    private String numero;

    /**
     * Contas devem ser instanciadas como "Conta Corrente" e não como
     * "Poupança". (R02) O valor padrão para atributos boolean é false, assim
     * não precisamos escrever código adicional para definir "poupanca" como
     * false. Mas é preciso escrever o teste para verificar tal situação. Com
     * isto, buscamos detectar se uma alteração no código fizer com que este
     * requisito deixe de ser atendido.
     */
    private boolean poupanca;

    /**
     * Indica se a conta é especial ou não. Caso seja, ela pode ter
     * {@link #limite}.
     */
    private boolean especial;

    /**
     * Valor que o cliente possui na conta, sem incluir o {@link #limite}.
     */
    private double saldo;

    /**
     * Limite da conta: valor que o cliente pode utilizar além do {@link #saldo}
     * disponível. Somente contas especiais podem ter limite, ou seja, o limite
     * de contas "não especiais" não pode ser maior que zero (R03).
     */
    private double limite;

    /**
     * Histórico de movimentações da conta. Deve ser inicializado com uma lista
     * vazia. Sem isto, ao tentar utilizar a lista, dará o erro
     * NullPointerException. Um teste deve verificar se, após instanciar uma
     * conta usando qualquer um dos construtores, a lista de movimentações não é
     * nula, chamando o método {@link #getMovimentacoes()}. (R04)
     */
    private List<Movimentacao> movimentacoes = new ArrayList<>();

    public Conta() {}

    public Conta(boolean especial, final double limite) {
        // Pra que serve agencia dentro deste construtor?
        this.especial = especial;
        this.limite = limite;
    }

    /**
     * Retorna a lista de movimentações.
     *
     * @return
     */
    public List<Movimentacao> getMovimentacoes() {
        return movimentacoes;
    }

    /**
     * Adiciona uma nova movimentação à lista de {@link #movimentacoes}. (R05)
     * Se a movimentação estiver confirmada, seu valor deve ser:
     * <ul>
     * <li>somado ao saldo da conta caso o tipo da movimentação seja 'C';</li>
     * <li>subtraída do saldo da conta caso o tipo da movimentação seja
     * 'D'.</li>
     * </ul>
     *
     * @param movimentacao {@link Movimentacao} a ser adicionada
     */
    public void addMovimentacao(Movimentacao movimentacao) {
        if(movimentacao.isConfirmada()){
            if(movimentacao.getTipo() == 'C')
                saldo+=movimentacao.getValor();
            else if(movimentacao.getTipo() == 'D')
                saldo-=movimentacao.getValor();
        }
    }

    /**
     * Valor total disponível na conta, representando o {@link #saldo} mais o
     * {@link #limite}. (R06)
     *
     * @return
     */
    public double getSaldoTotal() {
        if (especial) return limite + saldo;
        return saldo;
    }

    /**
     * Registra uma nova movimentação para retirar um determinado valor da
     * conta, caso o valor seja menor ou igual ao saldo total. Após realizar um
     * saque, o saldo deve ser atualizado.
     *
     * <p>
     * Se for tentando realizar dois saques ao mesmo tempo com valor igual ao
     * saldo total, somente o primeiro saque deve ser permitido. Isto evita que
     * um cliente que possua dois cartões da mesma conta tente retirar mais
     * dinheiro do que há disponível na conta. Ele poderia tentar fazer isso
     * solicitando a ajuda de outra pessoa. Assim, as duas pessoas poderiam
     * tentar ir em caixas diferentes ao mesmo tempo para tentar realizar um
     * saque em duplicidade.
     * </p>
     *
     * @param valor valor a ser sacado (deve ser um valor positivo)
     */
    public void saque(final double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("Valor para saque deve ser maior que zero!");
        }
        if (saldo < valor) {
            throw new IllegalArgumentException("Saldo em conta é menor que valor para saque!");
        }

        Movimentacao movimentacao = new Movimentacao(this, true);
        movimentacao.setTipo('D');
        movimentacao.setValor(valor);
        this.saldo -= valor;
        movimentacoes.add(movimentacao);
    }

    /**
     * Adiciona uma nova movimentação de depósito em dinheiro tanto realizada
     * por um funcionário quanto em um caixa eletrônico, que deve ser confirmada
     * automaticamente. Considera-se que todos os caixas eletrônicos do banco
     * têm capacidade de contar cédulas.
     *
     * @param valor valor a ser depositado (deve ser um valor positivo)
     */
    public void depositoDinheiro(final double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("O valor de depósito deve ser positivo.");
        }

        Movimentacao movimentacao = new Movimentacao(this, true);
        movimentacao.setTipo('C');
        movimentacao.setValor(valor);
        this.saldo += valor;
        movimentacoes.add(movimentacao);
    }

    /**
     * Adiciona uma nova movimentação de depósito em cheque (que deve ser
     * confirmada posteriormente por um funcionário do banco).
     *
     * @param valor valor a ser depositado (deve ser um valor positivo)
     */
    public void depositoCheque(final double valor) {
        if(valor <= 0) {
            throw new IllegalArgumentException("O valor de depósito deve ser positivo.");
        }

        Movimentacao movimentacao = new Movimentacao(this, false);
        movimentacao.setTipo('D');
        movimentacoes.add(movimentacao);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }
    //verificar se aceita null
    public void setNumero(String numero) {
        if(!numero.matches("\\d{5}-\\d")){
            throw new IllegalArgumentException("Numero invalido. Deve estar no formato 99999-9");
        }

        this.numero = numero;
    }

    public boolean isPoupanca() {
        return poupanca;
    }

    public void setPoupanca(boolean poupanca) {
        this.poupanca = poupanca;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Numero invalido. Deve ser positivo");
        }

        this.saldo = valor;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        if(!especial && limite > 0){
            throw new IllegalStateException("Somente contas especiais podem ter limite");
        }
        this.limite = limite;
    }
}
