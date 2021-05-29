package restaurante;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author Ricardo Reis
 */
public class Historico {
    ArrayList<Pedido> listaPedidos;
    
    public Historico(){
        listaPedidos = new ArrayList<>();
    }
    
    private void ordenarPedido(){
        boolean porOrdenar = false;
        Pedido pedidoTemp = new Pedido();
//        for(int i = 0; i <= listaPedidos.size(); i++)
//            if(listaPedidos.get(i+1).)
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
        for(Pedido elemento: listaPedidos)
            pedidos += elemento + "\n";
            
        return pedidos;
    }
    
}
