package Internals.Errors;

import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.ResourceBundle;


public class ErrorMessage {
    ErrorEnum errorEnum;
    Token token;
    List<Object> arguments;



    public ErrorMessage(ErrorEnum errorEnum, List<Object> args, Token token){
        this.errorEnum = errorEnum;
        this.token = token;
        this.arguments = args;
    }

    public String toString(boolean terminalColors){
        String res = "";


        //ANSI_RED[AVISO] linea 4: DATO_ERRONEO_BIF, El valor del bif no puede ser repetido
        String levelText = switch (errorEnum.level) {
            case WARNING -> "Aviso";
            case FATAL_ERROR,ANTLR4_FATAL_ERROR-> "Error grave";
            case BIG_WARNING -> "Aviso grave";
        };

        String errorExplanation = String.format(
                errorEnum.msgFormat,
                arguments.toArray()
        );



        res = String.format("[%s] linea %s, caracter %s:%s, %s",
                levelText,
                token.getLine(),
                token.getCharPositionInLine(),
                errorEnum.name(),
                errorExplanation
                );

        if(terminalColors){
            String ANSI_RED = "\u001B[31m";
            String ANSI_YELLOW = "\u001B[0;33m";
            String ANSI_RESET = "\u001B[0m";
            res = switch (errorEnum.level){
                case WARNING -> ANSI_YELLOW + res + ANSI_RESET;
                case FATAL_ERROR,BIG_WARNING,ANTLR4_FATAL_ERROR -> ANSI_RED + res + ANSI_RESET;
            };
        }
        return res;
    }

}


