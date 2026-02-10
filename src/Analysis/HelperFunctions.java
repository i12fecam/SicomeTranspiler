package Analysis;

 public class HelperFunctions {
    public static Integer  parseNumber(String num, int bitsSizeContainer){

        Integer res = 0;
        if (num.startsWith("0x")) {
            res = processHexadecimalNumber(num.substring(2),bitsSizeContainer);
        }else if(num.startsWith("0b")) {
            res = processBinaryNumber(num.substring(2),bitsSizeContainer);
        } else if(num.startsWith("0c")) {
            res = processComplement2Number(num.substring(2),bitsSizeContainer);
        } else if(num.startsWith("0s")) {
            res = processSignMagnitudeNumber(num.substring(2),bitsSizeContainer);
        }
        return res;

    }
    //



    private static Integer processComplement2Number(String input,int bitsSizeContainer){
        long num = Long.parseLong(input);

        double maxNumber= Math.pow(2,bitsSizeContainer-1) - 1;
        double minNumber = -Math.pow(2,bitsSizeContainer-1);

        if(num > maxNumber || num < minNumber){
            return null;
        }
        long mask = (1L << bitsSizeContainer) - 1;
        return Math.toIntExact((~num + 1) & mask);
        //return Integer.parseInt(paddedBinaryRepresentation,2);

    }


    private static Integer processSignMagnitudeNumber(String input,int bitsSizeContainer){
        int num = Integer.parseInt(input);

        double maxNumber= Math.pow(2,bitsSizeContainer-1) - 1;
        double minNumber = -(Math.pow(2,bitsSizeContainer-1)-1);

        if(num > maxNumber || num < minNumber){
            return null;
        }
        String binaryRepresentation =  Integer.toBinaryString(Math.abs(num));

        String paddedBinaryRepresentation = padLeft(binaryRepresentation,"0",bitsSizeContainer-1);
        if (num < 0){
            paddedBinaryRepresentation = "1" + paddedBinaryRepresentation;
        }else {
            paddedBinaryRepresentation = "0" + paddedBinaryRepresentation;
        }
        return Integer.parseInt(paddedBinaryRepresentation,2);
    }

    private static Integer processHexadecimalNumber(String input,int bitsSizeContainer){
        Integer num =  Integer.parseInt(input,16);
        double maxNumber= Math.pow(2,bitsSizeContainer);
        double minNumber = 0;


        if(num >= maxNumber || num < minNumber){
            return null;
        }
        return num;
    }

    private static Integer processBinaryNumber(String input,int bitsSizeContainer){
        //Strip .
        input = input.replace(".","");
        //process
        int num =  Integer.parseInt(input,2);

        double maxNumber= Math.pow(2,bitsSizeContainer);
        double minNumber = 0;
        if(num >= maxNumber || num < minNumber){
            return null;
        }
        return num;
    }

    private static String padLeft(String input, String padding,int totalSize){
        if(input.length()>totalSize){
            return input.substring(input.length()-totalSize);
        }
        return padding.repeat(totalSize-input.length()) + input;
    }

    public static void main(String[] arg){
        System.out.println(Integer.toBinaryString( parseNumber("0s4",12)));
    }
}
