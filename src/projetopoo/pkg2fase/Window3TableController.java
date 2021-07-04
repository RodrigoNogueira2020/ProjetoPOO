package projetopoo.pkg2fase;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static projetopoo.pkg2fase.ProjetoPOO2fase.management;

public class Window3TableController implements Initializable {
    
    @FXML
    private TableView<Table> tbMesas;
    @FXML
    private TableColumn<Table, String> clMesaNumero;
    @FXML
    private TableColumn<Table, String> clMesaDataHora;
    
    @FXML
    private DatePicker datePickerReserva;
    
    /* Produtos existententes */
    @FXML
    private TableView tbProdutosParaAdicionar;
    @FXML
    private TableColumn<Product, String> clNome;
    @FXML
    private TableColumn<Product, String> clPreco;
    @FXML
    private TableColumn<Product, String> clIVA;
    
    /* Produtos adicionados a um pedido */
    @FXML
    private TableView tbProdutosAdicionados;
    @FXML
    private TableColumn<Product, String> clNomePedido;
    @FXML
    private TableColumn<Product, String> clQuantidadePedido;
    
    /* Labels */
    @FXML
    private Label lblEstadoPedido;
    @FXML
    private Label lblPreco;
    @FXML
    private Label lblErro;
    
    /* ComboBox */
    @FXML
    private ComboBox cbBoxHour = new ComboBox();
    @FXML
    private ComboBox cbBoxMinute = new ComboBox();
    
    /* Radiobuttons */
    
    @FXML
    private RadioButton rbtReservarAgora = new RadioButton("rbtReservarAgora");
    @FXML
    private RadioButton rbtReservarMaisTarde = new RadioButton("rbtReservarMaisTarde");
    
    private Table table;
    private int tableIndex;
    
    /* Produtos disponiveis */
    public Product product;
    public int productIndex;
    
    /* Produtos no pedido */
    public Product productInTableOrder;
    public int productIndexInTableOrder;
    
    public Item item;
    
    @FXML
    private void btVoltarWindow1Clicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(1);
    }
    
    private void fillTbTables(){
        tbMesas.refresh();
        Table[] arr = management.getTableList();
        ObservableList<Table> data = FXCollections.observableArrayList(arr);
        
        clMesaNumero.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        clMesaDataHora.setCellValueFactory(new PropertyValueFactory<>("order"));
        
        tbMesas.setItems(data);
    }
    
    @FXML
    private void btMesasClicked(MouseEvent event) {
        fillTbTables();
        fillTbProducts();
        
        try{
            tableIndex = tbMesas.getSelectionModel().getSelectedIndex();
            table = management.getTableList()[tableIndex];
            System.out.println(table.getOrder().showState());
            lblEstadoPedido.setText(table.getOrder().showState());
            fillTbOrderProducts();
        }catch(NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void fillTbProducts(){
        ArrayList<Product> arr = management.getProductList();
        tbProdutosParaAdicionar.refresh();
        ObservableList<Product> data = FXCollections.observableArrayList(arr);
        
        clNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("price"));
        clIVA.setCellValueFactory(new PropertyValueFactory<>("iva"));
        
        tbProdutosParaAdicionar.setItems(data);
    }
    
    @FXML
    private void tbProdutosParaAdicionarClicked(MouseEvent event) {
        try{
            productIndex = tbProdutosParaAdicionar.getSelectionModel().getSelectedIndex();
            product = management.getProductList().get( productIndex );
            
            lblEstadoPedido.setText(table.getOrder().showState());
            fillTbOrderProducts();
        }catch(NullPointerException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    private void btAdicionarProdutoAoPedidoClicked(ActionEvent event) {
        try{
            if(rbtReservarAgora.isSelected())
                management.getTable(tableIndex).getOrder().openOrder(LocalDateTime.now());
            else{
                LocalDateTime reservaData;
                String data[] = datePickerReserva.getEditor().getText().split("-");

                reservaData = LocalDateTime.of(parseInt(data[2]), // YYYY
                                               parseInt(data[1]), // MM
                                               parseInt(data[0]), // DD
                                               parseInt(cbBoxHour.getSelectionModel().getSelectedItem().toString()),
                                               parseInt(cbBoxMinute.getSelectionModel().getSelectedItem().toString()));

                management.bookTable(table, reservaData);
                management.getTable(tableIndex).getOrder().openOrder(reservaData);

            }
            item = new Item(product, 1);
            management.isItemDuplicate(table, item);
            table.getOrder().setState(orderState.PREPARATION);
            
            fillTbOrderProducts();
            
            lblEstadoPedido.setText(table.getOrder().showState());
        }catch(ArrayIndexOutOfBoundsException e){
            lblErro.setText("ERRO: Falta definir a data/hora!");
        }catch(RestaurantException e){
            lblErro.setText(e.getMessage());
        }
    }
    
    @FXML
    private void btRemoverProdutoAoPedidoClicked(ActionEvent event) {
        table.getOrder().deleteItem(productIndexInTableOrder);
        fillTbTables();
        fillTbOrderProducts();
        lblEstadoPedido.setText(table.getOrder().showState());
    }
    
    @FXML
    private void tbProdutosAdicionadosClicked(MouseEvent event) {
        fillTbOrderProducts();
    }
    
    private void fillTbOrderProducts(){
        tbProdutosAdicionados.refresh();
        ArrayList<Item> arr = management.getTable(tableIndex).getOrder().getItemList();
        ObservableList<Item> data = FXCollections.observableArrayList(arr);
        
        clNomePedido.setCellValueFactory(new PropertyValueFactory<>("product"));
        clQuantidadePedido.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        tbProdutosAdicionados.setItems(data);
    }
    
    @FXML
    private void btPassarAServidoClicked(ActionEvent event){
        if(table.getOrder().getState() == orderState.PREPARATION)
            table.getOrder().setState(orderState.SERVED);
        else
            lblErro.setText("ERRO: Apenas mesas com pedidos em preparação podem passar os pedidos para servido!");
        lblEstadoPedido.setText(table.getOrder().showState());
    }
    
    @FXML
    private void btFecharPedidoClicked(ActionEvent event){
        if(table.getOrder().getState() == orderState.SERVED){
            table.getOrder().setState(orderState.CLOSED);
            
        }
        else
            lblErro.setText("ERRO: Apenas mesas que já foram servidas podem fechar pedidos!");
        
        fillTbTables();
        lblEstadoPedido.setText(table.getOrder().showState());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String hours[] = new String[24];
        String minutes[] = new String[60];
        
        for(int i = 0; i < 24; i++)
            hours[i] = ""+i;
        
        for(int i = 0; i < 60; i++)
            minutes[i] = ""+i;
        
        datePickerReserva.setEditable(false);
        
        cbBoxHour.setPromptText("Horas");
        cbBoxMinute.setPromptText("Minutos");
        
        cbBoxHour.getItems().setAll(FXCollections.observableArrayList(hours));
        cbBoxMinute.getItems().setAll(FXCollections.observableArrayList(minutes));
    }
    
}