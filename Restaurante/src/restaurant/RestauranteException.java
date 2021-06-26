package restaurant;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class RestauranteException extends RuntimeException{
    
    public RestauranteException(String error){
        super(error);
    }
}