package restaurante;

import java.util.ArrayList;
import java.util.Iterator;

public class Gestao {

    private String nome;
//    private ArrayList<Mesa> listaMesa;
    private ArrayList<Produto> listaProdutos;
    private InputReader scan;

    public Gestao() {
//        listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
        definirNome();
    }

    public Gestao(String nomeRestaurante) {
        if (nomeRestaurante != null || !nomeRestaurante.trim().equals("")) {
            nome = nomeRestaurante;
        } else {
            nome = "Um restaurante qualquer";
        }

//      listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
    }

    private void definirNome() {
        do {
            nome = scan.receberTexto("Introduza o nome do restaurante").trim();
            System.out.println(nome);
        } while (nome == null || nome.trim().equals(""));
    }

    private void menuGrafico() { // TODO: Eliminar produto/mesa | Mostrar produtos (e ordena-los)
        System.out.println("=== " + nome + " ===");
        System.out.println("* 1 - Adicionar produto *");
        System.out.println("* 2 - Reservar uma nova mesa *");
        System.out.println("* 3 - Adicionar uma nova mesa *");
        System.out.println("* 0 - Sair do menu *");
        System.out.println("**************");
    }

    public void menu() {
        int opcao;

        do {
            menuGrafico();
            opcao = scan.receberNumeroInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    System.out.println("ERRO! Opção inválida!");
            }
        } while (opcao != 0);
    }

    public void menuGraficoProdutos() {
        System.out.println("**** PRODUTOS ****");
        System.out.println("* 1 - Bebida     *");
        System.out.println("* 2 - Doce       *");
        System.out.println("* 3 - Prato      *");
        System.out.println("* 4 - Snack      *");
        System.out.println("******************");
    }
    
    /**
     *
     * @param produtoNovo Produto a ser introduzido na lista, verifica se o nome
     * de um produto já existe.
     * @return true - se já houver um produto com o mesmo nome
     */
    private boolean verificarDuplicados(Produto produtoNovo){
        for(Produto elemento: listaProdutos)
            if(elemento.getNome().equals(produtoNovo.getNome()))
                return true;
        
        return false;
    }
    
    private void adicionarBebida(){
        Bebida bebida = new Bebida();
        String bebidaString;
        char bebidaChar;
        double bebidaDouble;

        do {
            bebidaString = scan.receberTexto("Nome da bebida");
            if (bebidaString == null || bebidaString.trim().equals(""))
                System.out.println("ERRO: A bebida precisa de um nome!");

        } while (bebidaString == null || bebidaString.trim().equals(""));
        
        if(!verificarDuplicados(bebida)){
            bebida.setNome(bebidaString.trim());
            
            do {
                bebidaDouble = scan.receberNumeroDouble("Preço da bebida");
                if (bebidaDouble < 0) {
                    System.out.println("ERRO: O preço não pode ser negativo!");
                }
            } while (bebidaDouble < 0.00);
            bebida.setPreco(bebidaDouble);

            do {
                bebidaDouble = scan.receberNumeroDouble("Capacidade da bebida");
                if (bebidaDouble < 0) {
                    System.out.println("ERRO: O preço não pode ser negativo!");
                }
            } while (bebidaDouble < 0.33);
            bebida.setCapacidade(bebidaDouble);
            
            do {
                bebidaChar = scan.receberLetra("Bebida alcoólica? (s/n): ");
                switch (bebidaChar) {
                    case 's':case 'S':
                        bebida.setTemAlcool();
                        break;
                    case 'n':case 'N':
                        break;
                    default:
                    System.out.println("ERRO: É preciso especificar se a bebida é álcoolica!");
                }
            } while (bebidaChar != 's' && bebidaChar != 'S' || bebidaChar != 'n' && bebidaChar != 'N');
            
            listaProdutos.add(bebida);
            System.out.println("++ Bebida adicionada com sucesso! ++");
        }else
            System.out.println("ERRO: Já existe um produto com esse nome!");
    }
    
    private void adicionarDoce(){
        Doce doce = new Doce();
        double doceDouble;
        String doceString;
        char doceChar;

        do {
            doceString = scan.receberTexto("Nome do doce");
            if (doceString == null || doceString.trim().equals(""))
                System.out.println("ERRO: A bebida precisa de um nome!");
            
        } while (doceString == null || doceString.trim().equals(""));
        
        if(!verificarDuplicados(doce)){
            doce.setNome(doceString);

            do {
                doceDouble = scan.receberNumeroDouble("Preço do doce");
                if (doceDouble < 0) {
                    System.out.println("ERRO: O preço não pode ser negativo!");
                }
            } while (doceDouble < 0.00);
            doce.setPreco(doceDouble);

            do {
                doceString = scan.receberTexto("Descrição do doce");
                if (doceString == null || doceString.trim().equals("")) {
                    System.out.println("ERRO: A descrição não pode estar em branco!");
                }
            } while (doceString == null || doceString.trim().equals(""));
            doce.setDescricao(doceString);
            
            do {
                doceChar = scan.receberLetra("É feito no restaurante? (s/n): ");
                switch (doceChar) {
                    case 's':case 'S':
                        doce.setFeitoNoRestaurante();
                        break;
                    case 'n':case 'N':
                        break;
                    default:
                    System.out.println("ERRO: É preciso especificar se o doce é feito no restaurante!");
                }
            } while (doceChar != 's' && doceChar != 'S' || doceChar != 'n' && doceChar != 'N');
            
            listaProdutos.add(doce);
            System.out.println("++ Doce adicionado com sucesso! ++");
        }
    }
    
    private void adicionarPrato(){
        
    }
    
    private void adicionarSnack(){
        
    }
    
    public void adicionarProduto() {
        int opcao;
        menuGraficoProdutos();
        opcao = scan.receberNumeroInt("Que tipo de produto quer adicionar? ");

        switch (opcao) {
            case 1:
                adicionarBebida();
                break;
            case 2:
                adicionarDoce();
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("ERRO! Opção inválida!");
        }
    }

    public void setNome(String nomeRestaurante) {
        if (nomeRestaurante != null || nomeRestaurante.trim().equals("")) {
            nome = nomeRestaurante;
        }
    }

//    public void setListaMesa(ArrayList<Mesa> listaMesa) {
//        this.listaMesa = listaMesa;
//    }
    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
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
