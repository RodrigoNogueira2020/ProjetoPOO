package restaurant;

import java.io.Serializable;

public abstract class Product implements Serializable{
    
    private String name;
    private double price;
    private final double IVA;

    public Product(String name, double price) {
        if (name != null || !name.trim().equals(""))
            this.name = name.trim();
        else
            this.name = "Um produto qualquer";

        if (price >= 0)
            this.price = price;
        else
            this.price = 0;
        IVA = 0.23;
    }
    
    public Product(){
        this.price = 0;
        this.name = "Um produto qualquer";
        IVA = 0.23;
    }

    /*
     * Introduz um novo name ao produto, caso não passe a validação deixa ficar
     * com o name que tinha anteriormente
     *
     * @param name Novo name
     */
    public void setName(String nome) {
        if (nome != null || !nome.trim().equals(""))
            this.name = nome.trim();
        else
            throw new InvalidInputArgumentException("ERRO: Nome não pode ficar em branco!");
    }

    public void setPrice(double price) {
        if (price > 0)
            this.price = price;
        else if(price == 0)
            throw new InvalidInputArgumentException("ERRO: Preço não pode ser zero!");
        else if(price < 0)
            throw new InvalidInputArgumentException("ERRO: Preço não pode ser negativo!");
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
     * @return Devolve o IVA em decimal
     */
    public double getIVA() {
        return IVA;
    }

    @Override
    public String toString() {
        return "Nome: " + name + 
               " | Preco: " + price + 
               " (com IVA: " + (price * (1.00 - IVA)) + ")" +
               " | IVA: " + (int)(IVA * 100) + " ";
    }

}
