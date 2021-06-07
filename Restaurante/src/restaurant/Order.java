package restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order implements Serializable{
    private ArrayList<Item> itemList;
    private orderState state;
    
    private LocalDateTime openHour;
    private LocalDateTime closeHour; // = LocalDateTime.now();
    
    public Order(){
        itemList = new ArrayList<>();
        state = orderState.OPEN;
    }

    public void openOrder(LocalDateTime openHour) {
        this.state = orderState.OPEN;
        this.openHour = openHour;
    }
    
    /**
     * Fecha o pedido, guarda a data e hora que foi fechado
     * e imprime o recibo
     */
    public void closeOrder(){
        switch(state){
            case OPEN: case PREPARATION: case SERVED:
                state = orderState.CLOSED;
                closeHour = LocalDateTime.now();
                printBill();
                break;
            case CLOSED:
                System.out.println("ERRO: O pedido já está fechado!");
        }
        
    }
    
    public void addItem(Item item) {
        if(state == orderState.CLOSED)
                System.out.println("ERRO: Não é possivel adicionar nenhum item ao pedido porque este já está fechado!");
        else if(item == null)
            System.out.println("ERRO: Pedido introduzido está nulo!");
        else
            itemList.add(item);
    }
    
    /**
     *
     * @param i Indice do item que irá aparecer no menu para que o utilizador
     * escolha atraves de um número qual vai remover.
     * @return «true» se o indice introduzido estiver dentro do tamanho, «false»
     * caso o contrario.
     */
    public boolean deleteItem(int i){
        if(i > itemList.size()){
            System.out.println("ERRO: Número fora do indice da lista de itens.");
            return false;
        }

        for(Iterator<Item> it = itemList.iterator(); it.hasNext(); ){
            Item temp = it.next();
            System.out.println(temp);
            if (temp.equals(itemList.get(i))) {
//                System.out.println("--" + itemList.get(i).getProduto().getNome() + " removido com sucesso!");
                it.remove();
                return true;
            }
        }
        
        return false;
       
    }

    public void listItems(){
        for(Item u: itemList)
            System.out.println(u);
    }
    
    public void printBill(){
        System.out.println(this);
    }
    
    public void setState(orderState state) {
        switch(state){
            case OPEN:  case PREPARATION:
            case SERVED: case CLOSED:
                this.state = state;
                break;
            default:
                System.out.println("ERRO: Estado do pedido inserido é inválido!");
        }
        
    }

    public String showState(){
        switch(state){
            case OPEN:
                return "aberto";
            case PREPARATION:
                return "em preparação";
            case SERVED:
                return "servido";
             case CLOSED:
                return "fechado";
             default:
                 return "";
        }
    }
    
    public orderState getState() {
        return state;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public String getOpenHourFormatted() {
        return openHour.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"));
    }

    public LocalDateTime getOpenHour() {
        return openHour;
    }

    public LocalDateTime getCloseHour() {
        return closeHour;
    }
    
    @Override
    public String toString(){
        String returnBill = "";
        
        returnBill += "== " + openHour.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " ==\n";
        returnBill += "- " + openHour.getHour() + ":" + openHour.getMinute() + " - "  + 
                    closeHour.getHour() + ":" + closeHour.getMinute() + "\n";
        
        if(itemList.size() > 0){
            double precoFinal = 0;
            double precoFinalIVA = 0;
            double IVA = itemList.get(0).getProduct().getIVA();
            
            for(Item u: itemList){
                returnBill += u + "\n";
                precoFinal += u.getProduct().getPrice() * u.getQuantity();

            }

            precoFinalIVA += precoFinal + (precoFinal * IVA);

            returnBill += "--PREÇO FINAL: " + precoFinal + " \n" + 
                          "-- +IVA (" + IVA + "): " + precoFinalIVA + "\n";
        }
        
        return returnBill;
    }
    
}
