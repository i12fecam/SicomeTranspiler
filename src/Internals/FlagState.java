package Internals;

import java.util.Objects;

public class FlagState implements Comparable {



    private final FlagEnum flagEnum;
    private final Boolean activated;

    public FlagState(FlagEnum flagEnum, Boolean activated) {
        this.flagEnum = flagEnum;
        this.activated = activated;
    }

    public static FlagState ValueOfInput(String input){
        FlagEnum flagEnum = null;
        if(input.startsWith("!")){
            flagEnum = FlagEnum.ValueofInput(input.substring(1));
            if(flagEnum == null) return null;
            return new FlagState(flagEnum, false);
        }
        flagEnum = FlagEnum.ValueofInput(input);
        if(flagEnum == null) return null;
        return new FlagState(flagEnum,true);
    }

    public String getOutputName(){
        String res = new String();
        if(activated) return flagEnum.outputName;
        else return flagEnum.outputName +"'";
    }

    public String getInputName(){
        String res = new String();
        if(activated) return flagEnum.outputName;
        else return "!" + flagEnum.outputName;
    }

    public FlagEnum getFlag() {
        return flagEnum;
    }

    public boolean isStateDefined(){return activated != null;}
    public boolean getState(){
        return activated;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlagState flagState = (FlagState) o;
        return activated == flagState.activated && flagEnum == flagState.flagEnum;
    }

    @Override
    public String toString() {
        return getInputName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(flagEnum, activated);
    }

    @Override
    public int compareTo(Object o) {
        var other = (FlagState) o;
        var cmp = Integer.compare(this.flagEnum.ordinal(),other.flagEnum.ordinal() );
        switch (cmp){
            case 0 :
                if(this.activated == other.activated) return 0;
                if(this.activated) return 1;
                return -1;
            default:
                return cmp;
        }
    }
}
