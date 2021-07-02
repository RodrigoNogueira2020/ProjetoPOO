package projetopoo.pkg2fase;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Drink extends Product implements Serializable{

    private double capacity;
    private boolean hasAlcohol;

    public Drink() {
        this.capacity = 0.33;
        this.hasAlcohol = false;
    }
    
    public Drink(String name, double price, double iva, double capacity, boolean hasAlcohol) {
        super(name, price, iva);
        if (capacity > 0.33)
            this.capacity = capacity;
        else
            this.capacity = 0.33;

        this.hasAlcohol = hasAlcohol;
    }


    public void setCapacity(double capacity) {
        if (capacity > 0)
            this.capacity = capacity;
        else if(capacity < 0)
            throw new RestaurantException("ERRO: A capacidade da bebida não pode ser negativa!");
        else if(capacity < 0.33)
            throw new RestaurantException("ERRO: A capacidade da bebida tem de ter, pelo menos, 0,33L.");
    }

    /**
     * Muda se tem alcool para o estado oposto, se tiver alcool muda para que
     * nao tenha e vice-versa
     */
    public void toggleHasAlcohol() {
        hasAlcohol = !hasAlcohol;
    }
    
    public void setHasAlcohol(boolean hasAlcohol) {
        this.hasAlcohol = hasAlcohol;
    }

    public double getCapacity() {
        return capacity;
    }

    public boolean isHasAlcohol() {
        return hasAlcohol;
    }

    private String isAlchoolic() {
        if (hasAlcohol)
            return "Bebida álcoolica";
        
        return "Bebida não álcoolica";
    }

    @Override
    public String toString(){
        return "Bebida -> " + super.toString() +
               "| Capacidade: " + capacity + " L | " +
               isAlchoolic();
    }

}
