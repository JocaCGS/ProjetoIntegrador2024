package com.plane.dao;

import com.plane.db.FabricaConexoes;
import com.plane.models.Administrador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    // Variáveis de conexão
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    // Método para buscar todos os administradores
    public List<Administrador> findAll() {
        List<Administrador> administradores = new ArrayList<>();
        String sql = "SELECT * FROM Administrador";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Administrador adm = new Administrador(
                        rs.getString("nome_adm"),
                        rs.getString("senha_adm"),
                        rs.getString("cpf_adm_pk"),
                        rs.getString("email_adm")
                );
                administradores.add(adm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administradores;
    }

    // Método para salvar um novo administrador
    public void save(Administrador administrador) {
        String sql = "INSERT INTO Administrador (cpf_adm_pk, nome_adm, email_adm, senha_adm) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, administrador.getCpf());
            stmt.setString(2, administrador.getNome());
            stmt.setString(3, administrador.getEmail());
            stmt.setString(4, administrador.getSenha());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um administrador por CPF
    public Administrador findByCpf(String cpf) {
        String sql = "SELECT * FROM Administrador WHERE cpf_adm_pk = ?";
        Administrador administrador = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    administrador = new Administrador(
                            rs.getString("nome_adm"),
                            rs.getString("senha_adm"),
                            rs.getString("cpf_adm_pk"),
                            rs.getString("email_adm")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return administrador;
    }

    // Método para deletar um administrador pelo CPF
    public void deleteByCpf(String cpf) {
        String sql = "DELETE FROM Administrador WHERE cpf_adm_pk = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um administrador existente
    public void update(Administrador administrador) {
        String sql = "UPDATE Administrador SET nome_adm = ?, email_adm = ?, senha_adm = ? WHERE cpf_adm_pk = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, administrador.getNome());
            stmt.setString(2, administrador.getEmail());
            stmt.setString(3, administrador.getSenha());
            stmt.setString(4, administrador.getCpf());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
