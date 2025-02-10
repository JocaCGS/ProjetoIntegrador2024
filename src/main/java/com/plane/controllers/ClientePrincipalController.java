package com.plane.controllers;

import java.io.InputStream;

import com.plane.App;
import com.plane.models.AdministradorLogado;
import com.plane.models.ClienteLogado;
import com.plane.models.Viagem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class ClientePrincipalController {

   @FXML
   private Button agendareventosButton;

   @FXML
   private Button agendarviagensButton;

   @FXML
   private AnchorPane anchorfundoAnchorPane;

   @FXML
   private Button botaoiconeuserButton;

   @FXML
   private Button fazeranotacoesButton;

   @FXML
   private ImageView imagemfundoImageView;

   @FXML
   private ImageView imagemiconeuserImageView;

   @FXML
   private Line linhaLine;

   @FXML
   private Pane panefundoPane;

   @FXML
   private Button visualizareventosdisponiveisButton1;

   @FXML
   private Button visualizareventosmarcadasButton1;

   @FXML
   private Button visualizarviagensdisponiveisButton;

   @FXML
   private Button visualizarviagensmarcadasButton;

   @FXML
   private TableView<Viagem> tabelaTableView;


   public void initialize() {
      // Carregar imagens
      InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
      Image image = new Image(imageStream);
      imagemfundoImageView.setImage(image);

      InputStream imageStream2 = getClass().getResourceAsStream("/images/iconeuser.png");
      Image image2 = new Image(imageStream2);
      imagemiconeuserImageView.setImage(image2);

      // Criar e configurar o ContextMenu
      ContextMenu contextMenu = new ContextMenu();
      
      MenuItem logoutItem = new MenuItem("Logout");
      MenuItem menuADMItem = new MenuItem("Menu ADM");

      botaoiconeuserButton.setOnMouseClicked(event -> {
         System.out.println("ContextMenu chamado");
         if (contextMenu.isShowing()) {
            contextMenu.hide();
         } else {
            // Pegar a posição do botão e exibir o menu abaixo dele
            Double x = event.getScreenX() + 10;
            Double y = event.getScreenY() + 10;
            contextMenu.show(botaoiconeuserButton, x, y);   
         }     
      });
   
      logoutItem.setOnAction(event -> fazLogout());
      menuADMItem.setOnAction(event -> abrirMenuADM());

      
      AdministradorLogado admlogado = AdministradorLogado.getInstance();
      
      System.out.println(admlogado);
      String cpfADM = null;
      
      if(admlogado != null){
         cpfADM = admlogado.getAdministrador().getCpf();
      } 
      
      if(cpfADM != null) {
         contextMenu.getItems().add(logoutItem);
         contextMenu.getItems().add(menuADMItem);
      } else {
         contextMenu.getItems().add(logoutItem);
      }

   }

   private void fazLogout() {
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
      
      if(cpfADM != null && cpfCliente == null) {
         AdministradorLogado.getInstance().deslogar();
      } else if (cpfADM != null && cpfCliente != null) {
         AdministradorLogado.getInstance().deslogar();
         ClienteLogado.getInstance().deslogar();
      } else {
         ClienteLogado.getInstance().deslogar();
      }
      System.out.println("Usuário fez logout!");
      
      App.mudaTela("./views/TelaLogin.fxml");
   }
   
   private void abrirMenuADM() {
      App.mudaTela("./views/TelaMenuADM.fxml");
   }
   
   @FXML
   private void abrirTelaNotas() {
      App.mudaTela("./views/TelaNotas.fxml");
   }
   
   @FXML
   private void abrirTelaAgendarEventos() {
      App.mudaTela("./views/TelaAgendarEvento.fxml");
   }

   @FXML
   private void abrirTelaAgendarViagens() {
      App.mudaTela("./views/TelaAgendarViagem.fxml");
   }

   @FXML
   private void abrirTelaViagensDisponiveis() {
      App.mudaTela("./views/TelaViagensDisponiveis.fxml");
   }

   @FXML
   private void abrirTelaEventosDisponiveis() {
      App.mudaTela("./views/TelaEventosDisponiveis.fxml");
   }

   @FXML
   private void abrirTelaViagensMarcadas() {
      App.mudaTela("./views/TelaViagensMarcadas.fxml");
   }

   @FXML
   private void abrirTelaEventosMarcados() {
      App.mudaTela("./views/TelaEventosMarcados.fxml");
   }

}
