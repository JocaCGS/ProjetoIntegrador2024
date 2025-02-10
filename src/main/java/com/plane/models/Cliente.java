package com.plane.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    private StringProperty nome;
    private StringProperty senha;
    private StringProperty cpf;
    private StringProperty email;

    public Cliente(String nome, String senha, String cpf, String email) {
        this.nome = new SimpleStringProperty(nome);
        this.senha = new SimpleStringProperty(senha);
        this.cpf = new SimpleStringProperty(cpf);
        this.email = new SimpleStringProperty(email);
    }

    public StringProperty nomeProperty() {
        return nome;
    }
    public String getNome() {
        return nome.get();
    }
    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public StringProperty senhaProperty() {
        return senha;
    }
    public String getSenha() {
        return senha.get();
    }
    public void setSenha(String senha) {
        this.senha.set(senha);
    }

    public StringProperty cpfProperty() {
        return cpf;
    }
    public String getCpf() {
        return cpf.get();
    }
    public void setCpf(String cpf) {
        this.cpf.set(cpf);
    }

    public StringProperty emailProperty() {
        return email;
    }
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
}
