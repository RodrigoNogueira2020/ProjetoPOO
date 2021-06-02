package restaurante;

public class InvalidInputArgumentException extends IllegalArgumentException{
    String erro;
    
    public InvalidInputArgumentException(String erro){
        if(erro!=null)
            this.erro = erro;
    }
    
    @Override
    public String toString(){
        return erro;
    }
}
