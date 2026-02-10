package Analysis;

import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;
import Internals.SymbolTable;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.List;

import static java.lang.Integer.parseInt;

public class MicrocodeAnalysis extends SicomeBaseListener {
    public ParseTreeProperty<Integer> ids = new ParseTreeProperty<>();
    public SymbolTable symbolTable = new SymbolTable();
    private ErrorController err;

    public MicrocodeAnalysis(ParseTreeProperty<Integer> ids, SymbolTable symbolTable, ErrorController err) {
        this.ids = ids;
        this.symbolTable = symbolTable;
        this.err = err;
    }



    @Override
    public void enterInstructionMicro(SicomeParser.InstructionMicroContext ctx) {
        Token identifier = ctx.IDENTIFIER().getSymbol();
        Token arg = ctx.arg;
        String argString = "";
        if(arg!= null)  argString =ctx.arg.getText();
        int size = ctx.stepMicro().size();


        int instrId = -1;
        try {
            Integer nEstimatedSteps = null;
            if(ctx.nSteps != null){
                nEstimatedSteps = parseInt(ctx.nSteps.getText());
            }

            instrId = symbolTable.addInstruction(identifier.getText(), argString, size, nEstimatedSteps);
        }catch (RuntimeException e){
            err.addNewError(ErrorEnum.INSTRUCCION_MISMO_NOMBRE, List.of(ctx.IDENTIFIER().getText()), ctx.IDENTIFIER().getSymbol());

        }

        //Se anota en el arbol las IDs
        ids.put(ctx,instrId);
        int stepId = 0 ;
        for(var step:ctx.stepMicro()){
            ids.put(step,stepId);
            stepId++;
        }
    }

    @Override
    public void enterFetchDefinitionMicro(SicomeParser.FetchDefinitionMicroContext ctx){
        //we annotates the fetch with the special value -1
        var instrId = symbolTable.addInstruction("FETCH","",ctx.stepMicro().size(),null );

        ids.put(ctx,instrId);

        int stepId = 0 ;
        for(var step:ctx.stepMicro()){
            ids.put(step,stepId);
            stepId++;
        }
    }

}
