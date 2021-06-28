package projetopoo.pkg2fase;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author Ricardo Reis
 */
public class Window2ProductsSweetController implements Initializable {

    @FXML
    private RadioButton feitoNoRestaurante = new RadioButton("rbtSim");
    
    @FXML
    private void btGravarVoltarClicked(ActionEvent event) {
        if(feitoNoRestaurante.isSelected())
            System.out.println("Feito no restaurante.");
        
        ProjetoPOO2fase.changeScreen(2);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
