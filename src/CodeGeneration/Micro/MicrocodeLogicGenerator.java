package CodeGeneration.Micro;

import Analysis.FlagExhaustivenessHelper;
import Internals.Errors.ErrorController;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;
import Internals.FlagState;
import Internals.BifurcationLogic;
import Internals.SymbolTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static Internals.Errors.ErrorEnum.*;

public class MicrocodeLogicGenerator extends SicomeBaseListener {

    private final SymbolTable symbols;
    //private ParseTreeProperty<Integer> ids;

    private final MicroLogicHelper logic = new MicroLogicHelper();
    private final ErrorController errorController;
    private final FlagExhaustivenessHelper flagHelper;
    public MicrocodeLogicGenerator(SymbolTable st, ErrorController errorController) {
        this.symbols = st;
        this.errorController = errorController;
         this.flagHelper = new FlagExhaustivenessHelper(errorController);
    }


    @Override
    public void enterSimpleStatusLogic(SicomeParser.SimpleStatusLogicContext ctx) {
        String name = ctx.name.getText();
        BifurcationLogic bifLogic = symbols.getBifurcationLogic(name);
        boolean inc = false;
        boolean bif = false;
        boolean ret = false;
        if(ctx.option != null) {
            switch (ctx.option.getText()) {
                case "BIF":
                    bif = true;
                    break;
                case "INCR":
                    inc = true;
                    break;
                case "RTN":
                    ret = true;
                    break;
            }
        }
        boolean enable = true;
        if(ctx.disable!=null) enable=false;

        logic.addStatusLogic(bifLogic.getId(),new ArrayList<>(){},inc,bif,ret,enable);
    }

    @Override
    public void enterComplexStatusLogic(SicomeParser.ComplexStatusLogicContext ctx) {
        String name = ctx.name.getText();


        for(var optionCtx:ctx.statusLogicOption()){
            //Se registra flags
            ArrayList<FlagState> flags = new ArrayList<>();
            for(var flagToken:optionCtx.flags){
                FlagState flag = FlagState.ValueOfInput(flagToken.getText());
                if(flag==null) {
                   errorController.addNewError(BANDERA_NO_RECONOCIDA,
                                    List.of(flagToken.getText()),
                                    flagToken);
                }

                assert flag != null;
                if(!flag.getFlag().canBeUsedInMicro()){
                    errorController.addNewError(BANDERA_INVALIDA,
                            List.of(flagToken.getText()),
                            flagToken);
                }
                flags.add(flag);
            }

            //We check if there is defined flag multiples times in set

            var repeatedFlags = flags.stream()
                    .map(FlagState::getFlag)
                    .toList();
            repeatedFlags.stream()
                    .filter(f -> Collections.frequency(repeatedFlags,f) > 1)
                    .distinct()
                    .forEach(f -> errorController.addNewError(LOGICA_CONTROL_NO_EXHAUSTIVA,
                                List.of("La bandera " + f.inputName + " se encuentra definida varias veces en la misma condición"),
                                ctx.start)
                    );

            flagHelper.addNewFlagCombination(new HashSet<>(flags));


            BifurcationLogic bifLogic = symbols.getBifurcationLogic(name);
            boolean inc = false;
            boolean bif = false;
            boolean ret = false;
            if(optionCtx.option != null) {
                switch (optionCtx.option.getText()) {
                    case "BIF":
                        bif = true;
                        break;
                    case "INCR":
                        inc = true;
                        break;
                    case "RTN":
                        ret = true;
                        break;

                }
            }
            boolean enable = true;
            if(optionCtx.disable!=null) enable=false;

            logic.addStatusLogic(bifLogic.getId(),flags,inc,bif,ret,enable);
        }
    }

    @Override
    public void exitComplexStatusLogic(SicomeParser.ComplexStatusLogicContext ctx) {
        var res = flagHelper.check();
        switch (res){
            case FlagExhaustivenessHelper.NoError ignored ->{}
            case FlagExhaustivenessHelper.MissingConditionError err ->{
                err.getConditions().forEach(c ->
                        errorController.addNewError(LOGICA_CONTROL_NO_EXHAUSTIVA
                        ,List.of("Combinación " + c + " no controlada"),
                        ctx.start)
                );
            }
            case FlagExhaustivenessHelper.RepeatedConditionError err ->{
                err.getConditions().forEach(c ->
                        errorController.addNewError(LOGICA_CONTROL_NO_EXHAUSTIVA
                                ,List.of("Combinación " + c + " definida varias veces"),
                                ctx.start)
                );
            }
        }
    }

    public String getLogicFileString() {
        return logic.getText();
    }
}
