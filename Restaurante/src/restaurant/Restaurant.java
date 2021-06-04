package restaurant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Ricardo Reis 200262024
 *         Rodrigo Nogueira 200262002
 */
public class Restaurant {

    public static void main(String[] args) throws IOException {
        Management restaurante = new Management();
        restaurante.menu();
        
        restaurante.saveBook();
                
    }
}
