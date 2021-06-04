package restaurant;

public class InvalidInputArgumentException extends IllegalArgumentException{
    
    public InvalidInputArgumentException(String error){
        super(error);
    }
}
