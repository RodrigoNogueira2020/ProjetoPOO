package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Item {
    private Produto produto;
    private int quantidade;

    public Item() {
        produto = null;
        quantidade = 0;
    }
    
    public Item(Produto produto, int quantidade) {
        if(produto != null)
            this.produto = produto;
        else;
//            throw exception;
        
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
    
    private String mostrarProduto(){
        if(produto != null)
            return ""+produto;
        return "";
    }
    
    @Override
    public String toString(){
        return "Quantidade: " + quantidade + " -> " + mostrarProduto();
    }
    
}
