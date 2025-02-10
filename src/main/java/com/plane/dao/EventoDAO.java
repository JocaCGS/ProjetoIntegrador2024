package com.plane.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plane.db.FabricaConexoes;
import com.plane.models.Evento;

public class EventoDAO {

    FabricaConexoes fabrica = FabricaConexoes.getInstance();

    // Método para buscar todos os eventos
    public List<Evento> findAll() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM Evento";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getString("id_evento_pk"),
                        rs.getString("origem_evento"),
                        rs.getString("destino_evento"),
                        rs.getString("date_evento"),
                        rs.getString("estoque_evento"),
                        rs.getString("valor_evento"),
                        rs.getString("cpf_adm_fk_pk")
                );
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // Método para salvar um novo evento
    public void save(Evento evento) {
        String sql = "INSERT INTO Evento (origem_evento, destino_evento, date_evento, estoque_evento, valor_evento, cpf_adm_fk_pk) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, evento.getOrigem());
            stmt.setString(2, evento.getDestino());
            stmt.setString(3, evento.getData());
            stmt.setString(4, evento.getEstoque());
            stmt.setString(5, evento.getValor());
            stmt.setString(6, evento.getCpfADM());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar um evento pelo ID
    public Evento findById(int id) {
        String sql = "SELECT * FROM Evento WHERE id_evento_pk = ?";
        Evento evento = null;

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    evento = new Evento(
                            rs.getString("id_evento_pk"),
                            rs.getString("origem_evento"),
                            rs.getString("destino_evento"),
                            rs.getString("date_evento"),
                            rs.getString("estoque_evento"),
                            rs.getString("valor_evento"),
                            rs.getString("cpf_adm_fk_pk")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return evento;
    }

    // Método para deletar um evento pelo ID
    public void deleteById(int id) {
        String sql = "DELETE FROM Evento WHERE id_evento_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar um evento existente
    public void update(Evento evento) {
        String sql = "UPDATE Evento SET origem_evento = ?, destino_evento = ?, date_evento = ?, estoque_evento = ?, valor_evento = ?, cpf_adm_fk_pk = ? WHERE id_evento_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, evento.getOrigem());
            stmt.setString(2, evento.getDestino());
            stmt.setString(3, evento.getData());
            stmt.setString(4, evento.getEstoque());
            stmt.setString(5, evento.getValor());
            stmt.setString(6, evento.getCpfADM());
            stmt.setString(7, evento.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar eventos disponíveis (VIEW)
    public List<Evento> findAvailableEvents() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos_disponiveis"; // Consulta na VIEW

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getString("id_evento"),       // Ajuste conforme os nomes das colunas na VIEW
                        rs.getString("nome"),
                        rs.getString("local"),
                        rs.getString("data"),
                        rs.getString("estoque"),
                        rs.getString("valor"),
                        null // A VIEW não inclui 'cpf_adm_fk_pk', então é definido como null
                );
                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // Método para decrementar o estoque de um evento
    public void decrementEstoque(int idEvento) {
        String sql = "UPDATE Evento SET estoque_evento = estoque_evento - 1 WHERE id_evento_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void incrementarEstoque(String idEvento) {
        String sql = "UPDATE Evento SET estoque_evento = estoque_evento + 1 WHERE id_evento_pk = ?";

        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idEvento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
