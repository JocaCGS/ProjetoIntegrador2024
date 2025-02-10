package com.plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plane.db.FabricaConexoes;
import com.plane.models.Viagem;

public class ViagemDAO {

    FabricaConexoes fabrica = FabricaConexoes.getInstance();

    // Método para buscar todas as viagens
    public List<Viagem> findAll() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM Viagem";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Viagem viagem = new Viagem(
                        rs.getString("id_viagem_pk"),
                        rs.getString("origem_viagem"),
                        rs.getString("destino_viagem"),
                        rs.getString("data_viagem"),
                        rs.getString("estoque_viagem"),
                        rs.getString("valor_viagem"),
                        rs.getString("cpf_adm_fk_pk")
                );
                viagens.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viagens;
    }

    // Método para buscar as viagens disponíveis da view 'viagens_disponiveis'
    public List<Viagem> findViagensDisponiveis() {
        List<Viagem> viagensDisponiveis = new ArrayList<>();
        String sql = "SELECT * FROM viagens_disponiveis"; // Atualizado para o novo nome da view

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Viagem viagem = new Viagem(
                        rs.getString("id_viagem_pk"),
                        rs.getString("origem_viagem"),
                        rs.getString("destino_viagem"),
                        rs.getString("data_viagem"),
                        rs.getString("estoque_viagem"),
                        rs.getString("valor_viagem"),
                        rs.getString("cpf_adm_fk_pk")
                );
                viagensDisponiveis.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viagensDisponiveis;
    }

    // Método para salvar uma nova viagem
    public void save(Viagem viagem) {
        String sql = "INSERT INTO Viagem (origem_viagem, destino_viagem, data_viagem, estoque_viagem, valor_viagem, cpf_adm_fk_pk) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, viagem.getOrigem());
            stmt.setString(2, viagem.getDestino());
            stmt.setString(3, viagem.getData());
            stmt.setString(4, viagem.getEstoque());
            stmt.setString(5, viagem.getValor());
            stmt.setString(6, viagem.getCpfADM());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar uma viagem pelo ID
    public Viagem findById(int id) {
        String sql = "SELECT * FROM Viagem WHERE id_viagem_pk = ?";
        Viagem viagem = null;

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    viagem = new Viagem(
                            rs.getString("id_viagem_pk"),
                            rs.getString("origem_viagem"),
                            rs.getString("destino_viagem"),
                            rs.getString("data_viagem"),
                            rs.getString("estoque_viagem"),
                            rs.getString("valor_viagem"),
                            rs.getString("cpf_adm_fk_pk")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return viagem;
    }

    // Método para deletar uma viagem pelo ID
    public void deleteById(int id) {
        String sql = "DELETE FROM Viagem WHERE id_viagem_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar uma viagem existente
    public void update(Viagem viagem) {
        String sql = "UPDATE Viagem SET origem_viagem = ?, destino_viagem = ?, data_viagem = ?, estoque_viagem = ?, valor_viagem = ?, cpf_adm_fk_pk = ? WHERE id_viagem_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, viagem.getOrigem());
            stmt.setString(2, viagem.getDestino());
            stmt.setString(3, viagem.getData());
            stmt.setString(4, viagem.getEstoque());
            stmt.setString(5, viagem.getValor());
            stmt.setString(6, viagem.getCpfADM());
            stmt.setString(7, viagem.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para diminuir o estoque de uma viagem
    public void decrementarEstoque(String idViagem) {
        String sql = "UPDATE Viagem SET estoque_viagem = estoque_viagem - 1 WHERE id_viagem_pk = ? AND estoque_viagem > 0";

        try (Connection conn = fabrica.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idViagem);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementarEstoque(String idViagem) {
        String sql = "UPDATE Viagem SET estoque_viagem = estoque_viagem + 1 WHERE id_viagem_pk = ?";

        try (Connection conn = fabrica.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idViagem);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}