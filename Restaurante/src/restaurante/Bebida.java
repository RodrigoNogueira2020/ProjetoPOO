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

    public void setCapacidade(double capacidade) {
        if (capacidade > 0.33) {
            this.capacidade = capacidade;
        } else {
            this.capacidade = 0.33;
        }
    }

    /**
     * Muda se tem alcool para o estado oposto, se tiver alcool muda para que
     * nao tenha e vice-versa
     */
    public void setTemAlcool() {
        temAlcool = !temAlcool;
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
    public String toString() {
        return "Bebida: " + getNome() + " | "
                + "Capacidade: " + getCapacidade() + " L | "
                + eAlcoolica() + " | "
                + getPreco() + "€";
    }

}
