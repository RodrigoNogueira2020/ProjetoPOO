package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Prato extends Produto{
    private String descricao;
    
    public Prato(String nome, double preco, String descricao) {
        super(nome, preco);
        
        if(descricao != null || !descricao.trim().equals(""))
            this.descricao = descricao.trim();
    }
    
    public Prato(){
        this.descricao = "Uma descrição qualquer";
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
