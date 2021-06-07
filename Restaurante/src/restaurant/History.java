package restaurant;

import java.io.Serializable;
import java.util.ArrayList;


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
    
    @Override
    public String toString(){
        String orders = "";
        for(Order order: orderList){
            orders += order + "\n";
        }
        
        return orders;
    }
    
}
