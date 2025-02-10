package com.plane.models;

import javafx.beans.property.*;

public class ClienteEvento {
    private StringProperty nome_cliente;
    private StringProperty cpf_cliente_fk_pk;
    private StringProperty id_evento_fk_pk;
    private StringProperty destino_evento;
    private StringProperty data_evento;

    // Construtor
    public ClienteEvento(String nome_cliente, String cpf_cliente_fk_pk, String id_evento_fk_pk, String destino_evento, String data_evento) {
        this.nome_cliente = new SimpleStringProperty(nome_cliente);
        this.cpf_cliente_fk_pk = new SimpleStringProperty(cpf_cliente_fk_pk);
        this.id_evento_fk_pk = new SimpleStringProperty(id_evento_fk_pk);
        this.destino_evento = new SimpleStringProperty(destino_evento);
        this.data_evento = new SimpleStringProperty(data_evento);
    }

    public ClienteEvento(String cpf_cliente_fk_pk, String id_evento_fk_pk) {
        this.cpf_cliente_fk_pk = new SimpleStringProperty(cpf_cliente_fk_pk);
        this.id_evento_fk_pk = new SimpleStringProperty(id_evento_fk_pk);
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

    // Getters e Setters para id_evento_fk_pk
    public String getId_evento_fk_pk() {
        return id_evento_fk_pk.get();
    }

    public void setId_evento_fk_pk(String id_evento_fk_pk) {
        this.id_evento_fk_pk.set(id_evento_fk_pk);
    }

    public StringProperty id_evento_fk_pkProperty() {
        return id_evento_fk_pk;
    }

    // Getters e Setters para destino_evento
    public String getDestino_evento() {
        return destino_evento.get();
    }

    public void setDestino_evento(String destino_evento) {
        this.destino_evento.set(destino_evento);
    }

    public StringProperty destino_eventoProperty() {
        return destino_evento;
    }

    // Getters e Setters para data_evento
    public String getData_evento() {
        return data_evento.get();
    }

    public void setData_evento(String data_evento) {
        this.data_evento.set(data_evento);
    }

    public StringProperty data_eventoProperty() {
        return data_evento;
    }
}
