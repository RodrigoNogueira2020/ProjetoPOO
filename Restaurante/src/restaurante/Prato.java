/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Prato extends Produto{
    private String descricao;
    
    public Prato(String nome, double preco, String descricao) {
        super(nome, preco);
        
        if(descricao != null && !descricao.trim().equals(""))
            this.descricao = descricao.trim();
    }

    public void setDescricao(String descricao) {
        if(descricao != null && !descricao.trim().equals(""))
            this.descricao = descricao.trim();
    }

    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString(){
        return "Prato: " + getNome() + " | " + 
               descricao + " | " +
               getPreco() + " €";
    }
    
    
}
