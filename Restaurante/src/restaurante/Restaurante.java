package restaurante;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Ricardo Reis 200262024
 *         Rodrigo Nogueira 200262002
 */
public class Restaurante {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // https://www.callicoder.com/how-to-compare-date-time-java/
        // Guardar as horas
        LocalTime currentTime = LocalTime.now();
        String novaData = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        System.out.println( novaData );
        
        // Guardar as datas
        LocalDateTime dataAtual = LocalDateTime.now();
        String dataAtualFormatada = dataAtual.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"));
        System.out.println( dataAtualFormatada );
        
        LocalDateTime antigadata2 = LocalDateTime.of(2010, 11, 25, 11,12);
//        String antigadata2Formatada = antigadata2.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"));
        
        // Comparar as datas
        int diferenca = dataAtual.compareTo(antigadata2);
        
        System.out.println("adasda " + diferenca);
            
        if(diferenca > 0) {
            System.out.println(dataAtual + " é mais recente que " + antigadata2);
        } else if (diferenca < 0) {
            System.out.println(dataAtual + " é mais antiga que " + antigadata2);
        } else {
            System.out.println(dataAtual + " é igual a " + antigadata2);
        }
        
        Pedido p = new Pedido(dataAtual);
        Produto p1 = new Bebida("Chug Jug", 1, 1.5, false);
        Produto p2 = new Snack("Rodrigo", 0, 1, true);
        Produto p3 = new Prato("Lasanha vegetariana", 10, "Melhor comida de sempre");
        Produto p4 = new Doce("Pintarolas", 3, "Hmmmm que bom", false);
        Item i1 = new Item(p1, 5);
        Item i2 = new Item(p2, 1);
        Item i3 = new Item(p3, 5);
        Item i4 = new Item(p4, 5);
        p.adicionarItem(i1);
        p.adicionarItem(i2);
        p.adicionarItem(i3);
        p.adicionarItem(i4);
        // p.imprimirRecibo();
        p.apagarItem(3);
//        p.fecharPedido();
    }
    
}
