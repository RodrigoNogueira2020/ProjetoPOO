package restaurante;

public class Mesa {

    private int numero;
    private Pedido pedido;
    private boolean ocupada;
    
    public Mesa(int numero) {
        if(numero > -1)
            this.numero = numero;
        else
            this.numero = -1;
        
        pedido = new Pedido();
        ocupada = false;
    }
    
    public Mesa() {
        this.numero = -1;
        pedido = new Pedido();
        ocupada = false;
    }

    public void setNumero(int numero) {
        if(numero > 0)
            this.numero = numero;
        else
            this.numero = -1;
    }

    public void setPedido(Pedido pedido) {
        if(!ocupada)
            System.out.println("ERRO: Mesa ainda não está ocupada!");
        else if(pedido != null)
            this.pedido = pedido;
    }

    public void setOcupada() {
        ocupada = !ocupada;
    }
    
    public int getNumero() {
        return numero;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public boolean isOcupada() {
        return ocupada;
    }
    
    private String estaOcupada(){
        if(ocupada)
            return "Ocupada -> " + pedido.getDataHoraAbertura() + "|";
        
        return "Livre";
    }
    
    @Override
    public String toString(){
        return "Mesa " + numero + " | " + estaOcupada();
    }
    
}
