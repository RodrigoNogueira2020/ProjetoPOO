package projetopoo.pkg2fase;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class RestaurantManagement {
    private static Management restaurante;
    
    public static void startProgram(){
        
        RestaurantFileHandler saveFiles = new RestaurantFileHandler();
        
        // Verifica se existe ficheiro binário de dados
        if(saveFiles.readSerializedFile("savedata.bin") == null)
            restaurante = new Management();
        else
            restaurante = saveFiles.readSerializedFile("savedata.bin");
        
        restaurante.menu();
        saveFiles.saveFile(restaurante, "savedata.bin");
    }
    
}
