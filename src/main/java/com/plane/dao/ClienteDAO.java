package com.plane.dao;

import com.plane.db.FabricaConexoes;
import com.plane.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements GenericDAO<Cliente> {

    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    // Variáveis de conexão
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    @Override
    public void insert(Cliente cliente) {
        String sql = "INSERT INTO Cliente (cpf_cliente_pk, nome_cliente, email_cliente, senha_cliente) VALUES (?,?,?,?)";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getCpf());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getSenha());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update(Cliente cliente){
        String sql = "UPDATE Cliente SET nome_cliente =?, email_cliente =?, senha_cliente =? WHERE cpf_cliente_pk =?";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getCpf());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf_cliente_pk =?";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente findById(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf_cliente_pk =?";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getString("cpf_cliente_pk"),
                        rs.getString("nome_cliente"),
                        rs.getString("email_cliente"),
                        rs.getString("senha_cliente")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = fabrica.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Cliente(
                    rs.getString("cpf_cliente_pk"),
                    rs.getString("nome_cliente"),
                    rs.getString("email_cliente"),
                    rs.getString("senha_cliente")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

}
