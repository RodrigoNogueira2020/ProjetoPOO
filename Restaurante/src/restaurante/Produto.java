package restaurante;

/**
 * @author Ricardo Reis
 */
public abstract class Produto {
    private String nome;
    private double preco;
    private final double IVA;
    
    public Produto(String nome, double preco){
        if(nome != null || !nome.trim().equals(""))
            this.nome = nome.trim();
        else
            this.nome = "Um produto qualquer";
        
        if(preco >= 0)
            this.preco = preco;
        else
            this.preco = 0;
        
        IVA = 0.23;
    }

    /**
     *  Introduz um novo nome ao produto, caso não passe a validação
     * deixa ficar com o nome que tinha anteriormente
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        if(nome != null && !nome.trim().equals(""))
            this.nome = nome.trim();
    }

    public void setPreco(double preco) {
        if(preco >= 0)
            this.preco = preco;
        else
            this.preco = 0;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    /**
     * Caso seja imprimido para o recibo, tem de ser multiplicado por 100
     * @return Devolve o IVA em decimal
     */
    public double getIVA() {
        return IVA;
    }
    
    @Override
    public String toString(){
        return "Nome: " + nome + " | Preco: " + preco + " | IVA: " + (IVA*100);
    }
    
}
