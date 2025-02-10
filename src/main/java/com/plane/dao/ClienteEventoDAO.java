package com.plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plane.db.FabricaConexoes;
import com.plane.models.ClienteEvento;
import com.plane.models.Evento;

public class ClienteEventoDAO {

    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    // Método para salvar o relacionamento entre cliente e evento
    public void save(ClienteEvento clienteEvento) {
        String sql = "INSERT INTO Cliente_Evento (cpf_cliente_fk_pk, id_evento_fk_pk) VALUES (?, ?)";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Definir os valores dos parâmetros na consulta
            stmt.setString(1, clienteEvento.getCpf_cliente_fk_pk());
            stmt.setString(2, clienteEvento.getId_evento_fk_pk());

            // Executar o insert
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um evento agendado para um cliente
    public void deleteByCpf(String cpf, String id_evento) {
        String sql = "DELETE FROM Cliente_Evento WHERE cpf_cliente_fk_pk = ? AND id_evento_fk_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, id_evento);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os eventos de um cliente logado
    public List<Evento> getEventosByCpf(String cpf) {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT e.id_evento_pk, e.origem_evento, e.destino_evento, e.date_evento, e.estoque_evento, e.valor_evento, e.cpf_adm_fk_pk " +
                     "FROM Evento e " +
                     "JOIN Cliente_Evento ce ON e.id_evento_pk = ce.id_evento_fk_pk " +
                     "WHERE ce.cpf_cliente_fk_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id_evento_pk");
                String origem = rs.getString("origem_evento");
                String destino = rs.getString("destino_evento");
                String data = rs.getString("date_evento");
                String estoque = rs.getString("estoque_evento");
                String valor = rs.getString("valor_evento");
                String cpfADM = rs.getString("cpf_adm_fk_pk");

                // Adiciona o evento à lista
                Evento evento = new Evento(id, origem, destino, data, estoque, valor, cpfADM);
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }
}
