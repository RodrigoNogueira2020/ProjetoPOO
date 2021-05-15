package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Snack extends Produto{
    int quantidade;
    boolean temPicante;
    
    public Snack(String nome, double preco, int quantidade, boolean ePicante) {
        super(nome, preco);
        
        if(quantidade > 0)
            this.quantidade = quantidade;
        else
            this.quantidade = 0;
        
        this.temPicante = ePicante;
    }

    public void setQuantidade(int quantidade) {
        if(quantidade > 0)
            this.quantidade = quantidade;
        else
            this.quantidade = 0;
    }
    
    /**
     *  Muda se tem picante para o estado oposto, se tiver picante muda para que
     * nao tenha e vice-versa
     */
    public void setTemPicante() {
        temPicante = !temPicante;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public boolean isTemPicante() {
        return temPicante;
    }
    
    private String ePicante(){
        if(temPicante)
            return "É picante";
        
        return "Não é picante";
    }
    
    @Override
    public String toString(){
        return "Snack: " + getNome() + " | " + 
               "Capacidade: " + getQuantidade() + " | " +
               ePicante() + " | " + 
               getPreco() + "€";
    }
    
}
