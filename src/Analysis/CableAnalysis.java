package Analysis;

import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;
import Internals.SymbolTable;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.List;

import static Internals.Errors.ErrorEnum.NUMERO_INSTRUCCIONES_SUPERADO;
import static java.lang.Integer.parseInt;

public class CableAnalysis extends SicomeBaseListener {
    private final SymbolTable symbolTable;
    private final ParseTreeProperty<Integer> ids;
    private final ErrorController err;
    public CableAnalysis(ParseTreeProperty<Integer> ids, SymbolTable symbolTable, ErrorController err) {
        this.ids = ids;
        this.symbolTable = symbolTable;
        this.err = err;
    }


    @Override
    public void enterInstructionBlockCable(SicomeParser.InstructionBlockCableContext ctx){
        if(ctx.instructionCable().size() > 32){
            err.addNewError(NUMERO_INSTRUCCIONES_SUPERADO,List.of(),ctx.start);
        }
    }
    /**
     * Annotates the tree with the ids of the steps and the id of the function
     * Also adds to the symbol table the function definition
     * @param ctx the parse tree
     */
    @Override
    public void enterInstructionCable(SicomeParser.InstructionCableContext ctx) {
        String functionName =ctx.IDENTIFIER().getText();
        String args = "";
        if(ctx.arg!= null)  args =ctx.arg.getText();

        List<SicomeParser.StepCableContext> steps =ctx.stepCable();
        int instr_id = -1;
        try {
            Integer nEstimatedSteps = null;
            if(ctx.nSteps !=null){
                nEstimatedSteps = parseInt(ctx.nSteps.getText(),10);
            }
            instr_id = symbolTable.addInstruction(functionName, args, steps.size(),nEstimatedSteps);
        }catch (RuntimeException e){
            err.addNewError(ErrorEnum.INSTRUCCION_MISMO_NOMBRE, List.of(ctx.IDENTIFIER().getText()), ctx.IDENTIFIER().getSymbol());
        }
        ids.put(ctx,instr_id);
        int step_id = 0 ;
        for(var step : steps){
            ids.put(step,step_id);
            step_id++;
        }
    }
    @Override
    public void enterFetchDefinitionCable(SicomeParser.FetchDefinitionCableContext ctx) {
        ids.put(ctx,null);
        int step_id = 0 ;
        for(var step : ctx.stepCable()){
            ids.put(step,step_id);
            step_id++;
        }
    }

}
