package restaurante;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

public class Gestao {

    private final String NOME = "Top Wings";
    private Mesa[] listaMesa;
    private ArrayList<Produto> listaProdutos;
    private Historico historicoPedidos;
    private InputReader scan;

    public Gestao() {
        listaProdutos = new ArrayList<>();
        scan = new InputReader();
        historicoPedidos = new Historico();
        /*testes*/
        Produto p1 = new Bebida();
        Produto p2 = new Bebida("pop", 1, 5, true);
        listaProdutos.add(p1);
        listaProdutos.add(p2);
        definirNumeroMesas();
        /* Depois dos testes */
//        definirNome();
//        adicionarMesas("Quantas mesas tem o restaurante?");
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
    
    private void menuGrafico() { // Adicionar mesas e preservar dados do array atual para o novo
        System.out.println("=== " + NOME + " === Versão 0.6.0 02/06/2021 22:26");
        System.out.println("* 1 - Adicionar produto"); // feito
        System.out.println("* 2 - Remover produto"); // feito
        System.out.println("* 3 - Listar produtos"); // feito
        System.out.println("* 4 - Reservar uma nova mesa"); // feito
        System.out.println("* 5 - Editar uma mesa"); // feito, falta mostra o recibo com: Data de abertura/fecho, itens, preco sem/com IVA
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
                    editarMesa(selecionarMesa());
                    break;
                case 6: // Teste todo: retirars
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
     * @param produtoNovo Produto a ser introduzido na lista, verifica se o NOME
 de um produto já existe.
     * @return true - se já houver um produto com o mesmo NOME
     */
    private boolean verificarDuplicados(Produto produtoNovo) {
        for (Produto elemento : listaProdutos)
            if (elemento.getNome().toLowerCase().equals(produtoNovo.getNome().toLowerCase()))
                return true;

        return false;
    }

    private void adicionarBebida() {
        Bebida bebida = new Bebida();
        String tipoProduto = "A bebida";

        while ( !bebida.setNome(scan.receberTexto("Nome da bebida"), tipoProduto ) ){}
            
//            bebidaString = scan.receberTexto("Nome da bebida");
//            if (bebida.setNome(scan.receberTexto("Nome da bebida"), tipoProduto)) {
//                break;
//            }
//                System.out.println("ERRO: A bebida precisa de um NOME!");

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
                        editarMesa(listaMesa[i]);
                        break;
                    case 't':
                        LocalDateTime dataAtual = LocalDateTime.now();
                        LocalDateTime dataReserva = LocalDateTime.of(2011, 1, 1, 23, 59);
                        String data;
                        String[] dataFormatada = new String[10];
                        boolean temErros = false;
                        do{
                            try{
                                temErros = false;
                                data = scan.receberTexto("Introduza a data (DD-MM-AAAA)").trim(); // 2-12-2021

                                if(data.length() <= 10){
                                    if(data.contains("-"))
                                        dataFormatada = data.split("-");

                                    else if(data.contains("/"))
                                        dataFormatada = data.split("/");

                                    else if(data.contains(" "))
                                        dataFormatada = data.split(" ");
                                }
                                
                                // Caso o utilizador se esqueça de introduzir o dia e/ou mês e/ou ano
                                if(dataFormatada == null || dataFormatada[0] == null || dataFormatada[1] == null || dataFormatada[2] == null){
                                    throw new InvalidInputArgumentException("ERRO: Uma das partes da data está em falta!");
                                }

                                // ["1" , "5", "2005"]
                                /*Verificar o ano introduzido*/
                                try{
                                    dataReserva = dataReserva.withYear(Integer.parseInt(dataFormatada[2]));
                                }catch(DateTimeException ano){
                                    System.err.println("ERRO: Ano introduzido é inválido!");
                                    temErros = true;
                                }

                                /*Verificar o mês introduzido*/
                                try{
                                    dataReserva = dataReserva.withMonth(Integer.parseInt(dataFormatada[1]));
                                }catch(DateTimeException mesErro){
                                    System.err.println("ERRO: Mês introduzido é inválido!");
                                    temErros = true;
                                }

                                /*Verificar o dia introduzido, verifica se foi introduzido o 29 de Fevereiro num ano bissexto*/
                                try{
                                    dataReserva = dataReserva.withDayOfMonth(Integer.parseInt(dataFormatada[0]));
                                    if(!dataReserva.isAfter(dataAtual))
                                        throw new InvalidInputArgumentException("ERRO: Data introduzida deve ser posterior à data atual!");

                                }catch(DateTimeException diaErro){
                                    int verificarAnoBissexto = Integer.parseInt(dataFormatada[2]);

                                    if (verificarAnoBissexto % 4 == 0 && verificarAnoBissexto % 100 == 0 && verificarAnoBissexto % 400 == 0)
                                        System.err.println("ERRO: Dia introduzido é inválido!");
                                    else
                                        System.err.println("ERRO: Dia introduzido é inválido, verifique se o ano é bissexto!");
                                    temErros = true;

                                }catch(InvalidInputArgumentException dataAnterior){
                                    System.err.println(dataAnterior);
                                    temErros = true;
                                }
                            }catch(InvalidInputArgumentException dataNull){
                                System.err.println(dataNull);
                                temErros = true;
                            }
                        }while(temErros);
                        
                        data = "";
                        dataFormatada = null;
                        do{
                            data = scan.receberTexto("Introduza a hora (HH:MM)").trim();

                            if(data.contains(":"))
                                dataFormatada = data.split(":");
                            
                            else if(data.contains("-"))
                                dataFormatada = data.split("-");
                            
                            else if(data.contains("/"))
                                dataFormatada = data.split("/");
                            
                            else if(data.contains(" "))
                                dataFormatada = data.split(" ");

                            // ["10", "30"]
                            /*Verificar as horas introduzidas*/
                            try{
                                if(dataFormatada[0].length() > 2)
                                    throw new InvalidInputArgumentException("ERRO: Hora não pode ter mais do que 2 algarismos!");
                                else
                                    dataReserva = dataReserva.withHour(Integer.parseInt(dataFormatada[0]));
                                
                            }catch(InvalidInputArgumentException horaErro){
                                System.out.println(horaErro);
                                temErros = true;
                                
                            }catch(DateTimeException hora){
                                System.err.println("ERRO: Hora introduzida é inválido (Não pode conter letras ou outros caracteres inválidos)!");
                                temErros = true;
                                
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Hora introduzida não está formatada conforme especificado ou foram introduzidos outros caracteres não releveantes! (HH:MM)");
                                temErros = true;
                                
                            }
                            
                            /*Verificar os minutos introduzidos*/
                            try{
                                dataReserva = dataReserva.withMinute(Integer.parseInt(dataFormatada[1]));
                                
                                if(dataFormatada[0].length() > 2)
                                    throw new InvalidInputArgumentException("ERRO: Os minutos não podem ter mais do que 2 algarismos!");
                                else if(!dataReserva.isAfter(dataAtual))
                                    throw new InvalidInputArgumentException("ERRO: Data introduzida deve ser posterior à data atual!");
                                
                            }catch(DateTimeException minuto){
                                System.err.println("ERRO: Minuto introduzido é inválido!");
                                temErros = true;
                                
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Os minutos introduzidos não estam formatados conforme especificado ou foram introduzidos outros caracteres não releveantes! (HH:MM)");
                                temErros = true;
                                
                            }catch(InvalidInputArgumentException dataErro){
                                System.err.println(dataErro);
                                temErros = true;
                                
                            }

                    }while(temErros);

                    pedidoTemp.abrirPedido(dataReserva);
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

    public void setListaProdutos(ArrayList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public void imprimirMesas(){
        for(Mesa element: listaMesa)
            System.out.println(element);
    }

    public ArrayList<Produto> getListaProdutos() {
        return listaProdutos;
    }

}
