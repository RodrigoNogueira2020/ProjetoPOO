package restaurant;

public class RestaurantManagement {
    private static Management restaurante;
    
    public RestaurantManagement(){
        
    }
    
    public static void startProgram(){
        SaveFiles saveFiles = new SaveFiles();

        if(saveFiles.readSerializedFile("savedata.bin") == null)
            restaurante = new Management();
        else
            restaurante = saveFiles.readSerializedFile("savedata.bin");
        
        restaurante.menu();
    }
}
