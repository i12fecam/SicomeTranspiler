package CodeGeneration;

import Internals.SymbolTable;
import Internals.Variable;

public class ProgramCodeGeneratorHelper {

    private SymbolTable _symbols;

    private StringBuilder InstructionBuilder= new StringBuilder();
    public ProgramCodeGeneratorHelper(SymbolTable symbols){
        _symbols =symbols;
    }
    /*
    0 0
    1 1
    2 FFFF
    @
    3
    @
    LDA 0
    LDQ 2
    ADDAQ 0
    HALT
     */
    public String getText(){
        StringBuilder buider =new StringBuilder();
        for(Variable var : _symbols.getVariables()){
                Integer memDir=var.getStartPosition();
                for(int i = 0; i<var.capacity(); i++){
                    int memValue=var.getInitialValue(i);
                    buider.append(Integer.toHexString(memDir).toUpperCase());
                    buider.append(" ");
                    buider.append(Integer.toHexString(memValue).toUpperCase());
                    buider.append("\n");
                    memDir++;
                }
        }
        buider.append("@").append("\n");
        buider.append(  Integer.toHexString(_symbols.getStartOfInstruction()).toUpperCase()  ) .append("\n");
        buider.append("@").append("\n");
        buider.append(InstructionBuilder.toString());
        buider.append("@").append("\n");
        return buider.toString();
    }

    /**
     * Registers an instruction use
     * @param instruction The name of the instruction
     * @param argument the argument of the instructions (Position in memory), null if it doesnt have an argument
     */
    public void addInstructionUse(String instruction, Integer argument) {
        InstructionBuilder.append(instruction);
        if(argument!=null){
            InstructionBuilder.append(" ");
            InstructionBuilder.append(Integer.toHexString(argument).toUpperCase());
        }
        InstructionBuilder.append("\n");
    }
}
