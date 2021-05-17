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
    
    public String getText(String question){
        if(question == null)
            question = "";
        
        question += ">";
        
        System.out.print(question);
        
        return reader.nextLine();
    }
    
    public int getNumberInt(String question){
        if(question==null)
            question="";
        
        question+="> ";
        
        System.out.print(question);
        
        while(!reader.hasNextInt()){
            reader.nextLine();
            System.out.print(question);
        }
        
        int number = reader.nextInt();
        reader.nextLine();
        return number;
    }
    
    public double getNumberDouble(String question){
        if(question==null)
            question="";
        
        question+="> ";
        
        System.out.print(question);
        
        while(!reader.hasNextDouble()){
            reader.nextLine();
            System.out.print(question);
        }
        double number = reader.nextDouble();
        reader.nextLine();
        return number;
    }
}
