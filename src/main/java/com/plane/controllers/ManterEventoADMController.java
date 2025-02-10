package com.plane.controllers;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

import com.plane.App;
import com.plane.dao.EventoDAO;
import com.plane.models.AdministradorLogado;
import com.plane.models.Evento;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ManterEventoADMController {

    @FXML
    private Text adddataText;

    @FXML
    private TextField adddataTextField;

    @FXML
    private Text adddestinoText;

    @FXML
    private TextField adddestinoTextField;

    @FXML
    private Text addestoqueText;

    @FXML
    private TextField addestoqueTextField;

    @FXML
    private Text addorigemText;

    @FXML
    private TextField addorigemTextField;

    @FXML
    private Text addvalorText;

    @FXML
    private TextField addvalorTextField;

    @FXML
    private Button adicionareventoButton;

    @FXML
    private Text adicionareventoText;

    @FXML
    private AnchorPane anchorfundoAnchorPane;

    @FXML
    private TableColumn<Evento, String> dataTableColumn;

    @FXML
    private Button deletareventoButton;

    @FXML
    private TableColumn<Evento, String> destinoTableColumn;

    @FXML
    private Button editareventoButton;

    @FXML
    private Text editareventoText;

    @FXML
    private TableColumn<Evento, String> estoqueTableColumn;

    @FXML
    private Rectangle fundoazul1Rectangle;

    @FXML
    private Rectangle fundoazul2Rectangle;

    @FXML
    private ImageView imagemfundoImageView;

    @FXML
    private Text mantereventoText;

    @FXML
    private Text menuadministracaoText;

    @FXML
    private TableColumn<Evento, String> origemTableColumn;

    @FXML
    private Pane panefundoPane;

    @FXML
    private Button retornarButton;

    @FXML
    private TableView<Evento> tabelaTableView;

    @FXML
    private Text updatedataText;

    @FXML
    private TextField updatedataTextField;

    @FXML
    private Text updatedestinoText;

    @FXML
    private TextField updatedestinoTextField;

    @FXML
    private Text updateestoqueText;

    @FXML
    private TextField updateestoqueTextField;

    @FXML
    private Text updateorigemText;

    @FXML
    private TextField updateorigemTextField;

    @FXML
    private Text updatevalorText;

    @FXML
    private TextField updatevalorTextField;

    @FXML
    private TableColumn<Evento, String> valorTableColumn;

    ObservableList<Evento> eventos = FXCollections.observableArrayList();
    public String idEventoClick = null;

    @FXML
    public void initialize() {
        // Carrega a imagem de fundo
        InputStream imageStream = getClass().getResourceAsStream("/images/imagempadrao.png");
        Image image = new Image(imageStream);
        imagemfundoImageView.setImage(image);
        
        lerBDEvento();
        
        // Configurações das colunas da TableView
        origemTableColumn.setCellValueFactory(cellData -> cellData.getValue().origemProperty());
        destinoTableColumn.setCellValueFactory(cellData -> cellData.getValue().destinoProperty());
        dataTableColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        estoqueTableColumn.setCellValueFactory(cellData -> cellData.getValue().estoqueProperty());
        valorTableColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty());

        // Context Menu para deletar evento
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDeletar = new MenuItem("Deletar");
        contextMenu.getItems().addAll(menuItemDeletar);

        tabelaTableView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.SECONDARY || event.getButton() == MouseButton.PRIMARY) { // Clique com o botão direito
                // Verifique se um item foi selecionado
                if (tabelaTableView.getSelectionModel().getSelectedItem() != null) {
                    idEventoClick = tabelaTableView.getSelectionModel().getSelectedItem().getId();
                    System.out.println("Id Evento selecionado: " + idEventoClick);
                    if(event.getButton() == MouseButton.SECONDARY){
                        contextMenu.show(tabelaTableView, event.getScreenX(), event.getScreenY());
                    }
                    if(event.getButton() == MouseButton.PRIMARY){
                        Evento eventoSelecionado = tabelaTableView.getSelectionModel().getSelectedItem();
                        if (eventoSelecionado!= null){
                            updateorigemTextField.setText(eventoSelecionado.getOrigem());
                            updatedestinoTextField.setText(eventoSelecionado.getDestino());
                            String data_coletada = eventoSelecionado.getData();
                            String data_Formatada;
                            try {
                                // Converte a data de dd/MM/yyyy para yyyy-MM-dd
                                DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
                                LocalDate data = LocalDate.parse(data_coletada, formatterEntrada);
                                data_Formatada = data.format(formatterSaida);
                            } catch (DateTimeParseException e) {
                                System.out.println("Formato de data inválido! Acho que deu ruim...");
                                return; // Sai do método se a data for inválida
                            }
                            updatedataTextField.setText(data_Formatada);
                            
                            updateestoqueTextField.setText(eventoSelecionado.getEstoque());
                            updatevalorTextField.setText(eventoSelecionado.getValor().replace('.', ','));
                        }
                    }
                }
            }
        });
    
        
        menuItemDeletar.setOnAction(e -> deletarEvento());
    
        // Liga a ObservableList à TableView
        tabelaTableView.setItems(eventos);
    
    }

    public void adicionarEvento() {
        AcionaBanco acionaBanco = new AcionaBanco();

        String origem_add = addorigemTextField.getText();
        String destino_add = adddestinoTextField.getText();
        String data_add = adddataTextField.getText();
        String estoque_add = addestoqueTextField.getText();
        String string_valor_add = addvalorTextField.getText().replace(',', '.');
        
        if (origem_add.isEmpty() || destino_add.isEmpty() || data_add.isEmpty() || estoque_add.isEmpty() || string_valor_add.isEmpty()) {
            System.out.println("Todos os campos devem ser preenchidos!");
            return;
        }
        
        // Converte a string de valor para double
        double double_valor_add = Double.parseDouble(string_valor_add);

        if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", data_add)) {
            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
            return; 
        }

        // Formatação da data 
        String data_addFormatada;
        try {
            // Converte a data de dd/MM/yyyy para yyyy-MM-dd
            DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate data = LocalDate.parse(data_add, formatterEntrada);
            data_addFormatada = data.format(formatterSaida);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
            return; // Sai do método se a data for inválida
        }

        Evento novoEvento = new Evento(origem_add, destino_add, data_addFormatada, estoque_add, String.valueOf(double_valor_add), AdministradorLogado.newInstance().getAdministrador().getCpf());

        eventos.add(novoEvento);

        acionaBanco.inserirDadosEvento(origem_add, destino_add, data_addFormatada, estoque_add, double_valor_add);


        
        eventos.clear();
        lerBDEvento();


        System.out.println("Evento adicionado com sucesso!");
    }

    public void deletarEvento() {
        Evento eventoSelecionado = tabelaTableView.getSelectionModel().getSelectedItem();
        if (eventoSelecionado != null) {
            eventos.remove(eventoSelecionado);
            EventoDAO eventoDAO = new EventoDAO();
            Integer eventoIdint = Integer.parseInt(idEventoClick);
            eventoDAO.deleteById(eventoIdint);
        }

        
        eventos.clear();
        lerBDEvento();
        
        System.out.println("Evento deletado com sucesso!");
    }

    public void editarEvento(){

        String origem_update = updateorigemTextField.getText();
        String destino_update = updatedestinoTextField.getText();
        String data_update = updatedataTextField.getText();
        String estoque_update = updateestoqueTextField.getText();
        String string_valor_update = updatevalorTextField.getText().replace(',', '.');

        System.out.println("ABAIXO OS _update");
        System.out.println(origem_update);
        System.out.println(destino_update);
        System.out.println(data_update);
        System.out.println(estoque_update);
        System.out.println(string_valor_update);

        
        if (origem_update.isEmpty() || destino_update.isEmpty() || data_update.isEmpty() || estoque_update.isEmpty() || string_valor_update.isEmpty()){
            System.out.println("Todos os campos devem ser preenchidos!");
            return;
        }
        
        // Converte a string de valor para double
        double double_valor_update = Double.parseDouble(string_valor_update);

        if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", data_update)) {
            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
            return; 
        }

        // Formatação da data de nascimento
        String data_updateFormatada;
        try {
            // Converte a data de dd/MM/yyyy para yyyy-MM-dd
            DateTimeFormatter formatterEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterSaida = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate data = LocalDate.parse(data_update, formatterEntrada);
            data_updateFormatada = data.format(formatterSaida);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
            return; // Sai do método se a data for inválida
        }

        Evento novoEvento = new Evento(idEventoClick, origem_update, destino_update, data_updateFormatada, estoque_update, String.valueOf(double_valor_update), AdministradorLogado.newInstance().getAdministrador().getCpf());

        EventoDAO eventoDAO = new EventoDAO();
        eventoDAO.update(novoEvento);

        for (int i = 0; i < eventos.size(); i++) {
            Evento viagemExistente = eventos.get(i);
            if (viagemExistente.getId().equals(novoEvento.getId())) {
                eventos.set(i, novoEvento);
                break;
            }            
        }

        
        eventos.clear();
        lerBDEvento();

        System.out.println("Evento editado com sucesso!");
    }

    
    public void lerBDEvento(){
        AcionaBanco acionaBanco = new AcionaBanco();
        List<Evento> eventosLocal = acionaBanco.coletarDadosEvento();
        
        for(int i = 0; i < eventosLocal.size() ; i++){
            eventos.add(eventosLocal.get(i));
        }
        
    }

    
    public void mudaTelaMenuADM() {
        App.mudaTela("./views/TelaMenuADM.fxml");
    }
}
