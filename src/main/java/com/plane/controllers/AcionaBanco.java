package com.plane.controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.plane.db.FabricaConexoes;
import com.plane.models.Administrador;
import com.plane.models.AdministradorLogado;
import com.plane.models.Cliente;
import com.plane.models.ClienteEvento;
import com.plane.models.ClienteViagem;
import com.plane.models.Evento;
import com.plane.models.Viagem;

public class AcionaBanco {
    // Instanciando o objeto de conexão
    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    // Variáveis de conexão
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    // Método principal (entry point)
    public List<Viagem> coletarDadosViagem() {
        List<Viagem> viagens = new ArrayList<>();
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Exemplo de consulta SQL
            String sql = "SELECT id_viagem_pk, origem_viagem, destino_viagem, data_viagem, estoque_viagem, valor_viagem FROM Viagem";
            
            // Cria o PreparedStatement para a consulta
            stmt = conn.prepareStatement(sql);
            
            // Executa a consulta e obtém o resultado
            rs = stmt.executeQuery();
            
            // Processa o resultado da consulta
            while (rs.next()) {
                // Criando uma nova pessoa e adicionando à lista
                String id = rs.getString("id_viagem_pk");
                String origem = rs.getString("origem_viagem");
                String destino = rs.getString("destino_viagem");
                String data = rs.getString("data_viagem");
                String estoque = rs.getString("estoque_viagem");
                String valor = rs.getString("valor_viagem");
                Viagem viagem = new Viagem(id, origem, destino, data, estoque, valor, AdministradorLogado.newInstance().getAdministrador().getCpf());
                viagens.add(viagem);
            }

            return viagens;
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return viagens;
    }


    public List<Evento> coletarDadosEvento() {
        List<Evento> eventos = new ArrayList<>();
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Exemplo de consulta SQL
            String sql = "SELECT id_evento_pk, origem_evento, destino_evento, date_evento, estoque_evento, valor_evento FROM Evento";
            
            // Cria o PreparedStatement para a consulta
            stmt = conn.prepareStatement(sql);
            
            // Executa a consulta e obtém o resultado
            rs = stmt.executeQuery();
            
            // Processa o resultado da consulta
            while (rs.next()) {
                // Criando uma nova pessoa e adicionando à lista
                String id = rs.getString("id_evento_pk");
                String origem = rs.getString("origem_evento");
                String destino = rs.getString("destino_evento");
                String data = rs.getString("date_evento");
                String estoque = rs.getString("estoque_evento");
                String valor = rs.getString("valor_evento");
                Evento evento = new Evento(id, origem, destino, data, estoque, valor, AdministradorLogado.newInstance().getAdministrador().getCpf());
                eventos.add(evento);
            }

            return eventos;
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return eventos;
    }


    public List<Cliente> coletarDadosCliente() {
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Exemplo de consulta SQL
            String sql = "SELECT cpf_cliente_pk, nome_cliente, email_cliente, senha_cliente FROM Cliente";
            
            // Cria o PreparedStatement para a consulta
            stmt = conn.prepareStatement(sql);
            
            // Executa a consulta e obtém o resultado
            rs = stmt.executeQuery();
            
            // Processa o resultado da consulta
            while (rs.next()) {
                // Criando uma nova pessoa e adicionando à lista
                String cpfclientepk = rs.getString("cpf_cliente_pk");
                String nome_cliente = rs.getString("nome_cliente");
                String email_cliente = rs.getString("email_cliente");
                String senha_cliente = rs.getString("senha_cliente");
                Cliente cliente = new Cliente(nome_cliente, senha_cliente, cpfclientepk, email_cliente);
                clientes.add(cliente);

            }

            return clientes;
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientes;
    }

    
    public List<ClienteViagem> coletarDadosClienteViagem() {
        List<ClienteViagem> clienteViagem = new ArrayList<>();
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Exemplo de consulta SQL
            String sql = "SELECT * FROM View_Clientes_Viagem";
            
            // Cria o PreparedStatement para a consulta
            stmt = conn.prepareStatement(sql);
            
            // Executa a consulta e obtém o resultado
            rs = stmt.executeQuery();
            
            // Processa o resultado da consulta
            while (rs.next()) {
                // Criando uma nova pessoa e adicionando à lista
                String nome_cliente = rs.getString("Nome_Cliente");
                String cpf_cliente = rs.getString("CPF_Cliente");
                String id_viagem = rs.getString("ID_Viagem");
                String destino_viagem = rs.getString("Destino_Viagem");
                String data_viagem = rs.getString("Data_Viagem");
                ClienteViagem cliente_viagem = new ClienteViagem(nome_cliente, cpf_cliente, id_viagem, destino_viagem, data_viagem);
                clienteViagem.add(cliente_viagem);
            }

            return clienteViagem;
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clienteViagem;
    }

    public List<ClienteEvento> coletarDadosClienteEvento() {
        List<ClienteEvento> clienteEvento = new ArrayList<>();
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Exemplo de consulta SQL
            String sql = "SELECT * FROM View_Clientes_Evento";
            
            // Cria o PreparedStatement para a consulta
            stmt = conn.prepareStatement(sql);
            
            // Executa a consulta e obtém o resultado
            rs = stmt.executeQuery();
            
            // Processa o resultado da consulta
            while (rs.next()) {
                // Criando uma nova pessoa e adicionando à lista
                String nome_cliente = rs.getString("Nome_Cliente");
                String cpf_cliente = rs.getString("CPF_Cliente");
                String id_viagem = rs.getString("ID_Evento");
                String destino_viagem = rs.getString("Destino_Evento");
                String data_viagem = rs.getString("Data_Evento");   
                ClienteEvento cliente_evento = new ClienteEvento(nome_cliente, cpf_cliente, id_viagem, destino_viagem, data_viagem);
                clienteEvento.add(cliente_evento);
            }

            return clienteEvento;
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clienteEvento;
    }




    public void inserirDados(String nome, String senha, String cpf, String email) {
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Consulta SQL para inserir dados
            String sql = "INSERT INTO Cliente (cpf_cliente_pk, nome_cliente, email_cliente, senha_cliente) VALUES (?, ?, ?, ?)";
            
            // Cria o PreparedStatement para a inserção
            stmt = conn.prepareStatement(sql);
            
            // Define os valores nos placeholders
            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, email);
            stmt.setString(4, senha);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void inserirDadosViagem(String origem, String destino, String data, String estoque, Double valor) {
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Consulta SQL para inserir dados
            String sql = "INSERT INTO Viagem (origem_viagem, destino_viagem, data_viagem, estoque_viagem, valor_viagem, cpf_adm_fk_pk) VALUES (?, ?, ?, ?, ?, ?)";
            System.out.println("\n CPF ADM REGISTRADO: " +  AdministradorLogado.newInstance().getAdministrador().getCpf() + "\n");
            
            // Cria o PreparedStatement para a inserção
            stmt = conn.prepareStatement(sql);
            
            // Define os valores nos placeholders
            stmt.setString(1, origem);
            stmt.setString(2, destino   );
            stmt.setString(3, data);
            stmt.setString(4, estoque);
            stmt.setDouble(5, valor);
            stmt.setString(6, AdministradorLogado.newInstance().getAdministrador().getCpf());

            stmt.executeUpdate();
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void inserirDadosEvento(String origem, String destino, String data, String estoque, Double valor) {
        
        try {
            // Obtém a conexão com o banco de dados
            conn = fabrica.getConnection();
            
            // Consulta SQL para inserir dados
            String sql = "INSERT INTO Evento (origem_evento, destino_evento, date_evento, estoque_evento, valor_evento, cpf_adm_fk_pk) VALUES (?, ?, ?, ?, ?, ?)";
            System.out.println("\n CPF ADM REGISTRADO: " +  AdministradorLogado.newInstance().getAdministrador().getCpf() + "\n");
            
            // Cria o PreparedStatement para a inserção
            stmt = conn.prepareStatement(sql);
            
            // Define os valores nos placeholders
            stmt.setString(1, origem);
            stmt.setString(2, destino);
            stmt.setString(3, data);
            stmt.setString(4, estoque);
            stmt.setDouble(5, valor);
            stmt.setString(6, AdministradorLogado.newInstance().getAdministrador().getCpf());
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            // Tratar exceção
            e.printStackTrace();
        } finally {
            // Fechar os recursos (conexão, statement e resultSet)
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    public boolean verificarLoginAdministrador(String email, String senha) {
        String sql = "{CALL VerificarLoginAdministrador(?, ?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) { // Use CallableStatement aqui
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.registerOutParameter(3, java.sql.Types.BOOLEAN); // Registra o parâmetro de saída
            
            stmt.execute();
            return stmt.getBoolean(3); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarLoginCliente(String email, String senha) {
        String sql = "{CALL VerificarLoginCliente(?, ?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) { // Use CallableStatement aqui
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.registerOutParameter(3, java.sql.Types.BOOLEAN); // Registra o parâmetro de saída
            
            stmt.execute();
            return stmt.getBoolean(3); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean verificarADM(String cpf, String senha) {
        String sql = "{CALL VerificarADM(?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) { // Use CallableStatement aqui
            
            stmt.setString(1, cpf);
            stmt.registerOutParameter(2, java.sql.Types.BOOLEAN); // Registra o parâmetro de saída
            
            stmt.execute();
            return stmt.getBoolean(2); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean VerificarCpfAssociadoADM(String cpf, String senha) {
        String sql = "{CALL VerificarADM(?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) { 
            
            stmt.setString(1, cpf);
            stmt.registerOutParameter(2, java.sql.Types.BOOLEAN); // Registra o parâmetro de saída
            
            stmt.execute();
            return stmt.getBoolean(2); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String procurarCpfAssociadoADM(String email){
        String sql = "{CALL RetornarCpfAssociadoADM(?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, email);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            stmt.execute();
            return stmt.getString(2); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } 
    }

    public String procurarCpfAssociadoCliente(String email){
        String sql = "{CALL RetornarCpfAssociadoCliente(?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, email);
            stmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            stmt.execute();
            return stmt.getString(2); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public Cliente obterCliente(String cpf) {
        String sql = "SELECT cpf_cliente_pk, nome_cliente, email_cliente, senha_cliente FROM Cliente WHERE cpf_cliente_pk = ?";
        try (Connection conn = fabrica.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome_cliente");
                    String email = rs.getString("email_cliente");
                    String senha = rs.getString("senha_cliente");
                    return new Cliente(nome, senha, cpf, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Administrador obterAdministrador(String cpf) {
        String sql = "SELECT nome_adm, email_adm, senha_adm FROM Administrador WHERE cpf_adm_pk = ?";
        try (Connection conn = fabrica.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    String nome = rs.getString("nome_adm");
                    String senha = rs.getString("senha_adm");
                    String email = rs.getString("email_adm");

                    return new Administrador(nome, senha, cpf, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    
}
