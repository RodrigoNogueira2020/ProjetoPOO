package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Bebida extends Produto {

    private double capacidade;
    private boolean temAlcool;

    public Bebida(String nome, double preco, double capacidade, boolean temAlcool) {
        super(nome, preco);
        if (capacidade > 0.33) {
            this.capacidade = capacidade;
        } else {
            this.capacidade = 0.33;
        }

        this.temAlcool = temAlcool;
    }

    public Bebida() {
        this.capacidade = 0.33;
        this.temAlcool = false;
    }

    public boolean setCapacidade(double capacidade) {
        if (capacidade > 0.33) {
            this.capacidade = capacidade;
            return true;
        }
        
        System.out.println("ERRO: A bebida precisa de um nome!");
        return false;
    }

    /**
     * Muda se tem alcool para o estado oposto, se tiver alcool muda para que
     * nao tenha e vice-versa
     */
    public void setTemAlcool() {
        temAlcool = !temAlcool;
    }
    
    public boolean setTemAlcool(char alc) {
        switch(alc){
            case 's':case 'S':
                temAlcool = true;
                break;
            case 'n':case 'N':
                temAlcool = false;
                break;
            default:
                System.out.println("ERRO: É preciso especificar se a bebida é álcoolica!");
                return false;
        }
        return true;
    }

    public double getCapacidade() {
        return capacidade;
    }

    public boolean isTemAlcool() {
        return temAlcool;
    }

    private String eAlcoolica() {
        if (temAlcool) {
            return "Bebida álcoolica";
        }
        return "Bebida não álcoolica";
    }

    @Override
    public String toString(){
        return "Bebida: " + getNome() + " | " + 
               "Capacidade: " + capacidade + " L | " +
               eAlcoolica() + " | " + 
               getPreco() + "€";
    }

}
