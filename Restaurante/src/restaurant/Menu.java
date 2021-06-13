package restaurant;

import java.util.ArrayList;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Menu {
    
    public static void mainMenu() {
        System.out.println("=== Top Wings === Versão 0.6.0 07/06/2021 22:41");
        System.out.println("* 1 - Adicionar produto");
        System.out.println("* 2 - Remover produto");
        System.out.println("* 3 - Listar produtos");
        System.out.println("* 4 - Reservar uma nova mesa");
        System.out.println("* 5 - Editar uma mesa");
        System.out.println("* 6 - Mostrar histórico de pedidos");
        System.out.println("* 0 - Sair da aplicação");
        System.out.println("**************");
    }
    
    public static void mainMenuProducts() {
        System.out.println("**** PRODUTOS ****");
        System.out.println("* 1 - Bebida     *");
        System.out.println("* 2 - Doce       *");
        System.out.println("* 3 - Prato      *");
        System.out.println("* 4 - Snack      *");
        System.out.println("* 0 - Sair       *");
        System.out.println("******************");
    }
    
    public static void listProducts(ArrayList<Product> productList){
        int i = 0;
        try{
            if(productList.isEmpty())
                throw new InvalidInputArgumentException("ERRO: Ainda não foram introduzidos produtos!");
            
            for(Product p: productList){
                System.out.print(++i + ") ");
                System.out.println(p);
            }
            
        }catch(InvalidInputArgumentException e){
            System.err.println(e.getMessage());
        }
    }
    
    public static void listTables(Table[] tableList){
        int i = 0;
        for(Table m: tableList){
            System.out.print(++i + ") ");
            System.out.println(m);
        }
    }
    
    public static void showHistory(History history){
        int i = 0;
        
        switch(history.getOrderList().size()){
            case 0:
                throw new InvalidInputArgumentException("ERRO: Ainda não há pedidos, feche um para que seja adicionado aqui!");
            default:
                for(Order o: history.getOrderList())
                    System.out.println(++i + ") " + o.getOpenHourFormatted() + " - " + o.getCloseHourFormatted());
        }
    }
}
