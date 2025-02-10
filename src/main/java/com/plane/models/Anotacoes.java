package com.plane.models;

public class Anotacoes {
    private int id;
    private String conteudo;
    private String cpfCliente;
    private String cpfAdm;

    // Construtor com todos os parâmetros
    public Anotacoes(int id, String conteudo, String cpfCliente, String cpfAdm) {
        this.id = id;
        this.conteudo = conteudo;
        this.cpfCliente = cpfCliente;
        this.cpfAdm = cpfAdm;
    }

    // Construtor para adicionar nova anotação (sem ID)
    public Anotacoes(String conteudo, String cpfCliente, String cpfAdm) {
        this.conteudo = conteudo;
        this.cpfCliente = cpfCliente;
        this.cpfAdm = cpfAdm;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfAdm() {
        return cpfAdm;
    }

    public void setCpfAdm(String cpfAdm) {
        this.cpfAdm = cpfAdm;
    }
}
