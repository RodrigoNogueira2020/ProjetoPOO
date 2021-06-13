package restaurant;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class History implements Serializable {
    private ArrayList<Order> orderList;
    
    public History(){
        orderList = new ArrayList<>();
    }
    
    private void sortOrder(){
        switch(orderList.size()){
            case 1:
                return;
        }
        
        // Caso haja uma ordenação no percorrer do ciclo, provavelmente vai
        //haver mais. O ciclo de ordenação só pára quando estiver false
        boolean toOrder;
        
        // Pega num pedido temporariamente para o mover para um indice diferente
        do{
            Order temporaryOrder = new Order();
            toOrder = false;
            try{
                for(int i = 0; i < orderList.size(); i++){
                    if(orderList.get(i+1) == null)
                        break;
                    else if(orderList.get(i).getOpenHour().compareTo( orderList.get(i+1).getOpenHour() ) < 0 ){
                        temporaryOrder = orderList.get(i+1);

                        orderList.set(i+1, orderList.get(i));
                        orderList.set(i, temporaryOrder);
                        toOrder = true;
                    }
                }
            }catch(IndexOutOfBoundsException e){
            }
            
        }while(toOrder);
    }
    
    public void addOrder(Order order){
        if(order == null)
            return;
        
        orderList.add(order);
        sortOrder();
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }
    
    @Override
    public String toString(){
        String orders = "";
        for(Order order: orderList){
            orders += order + "\n";
        }
        
        return orders;
    }
    
}
