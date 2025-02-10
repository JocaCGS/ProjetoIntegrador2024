package com.plane.models;

public class Administrador {
    private String nome;
    private String senha;
    private String cpf;
    private String email;

    public Administrador(String nome, String senha, String cpf, String email) {
        this.nome = nome;
        this.senha = senha;
        this.cpf = cpf;
        this.email = email;
    }

    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    
}