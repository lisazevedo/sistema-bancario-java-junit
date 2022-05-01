package com.sistemabancario.model;

import java.util.HashMap;

public class ColecaoClientes {

    /**
     * Coleção de clientes feita em HashMap,
     * sendo a key o cpf do Cliente, e o value o seu objeto
     */
    private HashMap<String, Cliente> clientes;

    /**
     * Instancia uma coleção de clientes para ser utilizado nas movimentações
     * @param clientes a coleção {@link clientes} para inicializar a ColecaoClientes
     */
    public ColecaoClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Pesquisa por um Cliente utilizando o seu cpf dentro do HashMap
     * @param cpf
     * @return {@link Cliente}
     */
    public Cliente getCliente(String cpf) {
        return clientes.get(cpf);
    }

    /**
     * Adiciona um novo Cliente ao HashMap de Coleção de clientes
     * @param cliente
     * @param cpf
     */
    public void putCliente(Cliente cliente, String cpf) {
        clientes.put(cpf, cliente);
    }

    public static ColecaoClientes inicializaClientes() {
        HashMap<String, Cliente> contasMap = new HashMap<>();

        // Cliente 1
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("Lis Azevedo");
        String cliente1Cpf = "23906078027";
        cliente1.setCpf(cliente1Cpf);
        cliente1.setSenha("1234");
        Conta conta1 = new Conta(false, 4000);
        String numeroConta1 = "12345-1";
        conta1.setSaldo(2000);
        conta1.setNumero(numeroConta1);
        cliente1.setConta(conta1);

        // Cliente 2
        Cliente cliente2 = new Cliente();
        cliente2.setId(2);
        cliente2.setNome("Leonardo Andrade");
        String cliente2Cpf = "30612517071";
        cliente2.setCpf(cliente2Cpf);
        cliente2.setSenha("1234");
        Conta conta2 = new Conta(true, 15000);
        String numeroConta2 = "24355-7";
        conta2.setSaldo(7500);
        conta2.setNumero(numeroConta2);
        cliente2.setConta(conta2);

        // Cliente 3
        Cliente cliente3 = new Cliente();
        cliente3.setId(3);
        cliente3.setNome("Pablo Henrique");
        String cliente3Cpf = "36601046043";
        cliente3.setCpf(cliente3Cpf);
        cliente3.setSenha("1234");
        Conta conta3 = new Conta(false, 500);
        String numeroConta3 = "67842-3";
        conta3.setSaldo(15000);
        conta3.setNumero(numeroConta3);
        cliente3.setConta(conta3);

        // Cliente 4
        Cliente cliente4 = new Cliente();
        cliente4.setId(4);
        cliente4.setNome("Victor Hugo Makeks");
        String cliente4Cpf = "46566374086";
        cliente4.setCpf(cliente4Cpf);
        cliente4.setSenha("1234");
        Conta conta4 = new Conta(false, 500);
        String numeroConta4 = "67332-3";
        conta4.setSaldo(10);
        conta4.setNumero(numeroConta4);
        cliente4.setConta(conta4);

        contasMap.put(cliente1Cpf, cliente1);
        contasMap.put(cliente2Cpf, cliente2);
        contasMap.put(cliente3Cpf, cliente3);
        contasMap.put(cliente4Cpf, cliente4);

        return new ColecaoClientes(contasMap);
    }
}
