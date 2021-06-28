/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Window2ProductsDrinkController implements Initializable {

    @FXML
    private RadioButton rbtAlcoolica = new RadioButton("rbtAlcoolica");
    
    @FXML
    private void btGravarVoltarClicked(ActionEvent event) {
        if(rbtAlcoolica.isSelected())
            System.out.println("teste");
        
        ProjetoPOO2fase.changeScreen(2);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
