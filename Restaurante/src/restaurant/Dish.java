package restaurant;

import java.io.Serializable;

public class Dish extends Product implements Serializable{
    private String description;
    
    public Dish(String name, double price, String description) {
        super(name, price);
        
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
            throw new InvalidInputArgumentException("ERRO: O doce precisa de uma descrição!");
    }

    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString(){
        return "Prato: " + super.toString() + "| " + 
               description;
    }
       
}
