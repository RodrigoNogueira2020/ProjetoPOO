package restaurante;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author rodri
 */
public class Mesa {

    private int numero;
    //Pedido pedido;
    String horaChegada;
    boolean ocupada;
    //Scanner scanner = new Scanner(System.in);

        /*System.out.println("Número de mesas existentes: ");
        int numeroMesas = scanner.nextInt();*/
    
    public Mesa(int numero, String horaChegada, boolean ocupada) {
        //listaItem = new ArrayList<>();

        if (!ocupada) {

            ocupada = false;
            this.ocupada = ocupada;

            if (numero < 0 || numero > numeroMesas) {
                this.numero = numero;
                //pedido.aberto;
            }
            if (horaChegada != null || equals(horaChegada)) {
                LocalTime HoraAtual = LocalTime.now();
            }
        } else {
            System.out.println("ERRO! Mesa ocupada!");
        }
    }
}
