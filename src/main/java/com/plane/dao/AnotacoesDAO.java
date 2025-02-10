package com.plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.plane.models.Anotacoes;

public class AnotacoesDAO {

    private Connection connection;

    // Construtor para inicializar a conexão com o banco de dados
    public AnotacoesDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para verificar se já existe uma anotação para o CPF
    public boolean existeAnotacaoParaCpf(String cpfCliente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Anotacoes WHERE cpf_cliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpfCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Retorna true se existir uma anotação
                }
            }
        }
        return false;
    }

    // Método para atualizar uma anotação existente
    public void atualizarAnotacao(Anotacoes anotacao) throws SQLException {
        String sql = "UPDATE Anotacoes SET conteudo = ? WHERE cpf_cliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, anotacao.getConteudo());
            statement.setString(2, anotacao.getCpfCliente());
            statement.executeUpdate();
        }
    }

    // Método para adicionar uma nova anotação (caso não exista uma para o CPF)
    public void adicionarAnotacao(Anotacoes anotacao) throws SQLException {
        String sql = "INSERT INTO Anotacoes (conteudo, cpf_cliente, cpf_adm) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, anotacao.getConteudo());
            statement.setString(2, anotacao.getCpfCliente());
            statement.setString(3, anotacao.getCpfAdm());
            statement.executeUpdate();
        }
    }

    // Método para buscar a última anotação de um cliente (por CPF)
    public Anotacoes buscarUltimaAnotacaoPorCpf(String cpfCliente) throws SQLException {
        String sql = "SELECT * FROM Anotacoes WHERE cpf_cliente = ? ORDER BY id_pk DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cpfCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String conteudo = resultSet.getString("conteudo");
                    String cpfAdm = resultSet.getString("cpf_adm");
                    int id = resultSet.getInt("id_pk");
                    return new Anotacoes(id, conteudo, cpfCliente, cpfAdm);
                }
            }
        }
        return null; // Retorna null se não encontrar a anotação
    }
}
