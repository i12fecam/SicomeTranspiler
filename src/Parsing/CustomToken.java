package Parsing;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;

public class CustomToken implements Token {

    private int line;
    private int charInLine;
    private String text;
    public CustomToken(int line, int charInLine, String offendingText){
        this.line = line;
        this.charInLine = charInLine;
        this.text = offendingText;
    }
    @Override
    public String getText() {
        return "";
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getCharPositionInLine() {
        return charInLine;
    }

    @Override
    public int getChannel() {
        return 0;
    }

    @Override
    public int getTokenIndex() {
        return 0;
    }

    @Override
    public int getStartIndex() {
        return 0;
    }

    @Override
    public int getStopIndex() {
        return 0;
    }

    @Override
    public TokenSource getTokenSource() {
        return null;
    }

    @Override
    public CharStream getInputStream() {
        return null;
    }
}
