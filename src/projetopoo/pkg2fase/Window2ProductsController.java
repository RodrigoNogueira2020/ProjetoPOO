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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import static projetopoo.pkg2fase.ProjetoPOO2fase.management;


public class Window2ProductsController implements Initializable {
    
    @FXML
    private TableView<Product> tbProdutos;
    
    @FXML
    private TableColumn<Product, String> clNome;
    @FXML
    private TableColumn<Product, String> clPreco;
    @FXML
    private TableColumn<Product, String> clIVA;
    @FXML
    private TableColumn<Product, String> clPrecoIVA;
    
    @FXML
    private TextField txtProdutoNome;
    @FXML
    private TextField txtProdutoPreco;
    @FXML
    private TextField txtProdutoIVA;
    
    @FXML
    private Label lblErro;

    Product product;
    
    @FXML
    private void btAtualizarDetalhesGeraisClicked(ActionEvent event) {
        try{
            management.checkProductDuplicates(txtProdutoNome.getText());
            System.out.print(product.getName() + " -> ");
            for(Product p: management.getProductList())
                if(p.getName().equals(product.getName())){
                    p.setName(txtProdutoNome.getText());
                    p.setPrice(parseDouble(txtProdutoPreco.getText()));
                    p.setIva(parseDouble(txtProdutoIVA.getText()));
                }
            System.out.println(product.getName());
        }catch(RestaurantException e){
            lblErro.setText(e.getMessage());
        }
    }
    
    @FXML
    private void btProductClicked(MouseEvent event) {
        try{
            product = tbProdutos.getSelectionModel().getSelectedItem();

            txtProdutoNome.setText(product.getName());
            txtProdutoPreco.setText(""+product.getPrice());
            txtProdutoIVA.setText(""+(int)(product.getIva()*100));
            System.out.println(product.getName());
            
        }catch(NullPointerException e){
            fillTbProducts(management.getProductList());
        }
    }
    
    @FXML
    private void MenuAdicionarBebidaClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(21);
    }
    
    @FXML
    private void MenuAdicionarSweetClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(22);
    }
    
    @FXML
    private void MenuAdicionarPratoClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(23);
    }
    
    @FXML
    private void MenuAdicionarSnackClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(24);
    }
    
    @FXML
    private void btProdutosClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(1);
    }
    
    @FXML
    private void btVoltarWindow1Clicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(1);
    }
    
    @FXML
    private void mostrarProdutosClicked(ActionEvent event) {
        fillTbProducts(management.getProductList());
    }
    
    private void fillTbProducts(ArrayList<Product> arr){
        tbProdutos.refresh();
        ObservableList<Product> data = FXCollections.observableArrayList(arr);
        
        clNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        clPreco.setCellValueFactory(new PropertyValueFactory<>("price"));
        clIVA.setCellValueFactory(new PropertyValueFactory<>("iva"));
        
        tbProdutos.setItems(data);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
