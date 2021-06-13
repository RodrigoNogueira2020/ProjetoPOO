package restaurant;

import java.io.Serializable;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public class Table implements Serializable{

    private int tableNumber;
    private Order order;
    private boolean occupied;
    
    public Table(int number) {
        if(number > -1)
            this.tableNumber = number;
        else
            this.tableNumber = -1;
        
        order = new Order();
        occupied = false;
    }
    
    public Table() {
        this.tableNumber = -1;
        order = new Order();
        occupied = false;
    }

    public void setTableNumber(int tableNumber) {
        if(tableNumber > 0)
            this.tableNumber = tableNumber;
        else
            this.tableNumber = -1;
    }

    public void setOrder(Order order) {
        if(!occupied)
            throw new InvalidInputArgumentException("ERRO: Mesa ainda não está ocupada!");
        else if(order != null)
            this.order = order;
    }
    
    public void removeOrder() {
        this.order = null;
    }

    public void setOccupied() {
        occupied = !occupied;
    }
    
    public int getTableNumber() {
        return tableNumber+1;
    }

    public Order getOrder() {
        return order;
    }

    public boolean isOccupied() {
        return occupied;
    }
    
    private String occupied(){
        if(occupied)
            return "Ocupada -> " + order.getOpenHourFormatted();
        
        return "Livre";
    }
    
    @Override
    public String toString(){
        return "Mesa " + (tableNumber+1) + " | " + occupied();
    }
    
}
