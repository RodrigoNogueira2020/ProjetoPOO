package projetopoo.pkg2fase;

import java.io.Serializable;

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
    
    public void bookTable(Table tableToReserve, LocalDateTime dateOfReservation) throws RestaurantException{
        if(dateOfReservation.isBefore(LocalDateTime.now()))
            throw new RestaurantException("ERRO: Data introduzida deve ser posterior à data atual!");
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