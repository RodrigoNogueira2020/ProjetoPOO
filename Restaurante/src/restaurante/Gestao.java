package restaurante;

import java.util.ArrayList;

public class Gestao {

    private String nome;
//    private ArrayList<Mesa> listaMesa;
    private ArrayList<Produto> listaProdutos;
    private InputReader scan;

    public Gestao() {
        nome = "";
//        listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
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

    public void definirNome() {
        do {
            nome = scan.getText("Introduza o nome do restaurante");
            System.out.println(nome);
        } while (nome == null || nome.trim().equals(""));
    }

    public void menuGrafico() {
        System.out.println("**** MENU ****");
        System.out.println("* 1 - Criar produto *");
        System.out.println("* 2 - Reservar uma nova mesa *");
        System.out.println("* 3 - Adicionar uma nova mesa *");
        System.out.println("* 0 - Sair do menu *");
        System.out.println("**************");
    }

    public void menuGraficoProdutos() {
        System.out.println("**** PRODUTOS ****");
        System.out.println("* 1 - Bebida     *");
        System.out.println("* 2 - Doce       *");
        System.out.println("* 3 - Prato      *");
        System.out.println("* 4 - Snack      *");
        System.out.println("******************");
    }

    public void menu() {

        int opcao;

        do {
            menuGrafico();

            opcao = scan.getNumberInt("Escolha uma opção: ");

            switch (opcao) {
                case 1:
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

    public void criarProduto() {
        int opcao;

        opcao = scan.getNumberInt("Escolha uma opção: ");

        menuGraficoProdutos();

        switch (opcao) {
            case 1:
                Bebida bebida = new Bebida();
                String nomeBebida;
                double bebidaDouble;

                do {
                    nomeBebida = scan.getText("Nome da bebida: ");
                    if (nomeBebida == null || nomeBebida.trim().equals("")) {
                        System.out.println("ERRO: A bebida precisa de nome!");
                    }
                } while (nomeBebida == null || nomeBebida.trim().equals(""));
                bebida.setNome(nomeBebida);

                do {
                    bebidaDouble = scan.getNumberDouble("Preço da bebida: ");
                    if (bebidaDouble < 0) {
                        System.out.println("ERRO: O preço não pode ser negativo!");
                    }
                } while (bebidaDouble < 0);
                bebida.setPreco(bebidaDouble);
                bebidaDouble = 0;

                do {
                    bebidaDouble = scan.getNumberDouble("Quantidade da bebida: ");
                    if (bebidaDouble < 0) {
                        System.out.println("ERRO: O preço não pode ser negativo!");
                    }
                } while (bebidaDouble < 0);
                bebida.setCapacidade(bebidaDouble);
                break;

            case 2:
                Doce doce = new Doce();
                String descricao;
                String feitoNoRestaurante;

                do {
                    descricao = scan.getText("Nome da bebida: ");
                    if (descricao == null || descricao.trim().equals("")) {
                        System.out.println("ERRO: A bebida precisa de nome!");
                    }
                } while (descricao == null || descricao.trim().equals(""));
                doce.setDescricao(descricao);

                do {
                    feitoNoRestaurante = scan.getText("Bebida alcoólica? (S/N): ");
                    if (feitoNoRestaurante.toUpperCase() == "S" || feitoNoRestaurante.toUpperCase() == "N") {
                        switch (feitoNoRestaurante) {
                            case "S":
                                boolean feitoNoRestauranteBooleanSim = Boolean.parseBoolean(feitoNoRestaurante);
                                feitoNoRestauranteBooleanSim = true;
                                doce.setFeitoNoRestaurante(feitoNoRestauranteBooleanSim);
                                break;
                            case "N":
                                boolean feitoNoRestauranteBooleanNao = Boolean.parseBoolean(feitoNoRestaurante);
                                feitoNoRestauranteBooleanNao = false;
                                doce.setFeitoNoRestaurante(feitoNoRestauranteBooleanNao);
                                break;
                        }
                    } else {
                        System.out.println("ERRO: A bebida precisa de ter ou não alcoól!");
                    }
                } while (feitoNoRestaurante == null || feitoNoRestaurante.trim().equals(""));

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
