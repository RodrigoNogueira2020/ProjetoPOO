package projetopoo.pkg2fase;

import static java.lang.Double.parseDouble;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private void btVoltarWindow1Clicked(MouseEvent event) {
        ProjetoPOO2fase.changeScreen(1);
    }
    
    @FXML
    private void tbProdutosParaAdicionarClicked(MouseEvent event) {
        fillTbTables();
    }
    
    @FXML
    private void btAdicionarProdutoAoPedidoClicked(MouseEvent event) {
        fillTbTables();
    }
    @FXML
    private void btRemoverProdutoAoPedidoClicked(MouseEvent event) {
        fillTbTables();
    }
    
    @FXML
    private void tbProdutosAdicionadosClicked(MouseEvent event) {
        fillTbTables();
    }
    
    @FXML
    private void btMesasClicked(MouseEvent event) {
        fillTbTables();
    }
    
    private void fillTbTables(){
        Table[] arr = management.getTableList();
        tbMesas.refresh();
        ObservableList<Table> data = FXCollections.observableArrayList(arr);
        
        clMesaNumero.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        clMesaDataHora.setCellValueFactory(new PropertyValueFactory<>("order"));
        
        tbMesas.setItems(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //
    }
    
}