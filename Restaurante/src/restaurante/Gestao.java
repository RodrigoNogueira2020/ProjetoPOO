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
    private boolean verificarDuplicados(Produto produtoNovo) {
        for (Produto elemento : listaProdutos) {
            if (elemento.getNome().equals(produtoNovo.getNome())) {
                return true;
            }
        }

        return false;
    }

    private void adicionarBebida() {
        Bebida bebida = new Bebida();
        String tipoProduto = "A bebida";

        while (true) {
//            bebidaString = scan.receberTexto("Nome da bebida");
            if (bebida.setNome(scan.receberTexto("Nome da bebida"), tipoProduto)) {
                break;
            }
//                System.out.println("ERRO: A bebida precisa de um nome!");
        }

        if (!verificarDuplicados(bebida)) {

            while (true) {
//                bebidaDouble = scan.receberNumeroDouble("Preço da bebida");
                if (bebida.setPreco(scan.receberNumeroDouble("Preço da bebida"), tipoProduto)) {
                    break;
                }
//                    System.out.println("ERRO: O preço não pode ser negativo!");
            }

            while (true) {
//                bebidaDouble = scan.receberNumeroDouble("Capacidade da bebida (L)");
                if (bebida.setCapacidade(scan.receberNumeroDouble("Capacidade da bebida (L)"))) {
                    break;
                }
//                    System.out.println("ERRO: A capacidade da bebida não pode ser menor que 0,33 L!");
            }

            while (true) {
//                bebidaChar = scan.receberLetra("Bebida alcoólica? (s/n): ");
                if (bebida.setTemAlcool(scan.receberLetra("Bebida alcoólica? (s/n): "))) {
                    break;
                }
            }

            listaProdutos.add(bebida);
            System.out.println("++ Bebida adicionada com sucesso! ++");
        } else {
            System.out.println("ERRO: Já existe um produto com esse nome!");
        }
    }

    private void adicionarDoce() {
        Doce doce = new Doce();
        String tipoProduto = "O doce";

        while (true) {
//            doceString = scan.receberTexto("Nome do doce");
            if (doce.setNome(scan.receberTexto("Nome do doce"), tipoProduto)) {
                break;
            }
//                System.out.println("ERRO: A bebida precisa de um nome!");

        }

        if (!verificarDuplicados(doce)) {

            while (true) {
//            doceString = scan.receberTexto("Nome do doce");
                if (doce.setPreco(scan.receberNumeroDouble("Preço do doce"), tipoProduto)) {
                    break;
                }
//                System.out.println("ERRO: A bebida precisa de um nome!");
            }

            while (true) {
//                doceString = scan.receberTexto("Descrição do doce");
                if (doce.setDescricao(scan.receberTexto("Descrição do doce")))
                    break;
//                    System.out.println("ERRO: A descrição não pode estar em branco!");

            }
//            doce.setDescricao(doceString);

            while (true) {
//               doceChar = scan.receberLetra("É feito no restaurante? (s/n): ");
                if (doce.setFeitoNoRestaurante(scan.receberLetra("É feito no restaurante? (s/n): ")))
                    break;
            }

            listaProdutos.add(doce);
            System.out.println("++ Doce adicionado com sucesso! ++");
        }
    }

    private void adicionarPrato() {
        Prato prato = new Prato();
        String tipoProduto = "O prato";

        while (true) {
//            doceString = scan.receberTexto("Nome do doce");
            if (prato.setNome(scan.receberTexto("Nome do prato"), tipoProduto)) {
                break;
            }
//                System.out.println("ERRO: A bebida precisa de um nome!");

        }

        if (!verificarDuplicados(prato)) {

            while (true) {
//            doceString = scan.receberTexto("Nome do doce");
                if (prato.setPreco(scan.receberNumeroDouble("Preço do prato"), tipoProduto)) {
                    break;
                }
//                System.out.println("ERRO: A bebida precisa de um nome!");
            }

            while (true) {
//                doceString = scan.receberTexto("Descrição do doce");
                if (prato.setDescricao(scan.receberTexto("Descrição do prato")))
                    break;
//                    System.out.println("ERRO: A descrição não pode estar em branco!");

            }
//            doce.setDescricao(doceString);

            listaProdutos.add(prato);
            System.out.println("++ Prato adicionado com sucesso! ++");
        }
    }

    private void adicionarSnack() {
        Snack snack = new Snack();
        String tipoProduto = "O snack";

        while (true) {
//            doceString = scan.receberTexto("Nome do doce");
            if (snack.setNome(scan.receberTexto("Nome do snack"), tipoProduto)) {
                break;
            }
//                System.out.println("ERRO: A bebida precisa de um nome!");

        }

        if (!verificarDuplicados(snack)) {

            while (true) {
//            doceString = scan.receberTexto("Nome do doce");
                if (snack.setPreco(scan.receberNumeroDouble("Preço do snack"), tipoProduto)) {
                    break;
                }
//                System.out.println("ERRO: A bebida precisa de um nome!");
            }

            while (true) {
//                doceString = scan.receberTexto("Descrição do doce");
                if (snack.setQuantidade(scan.receberNumeroInt("Quantidade do snack")))
                    break;
//                    System.out.println("ERRO: A descrição não pode estar em branco!");
            }
//            doce.setDescricao(doceString);

            while (true) {
//                doceString = scan.receberTexto("Descrição do doce");
                if (snack.setTemPicante(scan.receberLetra("Descrição do snack")))
                    break;
//                    System.out.println("ERRO: A descrição não pode estar em branco!");
            }

            listaProdutos.add(snack);
            System.out.println("++ Snack adicionado com sucesso! ++");
        }
    }

    public void adicionarProduto() {
        int opcao;
        
        while(true){
            menuGraficoProdutos();

            switch(scan.receberNumeroInt("Que tipo de produto quer adicionar? ")) {
                case 0:
                    return;
                case 1:
                    adicionarBebida();
                    break;
                case 2:
                    adicionarDoce();
                    break;
                case 3:
                    adicionarPrato();
    //                break;
                case 4:
                    adicionarSnack();
                    break;
                default:
                    System.out.println("ERRO! Opção inválida!");
            }
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
