package com.plane.models;

import javafx.beans.property.*;

public class ClienteViagem {
    private StringProperty nome_cliente;
    private StringProperty cpf_cliente_fk_pk;
    private StringProperty id_viagem_fk_pk;
    private StringProperty destino_viagem;
    private StringProperty data_viagem;

    // Construtor
    public ClienteViagem(String nome_cliente, String cpf_cliente_fk_pk, String id_viagem_fk_pk, String destino_viagem, String data_viagem) {
        this.nome_cliente = new SimpleStringProperty(nome_cliente);
        this.cpf_cliente_fk_pk = new SimpleStringProperty(cpf_cliente_fk_pk);
        this.id_viagem_fk_pk = new SimpleStringProperty(id_viagem_fk_pk);
        this.destino_viagem = new SimpleStringProperty(destino_viagem);
        this.data_viagem = new SimpleStringProperty(data_viagem);
    }
    
    public ClienteViagem(String cpf_cliente_fk_pk, String id_viagem_fk_pk) {
        this.cpf_cliente_fk_pk = new SimpleStringProperty(cpf_cliente_fk_pk);
        this.id_viagem_fk_pk = new SimpleStringProperty(id_viagem_fk_pk);
    }

    // Getters e Setters para nome_cliente
    public String getNome_cliente() {
        return nome_cliente.get();
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente.set(nome_cliente);
    }

    public StringProperty nome_clienteProperty() {
        return nome_cliente;
    }

    // Getters e Setters para cpf_cliente_fk_pk
    public String getCpf_cliente_fk_pk() {
        return cpf_cliente_fk_pk.get();
    }

    public void setCpf_cliente_fk_pk(String cpf_cliente_fk_pk) {
        this.cpf_cliente_fk_pk.set(cpf_cliente_fk_pk);
    }

    public StringProperty cpf_cliente_fk_pkProperty() {
        return cpf_cliente_fk_pk;
    }

    // Getters e Setters para id_viagem_fk_pk
    public String getId_viagem_fk_pk() {
        return id_viagem_fk_pk.get();
    }

    public void setId_viagem_fk_pk(String id_viagem_fk_pk) {
        this.id_viagem_fk_pk.set(id_viagem_fk_pk);
    }

    public StringProperty id_viagem_fk_pkProperty() {
        return id_viagem_fk_pk;
    }

    // Getters e Setters para destino_viagem
    public String getDestino_viagem() {
        return destino_viagem.get();
    }

    public void setDestino_viagem(String destino_viagem) {
        this.destino_viagem.set(destino_viagem);
    }

    public StringProperty destino_viagemProperty() {
        return destino_viagem;
    }

    // Getters e Setters para data_viagem
    public String getData_viagem() {
        return data_viagem.get();
    }

    public void setData_viagem(String data_viagem) {
        this.data_viagem.set(data_viagem);
    }

    public StringProperty data_viagemProperty() {
        return data_viagem;
    }
}
