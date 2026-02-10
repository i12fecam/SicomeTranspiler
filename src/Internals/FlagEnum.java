package Internals;

public enum FlagEnum {
    Zb("Zb","Zb"), //micro, cable
    Za("Za","Za"), //micro ,cable
    Zac("Zac","Zac"), //micro, cable
    Zsc("Zsc","Zsc"), //micro, cable
    Ovf("Ovf","Ovf",false),
    N("N","N"), //micro ,cable
    Qn("Qn","Qn"), //micro , cable
    Qn1("Qn+","Qn_plus"), //micro, cable
    As("As","As"), //micro, cable
    Qs("Qs","Qs"), //micro, cable
    Bs("Bs","Bs"), //micro, cable
    X("X","X"), //micro ,cable
    F("F","F"); //micro , cable

    public final String outputName;

    public final String inputName;

    public final boolean admitsMicro;

    public static FlagEnum ValueofInput(String input){
        for (FlagEnum e : values()) {
            if (e.inputName.equals(input)) {
                return e;
            }
        }
        return null;
    }

    public static FlagEnum ValueofOutput(String output){
        for (FlagEnum e : values()) {
            if (e.outputName.equals(output)) {
                return e;
            }
        }
        return null;
    }

    public boolean canBeUsedInMicro(){
        return this.admitsMicro;
    }

    private FlagEnum(String inputName, String outputName){
        this.outputName = outputName;
        this.inputName = inputName;
        this.admitsMicro = true;
    }

    private FlagEnum(String inputName, String outputName,boolean admitsMicro){
        this.outputName = outputName;
        this.inputName = inputName;
        this.admitsMicro = admitsMicro;
    }




}
