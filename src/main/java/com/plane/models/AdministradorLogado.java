package com.plane.models;

public class AdministradorLogado {
    private static AdministradorLogado instancia;
    private Administrador administrador;

    private AdministradorLogado() {} // Construtor privado

    public static AdministradorLogado newInstance(){
        if (instancia == null) {
            instancia = new AdministradorLogado();
        }
        return instancia;
    }

    public static AdministradorLogado getInstance() {
        return instancia;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador cliente) {
        this.administrador = cliente;
    }

    public void escreveAdministrador() {
        System.out.println("Nome: " + administrador.getNome());
        System.out.println("CPF: " + administrador.getCpf());
        System.out.println("Email: " + administrador.getEmail());
    }

    public void deslogar() {
        AdministradorLogado.instancia = null;
        this.administrador = null; // Remove o cliente logado
    }
}

