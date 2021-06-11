package restaurant;

import java.util.Scanner;

public class InputReader {
    private Scanner reader;
    
    public InputReader(){
        reader = new Scanner(System.in);
    }
    
    public String getString(String prompt){
        if(prompt == null)
            prompt = "";
        
        prompt += ">";
        
        System.out.print(prompt);
        
        return reader.nextLine();
    }
    
    public char getChar(String prompt){
        if(prompt.length()> 1)
            return ' ';
        
        return getString(prompt).charAt(0);
    }
    
    public int getInt(String prompt){
        if(prompt==null)
            prompt="";
        
        prompt+="> ";
        
        System.out.print(prompt);
        
        while(!reader.hasNextInt()){
            reader.nextLine();
            System.out.print(prompt);
        }
        
        int number = reader.nextInt();
        reader.nextLine();
        
        return number;
    }
    
    public double getDouble(String prompt){
        if(prompt==null)
            prompt="";
        
        prompt+="> ";
        
        System.out.print(prompt);
        
        while(!reader.hasNextDouble()){
            reader.nextLine();
            System.out.print(prompt);
        }
        double number = reader.nextDouble();
        reader.nextLine();
        return number;
    }
}
