package restaurante;
import java.util.Scanner;

/**
 *
 * @author Ricardo Reis
 */
public class InputReader {
    private Scanner reader;
    
    public InputReader(){
        reader = new Scanner(System.in);
    }
    
    public String receberTexto(String pergunta){
        if(pergunta == null)
            pergunta = "";
        
        pergunta += ">";
        
        System.out.print(pergunta);
        
        return reader.nextLine();
    }
    
    public char receberLetra(String pergunta){
        return receberTexto(pergunta).charAt(0);
    }
    
    public int receberNumeroInt(String pergunta){
        if(pergunta==null)
            pergunta="";
        
        pergunta+="> ";
        
        System.out.print(pergunta);
        
        while(!reader.hasNextInt()){
            reader.nextLine();
            System.out.print(pergunta);
        }
        
        int number = reader.nextInt();
        reader.nextLine();
        
        return number;
    }
    
    public double receberNumeroDouble(String pergunta){
        if(pergunta==null)
            pergunta="";
        
        pergunta+="> ";
        
        System.out.print(pergunta);
        
        while(!reader.hasNextDouble()){
            reader.nextLine();
            System.out.print(pergunta);
        }
        double number = reader.nextDouble();
        reader.nextLine();
        return number;
    }
}
