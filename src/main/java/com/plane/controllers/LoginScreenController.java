package com.plane.controllers;

import java.io.InputStream;

import com.plane.App;
import com.plane.models.AdministradorLogado;
import com.plane.models.ClienteLogado;

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

public class LoginScreenController {

    @FXML
    private Text aindanaoText;

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private Button cadastreseButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Pane fundoPane;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Button logarButton;

    @FXML
    private Text loginText;

    @FXML
    private TextField senhaTextField;

    @FXML
    private Text seuemailTextField;

    @FXML
    private Text suasenhaText;

    public void initialize() {
        InputStream imageStream = getClass().getResourceAsStream("/images/loginscreen.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
    }


    public void login(){
        AcionaBanco acionaBanco = new AcionaBanco();
        
        String email_usuario = emailTextField.getText();
        String senha_usuario = senhaTextField.getText();

        if (email_usuario.isEmpty() || senha_usuario.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inválido!");
            alert.setHeaderText(null);
            alert.setContentText("Todos os campos devem ser preenchidos!");
            alert.showAndWait();
            return;
        }

        if (!acionaBanco.verificarLoginCliente(email_usuario, senha_usuario) && !acionaBanco.verificarLoginAdministrador(email_usuario, senha_usuario)) {
            System.out.println("Login ou senha inválidos!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Inválido!");
            alert.setHeaderText(null);
            alert.setContentText("E-mail ou senha inválidos!");
            alert.showAndWait();
            return;
        }

        String cpfADM = acionaBanco.procurarCpfAssociadoADM(email_usuario);
        String cpfCliente= acionaBanco.procurarCpfAssociadoCliente(email_usuario);
        if (cpfADM != null && acionaBanco.obterAdministrador(cpfADM) != null) {
            AdministradorLogado.newInstance().setAdministrador(acionaBanco.obterAdministrador(cpfADM));
            AdministradorLogado.newInstance().escreveAdministrador();
            mudaTelaADM();
        } else {
            ClienteLogado.newInstance().setCliente(acionaBanco.obterCliente(cpfCliente));
            ClienteLogado.newInstance().escreveCliente();
            mudaTelaCliente();
        }   
    }

    public void mudaTelaCliente() {
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }

    public void mudaTelaADM() {
        App.mudaTela("./views/TelaMenuADM.fxml");
    }

    public void mudaTelaSignUp() {
        App.mudaTela("./views/TelaSignUp.fxml");
    }
}
