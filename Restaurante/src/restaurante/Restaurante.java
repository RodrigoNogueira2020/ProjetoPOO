package restaurante;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        p.fecharPedido();
    }
    
}
