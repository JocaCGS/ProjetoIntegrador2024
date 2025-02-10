package com.plane.controllers;

import java.io.InputStream;
import java.util.List;

import com.plane.App;
import com.plane.dao.ClienteViagemDAO;
import com.plane.dao.ViagemDAO;
import com.plane.models.ClienteViagem;

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

public class DeletarClienteViagemController {

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<ClienteViagem, String> colunaidclienteTableColumn;

    @FXML
    private TableColumn<ClienteViagem, String> colunaidviagemTableColumn;

    @FXML
    private TableColumn<ClienteViagem, String> destinoTableColumn;

    @FXML
    private TableColumn<ClienteViagem, String> dataTableColumn;

    @FXML
    private TableColumn<ClienteViagem, String> nomeclienteTableColumn;

    @FXML
    private Button deletarassociacaoButton;

    @FXML
    private Text deletarclienteviagemText;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Text menuadministracaoText;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private TableView<ClienteViagem> tabelaTableView;


    
    ObservableList<ClienteViagem> clienteViagem = FXCollections.observableArrayList();
    public String idViagemClick = null;
    public String idClienteClick = null;

    public void initialize() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);

        lerBDClienteViagem();

        nomeclienteTableColumn.setCellValueFactory(cellData -> cellData.getValue().nome_clienteProperty());
        colunaidclienteTableColumn.setCellValueFactory(cellData -> cellData.getValue().cpf_cliente_fk_pkProperty());
        colunaidviagemTableColumn.setCellValueFactory(cellData -> cellData.getValue().id_viagem_fk_pkProperty());
        destinoTableColumn.setCellValueFactory(cellData -> cellData.getValue().destino_viagemProperty());
        dataTableColumn.setCellValueFactory(cellData -> cellData.getValue().data_viagemProperty());
        
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemDeletar = new MenuItem("Deletar");

        contextMenu.getItems().addAll(menuItemDeletar);

        tabelaTableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY || event.getButton() == MouseButton.PRIMARY) { // Clique com o botão direito
                // Verifique se um item foi selecionado
                if (tabelaTableView.getSelectionModel().getSelectedItem() != null) {
                    idViagemClick = tabelaTableView.getSelectionModel().getSelectedItem().getId_viagem_fk_pk();
                    idClienteClick = tabelaTableView.getSelectionModel().getSelectedItem().getCpf_cliente_fk_pk();
                    System.out.println("Id Viagem selecionada: " + idViagemClick);
                    System.out.println("Id Cliente selecionado: " + idClienteClick);
                    if(event.getButton() == MouseButton.SECONDARY){
                        contextMenu.show(tabelaTableView, event.getScreenX(), event.getScreenY());
                    }
                }
            }
        });

        
        menuItemDeletar.setOnAction(e -> deletarAssociacao());

        // Liga a ObservableList à TableView
        tabelaTableView.setItems(clienteViagem);


    }


    public void deletarAssociacao(){
        ClienteViagem clienteViagemSelecionada = tabelaTableView.getSelectionModel().getSelectedItem();
        if(clienteViagemSelecionada != null ){
            clienteViagem.remove(clienteViagemSelecionada);
            ClienteViagemDAO clienteViagemDAO = new ClienteViagemDAO();
            String id_viagem = idViagemClick;
            String id_cliente = idClienteClick;
            clienteViagemDAO.deleteByCpf(id_cliente, id_viagem);
        }

        ViagemDAO viagemDAO = new ViagemDAO();
        viagemDAO.incrementarEstoque(idViagemClick);

        clienteViagem.clear();
        lerBDClienteViagem();
        
        System.out.println("Associacao deletada com sucesso!"); 

    }

    public void lerBDClienteViagem(){
        AcionaBanco acionaBanco = new AcionaBanco();
        List<ClienteViagem> clienteViagemLocal = acionaBanco.coletarDadosClienteViagem();
        
        for(int i = 0; i < clienteViagemLocal.size() ; i++){
            clienteViagem.add(clienteViagemLocal.get(i));
        }   
    }

    
    public void mudaTelaMenuADM() {
        App.mudaTela("./views/TelaMenuADM.fxml");
    }

}
