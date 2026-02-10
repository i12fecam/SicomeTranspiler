package CodeGeneration.Cable;

import Internals.SymbolTable;
import Internals.Instruction;

public class CableRepositoryHelper {
    SymbolTable _symbols;
    public CableRepositoryHelper(SymbolTable symbols) {

    _symbols= symbols;
    }
    /*
    HALT false 0 q0
    LDA true 2 q1
    LDQ true 1 q2
    STA true 2 q3
    STQ true 1 q4
    LDAQ true 5 q5
    STAQ true 11 q6
    ZEROS32 true 7 q7
     */
    public String getText(){
        StringBuilder builder = new StringBuilder();
        for(Instruction instr: _symbols.getInstructions()){
            builder.append(instr.getName());
            builder.append(" ");
            builder.append(instr.getBooleanParam().toString());
            builder.append(" ");
            builder.append(instr.getEstimatedSteps());
            builder.append(" ");
            builder.append("q").append(instr.getId());

            builder.append("\n");
        }
        return builder.toString();
    }
}
