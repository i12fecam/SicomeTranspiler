package CodeGeneration.Micro;

import Internals.Errors.ErrorController;
import Internals.Instruction;
import Internals.MicroInstructionTypeEnum;
import Internals.SymbolTable;
import Parsing.SicomeBaseListener;
import Parsing.SicomeParser;

import Internals.MicroInstructionEnum;
import org.antlr.v4.runtime.tree.ParseTreeProperty;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Internals.Errors.ErrorEnum.*;
import static Internals.MicroInstructionEnum.load_sc;
import static java.lang.Integer.parseInt;

public class MicrocodeGenerator extends SicomeBaseListener {

    private final SymbolTable symbols;
    private final ParseTreeProperty<Integer> ids;
    private final MicroRepositoryHelper repository;
    private final ErrorController err;

    public MicrocodeGenerator(ParseTreeProperty<Integer> ids, SymbolTable st, ErrorController err) {
        this.ids = ids;
        this.symbols = st;
        this.err = err;
        this.repository = new MicroRepositoryHelper(st);
    }

    @Override
    public void enterInstructionBlockMicro(SicomeParser.InstructionBlockMicroContext ctx){
        if(ctx.instructionMicro().size() > 32){
            err.addNewError(NUMERO_INSTRUCCIONES_SUPERADO,List.of(), ctx.start);
        }
        var cromUsed = ctx.instructionMicro().stream()
                .mapToInt(i->{
                    return i.stepMicro().size();
                })
                .sum();
        if(cromUsed > 256){
            err.addNewError(TAMANYO_ROM_SUPERADO,List.of(), ctx.start);
        }

    }
    @Override
    public void enterStepMicro(SicomeParser.StepMicroContext ctx) {
        int id_func = ids.get(ctx.getParent());
        int id_step = ids.get(ctx);

        //We associate the bifurcation logic
        var bifLogic = symbols.getBifurcationLogic(ctx.biflogic.getText());

        if (bifLogic == null) {
            err.addNewError(LOGICA_BIFURCACION_NO_DEFINIDA,
                    List.of(ctx.biflogic.getText()),
                    ctx.biflogic);
        }
        if (bifLogic.needsArg() && ctx.bifLogicArgument() == null) {
            err.addNewError(ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO,
                    List.of(ctx.biflogic.getText()),
                    ctx.biflogic);
        }

        repository.associateControlFlow(id_func,id_step, bifLogic.getId());

        if( ctx.bifLogicArgument() != null){
            switch (ctx.bifLogicArgument()){
                case SicomeParser.InstructionBifLogicArgumentContext  ArgCtx ->{
                    var instruction = symbols.getInstructionByName(ArgCtx.instr.getText());
                    if(instruction == null){
                        err.addNewError(INSTRUCCION_NO_DEFINIDA,
                                List.of(ArgCtx.arg.getText()),
                                ArgCtx.arg);
                    }
                    var offset = parseInt(ArgCtx.arg.getText(),10);

                    if(offset >= instruction.getNSteps()){
                        err.addNewError(ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO,
                                List.of(ArgCtx.arg.getText()),
                                ArgCtx.arg);
                    }

                    var pos = symbols.getInstructions()
                            .stream()
                            .filter(i -> i.getId() < instruction.getId() )
                            .map(Instruction::getNSteps)
                            .reduce(0 , Integer::sum);
                    pos = pos + offset;
                    repository.associateBifValue(id_func,id_step,pos);
                }
                case SicomeParser.StartBifLogicArgumentContext ArgCtx ->{
                    repository.associateBifValue(id_func,id_step,0);
                }
                default ->{}
            }

        }


        Set<MicroInstructionTypeEnum> MicroInstructionTypeSeen = new HashSet<>();
        //We associate the microInstructions
        for(var mInstr:ctx.instr ){
            MicroInstructionEnum mi = MicroInstructionEnum.valueOfInput(mInstr.MICRO_INSTR().getText());

            if(mi==null) {
                err.addNewError(MICROINSTRUCCION_NO_RECONOCIDA,
                                List.of(mInstr.MICRO_INSTR().getText()),
                                mInstr.MICRO_INSTR().getSymbol());
            }
            assert mi != null;


            if(mi.getType() == MicroInstructionTypeEnum.cable){
                err.addNewError(MICROINSTRUCCION_INVALIDA,
                                List.of(mInstr.MICRO_INSTR().getText(),"No puede haber instrucciones de tipo cable"),
                                mInstr.MICRO_INSTR().getSymbol());
            }

            if (!MicroInstructionTypeSeen.add(mi.getType())) {
                err.addNewError(MICROINSTRUCCION_INVALIDA,
                                List.of(mInstr.MICRO_INSTR().getText(),"No puede haber dos instrucciones del mismo grupo en el mismo paso"),
                                mInstr.MICRO_INSTR().getSymbol());
            }



            repository.associateMicroInstruction(id_func,id_step,mi);

            if(mi.needsArgument && mInstr.arg == null){
                err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                                List.of(mInstr.MICRO_INSTR().getText(),"La instrucción necesita de argumento"),
                                mInstr.MICRO_INSTR().getSymbol());
            }
            if(!mi.needsArgument && mInstr.arg != null){
                err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO,
                                List.of(mInstr.MICRO_INSTR().getText()),
                                mInstr.MICRO_INSTR().getSymbol());
            }

            if(mi.needsArgument && bifLogic.needsArg()){
                err.addNewError(MICROINSTRUCCION_INVALIDA,
                                List.of(mInstr.MICRO_INSTR().getText(),
                                    "No puede haber una microinstrucción que necesite de argumento a la vez que una lógica de bifurcación que también lo necesite"),
                                mInstr.MICRO_INSTR().getSymbol());
            }

            if(mi.needsArgument){
                var argNumber = parseInt(mInstr.arg.getText(),10);
                if(mi == load_sc && argNumber >= 256){
                    err.addNewError(MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO,
                            List.of(mi.inputName,"La instrucción no puede superar el tamaño de 8 bits"),
                            mInstr.MICRO_INSTR().getSymbol());

                }

                repository.associateSCvalue(id_func,id_step,argNumber);
            }
        }



    }

    public String getRepositoryFileString() {
        return repository.getText();
    }
}
