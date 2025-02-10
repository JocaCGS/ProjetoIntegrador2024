package com.plane.controllers;

import java.io.InputStream;

import com.plane.App;
import com.plane.dao.ClienteViagemDAO;
import com.plane.dao.ViagemDAO;
import com.plane.models.AdministradorLogado;
import com.plane.models.ClienteLogado;
import com.plane.models.ClienteViagem;
import com.plane.models.Viagem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AgendarViagemController {

    @FXML
    private Text agendarviagemText;

    @FXML
    private Button retornarButton;
    
    @FXML
    private MenuButton escolherviagemMenuButton;

    @FXML
    private ImageView imagemfundoImageView;

    private ViagemDAO viagemDAO = new ViagemDAO();
    private ClienteViagemDAO clienteViagemDAO = new ClienteViagemDAO();

    // Variável de classe para armazenar a viagem selecionada
    private Viagem viagemSelecionada = null;

    // Método para carregar as viagens disponíveis no MenuButton
    private void loadViagensDisponiveis() {
        // Limpar itens anteriores, caso existam
        escolherviagemMenuButton.getItems().clear();

        // Buscar as viagens disponíveis
        for (Viagem viagem : viagemDAO.findViagensDisponiveis()) {
            MenuItem menuItem = new MenuItem(viagem.getOrigem() + " para " + viagem.getDestino());
            
            // Ao selecionar a viagem, ela é armazenada na variável viagemSelecionada
            menuItem.setOnAction(event -> {
                viagemSelecionada = viagem;  // Atribuindo a viagem selecionada
                System.out.println("Viagem escolhida: " + viagem.getOrigem() + " para " + viagem.getDestino());
            });

            escolherviagemMenuButton.getItems().add(menuItem);
        }
    }

    // Método para carregar a imagem de fundo
    private void carregarImagemFundo() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
    }

    // Método para agendar a viagem
    @FXML
    public void agendarViagem() {
        if (viagemSelecionada == null) {
            // Exibir uma mensagem de erro caso nenhuma viagem tenha sido selecionada
            showErrorMessage("Por favor, selecione uma viagem.");
            return;
        }

        // Verifica se o estoque da viagem está disponível
        if (Integer.parseInt(viagemSelecionada.getEstoque()) <= 0) {
            showErrorMessage("A viagem selecionada está sem estoque disponível.");
            return;
        }
        AdministradorLogado admlogado = AdministradorLogado.getInstance();
        ClienteLogado clienteLogado = ClienteLogado.getInstance();

        System.out.println(admlogado);
        System.out.println(clienteLogado);
        String cpfADM = null;
        String cpfCliente = null;

        if(admlogado != null){
            cpfADM = admlogado.getAdministrador().getCpf();
        } else {
            cpfCliente = clienteLogado.getCliente().getCpf();
        }
        
        if(cpfADM != null) {
            ClienteViagem clienteViagem = new ClienteViagem(cpfADM, viagemSelecionada.getId());
            clienteViagemDAO.save(clienteViagem);
        } else {
            ClienteViagem clienteViagem = new ClienteViagem(cpfCliente, viagemSelecionada.getId());
            clienteViagemDAO.save(clienteViagem);
        }


        // Decrementa o estoque da viagem após o agendamento
        viagemDAO.decrementarEstoque(viagemSelecionada.getId());

        // Atualiza a lista de viagens disponíveis
        loadViagensDisponiveis();

        // Exibir sucesso
        showSuccessMessage("Viagem agendada com sucesso!");
    }

    // Método para exibir mensagens de erro
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Erro");
        alert.showAndWait();
    }

    // Método para exibir mensagens de sucesso
    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle("Sucesso");
        alert.showAndWait();
    }

    // Método de inicialização do controlador
    @FXML
    public void initialize() {
        loadViagensDisponiveis();
        carregarImagemFundo();  // Carregar a imagem de fundo
    }

    public void agendarViagemRetornar() {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }
}
