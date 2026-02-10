package CodeGeneration.Cable;

import Analysis.FlagExhaustivenessHelper;
import Internals.Errors.ErrorController;
import Internals.Errors.ErrorEnum;
import Internals.MicroInstruction;
import Internals.SymbolTable;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;
import Internals.FlagState;
import Internals.MicroInstructionEnum;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.*;

import static Internals.Errors.ErrorEnum.*;
import static Internals.MicroInstructionEnum.*;
import static java.lang.Integer.parseInt;

public class CableCodeGenerator extends SicomeBaseListener {

    protected CableLogicHelper logic;
    protected CableRepositoryHelper repository;

    protected ParseTreeProperty<Integer> _ids = null;
    protected SymbolTable symbols;
    private final ErrorController err;
    private final FlagExhaustivenessHelper flagHelper;

    public CableCodeGenerator(ParseTreeProperty<Integer> ids, SymbolTable st, ErrorController err) {
        _ids = ids;
        logic = new CableLogicHelper();
        repository = new CableRepositoryHelper(st);
        symbols = st;
        this.err = err;
        this.flagHelper = new FlagExhaustivenessHelper(err);
    }

    public String getLogicFileString(){
        return logic.getText();
    }

    public String getRepositoryFileString(){
        return repository.getText();
    }

    //To build the repository and logic
    @Override
    public void exitSimpleCableStep(SicomeParser.SimpleCableStepContext ctx) {
        Integer id_func = _ids.get(ctx.getParent());
        int id_step = _ids.get(ctx);
        //Process left instruction
        var lmInstrEnum = MicroInstructionEnum.valueOfInput(ctx.linstr.MICRO_INSTR().getText());
        switch (lmInstrEnum){
            case sr_plus_to_sr -> {
                logic.addMicroInstructionUse(new MicroInstruction(lmInstrEnum,null), id_func, id_step,  null);
            }
            case load_sr -> {

                if(ctx.linstr.arg == null){
                    err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                    List.of(load_sr.inputName,"La instrucción necesita de argumento"),
                                    ctx.linstr.MICRO_INSTR().getSymbol());
                }

                Integer argValue = null;
                if(ctx.linstr.arg.getText().equals("START")){
                     argValue = null;
                }
                else {
                    argValue = parseInt(ctx.linstr.arg.getText(), 10);

                    var stepsInInstrucction = symbols.getInstructionById(id_func).getNSteps();
                    if(argValue >= stepsInInstrucction ){
                        err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                        List.of(lmInstrEnum.inputName,"El valor no puede superar el número de pasos en la que se encuentra"),
                                        ctx.linstr.MICRO_INSTR().getSymbol());
                    }
                }



                logic.addMicroInstructionUse(new MicroInstruction(lmInstrEnum,argValue), id_func, id_step,  null);

            }
            case null -> {
                err.addNewError(ErrorEnum.MICROINSTRUCCION_NO_RECONOCIDA,List.of(ctx.linstr.MICRO_INSTR().getText()),ctx.linstr.MICRO_INSTR().getSymbol());
            }

            default ->{
                err.addNewError(MICROINSTRUCCION_INVALIDA,List.of(ctx.linstr.MICRO_INSTR().getText(),"En el lado izquierdo solo está permitido las instrucciones "+ load_sr.inputName + " y "+ sr_plus_to_sr.inputName),ctx.linstr.MICRO_INSTR().getSymbol());
            }

        }

        Set<MicroInstructionEnum> seenMicroInstruction = new HashSet<>();

        //Process right instruction
        for (var mInstr: ctx.rinstr) {
            var rmIntrEnum = MicroInstructionEnum.valueOfInput(mInstr.MICRO_INSTR().getText());
            switch (rmIntrEnum){
                case sr_plus_to_sr,load_sr -> {
                    err.addNewError(MICROINSTRUCCION_INVALIDA,List.of(mInstr.MICRO_INSTR().getText(),"En lado derecho no está permitido las instrucciones"+ load_sr.inputName + " y "+ sr_plus_to_sr.inputName),mInstr.MICRO_INSTR().getSymbol());
                }
                case null -> {
                    err.addNewError(ErrorEnum.MICROINSTRUCCION_NO_RECONOCIDA,List.of(mInstr.MICRO_INSTR().getText()),mInstr.MICRO_INSTR().getSymbol());
                }
                default -> {

                    if (!seenMicroInstruction.add(rmIntrEnum)) {
                        err.addNewError(MICROINSTRUCCION_INVALIDA, List.of(mInstr.MICRO_INSTR().getText(),"No puede definirse la misma instrucción dos veces en el mismo paso:" + rmIntrEnum.inputName), mInstr.MICRO_INSTR().getSymbol());
                    }
                    if (rmIntrEnum.needsArgument && mInstr.arg == null) {
                        err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                List.of(rmIntrEnum.inputName,"La instrucción necesita de argumento"),
                                mInstr.MICRO_INSTR().getSymbol());
                    }

                    if (!rmIntrEnum.needsArgument && mInstr.arg != null){
                        err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO, List.of(rmIntrEnum.inputName), mInstr.MICRO_INSTR().getSymbol());
                    }


                    Integer argValue = null;
                    if (mInstr.arg != null) {
                        argValue = parseInt(mInstr.arg.getText(),10);

                        if(rmIntrEnum == load_sc && argValue >= 256){
                            err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                    List.of(rmIntrEnum.inputName,"La instrucción no puede superar el tamaño de 8 bits"),
                                    mInstr.MICRO_INSTR().getSymbol());

                        }
                    }

                    logic.addMicroInstructionUse(new MicroInstruction(rmIntrEnum,argValue), id_func, id_step,  null);
                }
            }


        }
    }

    @Override
    public void exitConditionalStepCable(SicomeParser.ConditionalStepCableContext ctx) {
        int id_func = _ids.get(ctx.getParent().getParent());
        int id_step = _ids.get(ctx.getParent());


        //Process flags
        List<FlagState> flags = new ArrayList<>();
        for( Token flag: ctx.flags){
            FlagState newFlag = FlagState.ValueOfInput(flag.getText());
            if(newFlag == null){
                err.addNewError(ErrorEnum.BANDERA_NO_RECONOCIDA,List.of(flag.getText()),flag);
            }
            flags.add(newFlag);

        }

        var repeatedFlags = flags.stream()
                .map(FlagState::getFlag)
                .toList();
        repeatedFlags.stream()
                .filter(f -> Collections.frequency(repeatedFlags,f) > 1)
                .distinct()
                .forEach(f -> err.addNewError(PASO_COMPLEJO_NO_EXHAUSTIVO,
                        List.of("La bandera " + f.inputName + " se encuentra definida varias veces en la misma condición"),
                        ctx.start)
                );

        flagHelper.addNewFlagCombination(new HashSet<>(flags));

        //Process left instruction
        var lmInstrEnum = MicroInstructionEnum.valueOfInput(ctx.linstr.MICRO_INSTR().getText());
        switch (lmInstrEnum){
            case sr_plus_to_sr -> {
                logic.addMicroInstructionUse(new MicroInstruction(lmInstrEnum,null), id_func, id_step,  flags);
            }
            case load_sr -> {

                if(ctx.linstr.arg == null){
                    err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                            List.of(load_sr.inputName,"La instrucción necesita de argumento"),
                            ctx.linstr.MICRO_INSTR().getSymbol());
                }

                Integer argValue = null;
                if(ctx.linstr.arg.getText().equals("START")){
                    argValue = null;
                }
                else {
                    argValue = parseInt(ctx.linstr.arg.getText(), 10);

                    var stepsInInstrucction = symbols.getInstructionById(id_func).getNSteps();
                    if(argValue >= stepsInInstrucction ){
                        err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                        List.of(lmInstrEnum.inputName,"El valor no puede superar el número de pasos en la que se encuentra"),
                                        ctx.linstr.MICRO_INSTR().getSymbol());
                    }
                }
                logic.addMicroInstructionUse(new MicroInstruction(lmInstrEnum,argValue), id_func, id_step,  flags);
            }
            case null -> {
                err.addNewError(ErrorEnum.MICROINSTRUCCION_NO_RECONOCIDA,List.of(ctx.linstr.MICRO_INSTR().getText()),ctx.linstr.MICRO_INSTR().getSymbol());
            }
            default -> {
                err.addNewError(MICROINSTRUCCION_INVALIDA,List.of(ctx.linstr.MICRO_INSTR().getText(),"En el lado izquierdo solo está permitido las instrucciones "+ load_sr.inputName + " y "+ sr_plus_to_sr.inputName),ctx.linstr.MICRO_INSTR().getSymbol());
            }

        }

        Set<MicroInstructionEnum> seenMicroInstruction = new HashSet<>();

        //Process right instruction
        for ( var mInstr: ctx.rinstr) {
            var rmIntrEnum = MicroInstructionEnum.valueOfInput(mInstr.MICRO_INSTR().getText());
            switch (rmIntrEnum){
                case sr_plus_to_sr,load_sr ->   {
                    err.addNewError(MICROINSTRUCCION_INVALIDA,List.of(mInstr.MICRO_INSTR().getText(),"En lado derecho no está permitido las instrucciones"+ load_sr.inputName + " y "+ sr_plus_to_sr.inputName),ctx.linstr.MICRO_INSTR().getSymbol());
                }
                case null ->{
                    err.addNewError(ErrorEnum.MICROINSTRUCCION_NO_RECONOCIDA,List.of(mInstr.MICRO_INSTR().getText()),mInstr.MICRO_INSTR().getSymbol());
                }
                default -> {

                    if (!seenMicroInstruction.add(rmIntrEnum)) {
                        err.addNewError(MICROINSTRUCCION_INVALIDA,List.of(mInstr.MICRO_INSTR().getText(),"No puede definirse la misma instrucción dos veces en el mismo paso:" + rmIntrEnum.inputName),mInstr.MICRO_INSTR().getSymbol());
                    }

                    if (rmIntrEnum.needsArgument && mInstr.arg == null) {
                        err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                List.of(rmIntrEnum.inputName,"La instrucción necesita de argumento"),
                                mInstr.MICRO_INSTR().getSymbol());
                    }

                    if (!rmIntrEnum.needsArgument && mInstr.arg != null){
                        err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO, List.of(rmIntrEnum.inputName), mInstr.MICRO_INSTR().getSymbol());
                    }

                    Integer argValue = null;
                    if (mInstr.arg != null) {
                        argValue = parseInt(mInstr.arg.getText(),10);

                        if(rmIntrEnum == load_sc && argValue >= 256){
                            err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                    List.of(rmIntrEnum.inputName,"La instrucción no puede superar el tamaño de 8 bits"),
                                    mInstr.MICRO_INSTR().getSymbol());

                        }
                    }

                    logic.addMicroInstructionUse(new MicroInstruction(rmIntrEnum,argValue), id_func, id_step,  flags);
                }
            }
        }

    }
    @Override
    public void exitConditionalCableStepBlock(SicomeParser.ConditionalCableStepBlockContext ctx){

        var res = flagHelper.check();
        switch (res){
            case FlagExhaustivenessHelper.NoError ignored ->{}
            case FlagExhaustivenessHelper.MissingConditionError error ->{
                error.getConditions().forEach(c ->
                        err.addNewError(PASO_COMPLEJO_NO_EXHAUSTIVO
                                ,List.of("Combinación " + c + " no controlada"),
                                ctx.start)
                );
            }
            case FlagExhaustivenessHelper.RepeatedConditionError error ->{
                error.getConditions().forEach(c ->
                        err.addNewError(PASO_COMPLEJO_NO_EXHAUSTIVO
                                ,List.of("Combinación " + c + " definida varias veces"),
                                ctx.start)
                );
            }
        }
    }
}
