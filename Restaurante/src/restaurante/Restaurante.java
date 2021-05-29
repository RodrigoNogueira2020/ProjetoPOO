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
//        LocalDateTime currentTime = LocalDateTime.now();
//        LocalDateTime dataAntiga0 = LocalDateTime.of(2010, 11, 25, 11,12);
//        System.out.println(dataAntiga0.isBefore(currentTime));
        
        Gestao restaurante = new Gestao();
        restaurante.menu();
        /*
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
//        isBefore(antigadata2); <-- ver
        System.out.println("adasda " + diferenca);
            
        if(diferenca > 0) {
            System.out.println(dataAtual + " é mais recente que " + antigadata2);
        } else if (diferenca < 0) {
            System.out.println(dataAtual + " é mais antiga que " + antigadata2);
        } else {
            System.out.println(dataAtual + " é igual a " + antigadata2);
        }
        
        
        
        LocalDateTime dataAtual = LocalDateTime.now();
        Pedido p = new Pedido(dataAtual);
        Bebida p1 = new Bebida("Danke", 1, 1.5, false);
        Produto p2 = new Snack("Barra de cereal", 0, 1, true);
        Produto p3 = new Prato("Lasanha vegetariana", 10, "Melhor comida de sempre");
        Produto p4 = new Doce("Pintarolas", 3, "Hmmmm que bom", false);
        Item i1 = new Item(p1, 5);
        Item i2 = new Item(p2, 1);
        Item i3 = new Item(p3, 5);
        Item i4 = new Item(p4, 5);
        
        System.out.println("zxcvbnm " + (int)(i1.getProduto().getIVA()*100));
//        System.out.println(i1.getProduto().getCapacidade());
        p.adicionarItem(i1);
        p.adicionarItem(i2);
        p.adicionarItem(i3);
        p.adicionarItem(i4);
        p.listarItens();
        // p.imprimirRecibo();
        p.apagarItem(3);
        System.out.println("-----");
        p.listarItens();
//        p.fecharPedido();
        
        System.out.println("-----");
        Mesa mesa = new Mesa(1);
        mesa.setOcupada();
        mesa.setPedido(p);
        mesa.getPedido().listarItens();*/
        
    }
    
}
