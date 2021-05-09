package restaurante;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ricardo Reis
 */
public class Pedido {
    private ArrayList<String> listaItens;
    private PedidoEstado estado;
    
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraFecho; // = LocalDateTime.now();
    
    // TODO: Mudar String pedido para Pedido pedido
    public Pedido (LocalDateTime dataHoraChegada){
        this.estado = PedidoEstado.ABERTO;
        this.dataHoraAbertura = dataHoraChegada;
        listaItens = new ArrayList<>();
        
        int diferenca = LocalDateTime.now().compareTo(dataHoraChegada);
        
        System.out.println("adasda " + diferenca);
            
//        if(0 < LocalDateTime.now().compareTo(dataHoraChegada)) {
//            System.out.println("ERRO: Data ou hora inserida é anterior à atual.");
//        } else if (LocalDateTime.now().compareTo(dataHoraChegada) < 0) {
//            System.out.println(dataAtual + " é mais antiga que " + antigadata2);
//        } else {
//            System.out.println(dataAtual + " é igual a " + antigadata2);
//        }
        
        /* Para o metodo de fechar o pedido e imprimir o recibo respetivamente
        LocalDateTime dataAtual = LocalDateTime.now();
        String dataAtualFormatada = dataAtual.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"));
        */
        
    }

    public void adicionarItem(String item) {
        /* Para o metodo de introduzir um item ao pedido, recebe um Item item */
        if(estado == PedidoEstado.FECHADO)
            System.out.println("ERRO: Não é possivel adicionar nenhum item ao pedido porque este já está fechado!");
        else if(item == null)
            System.out.println("ERRO: Pedido introduzido está nulo!");
        else
            listaItens.add(item);

    }
    
    public void imprimirRecibo(){
        System.out.println("== " + dataHoraAbertura.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " ==");
        System.out.println("Inicio: " + dataHoraFecho.getHour() + ":" + dataHoraFecho.getMinute());
        System.out.println("Fim   : " + dataHoraFecho.getHour() + ":" + dataHoraFecho.getMinute());
        
        /* Ciclar por todos os itens e mostrar os detalhes de cada
        for(Item u: listaItens)
            u.
        */
    }
    
    /**
     * Fecha o pedido, guarda a data e hora que foi fechado
     * e imprime o recibo
     */
    public void fecharPedido(){
        switch(estado){
            case ABERTO: case EM_PREPARACAO: case SERVIDO:
                estado = PedidoEstado.FECHADO;
                dataHoraFecho = LocalDateTime.now();
                break;
            case FECHADO:
                System.out.println("ERRO: O pedido já está fechado!");
        }
        
        imprimirRecibo();
    }
    
    public void definirEstado(PedidoEstado estado) {
        switch(estado){
            case ABERTO:  case EM_PREPARACAO:
            case SERVIDO: case FECHADO:
                this.estado = estado;
                break;
            default:
                System.out.println("ERRO: Estado do pedido inserido é inválido!");
        }
        
    }

    
    
    
}
