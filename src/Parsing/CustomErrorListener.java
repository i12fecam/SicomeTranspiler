package Parsing;

import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.Interval;

import java.util.Collections;
import java.util.List;

public class CustomErrorListener extends BaseErrorListener {
    private final ErrorController err;
    public CustomErrorListener(ErrorController err) {
        this.err=err;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg,
                            RecognitionException e)
    {
//        List<String> stack = ((Parser)recognizer).getRuleInvocationStack();
//        Collections.reverse(stack);
//        System.err.println("rule stack: "+stack);
//        System.err.println("line "+line+":"+charPositionInLine+" at "+
//                offendingSymbol+": "+msg);
        var token = offendingSymbol;
        var msgFinal = msg;
        if (e instanceof LexerNoViableAltException) {
            LexerNoViableAltException lexerEx = (LexerNoViableAltException) e;
            CharStream input = lexerEx.getInputStream();
            // Get the index where the error started
            int startIndex = lexerEx.getStartIndex();
            // Extract the offending text from the stream
            String offendingText = input.getText(Interval.of(startIndex, startIndex));
            token = new CustomToken(line, charPositionInLine, offendingText);
            msgFinal = "Carácter no reconocido '" + offendingText + "'";
        }

            err.addNewError(ErrorEnum.ANTLR4,List.of(msgFinal), (Token) token);
    }
}
