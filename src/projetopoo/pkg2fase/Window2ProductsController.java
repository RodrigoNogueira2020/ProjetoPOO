package projetopoo.pkg2fase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ricardo Reis
 */
public class Window2ProductsController implements Initializable {
    
    @FXML
    private void btAtualizarDetalhesGeraisClicked(ActionEvent event) {
        //
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
