package Analysis;

import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import Internals.Errors.EspecificationException;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;
import Internals.SymbolTable;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.ArrayList;
import java.util.List;

import static Analysis.HelperFunctions.parseNumber;
import static Internals.Errors.ErrorEnum.VALOR_VARIABLE_NO_VALIDO;
import static java.lang.Integer.parseInt;

/**
 * Anotates the tree in functions and steps with their number
 * Register in the Symbol Table Marks ,Variables and Function definitions
 */
public class ProgramAnalysis extends SicomeBaseListener {

    public SymbolTable symbolTable = new SymbolTable();
    public ParseTreeProperty<Integer> ids = new ParseTreeProperty<>();
    private ErrorController err;
    //max number value in a memory cell that occupies in 16 bits
    private final int memoryCellMax = 131072;
    public ProgramAnalysis(SymbolTable symbolTable, ParseTreeProperty<Integer> ids, ErrorController err){
        this.symbolTable = symbolTable;
        this.ids = ids;
        this.err = err;
    }


    /**
     * Adds to the symbol table the variable definition
     * @param ctx the parse tree
     */
    @Override
    public void exitSimpleVariableDeclaration(SicomeParser.SimpleVariableDeclarationContext ctx) {
        String id =ctx.id.getText();

        Integer value = parseNumber(ctx.value.getText(),16);
        if(value == null){
            err.addNewError(VALOR_VARIABLE_NO_VALIDO,List.of(ctx.id.getText()), ctx.id);
        }
        try {
            symbolTable.addSimpleVariable(id, value);
        } catch (EspecificationException e){
            err.addNewError(ErrorEnum.VARIABLE_MISMO_NOMBRE,List.of(ctx.id.getText()),ctx.id);
        }
    }
    /**
     * Adds the variable definition to the symbol table
     * @param ctx the parse tree
     */
    @Override
    public void exitVectorVariableDeclaration(SicomeParser.VectorVariableDeclarationContext ctx) {

        String id = ctx.id.getText();
        int size = parseInt(ctx.size.getText(),10);
        List<Integer> values= new ArrayList<>();
        ctx.value.forEach(token -> {
            var valueNumber = parseNumber(token.getText(),16);
            if(valueNumber == null){
                err.addNewError(VALOR_VARIABLE_NO_VALIDO,List.of(ctx.id.getText()), ctx.id);
            }
            values.add(valueNumber);

        });

        try {
            if (size <= 1) {
                err.addNewError(ErrorEnum.TAMANYO_VECTOR_INVALIDO,List.of(ctx.size.getText() + "< 2"),ctx.size);
            } else if (values.size() == 1) {
                symbolTable.addVectorVariable(id, size, values.get(0));
            } else if (size == values.size()) {
                symbolTable.addVectorVariable(id, size, values);
            } else {
                err.addNewError(ErrorEnum.INICIALIZACION_VECTOR_INVALIDA,List.of(),ctx.size);
            }
        }catch (EspecificationException e){
            err.addNewError(ErrorEnum.VARIABLE_MISMO_NOMBRE,List.of(id), ctx.id);
        }
    }

    int ProgramLine = 0;

    /**
     * Increments the program line to count the current line being processed
     * @param ctx the parse tree
     */
    @Override
    public void exitInstructionUse(SicomeParser.InstructionUseContext ctx) {
        ProgramLine++;
    }

    /**
     * Adds to the symbol table the label defintion
     * @param ctx the parse tree
     */
    @Override
    public void exitMarkUse(SicomeParser.MarkUseContext ctx) {
        try {
            symbolTable.addLabel(ctx.label.getText(), ProgramLine);
        } catch (EspecificationException e ){
            err.addNewError(ErrorEnum.ETIQUETA_MISMO_NOMBRE,List.of(ctx.label.getText()), ctx.label);
        }
    }


}
