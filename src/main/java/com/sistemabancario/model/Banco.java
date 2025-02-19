package com.sistemabancario.model;

import java.util.Objects;

/**
 * Representa uma instituição bancária com os seguintes requisitos:
 * 
 * - Ao cadastrar um banco, deve ser criada ao menos uma agência.
 * - Não deve-se excluir um banco se este tiver agências cadastradas.
 * 
 * @author Manoel Campos da Silva Filho
 */
public class Banco implements Cadastro {
    private long id;
    private String nome;
    private String sigla;

    /**
     * Número único do banco.
     * 
     * <ul>
     *  <li>Deve ter exatamente 3 algarismos. Senão, uma exceção deve ser 
     * lançada informando o problema. (R01)</li>
     *  <li>
     *      O tipo é String para permitir restringir o total de caracteres, mas deve
     *      ser informado um número válido (somente dígitos de 0 a 9). Senão for um
     *      número válido, uma exceção deve ser lançada informando o problema. 
     *  </li>
     *  <li>
     *      É um atributo obrigatório, não podendo ser nulo nem vazio. 
     *      Se não for preenchido, uma exceção deve ser lançada informando o problema. 
     *      (R02)
     *  </li>
     * </ul>
     */
    private String numero;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        Objects.requireNonNull(numero, "Número não pode ser nulo.");

        if(numero.trim().isEmpty()){
            throw new IllegalArgumentException("Número não pode ser vazio.");
        }

        if(!numero.matches("\\d{3}")){
            throw new IllegalArgumentException("Numero invalido. Deve estar no formato 333");
        }

        this.numero = numero;
    }

}