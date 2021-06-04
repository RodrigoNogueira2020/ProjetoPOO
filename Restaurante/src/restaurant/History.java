package restaurant;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;


public class History implements Serializable {
    ArrayList<Order> orderList;
    
    public History(){
        orderList = new ArrayList<>();
    }
    
    public History(Order order){
        orderList = new ArrayList<>();
        if(order != null)
            orderList.add(order);
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
        Order temporaryOrder = new Order();
        do{
            toOrder = false;
            try{
                for(int i = 0; i < orderList.size(); i++){
                    if(orderList.get(i+1) == null)
                        break;
                    if(orderList.get(i+1) != null && orderList.get(i).getOpenHourFormatted().compareTo(orderList.get(i+1).getOpenHourFormatted()) < 0 ){
                        temporaryOrder = orderList.get(i+1);

                        orderList.set(i+1, orderList.get(i));
                        orderList.set(i, temporaryOrder);
                        toOrder = true;
                    }
                }
            }catch(IndexOutOfBoundsException e){
                
            }
        }while(!toOrder);
    }
    
    public void addOrder(Order order){
        if(order == null)
            return;
        
        orderList.add(order);
        sortOrder();
    }
    
    @Override
    public String toString(){
        String orders = "";
        for (Iterator<Order> it = orderList.iterator(); it.hasNext();) {
            Order element = it.next();
            orders += element + "\n";
        }
        return orders;
    }
    
}
