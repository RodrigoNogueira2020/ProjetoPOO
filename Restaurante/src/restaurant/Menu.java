package restaurant;

import java.util.ArrayList;

public class Menu {
    
    public static void mainMenu() { // todo: Dar a opção ao utlizador para escolher qual o pedido quer ver, introduzir ficheiros
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
        for(Product p: productList){
            System.out.print(++i + ") ");
            System.out.println(p);
        }
    }
    
    public static void listTables(Table[] tableList){
        int i = 0;
        for(Table m: tableList){
            System.out.print(++i + ") ");
            System.out.println(m);
        }
    }
}
