package projetopoo.pkg2fase;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Sweet extends Product implements Serializable{

    private String description;
    private boolean madeInRestaurant;

    public Sweet(String name, double price, double iva, String description, boolean madeInRestaurant) {
        super(name, price, iva);

        if (description != null || !description.trim().equals(""))
            this.description = description.trim();

        this.madeInRestaurant = madeInRestaurant;
    }

    public Sweet() {
        this.madeInRestaurant = false;
        this.description = "Uma descrição qualquer";
    }

    public void setDescription(String description) {
        if (description != null && !description.trim().equals(""))
            this.description = description.trim();
        else
            throw new RestauranteException("ERRO: O doce precisa de uma descrição!");
    }

    public void setMadeInRestaurant() {
        madeInRestaurant = !madeInRestaurant;
    }
    
    public void setMadeInRestaurant(char res) {
        switch(res){
            case 's':case 'S':
                madeInRestaurant = true;
                break;
            case 'n':case 'N':
                madeInRestaurant = false;
                break;
            default:
                throw new RestauranteException("ERRO: Introduza apenas (s)im ou (n)ão!");
        }
    }

    public boolean isMadeInRestaurant() {
        return madeInRestaurant;
    }

    public String getDescription() {
        return description;
    }
    
    private String madeInRestaurant(){
        if(madeInRestaurant)
            return "Feito no restaurante";
        return "";
    }
    
    @Override
    public String toString() {
        return "Doce   -> " + super.toString() + "| " +
                description + " | "+ madeInRestaurant();
    }

}
