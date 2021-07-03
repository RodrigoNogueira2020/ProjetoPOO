package projetopoo.pkg2fase;

/**
 *
 * @author Ricardo Reis     200262024 200262024@estudantes.ips.pt
 *         Rodrigo Nogueira 200262002 200262002@estudantes.ips.pt
 */

public enum orderState {
    OPEN, PREPARATION, SERVED, CLOSED;
    
    @Override
    public String toString(){
        switch(this){
            case OPEN:
                return "aberto";
            case PREPARATION:
                return "em preparação";
            case SERVED:
                return "servido";
             case CLOSED:
                return "fechado";
        }
        return "";
    }
}
