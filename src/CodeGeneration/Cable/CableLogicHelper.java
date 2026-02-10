package CodeGeneration.Cable;


import Internals.FlagState;
import Internals.MicroInstruction;
import Internals.MicroInstructionEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CableLogicHelper {
    /*
    GPR+1->GPR:t4·q5 + t11·q6 + t6·q7·As' + t10·q8·F·Zsc + t10·q8·F·Zsc'
     */
    private final Map<MicroInstructionEnum, String> firstSection = new HashMap<>(24);
    private final Map<MicroInstructionEnum, String> secondSection = new HashMap<>(24);
    /**
     * Initializes the generator adding manually the microinstructions referring to the fetch functionality
     */
    public CableLogicHelper(){

    }

    /**
     * Registers a new use of a microInstruction, the first step of a function is assumed to be 0 unless the id of the fuction is null in which case it will not account for the steps of the fetch function
     * @param mi microinstruction used
     * @param idFunc the id of the function in which it is used
     * @param idStep the step the in which the microinstruction is used, The first step of a fuction is assumed to be 0
     * @param flags the flags that are to be activated to use the micro instruction
     */
    public void addMicroInstructionUse(MicroInstruction mi, Integer idFunc, int idStep, List<FlagState> flags) {

        /*Cuando no tiene funciones asociadas se asume que es una instrucción de fetch
         Si no es de fetch se le añade 3 pasos mas porque esos tres son los que pertence al inicio de fetch
         */
        var section = switch (mi.mInstr){
            case load_sc, load_sr,sc_minus_to_sc,sr_plus_to_sr -> secondSection;
            default -> firstSection;
        };

        if(idFunc != null){
            idStep+=3;
        }


        String uses = section.get(mi.mInstr);
        StringBuilder builder = null;
        if (uses == null) {
            builder = new StringBuilder();
            builder.append(mi.mInstr.outputName);
            builder.append(":");
        }else {
            builder = new StringBuilder(uses);
            builder.append(" + ");
        }


        builder.append("t").append(idStep);

        if(idFunc!=null) {
            builder.append("·");
            builder.append("q").append(idFunc);
        }
        if(flags!=null) {
            for (FlagState flag : flags) {
                builder.append("·");
                builder.append(flag.getOutputName());
            }
        }

        switch (mi.mInstr){
            case load_sr -> {
                if( mi.arg == null){
                    builder.append("-").append(0);
                }else{
                    builder.append("-").append(mi.arg+2);
                }
            }
            default -> {
                if(mi.arg != null){
                    builder.append("-").append(mi.arg);
                }
            }
        }


        section.put(mi.mInstr, builder.toString());


    }






    public String getText(){
        StringBuilder builder = new StringBuilder();

        builder.append("$").append("\n");
        for (Map.Entry<MicroInstructionEnum,String> entry :firstSection.entrySet()){
            builder.append(entry.getValue());
            builder.append("\n");

        }
        builder.append("$").append("\n");
        for(Map.Entry<MicroInstructionEnum,String> entry: secondSection.entrySet()){
            builder.append(entry.getValue());
            builder.append("\n");
        }
        builder.append("$").append("\n");
        return builder.toString();
    }
}
