package Analysis;

import org.antlr.v4.runtime.Token;

public class LogicException extends RuntimeException{

    Token token;
    public LogicException(String messsage, Token token){
            super(messsage);
            this.token=token;
    }

    public Token getToken() {
        return token;
    }
    public int getLine(){
        return token.getLine();
    }
    public int getCharInLine(){
        return token.getLine();
    }
}
