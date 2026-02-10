package CodeGeneration.Micro;

import Internals.SymbolTable;
import Internals.Instruction;
import Internals.InstructionArgumentTypeEnum;
import Internals.MicroInstructionEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MicroRepositoryHelper {

    private final Map<Integer,ArrayList<Integer>> table = new HashMap<>();

    private final SymbolTable _symbols;
    MicroRepositoryHelper(SymbolTable symbolTable){
        _symbols=symbolTable;
        var functions = _symbols.getInstructions();
        for(var func:functions){
            table.put(func.getId(),new ArrayList<>(func.getNSteps()));
            for(int i=0;i<func.getNSteps();i++){
                table.get(func.getId()).add(0);
            }
        }
    }
    public void associateControlFlow(int functionId, int stepId, int controlFlowIdentifier){
        int num = table.get(functionId).get(stepId);
        num+=controlFlowIdentifier*Math.pow(2,8);
        table.get(functionId).set(stepId,num);

    }
    public void associateMicroInstruction(int functionId, int stepId, MicroInstructionEnum microInstruction){
        int num = table.get(functionId).get(stepId);
        num+=microInstruction.microCode;
        table.get(functionId).set(stepId,num);

    }
    public void associateSCvalue(int functionId, int stepId,int value){
        int num = table.get(functionId).get(stepId);
        num+=value;
        table.get(functionId).set(stepId,num);

    }
    public void associateCROMvalue(int functionId, int stepId,int value){
        int num = table.get(functionId).get(stepId);
        num+=value;
        table.get(functionId).set(stepId,num);

    }
    public void associateBifValue(int functionId,int stepId,int value){
        int num = table.get(functionId).get(stepId);
        num+=value;
        table.get(functionId).set(stepId,num);
    }

    public String getText(){
        StringBuilder builder = new StringBuilder();


        var fetchInstruction = table.get(0);
        builder.append("$\n");
        for (var stepCode: fetchInstruction){
            builder.append("CB ").append(Integer.toHexString(stepCode).toUpperCase()).append("\n");
        }
        builder.append("$\n");

        for(var entry: table.entrySet()){
            int functionId =entry.getKey();

            //ignorar el ciclo de fetch guardado como primera instrucción
            if(functionId == 0){
                continue;
            }

            Instruction fun =_symbols.getInstructionById(functionId);
            builder.append(fun.getName()).append(" ");


            if(fun.getParam().equals(InstructionArgumentTypeEnum.None)){
                builder.append("false");
            }else{
                builder.append("true");
            }


            if(entry.getValue().isEmpty()){
                builder.append(" ");
                builder.append("0");
            }
            else{
                for(var StepCode:entry.getValue()){
                    builder.append(" ");
                    builder.append(Integer.toHexString(StepCode).toUpperCase());

                }
            }

            builder.append("\n");
        }

        return builder.toString();
    }
}
