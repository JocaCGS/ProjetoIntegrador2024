package com.plane.controllers;

import java.io.InputStream;
import java.util.List;

import com.plane.App;
import com.plane.dao.ClienteDAO;
import com.plane.models.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ManterClienteADMController {

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<Cliente, String> cpfTableColumn;

    @FXML
    private Text cpforigemText;

    @FXML
    private Button deletarviagemButton;

    @FXML
    private Button editarclienteButton;

    @FXML
    private Text editarclienteselecionadoText;

    @FXML
    private TableColumn<Cliente, String> emailTableColumn;

    @FXML
    private Rectangle fundoazul2Rectangle;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Text manterclientesText;

    @FXML
    private Text menuadministracaoText;

    @FXML
    private TableColumn<Cliente, String> nomeTableColumn;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private TableColumn<Cliente, String> senhaTableColumn;

    @FXML
    private TableView<Cliente> tabelaTableView;

    @FXML
    private TextField updatecpfTextField;

    @FXML
    private Text updateemailText;

    @FXML
    private TextField updateemailTextField;

    @FXML
    private Text updatenomeText;

    @FXML
    private TextField updatenomeTextField;

    @FXML
    private Text updatesenhaText;

    @FXML
    private TextField updatesenhaTextField;

    ObservableList<Cliente> clientes = FXCollections.observableArrayList();
    public String cpfClienteClick = null;

    @FXML
    public void initialize() {
        // Carrega a imagem de fundo
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
        
        lerBDCliente();
        
        // Configurações das colunas da TableView
        cpfTableColumn.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
        nomeTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        emailTableColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        senhaTableColumn.setCellValueFactory(cellData -> cellData.getValue().senhaProperty());
        // Context Menu para deletar Cliente
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDeletar = new MenuItem("Deletar");
        contextMenu.getItems().addAll(menuItemDeletar);

        tabelaTableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY || event.getButton() == MouseButton.PRIMARY) { // Clique com o botão direito
                // Verifique se um item foi selecionado
                if (tabelaTableView.getSelectionModel().getSelectedItem() != null) {
                    cpfClienteClick = tabelaTableView.getSelectionModel().getSelectedItem().getCpf();
                    System.out.println("Cpf Cliente selecionado: " + cpfClienteClick);
                    if(event.getButton() == MouseButton.SECONDARY){
                        contextMenu.show(tabelaTableView, event.getScreenX(), event.getScreenY());
                    }
                    if(event.getButton() == MouseButton.PRIMARY){
                        Cliente clienteselecionado = tabelaTableView.getSelectionModel().getSelectedItem();
                        if (clienteselecionado!= null){
                            updatecpfTextField.setText(clienteselecionado.getCpf());
                            updatenomeTextField.setText(clienteselecionado.getNome());
                            updateemailTextField.setText(clienteselecionado.getEmail());
                            updatesenhaTextField.setText(clienteselecionado.getSenha());
                        }
                    }
                }
            }
        });
    
        
        menuItemDeletar.setOnAction(e -> deletarCliente());
    
        // Liga a ObservableList à TableView
        tabelaTableView.setItems(clientes);
    
    }

        public void deletarCliente() {
        Cliente clienteSelecionado = tabelaTableView.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            clientes.remove(clienteSelecionado);
            ClienteDAO clienteDAO = new ClienteDAO();
            clienteDAO.delete(cpfClienteClick);
        }

        
        clientes.clear();
        lerBDCliente();
        
        System.out.println("Cliente deletado com sucesso!");
    }

    public void editarCliente(){

        String cpf_update = updatecpfTextField.getText();
        String nome_update = updatenomeTextField.getText();
        String email_update = updateemailTextField.getText();
        String senha_update = updatesenhaTextField.getText();

        System.out.println("ABAIXO OS _update");
        System.out.println(cpf_update);
        System.out.println(nome_update);
        System.out.println(email_update);
        System.out.println(senha_update);

        
        if (cpf_update.isEmpty() || nome_update.isEmpty() || email_update.isEmpty() || senha_update.isEmpty()){
            System.out.println("Todos os campos devem ser preenchidos!");
            return;
        }
        

        Cliente novoCliente = new Cliente(nome_update, senha_update, cpf_update, email_update);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.update(novoCliente);

        for (int i = 0; i < clientes.size(); i++) {
            Cliente clienteExistente = clientes.get(i);
            if (clienteExistente.getCpf().equals(novoCliente.getCpf())) {
                clientes.set(i, novoCliente);
                break;
            }            
        }

        
        clientes.clear();
        lerBDCliente();

        System.out.println("Cliente editado com sucesso!");
    }

    
    public void lerBDCliente(){
        AcionaBanco acionaBanco = new AcionaBanco();
        List<Cliente> clientesLocal = acionaBanco.coletarDadosCliente();
        
        for(int i = 0; i < clientesLocal.size() ; i++){
            clientes.add(clientesLocal.get(i));
        }
        
    }


    @FXML
    void mudaTelaMenuADM(ActionEvent event) {
        App.mudaTela("./views/TelaMenuADM.fxml");
    }

}
