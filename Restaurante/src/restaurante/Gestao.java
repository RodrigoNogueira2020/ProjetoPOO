package restaurante;
import java.util.ArrayList;

public class Gestao {
    private String nome;
//    private ArrayList<Mesa> listaMesa;
    private ArrayList<Produto> listaProdutos;
    private InputReader scan;
    
    public Gestao(){
        nome = "";
//        listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
    }
    
    public Gestao(String nomeRestaurante){
        if(nomeRestaurante != null || !nomeRestaurante.trim().equals(""))
            nome = nomeRestaurante;
        else
            nome = "Um restaurante qualquer";
        
//        listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
        
    }

    public void definirNome(){
        do{
            nome = scan.getText("Introduza o nome do restaurante");
            System.out.println(nome);
        }while(nome == null || nome.trim().equals(""));
    }
    
    public void setNome(String nomeRestaurante) {
        if(nomeRestaurante != null || nomeRestaurante.trim().equals(""))
            nome = nomeRestaurante;
    }

//    public void setListaMesa(ArrayList<Mesa> listaMesa) {
//        this.listaMesa = listaMesa;
//    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public void setScan(InputReader scan) {
        this.scan = scan;
    }

    
    
    public String getNome() {
        return nome;
    }

//    public ArrayList<Mesa> getListaMesa() {
//        return listaMesa;
//    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }
    
}
