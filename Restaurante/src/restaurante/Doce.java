package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Doce extends Produto{
    private String descricao;
    private boolean feitoNoRestaurante;
    
    public Doce(String nome, double preco, String descricao, boolean feitoNoRestaurante) {
        super(nome, preco);
        
        if(descricao != null || !descricao.trim().equals(""))
            this.descricao = descricao.trim();
        
        this.feitoNoRestaurante = feitoNoRestaurante;
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
               getPreco() + " €";
    }
    
}
