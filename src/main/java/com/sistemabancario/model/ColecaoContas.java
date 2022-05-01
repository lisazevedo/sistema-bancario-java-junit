package com.sistemabancario.model;

import java.util.HashMap;

public class ColecaoContas {
    /**
     * Coleção de contas feita em HashMap,
     * sendo a key o número da Conta, e o value o seu objeto
     */
    private HashMap<String, Conta> contas;

    /**
     * Instancia uma coleção de contas para ser utilizado nas movimentações
     * @param contas a coleção {@link contas} para inicializar a ColecaoContas
     */
    public ColecaoContas(HashMap<String, Conta> contas) {
        this.contas = contas;
    }

    /**
     * Pesquisa por um Conta utilizando o seu numero dentro do HashMap
     * @param numero
     * @return {@link Conta}
     */
    public Conta getConta(String numero) {
        return contas.get(numero);
    }

    /**
     * Adiciona um novo Conta ao HashMap de Coleção de contas
     * @param conta
     * @param numero
     */
    public void putConta(Conta conta, String numero) {
        contas.put(numero, conta);
    }

    public static ColecaoContas inicializaContas() {
        HashMap<String, Conta> contasMap = new HashMap<>();

        Banco itaivis = new Banco();
        itaivis.setId(1);
        itaivis.setNome("Itaívis");
        itaivis.setNumero("194");
        itaivis.setSigla("IT");

        // Conta 1
        Conta conta1 = new Conta(false, 4000);
        String numeroConta1 = "12345-1";
        conta1.setNumero(numeroConta1);

        // Conta 2
        Conta conta2 = new Conta(true, 15000);
        String numeroConta2 = "24355-7";
        conta2.setNumero(numeroConta2);

        // Conta 3
        Conta conta3 = new Conta(false, 500);
        String numeroConta3 = "67842-3";
        conta3.setNumero(numeroConta3);

        contasMap.put(numeroConta1, conta1);
        contasMap.put(numeroConta2, conta2);
        contasMap.put(numeroConta3, conta3);

        return new ColecaoContas(contasMap);
    }
}
