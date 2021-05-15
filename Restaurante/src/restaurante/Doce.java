package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Doce extends Produto{
    String descricao;
    
    public Doce(String nome, double preco, String descricao) {
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
        return "Doce: " + getNome() + " | " + 
               descricao + " | " +
               getPreco() + "€";
    }
    
}
