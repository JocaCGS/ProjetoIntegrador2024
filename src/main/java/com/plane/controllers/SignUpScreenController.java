package com.plane.controllers;

import java.io.InputStream;
import java.util.regex.Pattern;

import com.plane.App;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SignUpScreenController {

    @FXML
    private Button cadastrarButton;

    @FXML
    private Text cadastroText;

    @FXML
    private TextField cpfTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Button loginButton;

    @FXML
    private TextField nomeTextField;

    @FXML
    private Text perguntaText;

    @FXML
    private TextField senhaTextField;

    @FXML
    private Text seucpfText;

    @FXML
    private Text seuemailText;

    @FXML
    private Text seunomeText;

    @FXML
    private Text suasenhaText;

    @FXML
    private AnchorPane telafundoAnchorPane;

    @FXML
    private Pane telafundoPane;

    public void initialize() {
        InputStream imageStream = getClass().getResourceAsStream("/images/loginscreen.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);

    }



    public void cadastrarDados() {

        AcionaBanco acionaBanco = new AcionaBanco();
        
        String nome_cliente = nomeTextField.getText();
        String senha_cliente = senhaTextField.getText();
        String cpf_cliente = cpfTextField.getText();
        String email_cliente = emailTextField.getText();

        if (nome_cliente.isEmpty() || senha_cliente.isEmpty() || cpf_cliente.isEmpty() || email_cliente.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos!");
            System.out.println("Todos os campos devem ser preenchidos!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inválido!");
            alert.setHeaderText(null);
            alert.setContentText("Todos os campos devem ser preenchidos!");
            alert.showAndWait();
            return;
        }

        if (!Pattern.matches("\\d{11}", cpf_cliente)) {
            System.out.println("CPF inválido! Certifique-se de que contém exatamente 11 dígitos numéricos.");
            System.out.println("Todos os campos devem ser preenchidos!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inválido!");
            alert.setHeaderText(null);
            alert.setContentText("CPF inválido! Certifique-se de que contém exatamente 11 dígitos numéricos.");
            alert.showAndWait();
            return; // Bloqueia o procedimento se o CPF não for válido
        }
        acionaBanco.inserirDados(nome_cliente, senha_cliente, cpf_cliente, email_cliente);
        mudaTelaLogin();
    }

    
    public void mudaTelaLogin() {
        App.mudaTela("./views/TelaLogin.fxml");
    }
}

