package com.plane.controllers;
import java.io.InputStream;

import com.plane.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class InitialScreenController {

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private Button comecarButton;

    @FXML
    private Pane fundoPane;

    @FXML
    private ImageView imagemfundoImageView;

    
    public void initialize() {
        InputStream imageStream = getClass().getResourceAsStream("/images/initialscreen.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
    }

    public void mudaTelaLogin() {
        App.mudaTela("./views/TelaLogin.fxml");
    }
}
