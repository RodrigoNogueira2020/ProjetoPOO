package restaurant;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Snack extends Product implements Serializable{
    private int quantity;
    private boolean isSpicy;
    
    public Snack(String name, double price, double iva, int quantity, boolean isSpicy) {
        super(name, price, iva);
        
        if(quantity > 0)
            this.quantity = quantity;
        else
            this.quantity = 0;
        
        this.isSpicy = isSpicy;
    }
    
    public Snack(){
        this.quantity = 0;
        this.isSpicy = false;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0)
            throw new InvalidInputArgumentException("ERRO: O snack não pode ter uma quantidade negativa!");
        else if(quantity == 0)
            throw new InvalidInputArgumentException("ERRO: O snack não pode ter uma quantidade de zero!");
        
        this.quantity = quantity;
    }
    
    /**
     *  Muda se tem picante para o estado oposto, se tiver picante muda para que
     * nao tenha e vice-versa
     */
    public void setIsSpicy() {
        isSpicy = !isSpicy;
    }
    
    public void setIsSpicy(char spicy) {
        switch(spicy){
            case 's':case 'S':
                isSpicy = true;
                break;
            case 'n':case 'N':
                isSpicy = false;
                break;
            default:
                throw new InvalidInputArgumentException("ERRO: Introduza apenas (s)im ou (n)ão!");
        }
    }
    
    public int getQuantity() {
        return quantity;
    }

    public boolean isSpicy() {
        return isSpicy;
    }
    
    private String spiciness(){
        if(isSpicy)
            return "É picante";
        
        return "";
    }
    
    @Override
    public String toString(){
        return "Snack  -> " + super.toString() + "| " + 
               "Quantidade: " + getQuantity() + " | " +
               spiciness();
    }
    
}
