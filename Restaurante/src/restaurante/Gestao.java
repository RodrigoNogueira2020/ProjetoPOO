package restaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Gestao {

    private String nome;
    private ArrayList<Mesa> listaMesa;
    private ArrayList<Produto> listaProdutos;
    private InputReader scan;
    private LocalDateTime data;

    public Gestao() {
        listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
        /*testes*/
        nome = "Top";
        Produto p1 = new Bebida();
        Produto p2 = new Bebida("pop", 1, 5, true);
        listaProdutos.add(p1);
        listaProdutos.add(p2);
        /* Depois dos testes */
//        definirNome();
//        adicionarMesas("Quantas mesas tem o restaurante?");
    }

    public Gestao(String nomeRestaurante) {
        if (nomeRestaurante != null || !nomeRestaurante.trim().equals("")) {
            nome = nomeRestaurante;
        } else {
            nome = "Um restaurante qualquer";
        }

        listaMesa = new ArrayList<>();
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
    }

    private void definirNome() {
        do {
            nome = scan.receberTexto("Introduza o nome do restaurante").trim();
        } while (nome == null || nome.trim().equals(""));
    }
    
    // "Quantas mesas pretende adicionar?"
    private void adicionarMesas(String pedidoNumeroMesas) {
        int numeroMesas = 0;
         while (numeroMesas <= 0) {
            numeroMesas = scan.receberNumeroInt(pedidoNumeroMesas);
            
            if(numeroMesas <= 0)
                System.out.println("ERRO: Número de mesas precisa de ser um valor positivo!");
        }
         
        int j = listaMesa.size() + numeroMesas;
        
        for(int i = listaMesa.size()+1; i <= j; i++){
            Mesa novaMesa = new Mesa(i);
            listaMesa.add(novaMesa);
        }
        imprimirMesas();
    }

    private void menuGrafico() { // TODO: Eliminar produto/mesa | Mostrar produtos (e ordena-los)
        System.out.println("=== " + nome + " ===");
        System.out.println("* 1 - Adicionar produto");
        System.out.println("* 2 - Remover produto");
        System.out.println("* 3 - Listar produtos"); // ordenar
        System.out.println("* 4 - Reservar uma nova mesa");
        System.out.println("* 5 - Adicionar uma nova mesa");
        System.out.println("* 6 - Editar uma mesa");
        System.out.println("* 7 - Remover uma nova mesa");
        System.out.println("* 0 - Sair da aplicação");
        System.out.println("**************");
    }

    public void menu() {
        int opcao;

        do {
            menuGrafico();
            opcao = scan.receberNumeroInt("Escolha uma opção: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    adicionarProduto();
                    break;
                case 2:
                    removerProduto();
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    reservarMesa();
                    break;
                case 5:
                    adicionarMesas("Quantas mesas quer adicionar?");
                    break;
                case 6:
//                    editarMesa();
                    break;
                case 7:
//                    removerMesa();
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

        while (!bebida.setNome(scan.receberTexto("Nome da bebida"), tipoProduto)){}
            
//            bebidaString = scan.receberTexto("Nome da bebida");
//            if (bebida.setNome(scan.receberTexto("Nome da bebida"), tipoProduto)) {
//                break;
//            }
//                System.out.println("ERRO: A bebida precisa de um nome!");

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
            if (prato.setNome(scan.receberTexto("Nome do prato"), tipoProduto))
                break;
            
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
    
    private void removerProduto(){
        int i;
        listarProdutos();
        
        while(true){
            i = scan.receberNumeroInt("Introduza o número do produto que deseja remover");
            if(i <= listaProdutos.size())
                break;
            else
                System.out.println("ERRO: Indique o número do produto apresentado na lista!");
        }
        --i;
        for(Iterator<Produto> it = listaProdutos.iterator(); it.hasNext(); ){
            Produto temp = it.next();
            if (temp.equals(listaProdutos.get(i))) {
                System.out.println("--" + listaProdutos.get(i).getNome() + " removido com sucesso!");
                it.remove();
            }
        }
    }
    
    private void listarProdutos(){
        int i = 0;
        for(Produto p: listaProdutos){
            System.out.print(++i + ") ");
            System.out.println(p);
            
        }
    }
    
    private void reservarMesa(){
        int i;
        char j;
        listarMesas();
        
        while(true){
            i = scan.receberNumeroInt("Introduza o número da mesa que deseja reservar");
            if(i <= listaMesa.size())
                break;
            else
                System.out.println("ERRO: Indique o número do produto apresentado na lista!");
        }
        --i;
        for(Iterator<Mesa> it = listaMesa.iterator(); it.hasNext(); ){
            Mesa temp = it.next();
            if (temp.equals(listaMesa.get(i))) {
                
                do{
                    j = scan.receberTexto("Reserva-se a mesa para (a)gora ou para mais (t)arde?").toLowerCase().charAt(0);
                    
                }while(j != 'a' && j != 't');
                
                System.out.println("--Mesa " + listaMesa.get(i).getNumero() + " reservada com sucesso!");
                listaMesa.get(i).setOcupada();
            }
        }
    }
    
    private void listarMesas(){
        int i = 0;
        for(Mesa m: listaMesa){
            System.out.print(++i + ") ");
            System.out.println(m);
        }
    }

    public void setNome(String nomeRestaurante) {
        if (nomeRestaurante != null || !"".equals(nomeRestaurante.trim())) {
            nome = nomeRestaurante;
        }
    }

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public void imprimirMesas(){
        for(Mesa element: listaMesa)
            System.out.println(element);
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
