package projetopoo.pkg2fase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ricardo Reis
 */
public class ProjetoPOO2fase extends Application {
    
    private static Scene sceneWindow0, sceneWindow1, sceneWindow2,
                         sceneWindow2ProductsDrink, sceneWindow2ProductsSnack, sceneWindow2ProductsDish, sceneWindow2ProductsSweet;
    private static Stage primaryStage;
    
    public static Management management;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("Window0SetTables.fxml"));
        FXMLLoader window1 = new FXMLLoader(getClass().getResource("Window1.fxml"));
        FXMLLoader window2 = new FXMLLoader(getClass().getResource("Window2Products.fxml"));
        FXMLLoader window2ProductsDrink = new FXMLLoader(getClass().getResource("Window2ProductsDrink.fxml"));
        FXMLLoader window2ProductsSweet = new FXMLLoader(getClass().getResource("Window2ProductsSweet.fxml"));
        FXMLLoader window2ProductsDish = new FXMLLoader(getClass().getResource("Window2ProductsDish.fxml"));
        FXMLLoader window2ProductsSnack = new FXMLLoader(getClass().getResource("Window2ProductsSnack.fxml"));
        
        primaryStage = stage;
        primaryStage.setTitle("Restaurante");
        
        Parent parentWindow0 = root.load();
        Parent parentWindow1 = window1.load();
        Parent parentWindow2 = window2.load();
        Parent parentWindow2ProductsDrink = window2ProductsDrink.load();
        Parent parentWindow2ProductsSweet = window2ProductsSweet.load();
        Parent parentWindow2ProductsDish = window2ProductsDish.load();
        Parent parentWindow2ProductsSnack = window2ProductsSnack.load();
        
        sceneWindow0 = new Scene(parentWindow0);
        sceneWindow1 = new Scene(parentWindow1);
        sceneWindow2 = new Scene(parentWindow2);
        sceneWindow2ProductsDrink = new Scene(parentWindow2ProductsDrink);
        sceneWindow2ProductsSweet = new Scene(parentWindow2ProductsSweet);
        sceneWindow2ProductsDish = new Scene(parentWindow2ProductsDish);
        sceneWindow2ProductsSnack = new Scene(parentWindow2ProductsSnack);
        
        management = readSerializedFile("savedata.bin");
        
        stage.setScene(sceneWindow0);
        stage.show();
    }
    
    public static void changeScreen(int option){
        switch(option){
            case 0:
                // Definir/Adicionar mesas ao restaurante
                primaryStage.setScene(sceneWindow0);
                break;
            case 1:
                // Menu principal
                primaryStage.setScene(sceneWindow1);
                break;
            case 2:
                // Visualizar produtos
                primaryStage.setScene(sceneWindow2);
                break;
            case 21:
                primaryStage.setScene(sceneWindow2ProductsDrink);
                break;
            case 22:
                primaryStage.setScene(sceneWindow2ProductsSweet);
                break;
            case 23:
                primaryStage.setScene(sceneWindow2ProductsDish);
                break;
            case 24:
                primaryStage.setScene(sceneWindow2ProductsSnack);
                break;
        }
    }
    
    public static void saveFile(Management management, String filename){
        try{
            ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(filename));
            oos.writeObject(management);
            oos.flush();
            oos.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static Management readSerializedFile(String filename){
        Management management;
        
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            management = (Management)ois.readObject();
            ois.close();
            
        }catch(IOException e){
            System.out.println(e.getMessage());
            management = new Management();
            
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
            management = new Management();
            
        }
        
        return management;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
