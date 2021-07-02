package projetopoo.pkg2fase;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static projetopoo.pkg2fase.ProjetoPOO2fase.management;

public class Window2ProductsDishController implements Initializable {

    @FXML
    private TextField txtProdutoNome = new TextField("txtProdutoNome");
    @FXML
    private TextField txtProdutoPreco = new TextField("txtProdutoPreco");
    @FXML
    private TextField txtProdutoIVA = new TextField("txtProdutoIVA");
    
    @FXML
    private Label lblErro;
    
    @FXML
    private TextArea txtProdutoDescricao = new TextArea("txtProdutoDescricao");
    Dish dishTemporary = new Dish();
    
    @FXML
    private void btGravarVoltarClicked(ActionEvent event) {
        txtProdutoPreco.setText(txtProdutoPreco.getText().trim());
        txtProdutoIVA.setText(txtProdutoIVA.getText().trim());
        
        try{
            management.checkProductDuplicates(txtProdutoNome.getText().trim());
            
            dishTemporary.setName(txtProdutoNome.getText());
            dishTemporary.setPrice(parseInt(txtProdutoPreco.getText()));
            dishTemporary.setIva(parseDouble(txtProdutoIVA.getText()));
            dishTemporary.setDescription(txtProdutoDescricao.getText());
            
            System.out.println(dishTemporary);
            management.addProduct(dishTemporary);
            ProjetoPOO2fase.changeScreen(2);
            
        }catch(RestaurantException e){
            lblErro.setText(e.getMessage());
        }catch(NullPointerException e){
            lblErro.setText("ERRO: Existem campos por completar!");
            
        }catch(NumberFormatException e){
            String[] invalidStringInput = e.getMessage().split(" ");
            System.out.println(e.getMessage());
            
            switch(invalidStringInput[invalidStringInput.length-1].trim()){
                case "\"\"": case "empty String":
                    lblErro.setText("ERRO: Existem campos por completar!");
                    break;
                    
                default:
                    lblErro.setText("ERRO: " + invalidStringInput[invalidStringInput.length-1] + " não é um valor válido!");
                
            }
        }
        
    }
    
    @FXML
    private void btCancelarClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
