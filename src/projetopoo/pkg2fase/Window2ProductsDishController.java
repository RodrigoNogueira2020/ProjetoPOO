package projetopoo.pkg2fase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Ricardo Reis
 */
public class Window2ProductsDishController implements Initializable {

    @FXML
    private TextArea txtProdutoDescricao;
    
    @FXML
    private void btGravarVoltarClicked(ActionEvent event) {
        txtProdutoDescricao.setText(txtProdutoDescricao.getText().trim());
        
        String descTemp = txtProdutoDescricao.getText();
        
        if(descTemp.equals(""))
            System.out.println("teste");
        else
            ProjetoPOO2fase.changeScreen(2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtProdutoDescricao = new TextArea("txtProdutoDescricao");
    }    
    
}
