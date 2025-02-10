package com.plane.controllers;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.plane.App;
import com.plane.dao.AnotacoesDAO;
import com.plane.db.FabricaConexoes;
import com.plane.models.AdministradorLogado;
import com.plane.models.Anotacoes;
import com.plane.models.ClienteLogado;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class NotasController {

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TextArea areanotasTextArea;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Text notasText;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private Button salvaranotacoesButton;

    // Carrega a última anotação ao inicializar a tela
    @FXML
    private void initialize() {
        // Carrega a imagem de fundo
        carregarImagemFundo();
        
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

        if(cpfADM != null){
            carregarUltimaAnotacao(cpfADM);
        }else if (cpfCliente != null) {
            carregarUltimaAnotacao(cpfCliente);
        }
    }

    // Carrega a imagem de fundo
    private void carregarImagemFundo() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
    }

    // Carrega a última anotação do banco de dados
    private void carregarUltimaAnotacao(String cpfCliente) {
        try (Connection connection = FabricaConexoes.getInstance().getConnection()) {
            AnotacoesDAO anotacoesDAO = new AnotacoesDAO(connection);

            // Verifica se existe uma anotação para o CPF do cliente
            Anotacoes anotacao = anotacoesDAO.buscarUltimaAnotacaoPorCpf(cpfCliente);
            if (anotacao != null) {
                areanotasTextArea.setText(anotacao.getConteudo()); // Exibe a anotação na TextArea
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar a última anotação: " + e.getMessage());
        }
    }

    @FXML
    private void retornarTelaPrincipal(ActionEvent event) {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }

    @FXML
    private void salvarAnotacoes(ActionEvent event) {
        // Verifica se o campo de anotação não está vazio
        if (areanotasTextArea.getText().isEmpty()) {
            System.out.println("O conteúdo da anotação não pode ser vazio.");
            return; // Se o campo estiver vazio, retorna sem fazer nada
        }

        // Obtém o conteúdo da anotação
        String conteudo = areanotasTextArea.getText();

        // Obtém o CPF do cliente logado
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

        Anotacoes anotacaoObj = null;

        // Criação do objeto Anotacoes com os dados obtidos]
        if(cpfADM != null){
            anotacaoObj = new Anotacoes(conteudo, cpfADM, null);
        } else if (cpfCliente != null){
            anotacaoObj = new Anotacoes(conteudo, cpfCliente, null);
        }

        // Conecta ao banco de dados e salva ou atualiza a anotação
        try (Connection connection = FabricaConexoes.getInstance().getConnection()) {
            AnotacoesDAO anotacoesDAO = new AnotacoesDAO(connection);

            // Verifica se já existe uma anotação para o CPF do cliente
            if (anotacoesDAO.existeAnotacaoParaCpf(cpfCliente)) {
                // Se existir, faz um UPDATE na anotação existente
                anotacoesDAO.atualizarAnotacao(anotacaoObj);
                System.out.println("Anotação atualizada com sucesso!");
            } else {
                // Se não existir, faz um INSERT para salvar a anotação
                anotacoesDAO.adicionarAnotacao(anotacaoObj);
                System.out.println("Anotação salva com sucesso!");
            }

            // Exibe um Alert de sucesso após salvar ou atualizar as anotações
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Sucesso");
            alert.setHeaderText(null);
            alert.setContentText("Anotações Salvas ou Atualizadas!");
            alert.showAndWait();

            // Após o Alert ser fechado, volta para a tela principal
            App.mudaTela("./views/TelaClientePrincipal.fxml");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar ou atualizar a anotação: " + e.getMessage());
        }
    }
}
