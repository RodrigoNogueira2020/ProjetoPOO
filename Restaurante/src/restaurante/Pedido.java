package restaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Ricardo Reis
 */
public class Pedido {
    private ArrayList<Item> listaItens;
    private PedidoEstado estado;
    
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraFecho; // = LocalDateTime.now();
    
    // TODO: Mudar String pedido para Pedido pedido
    public Pedido (LocalDateTime dataHoraChegada){
        this.estado = PedidoEstado.ABERTO;
        this.dataHoraAbertura = dataHoraChegada;
        listaItens = new ArrayList<>();
        
    }

    public void adicionarItem(Item item) {
        /* Para o metodo de introduzir um item ao pedido, recebe um Item item */
        if(estado == PedidoEstado.FECHADO)
            System.out.println("ERRO: Não é possivel adicionar nenhum item ao pedido porque este já está fechado!");
        else if(item == null)
            System.out.println("ERRO: Pedido introduzido está nulo!");
        else
            listaItens.add(item);

    }
    
    /**
     *
     * @param i Indice do item que irá aparecer no menu para que o utilizador
     * escolha atraves de um número qual vai remover.
     * @return «true» se o indice introduzido estiver dentro do tamanho, «false»
     * caso o contrario.
     */
    public boolean apagarItem(int i){
        if(i > listaItens.size()){
            System.out.println("ERRO: Número fora do indice da lista de itens.");
            return false;
        }

        for(Iterator<Item> it = listaItens.iterator(); it.hasNext(); ){
            Item temp = it.next();
            if (temp.equals(listaItens.get(i))) {
                it.remove();
                System.out.println("--" + temp.getProduto().getNome() + " removido com sucesso!");
                return true;
            }
        }
        
        return false;
//            
//            if(j == i){
//                it.indexOf(i).remove();
//                System.out.println("removed");
//                return;
//            }
//            j++;
//            System.out.println(j);
       
    }

    
    public void imprimirRecibo(){
        System.out.println("== " + dataHoraAbertura.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " ==");
        System.out.println("Inicio: " + dataHoraAbertura.getHour() + ":" + dataHoraAbertura.getMinute());
        
        if(dataHoraFecho != null)
            System.out.println("Fim   : " + dataHoraFecho.getHour() + ":" + dataHoraFecho.getMinute());
        
        System.out.println("++");
        
        /* Ciclar por todos os itens e mostrar os detalhes de cada*/
        for(Item u: listaItens)
            System.out.println(u);
            
        
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
