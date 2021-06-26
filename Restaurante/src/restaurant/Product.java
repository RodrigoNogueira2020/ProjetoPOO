package restaurant;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public abstract class Product implements Serializable{
    
    private String name;
    private double price;
    private double iva;

    public Product(){
        this.price = 0;
        this.name = "Um produto qualquer";
        iva = 0.23;
    }
    
    public Product(String name, double price, double iva) {
        if (name == null || name.trim().equals(""))
            throw new RestauranteException("ERRO: Nome não pode ficar em branco!");
        else
            this.name = name.trim();

        if(price < 0)
            throw new RestauranteException("ERRO: Preço não pode ser menor que zero!");
        else
            this.price = price;
        
        if(iva > 1)
            throw new RestauranteException("ERRO: IVA não pode exceder os 100%!");
        else if(iva < 0)
            throw new RestauranteException("ERRO: IVA não pode ser negativo!");
        else
            this.iva = iva;
    }
    

    /*
     * Introduz um novo name ao produto, caso não passe a validação deixa ficar
     * com o name que tinha anteriormente
     *
     * @param name Novo name
     */
    public void setName(String nome) {
        if (nome == null || nome.trim().equals(""))
            throw new RestauranteException("ERRO: Nome não pode ficar em branco!");
        
        this.name = nome.trim();
    }

    public void setPrice(double price) {
        if(price == 0)
            throw new RestauranteException("ERRO: Preço não pode ser zero!");
        else if(price < 0)
            throw new RestauranteException("ERRO: Preço não pode ser negativo!");
        
        this.price = price;
    }
    
    public void setIva(double iva) {
        iva /= 100;
        
        if(iva > 1)
            throw new RestauranteException("ERRO: IVA não pode exceder os 100%!");
        else if(iva < 0)
            throw new RestauranteException("ERRO: IVA não pode ser negativo!");
        
        this.iva = iva;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Caso seja imprimido para o recibo, tem de ser multiplicado por 100
     *
     * @return Devolve o iva em decimal
     */
    public double getIva() {
        return iva;
    }
    
    

    @Override
    public String toString() {
        return "Nome: " + name + 
               " | Preco: " + price + 
               " (com IVA: " + (price + (price * iva)) + ")" +
               " | IVA: " + (int)(iva * 100) + " ";
    }

}
