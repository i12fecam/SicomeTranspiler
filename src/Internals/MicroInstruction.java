package Internals;

public class MicroInstruction {
    public MicroInstructionEnum mInstr;
    public Integer arg;


    public MicroInstruction(MicroInstructionEnum mInstr,Integer value){
        this.mInstr = mInstr;
        this.arg = value;
        if (this.mInstr.needsArgument && this.arg == null && this.mInstr != MicroInstructionEnum.load_sr){
            throw new IllegalArgumentException("The microinstruction requires a value");
        }

        if(!this.mInstr.needsArgument && this.arg != null){
            throw new IllegalArgumentException("The microInstruction doesnt require a value");
        }
    }

}


