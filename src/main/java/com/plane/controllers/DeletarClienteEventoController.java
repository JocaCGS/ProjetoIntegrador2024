package com.plane.controllers;

import java.io.InputStream;
import java.util.List;

import com.plane.App;
import com.plane.dao.ClienteEventoDAO;
import com.plane.dao.EventoDAO;
import com.plane.models.ClienteEvento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DeletarClienteEventoController {

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<ClienteEvento, String> colunaidclienteTableColumn;

    @FXML
    private TableColumn<ClienteEvento, String> colunaideventoTableColumn;

    @FXML
    private TableColumn<ClienteEvento, String> dataTableColumn;

    @FXML
    private TableColumn<ClienteEvento, String>destinoTableColumn;

    @FXML
    private TableColumn<ClienteEvento, String> nomeclienteTableColumn;


    @FXML
    private Button deletarassociacaoButton;

    @FXML
    private Text deletarclienteeventoText;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Text menuadministracaoText;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private TableView<ClienteEvento> tabelaTableView;

    
    ObservableList<ClienteEvento> clienteEvento = FXCollections.observableArrayList();
    public String idEventoClick = null;
    public String idClienteClick = null;

    public void initialize() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);

        lerBDClienteEvento();
        nomeclienteTableColumn.setCellValueFactory(celldata -> celldata.getValue().nome_clienteProperty());
        colunaidclienteTableColumn.setCellValueFactory(cellData -> cellData.getValue().cpf_cliente_fk_pkProperty());
        colunaideventoTableColumn.setCellValueFactory(cellData -> cellData.getValue().id_evento_fk_pkProperty());
        destinoTableColumn.setCellValueFactory(cellData -> cellData.getValue().destino_eventoProperty());
        dataTableColumn.setCellValueFactory(cellData -> cellData.getValue().data_eventoProperty());
        
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemDeletar = new MenuItem("Deletar");

        contextMenu.getItems().addAll(menuItemDeletar);

        tabelaTableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY || event.getButton() == MouseButton.PRIMARY) { // Clique com o botão direito
                // Verifique se um item foi selecionado
                if (tabelaTableView.getSelectionModel().getSelectedItem() != null) {
                    idEventoClick = tabelaTableView.getSelectionModel().getSelectedItem().getId_evento_fk_pk();
                    idClienteClick = tabelaTableView.getSelectionModel().getSelectedItem().getCpf_cliente_fk_pk();
                    System.out.println("Id Evento selecionada: " + idEventoClick);
                    System.out.println("Id Cliente selecionado: " + idClienteClick);
                    if(event.getButton() == MouseButton.SECONDARY){
                        contextMenu.show(tabelaTableView, event.getScreenX(), event.getScreenY());
                    }
                }
            }
        });

        
        menuItemDeletar.setOnAction(e -> deletarAssociacao());

        // Liga a ObservableList à TableView
        tabelaTableView.setItems(clienteEvento);


    }


    public void deletarAssociacao(){
        ClienteEvento clienteEventoSelecionada = tabelaTableView.getSelectionModel().getSelectedItem();
        if(clienteEventoSelecionada != null ){
            clienteEvento.remove(clienteEventoSelecionada);
            ClienteEventoDAO clienteEventoDAO = new ClienteEventoDAO();
            String id_evento = idEventoClick;
            String id_cliente = idClienteClick;
            clienteEventoDAO.deleteByCpf(id_cliente, id_evento);
        }

        EventoDAO eventoDAO = new EventoDAO();
        eventoDAO.incrementarEstoque(idEventoClick);

        clienteEvento.clear();
        lerBDClienteEvento();
        
        System.out.println("Associacao deletada com sucesso!"); 

    }

    public void lerBDClienteEvento(){
        AcionaBanco acionaBanco = new AcionaBanco();
        List<ClienteEvento> clienteEventoLocal = acionaBanco.coletarDadosClienteEvento();
        
        for(int i = 0; i < clienteEventoLocal.size() ; i++){
            clienteEvento.add(clienteEventoLocal.get(i));
        }
    }
    
    public void mudaTelaMenuADM() {
        App.mudaTela("./views/TelaMenuADM.fxml");
    }

}
