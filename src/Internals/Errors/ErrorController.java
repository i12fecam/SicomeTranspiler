package Internals.Errors;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class ErrorController {


    //private final List<ErrorMessage> msgs = new ArrayList<>();
    private final List<ErrorMessage> msgs = new ArrayList<>();



    public void printToError(boolean terminalColors){
        msgs.forEach(msg -> System.err.println(msg.toString(terminalColors)));
    }
    public void printToConsole(boolean terminalColors){
        msgs.forEach(msg -> System.out.println(msg.toString(terminalColors)));
    }

    public boolean containsErrorEnum(ErrorEnum errorEnum){
        return msgs.stream().anyMatch(errorMsg -> errorMsg.errorEnum == errorEnum );
    }
    public void addNewError(ErrorEnum id,List<Object> args, Token token ){
        ErrorMessage msg = new ErrorMessage(
                id,
                args,
                token
                );
        addNewErrorMsg(msg);
        if(id.level == ErrorLevelEnum.FATAL_ERROR)
        throw new RuntimeException("Parado ejecución por error grave");
    }


    private void addNewErrorMsg(ErrorMessage msg){
        msgs.add(msg);
    }


    public boolean canCompile() {
       return  msgs.stream().filter(msgs -> {
            return msgs.errorEnum.level == ErrorLevelEnum.ANTLR4_FATAL_ERROR
            || msgs.errorEnum.level == ErrorLevelEnum.FATAL_ERROR;
        }).toList().isEmpty();
    }
}
