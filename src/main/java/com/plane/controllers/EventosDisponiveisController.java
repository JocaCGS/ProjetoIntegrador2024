package com.plane.controllers;

import java.io.InputStream;
import java.util.List;

import com.plane.App;
import com.plane.dao.EventoDAO;
import com.plane.models.Evento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EventosDisponiveisController {

    @FXML
    private TableColumn<Evento, String> DestinoTableColumn;

    @FXML
    private TableColumn<Evento, String> OrigemTableColumn;

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<Evento, String> dataTableColumn;

    @FXML
    private TableColumn<Evento, Integer> estoqueTableColumn;

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
    private TableColumn<Evento, Double> valorTableColumn;

    @FXML
    private Text viagensdisponiveisText;

    private EventoDAO eventoDAO = new EventoDAO();

    public void initialize() {
        // Carregar imagem de fundo
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);

        // Configurar colunas da tabela
        idviagemTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        OrigemTableColumn.setCellValueFactory(new PropertyValueFactory<>("origem"));
        DestinoTableColumn.setCellValueFactory(new PropertyValueFactory<>("destino"));
        dataTableColumn.setCellValueFactory(new PropertyValueFactory<>("data"));
        estoqueTableColumn.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        valorTableColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));

        // Carregar eventos disponíveis na tabela
        carregarEventosDisponiveis();
    }

    private void carregarEventosDisponiveis() {
        List<Evento> eventosDisponiveis = eventoDAO.findAvailableEvents(); // Buscar eventos disponíveis no DAO
        ObservableList<Evento> eventos = FXCollections.observableArrayList(eventosDisponiveis);
        tabelaTableView.setItems(eventos); // Adicionar à tabela
    }

    @FXML
    private void retornarTelaPrincipal(ActionEvent event) {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }
}
