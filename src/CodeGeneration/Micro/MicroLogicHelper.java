package CodeGeneration.Micro;

import Internals.FlagEnum;
import Internals.FlagState;

import java.util.List;

public class MicroLogicHelper {

    private StringBuilder builder= new StringBuilder("B3 B2 B1 B0 F Zb Za Zac Zsc X Qn Qn1 As Qs Bs N I B R E\n");

    public String getText(){
        return builder.toString();
    }
    public void addStatusLogic(int id, List<FlagState> flags, boolean inc, boolean bif, boolean ret, boolean enable){
        assert(id<16);

        String stringId =Integer.toBinaryString(id);
        //Se padea a que llegue a cuatro números
        while(true){
            if(stringId.length()<4){
                stringId = "0" + stringId;
            }else{
                break;
            }
        }

        for(var charID: stringId.toCharArray()){
            builder.append(charID);
            builder.append("  ");
        }

        processFlag(flags, FlagEnum.F);
        processFlag(flags, FlagEnum.Zb);
        processFlag(flags, FlagEnum.Za);
        processFlag(flags, FlagEnum.Zac);
        processFlag(flags, FlagEnum.Zsc);
        processFlag(flags, FlagEnum.X);
        processFlag(flags, FlagEnum.Qn);
        processFlag(flags, FlagEnum.Qn1);
        processFlag(flags, FlagEnum.As);
        processFlag(flags, FlagEnum.Qs);
        processFlag(flags, FlagEnum.Bs);
        processFlag(flags, FlagEnum.N);

        if(inc){
            builder.append("1");
        }else{
            builder.append("0");
        }

        builder.append(" ");

        if(bif){
            builder.append("1");
        }else{
            builder.append("0");
        }

        builder.append(" ");

        if(ret){
            builder.append("1");
        }else{
            builder.append("0");
        }

        builder.append(" ");

        if(enable){
            builder.append("1");
        }else{
            builder.append("0");
        }

        builder.append("\n");
    }

    private Boolean checkFlag(List<FlagState> flags, FlagEnum flagEnum){
        for (var i:flags){
            if(i.getFlag().equals(flagEnum)){
                return  i.getState();
            }
        }
        return null;
    }

    private void processFlag(List<FlagState> flags, FlagEnum flagEnum){
        Boolean FlagStatus = checkFlag(flags, flagEnum);
        if(FlagStatus==null){
            builder.append("X");
        } else if (FlagStatus) {
            builder.append("1");
        }else {
            builder.append("0");
        }
        for(int i = 0; i< flagEnum.toString().length(); i++){
            builder.append(" ");
        }

    }

}
