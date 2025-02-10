package com.plane.models;

public class ClienteLogado {
    private static ClienteLogado instancia;
    private Cliente cliente;

    private ClienteLogado() {} // Construtor privado

    public static ClienteLogado newInstance() {
        if (instancia == null) {
            instancia = new ClienteLogado();
        }
        return instancia;
    }

    public static ClienteLogado getInstance(){
        return instancia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void escreveCliente(){
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("CPF: " + cliente.getCpf());
        System.out.println("Email: " + cliente.getEmail());
    }

    public void deslogar() {
        ClienteLogado.instancia = null;
        this.cliente = null; // Remove o cliente logado
    }
}

