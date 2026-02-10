package Analysis;

import Internals.Errors.ErrorController;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;
import Internals.SymbolTable;

import java.util.List;

import static Internals.Errors.ErrorEnum.LOGICA_BIFURCACION_MISMO_NOMBRE;
import static Internals.Errors.ErrorEnum.NUMERO_LOGICA_BIFURCACION_SUPERADO;

public class MicrocodeLogicAnalysis extends SicomeBaseListener {
    public SymbolTable symbolTable = new SymbolTable();
    public ErrorController err;
    public MicrocodeLogicAnalysis(SymbolTable symbolTable, ErrorController err) {
        this.symbolTable = symbolTable;
        this.err = err;
    }

    @Override
    public void enterSimpleStatusLogic(SicomeParser.SimpleStatusLogicContext ctx) {
        String name = ctx.name.getText();
        boolean needsArg = false;
        if(ctx.option != null && ctx.option.getText().equals("BIF")){//TODO reformular para no poner literales
            needsArg = true;
        }


        var resadd = symbolTable.addBifurcationLogic(name,needsArg);

        if(resadd == -1){
           err.addNewError(NUMERO_LOGICA_BIFURCACION_SUPERADO,List.of(), ctx.name);
        }else if (resadd == -2){
           err.addNewError(LOGICA_BIFURCACION_MISMO_NOMBRE, List.of(ctx.name.getText()), ctx.name);

        }


    }

    @Override
    public void enterComplexStatusLogic(SicomeParser.ComplexStatusLogicContext ctx) {
        String name = ctx.name.getText();
        boolean needsArg = false;
        for(var newCtx :ctx.statusLogicOption() ){
            if(newCtx.option.getText().equals("BIF")){
                needsArg = true;
                break;
            }
        }


        var resadd = symbolTable.addBifurcationLogic(name,needsArg);
        if(resadd == -1){
           err.addNewError(NUMERO_LOGICA_BIFURCACION_SUPERADO,List.of(), ctx.name);
        }else if (resadd == -2){
           err.addNewError(LOGICA_BIFURCACION_MISMO_NOMBRE, List.of(ctx.name.getText()), ctx.name);

        }


    }
}
