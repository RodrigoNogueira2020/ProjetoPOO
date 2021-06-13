package restaurant;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Item implements Serializable{
    private Product product;
    private int quantity;

    public Item() {
        product = null;
        quantity = 0;
    }
    
    public Item(Product product, int quantity) {
        if(product != null)
            this.product = product;
        else;
//            throw exception;
        
        if(quantity > 0)
            this.quantity = quantity;
        else
            this.quantity = 0;
            
    }

    public void setProduct(Product product) {
        if(product != null)
            this.product = product;
        else
            throw new InvalidInputArgumentException("ERRO: Produto introduzido é nulo!");
    }

    public void setQuantity(int quantity) {
        if(quantity > 0)
            this.quantity = quantity;
        else
            this.quantity = 0;
    }
    
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    
    private String showProduct(){
        if(product != null)
            return ""+product;
        return "";
    }
    
    @Override
    public String toString(){
        return "Quantidade: " + quantity + " -> " + showProduct();
    }
    
}
