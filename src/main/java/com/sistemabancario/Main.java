package com.sistemabancario;

import java.util.Objects;
import java.util.Scanner;

import com.sistemabancario.model.Cliente;
import com.sistemabancario.model.ColecaoClientes;
import com.sistemabancario.model.ColecaoContas;
import com.sistemabancario.model.Conta;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando coleção de clientes
        ColecaoClientes clientes = ColecaoClientes.inicializaClientes();
        // Criando coleção de contas
        ColecaoContas contas = ColecaoContas.inicializaContas();

        System.out.println("SISTEMA BANCO ITAÍVIS");
        System.out.println("Insira os dados para se autenticar: ");
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Cliente cliente = clientes.getCliente(cpf);

        while (!Cliente.autentica(cliente, cpf, senha)) {
            System.out.println("Acesso negado! Tente novamente!");

            System.out.print("CPF: ");
            cpf = scanner.nextLine();
            
            System.out.print("Senha: ");
            senha = scanner.nextLine();

            cliente = clientes.getCliente(cpf);
        }

        System.out.println("Autenticado!");

        System.out.println("OPÇÕES: ");
        System.out.println("1 - SAQUE");
        System.out.println("2 - DEPÓSITO");
        System.out.println("3 - TRANSFERÊNCIA");
        System.out.println("4 - SAIR");
        Integer opcao = scanner.nextInt();
        Double valor;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        try {
            while(opcao != 4) {
                switch (opcao) {
                    case 1:
                        System.out.println("==== SAQUE ====");
                        System.out.print("Valor: ");
                        valor = scanner.nextDouble();
                        cliente.getConta().saque(valor);
                        
                        break;
                    case 2:
                        System.out.println("==== DEPÓSITO ====");
                        System.out.print("Valor: ");
                        valor = scanner.nextDouble();
                        cliente.getConta().depositoDinheiro(valor);
                        break; 
                    case 3:
                        System.out.println("==== TRANSFERÊNCIA ====");
                        System.out.print("Conta para depósito: ");
                        String conta = scanner.next();
                        System.out.print("Valor: ");
                        valor = scanner.nextDouble();
                        cliente.getConta().saque(valor);
                        Conta contaParaDeposito = contas.getConta(conta);
                        contaParaDeposito.depositoDinheiro(valor);
                        break;
                    default:
                        System.out.println("Opção não suportada!");
                        break;
                    }
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Saldo Atual: " + cliente.getConta().getSaldoTotal());
                System.out.println("OPÇÕES: ");
                System.out.println("1 - SAQUE");
                System.out.println("2 - DEPÓSITO");
                System.out.println("3 - TRANSFERÊNCIA");
                System.out.println("4 - SAIR");
                opcao = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
            
        System.out.println("BYE BYE!");

        scanner.close();
    }
}
