package restaurant;

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
        setNumberOfTables();
<<<<<<< HEAD
=======
        
>>>>>>> 8a38365a3ad50305b887bdb45d5271ce96bc121e
    }
    
    private void setNumberOfTables(){
        InputReader scan = new InputReader();
        
        int numberOfTables = 0;
        while (numberOfTables < 4)
            try{
                numberOfTables = scan.getInt("Quantas mesas tem o restaurante?");
                if(numberOfTables < 0)
                    throw new InvalidInputArgumentException("ERRO: Número de mesas precisa de ser um valor positivo!");
                else if(numberOfTables < 4)
                    throw new InvalidInputArgumentException("ERRO: Número de mesas precisa de ser, no minimo, 4!");
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }
        
        tableList = new Table[numberOfTables];
        for(int i=0; i < tableList.length; i++)
            tableList[i] = new Table(i);
        
    }

    public void menu() {
        int option=0;
        InputReader scan = new InputReader();
        
        do {
            try{
                Menu.mainMenu();
                option = scan.getInt("Escolha uma opção: ");

                switch (option) {
                    case 0:
                        break;
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        Menu.listProducts(productList);
                        break;
                    case 4:
                        bookTable();
                        break;
                    case 5:
                        editTable(selectTable());
                        break;
                    case 6:
                        viewPastOrders();
                        break;
                    default:
                        throw new InvalidInputArgumentException("ERRO: Opção inválida!");
                }
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());

            }
            
        } while (option != 0);
    }

    /**
     *
     * @param newProduct Product a ser introduzido na lista, verifica se o NOME
     * de um produto já existe.
     * @return true - se já houver um produto com o mesmo NOME
     */
    private void checkProductDuplicates(String productName) {
        for (Product elemento : productList)
            if (elemento.getName().toLowerCase().equals(productName.toLowerCase()))
                throw new InvalidInputArgumentException("ERRO: Já existe um produto com esse nome!");

    }
    
    public void addProduct() {
        InputReader scan = new InputReader();
        int option;
        String productName;
        double productPrice, productIva;
        
        while(true){
            try{
                Menu.mainMenuProducts();
                option = scan.getInt("Que tipo de produto quer adicionar? ");
                
                if(option < 0 || option > 4)     
                    throw new InvalidInputArgumentException("ERRO! Opção inválida!");
                else if(option == 0)
                    return;
                
                //Verifica se o nome não está em branco ou se já existe um produto com o nome introduzido
                do{
                    try{
                        productName = scan.getString("Nome");
                        if("0".equals(productName))
                            return;
                        else if(productName == null || productName.trim().equals(""))
                            throw new InvalidInputArgumentException("ERRO: Nome não pode ficar em branco!");
                        else{
                            checkProductDuplicates(productName);
                            break;
                        }

                    }catch(InvalidInputArgumentException e){
                        System.err.println(e.getMessage());
                    }
                }while(true);
                
                //Verifica se o preço não é zero ou número negativo
                do{
                    try{
                        productPrice = scan.getDouble("Preço (€)");
                        if(productPrice == 0)
                            throw new InvalidInputArgumentException("ERRO: Preço não pode ser zero!");
                        else if(productPrice < 0)
                            throw new InvalidInputArgumentException("ERRO: Preço não pode ser negativo!");
                        break;
                    }catch(InvalidInputArgumentException e){
                        System.err.println(e.getMessage());
                    }
                }while(true);
            
                do{
                    try{
                        productIva = scan.getDouble("IVA (0-100%)");
                        if(productIva > 100)
                            throw new InvalidInputArgumentException("ERRO: IVA não pode exceder os 100%!");
                        else if(productIva < 0)
                            throw new InvalidInputArgumentException("ERRO: IVA não pode ser negativo!");
                        break;
                    }catch(InvalidInputArgumentException e){
                        System.err.println(e.getMessage());
                    }
                }while(true);
                
                break;
                
            }catch(InvalidInputArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        switch(option) {
            case 1:
                addDrink(productName, productPrice, productIva);
                break;
            case 2:
                addSweet(productName, productPrice, productIva);
                break;
            case 3:
                addDish(productName, productPrice, productIva);
                break;
            case 4:
                addSnack(productName, productPrice, productIva);
        }
    }
   
    private void addDrink(String drinkName, double drinkPrice, double productIva) {
        Drink drink = new Drink();
        InputReader scan = new InputReader();
        
        drink.setName(drinkName);
        drink.setPrice(drinkPrice);
        drink.setIva(productIva);

        while (true)
            try{
                drink.setCapacity(scan.getDouble("Capacidade da bebida (L)"));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }

        while (true)
            try{
                drink.setHasAlcohol(scan.getString("Bebida alcoólica? (s/n)").toLowerCase().charAt(0));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }

        productList.add(drink);
        System.out.println("++ Bebida adicionada com sucesso! ++");
        
    }

    private void addSweet(String sweetName, double sweetPrice, double productIva) {
        Sweet sweet = new Sweet();
        InputReader scan = new InputReader();
        
        sweet.setName(sweetName);
        sweet.setPrice(sweetPrice);
        sweet.setIva(productIva);
        
        while (true)
            try{
                sweet.setDescription(scan.getString("Descrição do doce"));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }
        
        while (true)
            try{
                sweet.setMadeInRestaurant(scan.getString("É feito no restaurante? (s/n)").toLowerCase().charAt(0));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }

        productList.add(sweet);
        System.out.println("++ Doce adicionado com sucesso! ++");
    }

    private void addDish(String dishName, double dishPrice, double productIva) {
        Dish dish = new Dish();
        InputReader scan = new InputReader();

        dish.setName(dishName);
        dish.setPrice(dishPrice);
        dish.setIva(productIva);

        while (true)
            try{
                dish.setDescription(scan.getString("Descrição do prato"));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }

        productList.add(dish);
        System.out.println("++ Prato adicionado com sucesso! ++");
    }

    private void addSnack(String snackName, double snackPrice, double productIva) {
        Snack snack = new Snack();
        InputReader scan = new InputReader();

        snack.setName(snackName);
        snack.setPrice(snackPrice);
        snack.setIva(productIva);
        
        while (true)
            try{
                snack.setQuantity(scan.getInt("Quantidade do snack"));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }
        while (true)
            try{
                snack.setIsSpicy(scan.getString("É picante? (s/n)").toLowerCase().charAt(0));
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
            }

        productList.add(snack);
        System.out.println("++ Snack adicionado com sucesso! ++");
    }

    private void removeProduct(){
        int i;
        Menu.listProducts(productList);
        InputReader scan = new InputReader();
        
        while(true)
            try{
                i = scan.getInt("Introduza o número do produto que deseja remover");
                --i;
                if(i == -1)
                    return;
                else if(i <= productList.size() && i >=0)
                    break;
                throw new InvalidInputArgumentException("ERRO: Indique o número do produto apresentado na lista!");
                
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
                
            }
        
        for(Iterator<Product> it = productList.iterator(); it.hasNext(); )
            if ( it.next().getName().equals( productList.get(i).getName() ) ) {
                
                System.out.println("--" + productList.get(i).getName() + " removido com sucesso!");
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
                    throw new InvalidInputArgumentException("ERRO: Mesa ainda não está reservada!");
                
            }catch(ArrayIndexOutOfBoundsException OutOfBounds){
                System.err.println("ERRO: Indique o número da mesa apresentado na lista!");
            }catch(InvalidInputArgumentException e){
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
                                throw new InvalidInputArgumentException("ERRO: Introduza apenas (s)im ou (n)ão!");
                            
                            else if(getChar == 's'){
                                
                                table.getOrder().closeOrder();
                                table.setOccupied();
                                table.removeOrder();
                                
                            }
                            return false;
                        }catch(InvalidInputArgumentException e){
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
                                    throw new InvalidInputArgumentException("ERRO: Estado do pedido já está como servido!");
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
                                throw new InvalidInputArgumentException("ERRO: Introduza (a), (s) ou (f) apenas.");
                        }
                    }catch(InvalidInputArgumentException e){
                        System.err.println(e.getMessage());
                    }
                }

        }
        
        return false;
    }
    
    private void editTable(Table table){
        int productNumber;
        char getChar=' ';
        InputReader scan = new InputReader();
        
        if(!checkTableOrderState(table) || table == null)
            return;
        
        while(true){
            Item item = new Item();
            try{
                Menu.listProducts(productList);
                
                productNumber = scan.getInt("Introduza o número dos produtos que deseja adicionar (0 - Sair)");
                --productNumber;

                if(productNumber == -1)
                    return;
                else if(productNumber < productList.size() && productNumber >= 0)
                    item.setProduct(productList.get(productNumber));
                else
                    throw new InvalidInputArgumentException("ERRO: Indique o número do produto apresentado na lista!");
                
                while(true){
                    try{
                        productNumber = scan.getInt("Introduza a quantidade");

                        if(productNumber == 0)
                            return;
                        else if(productNumber < 0)
                            throw new InvalidInputArgumentException("ERRO: Quantidade não pode ser negativa!");
                        
                        item.setQuantity(productNumber);
                        break;
                        
                    }catch(InvalidInputArgumentException e){
                        System.err.println(e.getMessage());
                    }
                }
                
                table.getOrder().addItem(item);
                System.out.println("++" + item.getProduct().getName() + " adicionado com sucesso!");
                break;
            }catch(InvalidInputArgumentException outOfBounds){
                System.err.println(outOfBounds.getMessage());
            }
        }
        table.getOrder().setState(orderState.PREPARATION);
        while(true){
            try{
                getChar = scan.getString("Deseja adicionar mais? [s/n]").trim().toLowerCase().charAt(0);
                if(getChar != 's' && getChar != 'n')
                    throw new InvalidInputArgumentException("ERRO: Introduza apenas (s)im ou (n)ão!");
                break;
            }catch(InvalidInputArgumentException e){
                System.err.println(e.getMessage());
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
                throw new InvalidInputArgumentException("ERRO: Não há mesas disponíveis neste momento, feche um pedido.");
        }catch(InvalidInputArgumentException e){
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
                    throw new InvalidInputArgumentException("ERRO: Mesa já está reservada!");
                else if(tableNumber<= tableList.length && tableNumber >= 0 && !tableList[tableNumber].isOccupied())
                    break;
            }catch(ArrayIndexOutOfBoundsException OutOfBounds){
                System.err.println("ERRO: Indique o número da mesa apresentado na lista!");
            }catch(InvalidInputArgumentException e){
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
                                    throw new InvalidInputArgumentException("ERRO: Uma das partes da data está em falta!");
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
                                        throw new InvalidInputArgumentException("ERRO: Data introduzida deve ser posterior à data atual!");

                                }catch(DateTimeException diaErro){
                                    int verificarAnoBissexto = Integer.parseInt(formattedDate[2]);

                                    if (verificarAnoBissexto % 4 == 0 && verificarAnoBissexto % 100 == 0 && verificarAnoBissexto % 400 == 0)
                                        System.err.println("ERRO: Dia introduzido é inválido!");
                                    else
                                        System.err.println("ERRO: Dia introduzido é inválido, verifique se o ano é bissexto!");
                                    hasErrors = true;

                                }catch(InvalidInputArgumentException dataAnterior){
                                    System.err.println(dataAnterior.getMessage());
                                    hasErrors = true;
                                }
                            }catch(InvalidInputArgumentException dataNull){
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
                                    throw new InvalidInputArgumentException("ERRO: Hora não pode ter mais do que 2 algarismos!");
                                else
                                    reservationDate = reservationDate.withHour(Integer.parseInt(formattedDate[0]));
                                
                            }catch(InvalidInputArgumentException horaErro){
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
                                    throw new InvalidInputArgumentException("ERRO: Os minutos não podem ter mais do que 2 algarismos!");
                                else if(!reservationDate.isAfter(currentDate))
                                    throw new InvalidInputArgumentException("ERRO: Data introduzida deve ser posterior à data atual!");
                                
                            }catch(DateTimeException minuto){
                                System.err.println("ERRO: Minuto introduzido é inválido!");
                                hasErrors = true;
                                
                            }catch (NumberFormatException dataParseExcecao){
                                System.err.println("ERRO: Os minutos introduzidos não estam formatados conforme especificado ou foram introduzidos outros caracteres não releveantes! (HH:MM)");
                                hasErrors = true;
                                
                            }catch(InvalidInputArgumentException dataErro){
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
                    else if(option<= orderHistory.getOrderList().size() && option >= 0)
                        System.out.println(orderHistory.getOrderList().get(option));
                }catch(InvalidInputArgumentException e){
                    System.err.println(e.getMessage());
                }catch(IndexOutOfBoundsException outOfBounds){
                    System.err.println("ERRO: Introduza um número da lista!");
                }
            }
        }catch(InvalidInputArgumentException e){
            System.err.println(e.getMessage());
        }
    }
}