package com.plane.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Viagem {
    private StringProperty id;
    private StringProperty origem;
    private StringProperty destino;
    private StringProperty data;
    private StringProperty estoque;
    private StringProperty valor;
    private StringProperty cpfADM;

    // Construtor para inclusão de viagens (sem ID, usado ao adicionar novas viagens)
    public Viagem(String origem, String destino, String data, String estoque, String valor, String cpfADM) {
        this.id = new SimpleStringProperty();  // ID vazio para novas viagens
        this.origem = new SimpleStringProperty(origem);
        this.destino = new SimpleStringProperty(destino);
        this.data = new SimpleStringProperty(data);
        this.estoque = new SimpleStringProperty(estoque);
        this.valor = new SimpleStringProperty(valor);
        this.cpfADM = new SimpleStringProperty(cpfADM);
    }

    // Construtor para incluir o ID (usado para consultas, atualizações ou ao carregar dados)
    public Viagem(String id, String origem, String destino, String data, String estoque, String valor, String cpfADM) {
        this.id = new SimpleStringProperty(id);
        this.origem = new SimpleStringProperty(origem);
        this.destino = new SimpleStringProperty(destino);
        this.data = new SimpleStringProperty(data);
        this.estoque = new SimpleStringProperty(estoque);
        this.valor = new SimpleStringProperty(valor);
        this.cpfADM = new SimpleStringProperty(cpfADM);
    }

    // Construtor vazio (útil em alguns casos como quando você precisa criar uma instância e depois preencher)
    public Viagem() {
        this.id = new SimpleStringProperty();
        this.origem = new SimpleStringProperty();
        this.destino = new SimpleStringProperty();
        this.data = new SimpleStringProperty();
        this.estoque = new SimpleStringProperty();
        this.valor = new SimpleStringProperty();
        this.cpfADM = new SimpleStringProperty();
    }

    // Getters e setters com propriedades observáveis
    public StringProperty idProperty() {
        return id;
    }

    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty origemProperty() {
        return origem;
    }

    public String getOrigem() {
        return origem.get();
    }

    public void setOrigem(String origem) {
        this.origem.set(origem);
    }

    public StringProperty destinoProperty() {
        return destino;
    }

    public String getDestino() {
        return destino.get();
    }

    public void setDestino(String destino) {
        this.destino.set(destino);
    }

    public StringProperty dataProperty() {
        return data;
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public StringProperty estoqueProperty() {
        return estoque;
    }

    public String getEstoque() {
        return estoque.get();
    }

    public void setEstoque(String estoque) {
        this.estoque.set(estoque);
    }

    public StringProperty valorProperty() {
        return valor;
    }

    public String getValor() {
        return valor.get();
    }

    public void setValor(String valor) {
        this.valor.set(valor);
    }

    public StringProperty cpfADMProperty() {
        return cpfADM;
    }

    public String getCpfADM() {
        return cpfADM.get();
    }

    public void setCpfADM(String cpfADM) {
        this.cpfADM.set(cpfADM);
    }
}
