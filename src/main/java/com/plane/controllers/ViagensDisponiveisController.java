package com.plane.controllers;

import java.io.InputStream;
import java.util.List;

import com.plane.App;
import com.plane.dao.ViagemDAO;
import com.plane.models.Viagem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ViagensDisponiveisController {

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

    public void initialize() {
        // Carrega a imagem de fundo
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);

        // Configura as colunas da TableView para pegar as propriedades corretas
        idviagemTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        OrigemTableColumn.setCellValueFactory(cellData -> cellData.getValue().origemProperty());
        DestinoTableColumn.setCellValueFactory(cellData -> cellData.getValue().destinoProperty());
        dataTableColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        estoqueTableColumn.setCellValueFactory(cellData -> cellData.getValue().estoqueProperty());
        valorTableColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty());

        // Carrega as viagens disponíveis na TableView
        ViagemDAO viagemDAO = new ViagemDAO();
        List<Viagem> viagensDisponiveis = viagemDAO.findViagensDisponiveis();  // Método para buscar viagens disponíveis
        ObservableList<Viagem> viagemObservableList = FXCollections.observableArrayList(viagensDisponiveis);
        tabelaTableView.setItems(viagemObservableList);
    }

    @FXML
    private void retornarTelaPrincipal(ActionEvent event) {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }
}
