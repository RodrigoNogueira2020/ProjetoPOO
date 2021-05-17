package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Item {
    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) {
        if(produto != null)
            this.produto = produto;
        else;
//            this.produto = new Produto("", 0);
        
        if(quantidade > 0)
            this.quantidade = quantidade;
        else
            this.quantidade = 0;
            
    }

    public void setProduto(Produto produto) {
        if(produto != null)
            this.produto = produto;
        else;
//            this.produto = new Produto("", 0);
    }

    public void setQuantidade(int quantidade) {
        if(quantidade > 0)
            this.quantidade = quantidade;
        else
            this.quantidade = 0;
    }
    
    public Produto getProduto() {
        if(produto instanceof Bebida)
            return (Bebida) produto;
        
        return null;
    }

    public int getQuantidade() {
        return quantidade;
    }
    
    @Override
    public String toString(){
        return produto + " | " + "Quantidade: " + quantidade;
    }
    
}
