package restaurante;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Gestao {

    private String nome;
    private Mesa[] listaMesa;
    private ArrayList<Produto> listaProdutos;
    private Historico historicoPedidos;
    private InputReader scan;
    private LocalDateTime data;

    public Gestao() {
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
        historicoPedidos = new Historico();
        /*testes*/
        nome = "Top";
        Produto p1 = new Bebida();
        Produto p2 = new Bebida("pop", 1, 5, true);
        listaProdutos.add(p1);
        listaProdutos.add(p2);
        definirNumeroMesas();
        /* Depois dos testes */
//        definirNome();
//        adicionarMesas("Quantas mesas tem o restaurante?");
    }

    public Gestao(String nomeRestaurante) {
        if (nomeRestaurante != null || !nomeRestaurante.trim().equals(""))
            nome = nomeRestaurante;
        else
            nome = "Um restaurante qualquer";

        listaProdutos = new ArrayList<>();
        scan = new InputReader();
    }

    private void definirNome() {
        do {
            nome = scan.receberTexto("Introduza o nome do restaurante").trim();
        } while (nome == null || nome.trim().equals(""));
    }
    
    private void preencherMesas(int numeroAtualMesas){
        int quantidadeMesas;
        
        if(listaMesa[0] == null)
            quantidadeMesas = listaMesa.length;
        else
            quantidadeMesas = listaMesa.length+1;
            
        for(int i = 0; i < quantidadeMesas; i++){
            if(listaMesa[i] == null){
                Mesa novaMesa = new Mesa(i);
                listaMesa[i] = novaMesa;
            }
        }
    }
    
    private void definirNumeroMesas(){
        int numeroMesas = 0;
        while (numeroMesas <= 0) {
            numeroMesas = scan.receberNumeroInt("Quantas mesas tem o restaurante?");
            
            if(numeroMesas <= 0)
                System.out.println("ERRO: Número de mesas precisa de ser um valor positivo!");
        }
        
        listaMesa = new Mesa[numeroMesas];
        preencherMesas(numeroMesas);
    }
    
    private void adicionarMesas(String pedidoNumeroMesas) {
        int numeroMesas = 0;
        while (numeroMesas <= 0) {
            numeroMesas = scan.receberNumeroInt(pedidoNumeroMesas);
            
            if(numeroMesas <= 0)
                System.out.println("ERRO: Número de mesas precisa de ser um valor positivo!");
        }
        
        int j = listaMesa.length + numeroMesas;
        Mesa[] mesasTemp = new Mesa[j];
        mesasTemp = listaMesa;
        
        listaMesa = new Mesa[j];
        listaMesa = mesasTemp;
//        preencherMesas(j);
        imprimirMesas();
    }
    
    private void menuGrafico() { // TODO: Eliminar mesa | Mostrar produtos (e ordena-los) | Adicionar mesas e preservar dados do array atual para o novo
        System.out.println("=== " + nome + " === Versão 0.5.8 29/05/2021 23:12");
        System.out.println("* 1 - Adicionar produto"); // feito
        System.out.println("* 2 - Remover produto"); // feito
        System.out.println("* 3 - Listar produtos"); // feito, falta ordenar
        System.out.println("* 4 - Reservar uma nova mesa"); // Falta perguntar se quer mudar para servido o estado do pedido
        System.out.println("* 5 - Adicionar uma nova mesa");
        System.out.println("* 6 - Editar uma mesa");
        System.out.println("* 7 - Remover uma mesa");
        System.out.println("* 0 - Sair da aplicação"); // feito
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
                    editarMesa(selecionarMesa());
                    
                    break;
                case 7:
//                    removerMesa();
                case 8:
//                    removerMesa();
                    System.out.println(historicoPedidos);
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
        System.out.println("* 0 - Sair       *");
        System.out.println("******************");
    }

    /**
     *
     * @param produtoNovo Produto a ser introduzido na lista, verifica se o nome
     * de um produto já existe.
     * @return true - se já houver um produto com o mesmo nome
     */
    private boolean verificarDuplicados(Produto produtoNovo) {
        for (Produto elemento : listaProdutos)
            if (elemento.getNome().equals(produtoNovo.getNome()))
                return true;

        return false;
    }

    private void adicionarBebida() {
        Bebida bebida = new Bebida();
        String tipoProduto = "A bebida";

        while ( !bebida.setNome( scan.receberTexto("Nome da bebida"), tipoProduto ) ){}
            
//            bebidaString = scan.receberTexto("Nome da bebida");
//            if (bebida.setNome(scan.receberTexto("Nome da bebida"), tipoProduto)) {
//                break;
//            }
//                System.out.println("ERRO: A bebida precisa de um nome!");

        if (!verificarDuplicados(bebida)) {

            while (true) {
                if (bebida.setPreco(scan.receberNumeroDouble("Preço da bebida"), tipoProduto)) {
                    break;
                }
            }

            while (true) {
                if (bebida.setCapacidade(scan.receberNumeroDouble("Capacidade da bebida (L)"))) {
                    break;
                }
            }

            while (true) {
                if (bebida.setTemAlcool(scan.receberLetra("Bebida alcoólica? (s/n): "))) {
                    break;
                }
            }

            listaProdutos.add(bebida);
            System.out.println("++ Bebida adicionada com sucesso! ++");
        } else
            System.out.println("ERRO: Já existe um produto com esse nome!");
    }

    private void adicionarDoce() {
        Doce doce = new Doce();
        String tipoProduto = "O doce";

        while (true) {
            if (doce.setNome(scan.receberTexto("Nome do doce"), tipoProduto)) {
                break;
            }

        }

        if (!verificarDuplicados(doce)) {

            while (true) {
                if (doce.setPreco(scan.receberNumeroDouble("Preço do doce"), tipoProduto)) {
                    break;
                }
            }

            while (true) {
                if (doce.setDescricao(scan.receberTexto("Descrição do doce")))
                    break;

            }

            while (true) {
                if (doce.setFeitoNoRestaurante(scan.receberLetra("É feito no restaurante? (s/n): ")))
                    break;
            }

            listaProdutos.add(doce);
            System.out.println("++ Doce adicionado com sucesso! ++");
        } else
            System.out.println("ERRO: Já existe um produto com esse nome!");
    }

    private void adicionarPrato() {
        Prato prato = new Prato();
        String tipoProduto = "O prato";

        while (true) {
            if (prato.setNome(scan.receberTexto("Nome do prato"), tipoProduto))
                break;
            
        }

        if (!verificarDuplicados(prato)) {

            while (true) {
                if (prato.setPreco(scan.receberNumeroDouble("Preço do prato"), tipoProduto)) {
                    break;
                }
            }

            while (true) {
                if (prato.setDescricao(scan.receberTexto("Descrição do prato")))
                    break;

            }

            listaProdutos.add(prato);
            System.out.println("++ Prato adicionado com sucesso! ++");
        } else
            System.out.println("ERRO: Já existe um produto com esse nome!");
    }

    private void adicionarSnack() {
        Snack snack = new Snack();
        String tipoProduto = "O snack";

        while (true) {
            if (snack.setNome(scan.receberTexto("Nome do snack"), tipoProduto)) {
                break;
            }

        }

        if (!verificarDuplicados(snack)) {

            while (true) {
                if (snack.setPreco(scan.receberNumeroDouble("Preço do snack"), tipoProduto)) {
                    break;
                }
            }

            while (true) {
                if (snack.setQuantidade(scan.receberNumeroInt("Quantidade do snack")))
                    break;
            }

            while (true) {
                if (snack.setTemPicante(scan.receberLetra("Descrição do snack")))
                    break;
            }

            listaProdutos.add(snack);
            System.out.println("++ Snack adicionado com sucesso! ++");
        } else
            System.out.println("ERRO: Já existe um produto com esse nome!");
    }

    public void adicionarProduto() {
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
                    break;
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
            
            if ( it.next().getNome().equals( listaProdutos.get(i).getNome() ) ) {
                System.out.println("--" + listaProdutos.get(i).getNome() + " removido com sucesso!");
                it.remove();
                break;
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
    
    private Mesa selecionarMesa(){
        int i=0;
        while(true){
            listarMesas();
            try{
                i = scan.receberNumeroInt("Introduza o número da mesa que deseja editar");
                --i;

                if(i == -1)
                    return null;
                else if(listaMesa[i].isOcupada() && 0 <= i && i<= listaMesa.length)
                    break;
                else if(i<= listaMesa.length && i >= 0 && !listaMesa[i].isOcupada())
                    System.out.println("ERRO: Mesa ainda não está reservada!");
            }catch(ArrayIndexOutOfBoundsException OutOfBounds){
                System.err.println("ERRO: Indique o número da mesa apresentado na lista!");
            }
        }
        return listaMesa[i];
    }
    
    private boolean verificarEstado(Mesa mesa){
        char pedirCaracter=' ';
        if(mesa == null)
            return false;
        
        try{
            switch(mesa.getPedido().getEstado()){
                case ABERTO:
                    return true;
                case EM_PREPARACAO: 
                    do{
                        System.out.println("A mesa " + mesa.getNumero() + " tem o estado: " + mesa.getPedido().mostrarEstado());
                        
                        pedirCaracter = scan.receberTexto("Deseja (a)dicionar mais produtos, passar o estado para (s)ervido, (f)echar o pedido ou sair(= 0)?").trim().toLowerCase().charAt(0);
                        switch(pedirCaracter){
                            case 'a':
                                return true;
                            case 's':
                                mesa.getPedido().definirEstado(PedidoEstado.SERVIDO);
                                return false;
                            case 'f':
                                mesa.getPedido().fecharPedido();
                                /*todo: Meter no historico*/
                                historicoPedidos.adicionarPedido(mesa.getPedido());
                                mesa.setOcupada();
                                mesa.removerPedido();
                                return false;
                            case '0':
                                return false;
                            default:
                                System.out.println("ERRO: Introduza (a), (s) ou (f) apenas.");
                        }
                        
                    }while(true);
                    
                case SERVIDO:
                    do{
                        System.out.println("A mesa " + mesa.getNumero() + " tem o estado: " + mesa.getPedido().mostrarEstado());
                        
                        pedirCaracter = scan.receberTexto("Deseja (a)dicionar mais produtos, (f)echar o pedido ou sair(= 0)?").trim().toLowerCase().charAt(0);
                        switch(pedirCaracter){
                            case 'a':
                                return true;
                            case 'f':
                                mesa.getPedido().fecharPedido();
                                /*todo: Meter no historico*/
                                historicoPedidos.adicionarPedido(mesa.getPedido());
                                mesa.setOcupada();
                                mesa.removerPedido();
                                return false;
                            case '0':
                                return false;
                            default:
                                System.out.println("ERRO: Introduza (a) ou (f) apenas.");
                        }
                        
                    }while(true);
                default:
                    System.out.println("ERRO: Estado do pedido inserido é inválido!");
            }
        }catch(java.lang.NullPointerException semPedido){
            System.err.println("ERRO: Mesa ainda não está reservada!");
            return false;
        }
        return false;
    }
    
    private void editarMesa(Mesa mesa){
        int j;
        char pedirCaracter=' ';
        
        if(!verificarEstado(mesa) || mesa == null)
            return;
        
        while(true){
            Item item = new Item();
            try{
                listarProdutos();
                j = scan.receberNumeroInt("Introduza o número dos produtos que deseja adicionar (0 - Sair)");
                --j;

                if(j == -1)
                    break;
                else if(j<= listaProdutos.size() && j >= 0)
                    item.setProduto(listaProdutos.get(j));

                while(true){
                    j = scan.receberNumeroInt("Introduza a quantidade");

                    if(j == 0)
                        break;
                    else if(j > 0){
                        item.setQuantidade(j);
                        break;
                    }
                    else
                        System.err.println("ERRO: Quantidade tem de ser maior que zero!");
                }
                mesa.getPedido().adicionarItem(item);
                System.out.println("++" + item.getProduto().getNome() + " adicionado com sucesso!");
            }catch(ArrayIndexOutOfBoundsException OutOfBounds){
                System.err.println("ERRO: Indique o número do produto apresentado na lista!");
            }

            mesa.getPedido().definirEstado(PedidoEstado.EM_PREPARACAO);
            do{
                pedirCaracter = scan.receberTexto("Deseja adicionar mais? [s/n]").trim().toLowerCase().charAt(0);
                if(pedirCaracter != 's' && pedirCaracter != 'n')
                    System.out.println("ERRO: Introduza 's' ou 'n' para sim ou não respetivamente");
            }while(pedirCaracter != 's' && pedirCaracter != 'n');

            if(pedirCaracter == 'n')
                break;
        }
        
        System.out.println("== Items adicionados ao pedido da mesa " + mesa.getNumero() + " ====");
        mesa.getPedido().listarItens();
        System.out.println("== ===================================== ====");
    }
    
    private void reservarMesa(){
        int i;
        char j;
        
        while(true){
            listarMesas();
            
            try{
                i = scan.receberNumeroInt("Introduza o número da mesa que deseja reservar");
                --i;
                if(i == -1)
                    return;
                else if(listaMesa[i].isOcupada() == true)
                    System.out.println("ERRO: Mesa já está reservada!");
                else if(i<= listaMesa.length && i >= 0 && !listaMesa[i].isOcupada())
                    break;
            }catch(ArrayIndexOutOfBoundsException OutOfBounds){
                System.err.println("ERRO: Indique o número da mesa apresentado na lista!");
            }
        }
        
        for(int m = 0; m <= listaMesa.length; m++){
            if (listaMesa[i].equals(listaMesa[m])) {
                
                do{
                    j = scan.receberTexto("Reserva-se a mesa para (a)gora ou para mais (t)arde?").toLowerCase().charAt(0);
                }while(j != 'a' && j != 't');
                
                Pedido pedidoTemp = new Pedido();
                switch(j){
                    case 'a':
                        pedidoTemp.abrirPedido(LocalDateTime.now());
                        listaMesa[i].setOcupada(); // Se a mesa é reservada para agora, é porque vai estar ocupada agora
                        listaMesa[i].setPedido(pedidoTemp);
                        break;
                    case 't':
                        LocalDateTime dataAtual = LocalDateTime.now();
                        LocalDateTime dataReserva = LocalDateTime.of(2011, 1, 1, 1,1);
                        String data;
                        String[] dataFormatada = new String[10];
                        do{
                            try{
                                data = scan.receberTexto("Introduza a data (DD-MM-AAAA)").trim(); // 2-12-2021
                                    
                                if(data.length() <= 10){
                                    if(data.contains("-"))
                                        dataFormatada = data.split("-");
                                    
                                    else if(data.contains("/"))
                                        dataFormatada = data.split("/");
                                    
                                    else if(data.contains(" "))
                                        dataFormatada = data.split(" ");
                                    
                                    else
                                        System.err.println("ERRO: ");
                                }
                                
                                // ["1" , "5", "2005"]
                                dataReserva = dataReserva.withYear(Integer.parseInt(dataFormatada[2]));
                                dataReserva = dataReserva.withMonth(Integer.parseInt(dataFormatada[1]));
                                dataReserva = dataReserva.withDayOfMonth(Integer.parseInt(dataFormatada[0]));
                                
                                if(dataReserva.isAfter(dataAtual))
                                    break;
                                else
                                    System.err.println("ERRO: Ano introduzido deve ser posterior ao ano atual!");
                            }catch (DateTimeException dataExcecao){
                                System.err.println("ERRO: Data introduzida não está formatada conforme especificado! (DD-MM-AAAA)");
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Data introduzida não está formatada conforme especificado ou foram introduzidos outros caracteres não releveantes! (DD-MM-AAAA)");
                            }
                            
                        }while(dataReserva.isBefore(dataAtual));
                        data = "";
                        do{
                            try{
                                data = scan.receberTexto("Introduza a hora (HH:MM)").trim();
                                    
                                if(data.length() <= 5){
                                    if(data.contains(":"))
                                        dataFormatada = data.split(":");
                                    else if(data.contains("-"))
                                        dataFormatada = data.split("-");
                                    else if(data.contains("/"))
                                        dataFormatada = data.split("/");
                                    else if(data.contains(" "))
                                        dataFormatada = data.split(" ");
                                }
                                
                                // ["10", "30"]
                                dataReserva = dataReserva.withHour(Integer.parseInt(dataFormatada[0]));
                                dataReserva = dataReserva.withMinute(Integer.parseInt(dataFormatada[1]));
                                
                                if(dataReserva.isAfter(dataAtual)){
                                    pedidoTemp.abrirPedido(dataReserva);
                                    break;
                                }
                                else
                                    System.err.println("ERRO: Hora introduzida deve ser posterior à hora atual!");
                            }catch (DateTimeException dataExcecao){
                                System.err.println("ERRO: Hora introduzida não está formatada conforme especificado! (HH:MM)");
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Hora introduzida não está formatada conforme especificado ou foram introduzidos outros caracteres não releveantes! (HH:MM)");
                            }
                            
                        }while(dataReserva.isBefore(dataAtual));
                        
                        listaMesa[i].setOcupada(); 
                        listaMesa[i].setPedido(pedidoTemp);
                        
                }
                
                System.out.println("++Mesa " + listaMesa[i].getNumero() + " reservada com sucesso!++");
                break;
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

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

}
