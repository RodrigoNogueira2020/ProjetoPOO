/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo.pkg2fase;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import static projetopoo.pkg2fase.ProjetoPOO2fase.management;

/**
 * FXML Controller class
 *
 * @author Ricardo Reis
 */
public class Window2ProductsSnackController implements Initializable {

    @FXML
    private TextField txtProdutoNome = new TextField("txtProdutoNome");
    @FXML
    private TextField txtProdutoPreco = new TextField("txtProdutoPreco");
    @FXML
    private TextField txtProdutoIVA = new TextField("txtProdutoIVA");
    
    
    @FXML
    private TextField txtProdutoQuantidade = new TextField("txtProdutoQuantidade");
    
    @FXML
    private Label lblErro;
    
    // Picante -> Sim
    @FXML
    private RadioButton rbtSim = new RadioButton("rbtSim");
    Snack snackTemporary = new Snack();
    
    @FXML
    private void btGravarVoltarClicked(ActionEvent event) {
        txtProdutoPreco.setText(txtProdutoPreco.getText().trim());
        txtProdutoIVA.setText(txtProdutoIVA.getText().trim());
        
        try{
            management.checkProductDuplicates(txtProdutoNome.getText().trim());
            
            snackTemporary.setName(txtProdutoNome.getText());
            snackTemporary.setPrice(parseInt(txtProdutoPreco.getText()));
            snackTemporary.setIva(parseDouble(txtProdutoIVA.getText()));
            snackTemporary.setIsSpicy(rbtSim.isSelected());
            
            System.out.println(snackTemporary);
            management.addProduct(snackTemporary);
            ProjetoPOO2fase.changeScreen(2);
            
        }catch(RestaurantException e){
            lblErro.setText(e.getMessage());
        }catch(NullPointerException e){
            lblErro.setText("ERRO: Existem campos por completar!");
            
        }catch(NumberFormatException e){
            String[] invalidStringInput = e.getMessage().split(" ");
            System.out.println(e.getMessage());
            
            switch(invalidStringInput[invalidStringInput.length-1]){
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
        // TODO
    }    
    
}
