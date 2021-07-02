/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo.pkg2fase;

import javafx.scene.text.Font;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static projetopoo.pkg2fase.ProjetoPOO2fase.management;
import static projetopoo.pkg2fase.RestaurantFileHandler.saveFile;


public class Window1Controller implements Initializable {

    @FXML
    private Label lblNomeRestaurante;
    
    @FXML
    private Button btProdutos;
    
    @FXML
    private Button btMesas;
    
    @FXML
    private Button btHistorico;
    
    @FXML
    private void btProdutosClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(2);
    }
    
    @FXML
    private void btMesasClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(1);
    }
    
    @FXML
    private void btHistoricoClicked(ActionEvent event) {
        ProjetoPOO2fase.changeScreen(1);
    }
    
    @FXML
    private void btSairClicked(ActionEvent event) {
        saveFile(management, "savedata.bin");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
