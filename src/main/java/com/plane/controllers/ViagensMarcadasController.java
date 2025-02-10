package com.plane.controllers;

import java.io.InputStream;

import com.plane.App;
import java.util.List;
import com.plane.dao.ClienteViagemDAO;
import com.plane.models.AdministradorLogado;
import com.plane.models.ClienteLogado;
import com.plane.models.Viagem;

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

public class ViagensMarcadasController {

    @FXML
    private TableColumn<Viagem, String> DestinoTableColumn;

    @FXML
    private TableColumn<Viagem, String> OrigemTableColumn;

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<Viagem, String> dataTableColumn;

    @FXML
    private TableColumn<Viagem, String> estoqueTableColumn;

    @FXML
    private TableColumn<Viagem, String> idviagemTableColumn;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private TableView<Viagem> tabelaTableView;

    @FXML
    private TableColumn<Viagem, String> valorTableColumn;

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
        carregarViagens();
    }

    // Método para carregar as viagens do cliente logado na TableView
    private void carregarViagens() {
        // Obtém o cliente logado
        ClienteLogado clienteLogado = ClienteLogado.getInstance();
        AdministradorLogado admLogado = AdministradorLogado.getInstance();

        if ((clienteLogado != null && clienteLogado.getCliente() != null) || (admLogado != null && admLogado.getAdministrador() != null)) {
            // Obter as viagens do cliente usando o DAO
            ClienteViagemDAO clienteViagemDAO = new ClienteViagemDAO();
            List<Viagem> viagens = null;

            if(admLogado != null){
                viagens = clienteViagemDAO.getViagensByCpf(admLogado.getAdministrador().getCpf());
            } else {
                viagens = clienteViagemDAO.getViagensByCpf(clienteLogado.getCliente().getCpf());
            }

            // Preenche as colunas da TableView com os dados das viagens
            idviagemTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
            OrigemTableColumn.setCellValueFactory(cellData -> cellData.getValue().origemProperty());
            DestinoTableColumn.setCellValueFactory(cellData -> cellData.getValue().destinoProperty());
            dataTableColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
            estoqueTableColumn.setCellValueFactory(cellData -> cellData.getValue().estoqueProperty());
            valorTableColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty());

            // Adiciona os eventos à TableView
            tabelaTableView.getItems().setAll(viagens);
        }
    }

    @FXML
    private void retornarTelaPrincipal(ActionEvent event) {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }
}
