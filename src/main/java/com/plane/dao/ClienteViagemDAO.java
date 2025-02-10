package com.plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plane.db.FabricaConexoes;
import com.plane.models.ClienteViagem;
import com.plane.models.Viagem;

public class ClienteViagemDAO {

    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    // Método para salvar o relacionamento entre cliente e viagem
    public void save(ClienteViagem clienteViagem) {
        String sql = "INSERT INTO Cliente_Viagem (cpf_cliente_fk_pk, id_viagem_fk_pk) VALUES (?, ?)";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definir os valores dos parâmetros na consulta
            stmt.setString(1, clienteViagem.getCpf_cliente_fk_pk());
            stmt.setString(2, clienteViagem.getId_viagem_fk_pk());

            // Executar o insert
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar uma viagem agendada
    public void deleteByCpf(String cpf, String id_viagem) {
        String sql = "DELETE FROM Cliente_Viagem WHERE cpf_cliente_fk_pk = ? AND id_viagem_fk_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, id_viagem);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os eventos de um cliente logado
    public List<Viagem> getViagensByCpf(String cpf) {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT e.id_viagem_pk, e.origem_viagem, e.destino_viagem, e.data_viagem, e.estoque_viagem, e.valor_viagem, e.cpf_adm_fk_pk " +
                     "FROM Viagem e " +
                     "JOIN Cliente_Viagem ce ON e.id_viagem_pk = ce.id_viagem_fk_pk " +
                     "WHERE ce.cpf_cliente_fk_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_viagem_pk");
                String origem = rs.getString("origem_viagem");
                String destino = rs.getString("destino_viagem");
                String data = rs.getString("data_viagem");
                String estoque = rs.getString("estoque_viagem");
                String valor = rs.getString("valor_viagem");
                String cpfADM = rs.getString("cpf_adm_fk_pk");

                // Adiciona o evento à lista
                Viagem viagem = new Viagem(id, origem, destino, data, estoque, valor, cpfADM);
                viagens.add(viagem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }
}
