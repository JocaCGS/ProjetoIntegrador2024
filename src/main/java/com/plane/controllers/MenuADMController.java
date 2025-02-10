package com.plane.controllers;
import java.io.InputStream;

import com.plane.App;
import com.plane.models.AdministradorLogado;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MenuADMController {

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Button mantereventosButton;

    @FXML
    private Button manterpassagensButton;

    @FXML
    private Button manterviagensButton;

    @FXML
    private Text menuadministracaoText;

    @FXML
    private Pane panefundoPane;
    
    public void initialize() {
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);


        AdministradorLogado.newInstance().escreveAdministrador();
    }


    public void mudaTelaEscolherMenuADM(){
        App.mudaTela("./views/TelaClientePrincipal.fxml");
    }

    public void mudaTelaManterViagem(){
        App.mudaTela("./views/TelaManterViagemADM.fxml");
    }

    public void mudaTelaManterEvento(){
        App.mudaTela("./views/TelaManterEventoADM.fxml");
        
    }

    public void mudaTelaDeletarClienteViagem(){
        App.mudaTela("./views/TelaDeletarClienteViagem.fxml");
        
    }

    public void mudaTelaDeletarClienteEvento(){
        App.mudaTela("./views/TelaDeletarClienteEvento.fxml");
        
    }

    public void mudaTelaManterCliente(){
        App.mudaTela("./views/TelaManterClienteADM.fxml");

    }

    
}
