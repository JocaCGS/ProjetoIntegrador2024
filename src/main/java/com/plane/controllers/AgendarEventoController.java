package com.plane.controllers;

import java.io.InputStream;

import com.plane.App;
import com.plane.dao.ClienteEventoDAO;
import com.plane.dao.EventoDAO;
import com.plane.models.AdministradorLogado;
import com.plane.models.ClienteEvento;
import com.plane.models.ClienteLogado;
import com.plane.models.Evento;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class AgendarEventoController {

    @FXML
    private Text agendareventoText;

    @FXML
    private Button retornarButton;
    
    @FXML
    private MenuButton escolhereventoMenuButton;

    @FXML
    private ImageView imagemfundoImageView;

    private EventoDAO eventoDAO = new EventoDAO();
    private ClienteEventoDAO clienteEventoDAO = new ClienteEventoDAO();

    // Variável de classe para armazenar o evento selecionado
    private Evento eventoSelecionado = null;

    // Método para carregar os eventos disponíveis no MenuButton
    private void loadEventosDisponiveis() {
        // Limpar itens anteriores, caso existam
        escolhereventoMenuButton.getItems().clear();

        // Buscar os eventos disponíveis
        for (Evento evento : eventoDAO.findAvailableEvents()) {
            MenuItem menuItem = new MenuItem(evento.getOrigem() + " para " + evento.getDestino());
            
            // Ao selecionar o evento, ele é armazenado na variável eventoSelecionado
            menuItem.setOnAction(event -> {
                eventoSelecionado = evento;  // Atribuindo o evento selecionado
                System.out.println("Evento escolhido: " + evento.getOrigem() + " para " + evento.getDestino());
            });

            escolhereventoMenuButton.getItems().add(menuItem);
        }
    }

    // Método para carregar a imagem de fundo
    private void carregarImagemFundo() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
    }

    // Método para agendar o evento
    @FXML
    public void agendarEvento() {
        if (eventoSelecionado == null) {
            // Exibir uma mensagem de erro caso nenhum evento tenha sido selecionado
            showErrorMessage("Por favor, selecione um evento.");
            return;
        }

        // Verifica se o estoque do evento está disponível
        if (Integer.parseInt(eventoSelecionado.getEstoque()) <= 0) {
            showErrorMessage("O evento selecionado está sem estoque disponível.");
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
            ClienteEvento clienteViagem = new ClienteEvento(cpfADM, eventoSelecionado.getId());
            clienteEventoDAO.save(clienteViagem);
        } else {
            ClienteEvento clienteEvento = new ClienteEvento(cpfCliente, eventoSelecionado.getId());
            clienteEventoDAO.save(clienteEvento);
        }

        // Decrementa o estoque do evento após o agendamento
        eventoDAO.decrementEstoque(Integer.parseInt(eventoSelecionado.getId()));

        // Atualiza a lista de eventos disponíveis
        loadEventosDisponiveis();

        // Exibir sucesso
        showSuccessMessage("Evento agendado com sucesso!");
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
        loadEventosDisponiveis();
        carregarImagemFundo();  // Carregar a imagem de fundo
    }

    // Método para retornar à tela principal
    public void agendarEventoRetornar() {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }
}
