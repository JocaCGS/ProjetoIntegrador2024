package com.plane.controllers;

import java.io.InputStream;
import java.util.List;

import com.plane.App;
import com.plane.dao.ClienteEventoDAO;
import com.plane.models.AdministradorLogado;
import com.plane.models.ClienteLogado;
import com.plane.models.Evento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EventosMarcadosController {

    @FXML
    private TableColumn<Evento, String> DestinoTableColumn;

    @FXML
    private TableColumn<Evento, String> OrigemTableColumn;

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<Evento, String> dataTableColumn;

    @FXML
    private TableColumn<Evento, String> estoqueTableColumn;

    @FXML
    private TableColumn<Evento, String> idviagemTableColumn;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private TableView<Evento> tabelaTableView;

    @FXML
    private TableColumn<Evento, String> valorTableColumn;

    @FXML
    private Text viagensdisponiveisText;

    // Método para carregar a imagem de fundo
    private void carregarImagemFundo() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
    }

    // Método de inicialização
    @FXML
    private void initialize() {
        // Carrega a imagem de fundo
        carregarImagemFundo();
        // Carrega os eventos do cliente logado
        carregarEventos();
    }

    // Método para carregar os eventos do cliente logado na TableView
    private void carregarEventos() {
        // Obtém o cliente logado
        ClienteLogado clienteLogado = ClienteLogado.getInstance();
        AdministradorLogado admLogado = AdministradorLogado.getInstance();

        if ((clienteLogado != null && clienteLogado.getCliente() != null) || (admLogado != null && admLogado.getAdministrador() != null)) {
            // Obter as viagens do cliente usando o DAO
            ClienteEventoDAO clienteEventoDAO = new ClienteEventoDAO();
            List<Evento> eventos = null;

            if(admLogado != null){
                eventos = clienteEventoDAO.getEventosByCpf(admLogado.getAdministrador().getCpf());
            } else {
                eventos = clienteEventoDAO.getEventosByCpf(clienteLogado.getCliente().getCpf());
            }

            // Preenche as colunas da TableView com os dados dos eventos
            idviagemTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
            OrigemTableColumn.setCellValueFactory(cellData -> cellData.getValue().origemProperty());
            DestinoTableColumn.setCellValueFactory(cellData -> cellData.getValue().destinoProperty());
            dataTableColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
            estoqueTableColumn.setCellValueFactory(cellData -> cellData.getValue().estoqueProperty());
            valorTableColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty());

            // Adiciona os eventos à TableView
            tabelaTableView.getItems().setAll(eventos);
        }
    }

    // Método para retornar à tela principal
    @FXML
    private void retornarTelaPrincipal(ActionEvent event) {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }
}
