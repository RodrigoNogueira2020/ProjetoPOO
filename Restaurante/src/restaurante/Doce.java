package restaurante;

/**
 *
 * @author Ricardo Reis
 */
public class Doce extends Produto {

    private String descricao;
    private boolean feitoNoRestaurante;

    public Doce(String nome, double preco, String descricao, boolean feitoNoRestaurante) {
        super(nome, preco);

        if (descricao != null || !descricao.trim().equals("")) {
            this.descricao = descricao.trim();
        }

        this.feitoNoRestaurante = feitoNoRestaurante;
    }

    public Doce() {
        this.feitoNoRestaurante = false;
        this.descricao = "Uma descrição qualquer";
    }

    public boolean setDescricao(String descricao) {
        if (descricao != null && !descricao.trim().equals("")) {
            this.descricao = descricao.trim();
            return true;
        }
        
        System.out.println("ERRO: O doce precisa uma descrição!");
        return false;
    }

    public void setFeitoNoRestaurante() {
        feitoNoRestaurante = !feitoNoRestaurante;
    }
    
    public boolean setFeitoNoRestaurante(char res) {
        switch(res){
            case 's':case 'S':
                feitoNoRestaurante = true;
                break;
            case 'n':case 'N':
                feitoNoRestaurante = false;
                break;
            default:
                System.out.println("ERRO: É preciso especificar se o doce é feito no restaurante!");
                return false;
        }
        return true;
    }

    public boolean isFeitoNoRestaurante() {
        return feitoNoRestaurante;
    }

    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return "Doce: " + getNome() + " | "
                + descricao + " | "
                + getPreco() + " €";
    }

}
