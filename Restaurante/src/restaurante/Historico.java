package restaurante;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
/**
 *
 * @author Ricardo Reis
 */
public class Historico {
    ArrayList<Pedido> listaPedidos;
    
    public Historico(){
        listaPedidos = new ArrayList<>();
    }
    
    public Historico(Pedido pedido){
        listaPedidos = new ArrayList<>();
        if(pedido != null)
            listaPedidos.add(pedido);
    }
    
    private void ordenarPedido(){
        
        switch(listaPedidos.size()){
            case 1:
                return;
        }
        
        // Caso haja uma ordenação no percorrer do ciclo, provavelmente vai
        //haver mais. O ciclo de ordenação só pára quando estiver false
        boolean porOrdenar;
        
        // Pega num pedido temporariamente para o mover para um indice diferente
        Pedido pedidoTemp = new Pedido();
        do{
            porOrdenar = false;
            try{
                for(int i = 0; i < listaPedidos.size(); i++){
                    if(listaPedidos.get(i+1) == null)
                        break;
                    if(listaPedidos.get(i+1) != null && listaPedidos.get(i).getDataHoraAbertura().compareTo(listaPedidos.get(i+1).getDataHoraAbertura()) < 0 ){
                        pedidoTemp = listaPedidos.get(i+1);

                        listaPedidos.set(i+1, listaPedidos.get(i));
                        listaPedidos.set(i, pedidoTemp);
                        porOrdenar = true;
                    }
                }
            }catch(IndexOutOfBoundsException e){
                
            }
        }while(!porOrdenar);
    }
    
    public void adicionarPedido(Pedido pedido){
        if(pedido == null)
            return;
        
        listaPedidos.add(pedido);
        ordenarPedido();
    }
    
    @Override
    public String toString(){
        String pedidos = "";
        for (Iterator<Pedido> it = listaPedidos.iterator(); it.hasNext();) {
            Pedido elemento = it.next();
            pedidos += elemento + "\n";
        }
        return pedidos;
    }
    
}
