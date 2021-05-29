package restaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Ricardo Reis
 */
public class Pedido {
    private ArrayList<Item> listaItens;
    private PedidoEstado estado;
    
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraFecho; // = LocalDateTime.now();
    
    public Pedido(){
        listaItens = new ArrayList<>();
        estado = PedidoEstado.ABERTO;
    }

    public void abrirPedido(LocalDateTime dataHoraAbertura) {
        this.estado = PedidoEstado.ABERTO;
        this.dataHoraAbertura = dataHoraAbertura;
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
                imprimirRecibo();
                break;
            case FECHADO:
                System.out.println("ERRO: O pedido já está fechado!");
        }
        
    }
    
    public void adicionarItem(Item item) {
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
            System.out.println(temp);
            if (temp.equals(listaItens.get(i))) {
//                System.out.println("--" + listaItens.get(i).getProduto().getNome() + " removido com sucesso!");
                it.remove();
                return true;
            }
        }
        
        return false;
       
    }

    public void listarItens(){
        for(Item u: listaItens)
            System.out.println(u);
    }
    
    public void imprimirRecibo(){
        System.out.println("== " + dataHoraAbertura.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " ==");
        System.out.println("Inicio: " + dataHoraAbertura.getHour() + ":" + dataHoraAbertura.getMinute());
        
        if(dataHoraFecho != null)
            System.out.println("Fim   : " + dataHoraFecho.getHour() + ":" + dataHoraFecho.getMinute());
        
        System.out.println("++");
        
        listarItens();
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

    public String mostrarEstado(){
        switch(estado){
            case ABERTO:
                return "aberto";
            case EM_PREPARACAO:
                return "em preparação";
            case SERVIDO:
                return "servido";
             case FECHADO:
                return "fechado";
             default:
                 return "";
        }
    }
    
    public PedidoEstado getEstado() {
        return estado;
    }

    public ArrayList<Item> getListaItens() {
        return listaItens;
    }

    public String getDataHoraAbertura() {
        return dataHoraAbertura.format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"));
    }

    public LocalDateTime getDataHoraFecho() {
        return dataHoraFecho;
    }
    
    @Override
    public String toString(){
        String devolverItems = "";
        for(Item u: listaItens)
            devolverItems += u;
        
        return devolverItems;
    }
    
}
