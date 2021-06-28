/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopoo.pkg2fase;

import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static projetopoo.pkg2fase.ProjetoPOO2fase.management;

/**
 *
 * @author Ricardo Reis
 */
public class Window0SetTables implements Initializable {
    
    /* 1ª vez -> Quantas mesas tem o restaurante? */
    /* 2ª vez -> Quantas mesas pretende adicionar? */
    @FXML
    private Label lblAskForTables;
    
    
    /* 1ª vez -> ERRO
    *            Número < 0
    *            Número < 4
    */
    @FXML
    private Label lblErro;
    
    @FXML
    private TextField txtTables = new TextField("txtTables");
    
    @FXML
    private void btAddTablesClicked(ActionEvent event) {
        System.out.println("You clicked me!");
        
        try{
            if(txtTables.getText().equals("") && management.getTableList() == null)
                throw new RestauranteException("ERRO: Número de mesas não pode ficar vazio!");
            else if(!txtTables.getText().equals("")){
                int numberOfTables = parseInt(txtTables.getText());

                management.addTables(numberOfTables);
                
            }
            
            /* Teste */
            for(int j = 0; j < management.getTableList().length; j++)
                System.out.println(management.getTable(j));
            
            ProjetoPOO2fase.changeScreen(1);
        }catch(RestauranteException e){
            lblErro.setText(e.getMessage());
            
        }catch(NumberFormatException e){
            lblErro.setText("ERRO: Introduza apenas números!");
            
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
