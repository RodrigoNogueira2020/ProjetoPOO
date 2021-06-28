package projetopoo.pkg2fase;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Dish extends Product implements Serializable{
    private String description;
    
    public Dish(String name, double price, double iva, String description) {
        super(name, price, iva);
        
        if(description != null || !description.trim().equals(""))
            this.description = description.trim();
    }
    
    public Dish(){
        this.description = "Uma descrição qualquer";
    }
    
    public void setDescription(String descricao) {
        if (descricao != null && !descricao.trim().equals(""))
            this.description = descricao.trim();
        else
            throw new RestauranteException("ERRO: O prato precisa de uma descrição!");
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString(){
        return "Prato  -> " + super.toString() + "| " + 
               description;
    }
       
}
