package restaurante;

/**
 * @author Ricardo Reis
 */

public abstract class Produto {
    
    private String nome;
    private double preco;
    private final double IVA;

    public Produto(String nome, double preco) {
        if (nome != null || !nome.trim().equals("")) {
            this.nome = nome.trim();
        } else {
            this.nome = "Um produto qualquer";
        }

        if (preco >= 0) {
            this.preco = preco;
        } else {
            this.preco = 0;
        }

        IVA = 0.23;
    }
    
    public Produto(){
        IVA = 0.23;
        this.preco = 0;
        this.nome = "Um produto qualquer";
    }

    /*
     * Introduz um novo nome ao produto, caso não passe a validação deixa ficar
     * com o nome que tinha anteriormente
     *
     * @param nome Novo nome
     */
    public boolean setNome(String nome) {
        if (nome != null && !nome.trim().equals("")) {
            this.nome = nome.trim();
            return true;
        }
        return false;
    }
    
    public boolean setNome(String nome, String tipoProduto) {
        if (nome != null && !nome.trim().equals("")) {
            this.nome = nome.trim();
            return true;
        }
        
        if(tipoProduto == null || tipoProduto.trim().equals(""))
            tipoProduto = "O produto";
        
        System.out.println("ERRO: " + tipoProduto + " precisa de um nome!");
        return false;
    }

    public boolean setPreco(double preco) {
        if (preco >= 0) {
            this.preco = preco;
            return true;
        }
        return false;
    }
    
    public boolean setPreco(double preco, String tipoProduto) {
        if (preco >= 0) {
            this.preco = preco;
            return true;
        }
        
        if(tipoProduto == null || tipoProduto.trim().equals(""))
            tipoProduto = "O produto";
        
        System.out.println("ERRO: " + tipoProduto + " precisa de um nome!");
        return false;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
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
        return "Nome: " + nome + 
               " | Preco: " + preco + 
               "(com IVA: " + (preco * (1.00 - IVA)) + ")" +
               " | IVA: " + (IVA * 100);
    }

}
