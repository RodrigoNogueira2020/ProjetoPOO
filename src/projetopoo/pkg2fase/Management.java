package projetopoo.pkg2fase;

import java.io.Serializable;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Management implements Serializable{
    private Table[] tableList;
    private ArrayList<Product> productList;
    private History orderHistory;

    public Management() {
        productList = new ArrayList<>();
        orderHistory = new History();
        
    }
    
    public Table[] getTableList(){
        return tableList;
    }
    public Table getTable(int i){
        return tableList[i];
    }
    
    public void addTables(int numberOfTables) throws RestaurantException{
        Table[] tableTemp;
        
        try{
            
            if(numberOfTables < 0)
                throw new RestaurantException("ERRO: Número de mesas precisa de ser um valor positivo!");
            else if(numberOfTables < 4)
                throw new RestaurantException("ERRO: Número de mesas precisa de ser, no minimo, 4!");
            
            tableTemp = new Table[tableList.length + numberOfTables];

            int i = 0;

            // Atribui todas as mesas existentes para um array temporario
            for(; i < tableList.length; i++)
                tableTemp[i] = tableList[i];

            // Preenche os restantes indices do array temporario
            for(;i < tableTemp.length;i++)
                tableTemp[i] = new Table(i);

            tableList = new Table[tableTemp.length];

            // Copia indice a indice do array temporario ao atributo da classe
            for(i = 0; i < tableTemp.length; i++)
                tableList[i] = tableTemp[i];
            
        }catch(NullPointerException e){
            tableList = new Table[numberOfTables];
            
            for(int j = 0; j < tableList.length; j++)
                tableList[j] = new Table(j);
                
            
        }
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * Verifica se o NOME de um produto já existe,
     * utilizado quando se introduz um produto pela primeira vez.
     * 
     * @param productName Product a ser introduzido na lista
     */
    public void checkProductDuplicates(String productName) {
        for (Product elemento : productList)
            if (elemento.getName().toLowerCase().equals(productName.toLowerCase().trim()))
                throw new RestaurantException("ERRO: Já existe um produto com esse nome!");

    }
    
    /**
     * Cria uma cópia da lista de produtos e substitui o produto com os valores editados
     * pelo produto original de forma a que, caso o nome alterado coincida com um produto
     * já existente, é lançada uma exceção.
     * @param editedProduct Produto com valores alterados
     * @param index O indice do produto alterado
     * @throws RestaurantException Se o novo nome de um produto já existente coincidir com
     * o nome de outro produto.
     */
    public void checkProductDuplicates(Product editedProduct, int index) throws RestaurantException{
        ArrayList<Product> temporaryProductList = productList;
        temporaryProductList.set(index, editedProduct);
        int i = 0;
        
        for(Product elemento : productList)
            if(elemento.getName().equals(editedProduct.getName()))
                ++i;
            else switch(i){
                case 2:
                    throw new RestaurantException("ERRO: Já existe um produto com esse nome!");
            }

    }
    
    public void addProduct(Product newProduct) {
        productList.add(newProduct);
    }
    
    public void removeProduct(Product i){
        for(Iterator<Product> it = productList.iterator(); it.hasNext(); )
            if ( it.next().getName().equals( i.getName() ) ) {
                
                System.out.println("--" + i.getName() + " removido com sucesso!");
                it.remove();
                
                break;
            }
    }
    
    private boolean checkForUnoccupiedTables(){
        for(Table m: tableList)
            if(!m.isOccupied())
                return true; // Há mesas disponiveis
        
        return false;
    }
    
    private Table selectTable(){
        int i=0;
        InputReader scan = new InputReader();
        while(true){
            Menu.listTables(tableList);
            try{
                i = scan.getInt("Introduza o número da mesa que deseja editar");
                --i;

                if(i == -1)
                    return null;
                else if(tableList[i].isOccupied() && 0 <= i && i<= tableList.length)
                    break;
                else if(i<= tableList.length && i >= 0 && tableList[i].getOrder() == null)
                    throw new RestaurantException("ERRO: Mesa ainda não está reservada!");
                
            }catch(ArrayIndexOutOfBoundsException outOfBounds){
                System.err.println("ERRO: Indique o número da mesa apresentado na lista!");
            }catch(RestaurantException e){
                System.err.println(e.getMessage());
            }
        }
        return tableList[i];
    }
    
    private boolean checkTableOrderState(Table table){
        char getChar;
        InputReader scan = new InputReader();
        
        if(table == null)
            return false;
        
        switch(table.getOrder().getState()){
            case OPEN:
                if(table.getOrder().getOpenHour().isAfter( LocalDateTime.now() )){
                    while(true)
                        try{
                            
                            getChar = scan.getString("Ainda não chegou a data/hora deste pedido, deseja cancelar o pedido? [s/n]").trim().toLowerCase().charAt(0);
                            if(getChar != 's' && getChar != 'n')
                                throw new RestaurantException("ERRO: Introduza apenas (s)im ou (n)ão!");
                            
                            else if(getChar == 's'){
                                table.getOrder().closeOrder();
                                table.setOccupied();
                                table.removeOrder();
                                
                            }
                            return false;
                        }catch(RestaurantException e){
                            System.out.println(e.getMessage());
                        }
                }else
                    return true;
            case PREPARATION: case SERVED:
                while(true){
                    try{
                        System.out.println("A mesa " + table.getTableNumber() + " tem o estado: " + table.getOrder().showState());
                        if(table.getOrder().getState().equals(orderState.PREPARATION))
                            getChar = scan.getString("Deseja (a)dicionar mais produtos, passar o estado para (s)ervido, (f)echar o pedido ou sair(= 0)?").trim().toLowerCase().charAt(0);
                        else
                            getChar = scan.getString("Deseja (a)dicionar mais produtos, (f)echar o pedido ou sair(= 0)?").trim().toLowerCase().charAt(0);
                        switch(getChar){
                            case 'a':
                                return true;
                            case 's':
                                if(!table.getOrder().getState().equals(orderState.PREPARATION))
                                    throw new RestaurantException("ERRO: Estado do pedido já está como servido!");
                                else{
                                    table.getOrder().setState(orderState.SERVED);
                                    return false;
                                }
                            case 'f':
                                table.getOrder().closeOrder();
                                orderHistory.addOrder(table.getOrder());
                                table.setOccupied();
                                table.removeOrder();
                                return false;
                            case '0':
                                return false;
                            default:
                                throw new RestaurantException("ERRO: Introduza (a), (s) ou (f) apenas.");
                        }
                    }catch(RestaurantException e){
                        System.err.println(e.getMessage());
                    }
                }

        }
        
        return false;
    }
    
    private void checkItemDuplicatesInTableOrder(Table table, Item item){
        
        switch(table.getOrder().getItemList().size()){
            case 0:
                table.getOrder().addItem(item);
                return;
        }
        
        for(int i = 0; i < table.getOrder().getItemList().size(); i++)
            if(table.getOrder().getItemList().get(i).getProduct().getName().equals(item.getProduct().getName())){
                table.getOrder().getItemList().get(i).addQuantity(item.getQuantity());
                return;
            }
        table.getOrder().addItem(item);
    }
    
    public void isItemDuplicate(Table table, Item item){
        
        System.out.println(table.getOrder());
        System.out.println(table.getOrder().getItemList());
        System.out.println(table.getOrder().getItemList().size());
        
        switch(table.getOrder().getItemList().size()){
            case 0:
                table.getOrder().addItem(item);
                return;
        }
        
        for(int i = 0; i < table.getOrder().getItemList().size(); i++)
            if(table.getOrder().getItemList().get(i).getProduct().getName().equals(item.getProduct().getName())){
                table.getOrder().getItemList().get(i).addQuantity(item.getQuantity());
                return;
            }
        table.getOrder().addItem(item);
    }
    
    private void editTable(Table table){
        int productNumber = 0, productQuantity = 0;
        char getChar=' ';
        InputReader scan = new InputReader();
        
        if(!checkTableOrderState(table) || table == null)
            return;
        
        while(true){
            Item item = new Item();
            try{
                while(true){
                    try{
                        Menu.listProducts(productList);

                        productNumber = scan.getInt("Introduza o número correspondente ao produto que deseja adicionar (0 - Sair)");
                        --productNumber;

                        if(productNumber == -1)
                            return;
                        else if(productNumber > productList.size() || productNumber < 0)
                            throw new RestaurantException("ERRO: Indique o números correspondentes aos produtos apresentado na lista!");
                        
                        
                        item.setProduct(productList.get(productNumber));
                        break;
                    }catch(RestaurantException e){
                        System.err.println(e.getMessage());
                    }
                }
                
                
                while(true){
                    try{
                        productQuantity = scan.getInt("Introduza a quantidade");

                        if(productQuantity == 0)
                            return;
                        else if(productQuantity < 0)
                            throw new RestaurantException("ERRO: Quantidade não pode ser negativa!");
                        else if(productQuantity == 0)
                            throw new RestaurantException("ERRO: Quantidade não pode ser menor que zero!");
                        
                        item.setQuantity(productQuantity);
                        break;
                        
                    }catch(RestaurantException e){
                        System.err.println(e.getMessage());
                    }
                }
                
                checkItemDuplicatesInTableOrder(table, item);
                
                System.out.println("++" + item.getProduct().getName() + " adicionado com sucesso!");
            }catch(RestaurantException outOfBounds){
                System.err.println(outOfBounds.getMessage());
            }
            
            table.getOrder().setState(orderState.PREPARATION);
            
            while(true){
                try{
                    getChar = scan.getString("Deseja adicionar mais? [s/n]").trim().toLowerCase().charAt(0);
                    if(getChar != 's' && getChar != 'n')
                        throw new RestaurantException("ERRO: Introduza apenas (s)im ou (n)ão!");
                    break;
                }catch(RestaurantException e){
                    System.err.println(e.getMessage());
                }

            }
            
            if(getChar == 'n')
                break;
        }
        
        System.out.println("== Items adicionados ao pedido da mesa " + table.getTableNumber() + " ====");
        table.getOrder().listItems();
        System.out.println("== ===================================== ====");
    }
    
    private void bookTable(){
        try{
            if(!checkForUnoccupiedTables())
                throw new RestaurantException("ERRO: Não há mesas disponíveis neste momento, feche um pedido.");
        }catch(RestaurantException e){
            System.err.println(e.getMessage());
            return;
        }
        
        InputReader scan = new InputReader();
        int tableNumber;
        char tableOption;
        
        while(true)
            try{
                Menu.listTables(tableList);
                tableNumber = scan.getInt("Introduza o número da mesa que deseja reservar");
                --tableNumber;
                if(tableNumber == -1)
                    return;
                else if(tableList[tableNumber].isOccupied() == true)
                    throw new RestaurantException("ERRO: Mesa já está reservada!");
                else if(tableNumber<= tableList.length && tableNumber >= 0 && !tableList[tableNumber].isOccupied())
                    break;
            }catch(ArrayIndexOutOfBoundsException OutOfBounds){
                System.err.println("ERRO: Indique o número da mesa apresentado na lista!");
            }catch(RestaurantException e){
                System.err.println(e.getMessage());
            }
        
        for(int m = 0; m <= tableList.length; m++){
            if (tableList[tableNumber].equals(tableList[m])) {
                
                do{
                    tableOption = scan.getString("Reserva-se a mesa para (a)gora ou para mais (t)arde?").toLowerCase().charAt(0);
                }while(tableOption != 'a' && tableOption != 't');
                
                Order temporaryOrder = new Order();
                switch(tableOption){
                    case 'a':
                        temporaryOrder.openOrder(LocalDateTime.now());
                        tableList[tableNumber].setOccupied(); // Se a mesa é reservada para agora, é porque vai estar ocupada agora
                        tableList[tableNumber].setOrder(temporaryOrder);
                        editTable(tableList[tableNumber]);
                        break;
                    case 't':
                        LocalDateTime currentDate = LocalDateTime.now();
                        LocalDateTime reservationDate = LocalDateTime.of(2011, 1, 1, 23, 59);
                        String dateInput;
                        String[] formattedDate = new String[10];
                        temporaryOrder.openOrder(reservationDate);

                        boolean hasErrors = false;
                        do{
                            try{
                                hasErrors = false;
                                dateInput = scan.getString("Introduza a data (DD-MM-AAAA)").trim(); // 2-12-2021

                                if(dateInput.length() <= 10){
                                    if(dateInput.contains("-"))
                                        formattedDate = dateInput.split("-");

                                    else if(dateInput.contains("/"))
                                        formattedDate = dateInput.split("/");

                                    else if(dateInput.contains(" "))
                                        formattedDate = dateInput.split(" ");
                                }
                                
                                // Caso o utilizador se esqueça de introduzir o dia e/ou mês e/ou ano
                                if(formattedDate == null || formattedDate[0] == null || formattedDate[1] == null || formattedDate[2] == null){
                                    throw new RestaurantException("ERRO: Uma das partes da data está em falta!");
                                }

                                /*Verificar o ano introduzido*/
                                try{
                                    reservationDate = reservationDate.withYear(Integer.parseInt(formattedDate[2]));
                                }catch(DateTimeException ano){
                                    System.err.println("ERRO: Ano introduzido é inválido!");
                                    hasErrors = true;
                                }

                                /*Verificar o mês introduzido*/
                                try{
                                    reservationDate = reservationDate.withMonth(Integer.parseInt(formattedDate[1]));
                                }catch(DateTimeException mesErro){
                                    System.err.println("ERRO: Mês introduzido é inválido!");
                                    hasErrors = true;
                                }

                                /*Verificar o dia introduzido, verifica se foi introduzido o 29 de Fevereiro num ano bissexto*/
                                try{
                                    reservationDate = reservationDate.withDayOfMonth(Integer.parseInt(formattedDate[0]));
                                    if(!reservationDate.isAfter(currentDate))
                                        throw new RestaurantException("ERRO: Data introduzida deve ser posterior à data atual!");

                                }catch(DateTimeException diaErro){
                                    int verificarAnoBissexto = Integer.parseInt(formattedDate[2]);

                                    if (verificarAnoBissexto % 4 == 0 && verificarAnoBissexto % 100 == 0 && verificarAnoBissexto % 400 == 0)
                                        System.err.println("ERRO: Dia introduzido é inválido!");
                                    else
                                        System.err.println("ERRO: Dia introduzido é inválido, verifique se o ano é bissexto!");
                                    hasErrors = true;

                                }catch(RestaurantException dataAnterior){
                                    System.err.println(dataAnterior.getMessage());
                                    hasErrors = true;
                                }
                            }catch(RestaurantException dataNull){
                                System.err.println(dataNull.getMessage());
                                hasErrors = true;
                            }
                        }while(hasErrors);
                        
                        dateInput = "";
                        formattedDate = null;
                        do{
                            hasErrors = false;
                            dateInput = scan.getString("Introduza a hora (HH:MM)").trim();

                            if(dateInput.contains(":"))
                                formattedDate = dateInput.split(":");
                            
                            else if(dateInput.contains("-"))
                                formattedDate = dateInput.split("-");
                            
                            else if(dateInput.contains("/"))
                                formattedDate = dateInput.split("/");
                            
                            else if(dateInput.contains(" "))
                                formattedDate = dateInput.split(" ");

                            // ["10", "30"]
                            /*Verificar as horas introduzidas*/
                            try{
                                if(formattedDate[0].length() > 2)
                                    throw new RestaurantException("ERRO: Hora não pode ter mais do que 2 algarismos!");
                                else
                                    reservationDate = reservationDate.withHour(Integer.parseInt(formattedDate[0]));
                                
                            }catch(RestaurantException horaErro){
                                System.out.println(horaErro.getMessage());
                                hasErrors = true;
                                
                            }catch(DateTimeException hora){
                                System.err.println("ERRO: Hora introduzida é inválida!");
                                hasErrors = true;
                                
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Hora introduzida não está formatada conforme especificado ou foram introduzidos outros caracteres não releveantes! (HH:MM)");
                                hasErrors = true;
                                
                            }
                            
                            /*Verificar os minutos introduzidos*/
                            try{
                                reservationDate = reservationDate.withMinute(Integer.parseInt(formattedDate[1]));
                                
                                if(formattedDate[0].length() > 2)
                                    throw new RestaurantException("ERRO: Os minutos não podem ter mais do que 2 algarismos!");
                                else if(!reservationDate.isAfter(currentDate))
                                    throw new RestaurantException("ERRO: Data introduzida deve ser posterior à data atual!");
                                
                            }catch(DateTimeException minuto){
                                System.err.println("ERRO: Minuto introduzido é inválido!");
                                hasErrors = true;
                                
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Os minutos introduzidos não estam formatados conforme especificado ou foram introduzidos outros caracteres não releveantes! (HH:MM)");
                                hasErrors = true;
                                
                            }catch(RestaurantException dataErro){
                                System.err.println(dataErro.getMessage());
                                hasErrors = true;
                                
                            }

                    }while(hasErrors);

                    temporaryOrder.openOrder(reservationDate);
                    tableList[tableNumber].setOccupied(); 
                    tableList[tableNumber].setOrder(temporaryOrder);
                }
                
                System.out.println("++Mesa " + tableList[tableNumber].getTableNumber() + " reservada com sucesso!++");
                break;
            }
        }
    }

    private void viewPastOrders() {
        InputReader scan = new InputReader();
        int option=0;
        try{
            while(true){
                Menu.showHistory(orderHistory);

                try{
                    option = scan.getInt("Introduza o número do pedido que deseja consultar (0 - Sair)");
                    --option;
                    if(option == -1)
                        return;
                    else if(option <= orderHistory.getOrderList().size() && option >= 0)
                        System.out.println(orderHistory.getOrderList().get(option));
                    
                }catch(RestaurantException e){
                    System.err.println(e.getMessage());
                }catch(IndexOutOfBoundsException outOfBounds){
                    System.err.println("ERRO: Introduza um número da lista!");
                }
            }
        }catch(RestaurantException e){
            System.err.println(e.getMessage());
        }
    }
}