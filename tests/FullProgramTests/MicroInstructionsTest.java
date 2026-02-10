package FullProgramTests;

import Runner.Runner;
import org.junit.jupiter.api.Test;

import static Runner.ObjetiveConfig.repertorio;
import static org.junit.jupiter.api.Assertions.*;

public class MicroInstructionsTest {

    @Test
    void p2(){
        String inputText = """
                Lógica{
                 nop -> INCR DISABLE //0
                 inc ->  INCR //1
                 bif ->  BIF  //2
                 ret -> RTN //3
                 jmp_if_Z -> { //4
                    !F:INCR
                     F:BIF
                 }
                 bif_disable_if_F ->{ //5
                    !F:BIF
                    F: BIF DISABLE
                 }
                 jmp_if_not_Zb -> { //6
                    !Zb:BIF
                     Zb:INCR
                 }
                 bif_and_disable_if_not_Zb -> { //7
                    !Zb:BIF DISABLE
                    Zb:BIF
                 }
                 jmp_and_disable_if_Zac -> { //8
                    !Zac: INCR
                    Zac: BIF DISABLE
                 }
                 bifurcate_and_disable_if_not_As -> { //9
                    !As: BIF DISABLE
                     As: BIF
                 }
                 continue_and_disable_if_As -> { //10
                    !As: INCR
                     As: INCR DISABLE
                 }
                    
                 jmp_and_disable_if_Zsc -> { //11
                    !Zsc:BIF DISABLE
                    Zsc:INCR
                 }
                 
                 continue_and_disable_if_Zb -> {  //12
                    !Zb:INCR
                    Zb: INCR DISABLE
                 }
                }
                
                @Micro
                Instrucciones {
                    Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |ret| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    halt(){
                    }
                    isz(Value){
                          |inc| M->GPR;
                          |inc| GPR+1->GPR;
                          |inc| GPR->M;
                          |jmp_if_not_Zb(0)| ;
                          |bif(0)| PC+1->PC;
                    }
                    isz_r(Value){
                          |inc| M->GPR;
                          |inc| GPR+1->GPR;
                          |inc| GPR->M;
                          |bif_and_disable_if_not_Zb(0)| PC+1->PC;
                    }
                    push(Value){
                        |inc| M->GPR SP-1->SP;
                        |inc| SP->MAR;
                        |bif(0)| GPR->M;
                    }
                    pop(Value){
                        |inc| SP->MAR;
                        |inc| M->QR SP+1->SP;
                        |inc| GPR[AD]->MAR;
                        |bif(0)| QR->M;
                    }
                    push_i(Value){
                        |inc| M->GPR SP-1->SP;
                        |inc| GPR[AD]->MAR;
                        |inc| M->GPR;
                        |inc| SP->MAR;
                        |bif(0)| GPR->M;
                    }
                    pop_i(Value){
                        |inc| SP->MAR;
                        |inc| M->QR SP+1->SP;
                        |inc| GPR[AD]->MAR;
                        |inc| M->GPR;
                        |inc| GPR[AD]->MAR;
                        |bif(0)| QR->M;
                    }
                    jazpn(){
                        |jmp_and_disable_if_Zac(0)| PC+1->PC;
                        |bifurcate_and_disable_if_not_As(0)| PC+1->PC;
                    }
                    jmpr(Dir){
                        |inc| GPR->QR SP-1->SP;
                        |inc| ACC->GPR SP->MAR;
                        |inc| GPR->M 0->ACC;
                        |inc| QR->GPR !ACC->ACC;
                        |inc| PC->GPR GPR->PC;
                        |inc| ACC+GPR->ACC;
                        |inc| PC->GPR;
                        |inc| ACC+GPR->ACC;
                        |inc| ACC->GPR;
                        |inc| GPR->PC 0->ACC;
                        |inc| M->GPR SP+1->SP;
                        |bif(0)| ACC+GPR->ACC;
                    }
                    
                    
                }
                Variables{}
                Programa{}
                """;

        String outputRepositoryText = """
                $
                CB 4000100
                CB 201100
                CB B000300
                $
                halt false 0
                isz true 1100 4100 1000100 600 200200
                isz_r true 1100 4100 1000100 200700
                push true 801100 C000100 1000200
                pop true C000100 670100 8000100 2000200
                push_i true 801100 8000100 1100 C000100 1000200
                pop_i true C000100 670100 8000100 1100 8000100 2000200
                jazpn false 200800 200900
                jmpr true 868100 C002100 1008100 15100 403100 28100 3100 28100 2100 408100 601100 28200
                """;
                //JEG11 true 8000100 840100 C068100 AB2105 1008100 C90100 8B5D A90106 2100 8100 10C00 C98100 C000B63 1100 F100 28100 5100 28100 200A00 609100 28200

        String outputLogicText = """
                B3 B2 B1 B0 F Zb Za Zac Zsc X Qn Qn1 As Qs Bs N I B R E
                0  0  0  0  X X  X  X   X   X X  X   X  X  X  X 1 0 0 0
                0  0  0  1  X X  X  X   X   X X  X   X  X  X  X 1 0 0 1
                0  0  1  0  X X  X  X   X   X X  X   X  X  X  X 0 1 0 1
                0  0  1  1  X X  X  X   X   X X  X   X  X  X  X 0 0 1 1
                0  1  0  0  0 X  X  X   X   X X  X   X  X  X  X 1 0 0 1
                0  1  0  0  1 X  X  X   X   X X  X   X  X  X  X 0 1 0 1
                0  1  0  1  0 X  X  X   X   X X  X   X  X  X  X 0 1 0 1
                0  1  0  1  1 X  X  X   X   X X  X   X  X  X  X 0 1 0 0
                0  1  1  0  X 0  X  X   X   X X  X   X  X  X  X 0 1 0 1
                0  1  1  0  X 1  X  X   X   X X  X   X  X  X  X 1 0 0 1
                0  1  1  1  X 0  X  X   X   X X  X   X  X  X  X 0 1 0 0
                0  1  1  1  X 1  X  X   X   X X  X   X  X  X  X 0 1 0 1
                1  0  0  0  X X  X  0   X   X X  X   X  X  X  X 1 0 0 1
                1  0  0  0  X X  X  1   X   X X  X   X  X  X  X 0 1 0 0
                1  0  0  1  X X  X  X   X   X X  X   0  X  X  X 0 1 0 0
                1  0  0  1  X X  X  X   X   X X  X   1  X  X  X 0 1 0 1
                1  0  1  0  X X  X  X   X   X X  X   0  X  X  X 1 0 0 1
                1  0  1  0  X X  X  X   X   X X  X   1  X  X  X 1 0 0 0
                1  0  1  1  X X  X  X   0   X X  X   X  X  X  X 0 1 0 0
                1  0  1  1  X X  X  X   1   X X  X   X  X  X  X 1 0 0 1
                1  1  0  0  X 0  X  X   X   X X  X   X  X  X  X 1 0 0 1
                1  1  0  0  X 1  X  X   X   X X  X   X  X  X  X 1 0 0 0
                """;


        var helper = new Runner();
        try {
            helper.run(inputText);

        }catch (RuntimeException e){
            helper.printErrorsToErr(true);

        }
            helper.run(inputText);



        assertEquals(outputRepositoryText,helper.getRepositoryText());
        assertEquals(outputLogicText,helper.getLogicText());
    }




    @Test
    void testArgumentoBifUso(){
        String inputText = """
            Lógica{
                 nop ->
                 inc -> INCR
                 bif -> BIF
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                halt(){
                    |nop|;
                }
                instruccion1(Value){
                |inc| GPR->PC;
                |bif ( instruccion2 ~ 0)| GPR->PC;
                }
                instruccion2(Value){
                |inc| GPR->PC;
                |bif(instruccion3 ~ 1)| GPR->PC;
                }
                instruccion3(Value){
                |inc| GPR->PC;
                |bif(instruccion1 ~ 0)| GPR->PC;
                }
                instruccion4(Value){
                |inc| GPR->PC;
                |bif(0)| GPR->PC;
                }
            }\
            """;
        String outputRepositoryText = """
                $
                CB 4000100
                CB 201100
                CB B000300
                $
                halt false 0
                instruccion1 true 400100 400206
                instruccion2 true 400100 400209
                instruccion3 true 400100 400204
                instruccion4 true 400100 400200
                """;
        var helper = new Runner();

        helper.run(inputText, repertorio,null);

        helper.printErrorsToErr(true);
        assertEquals(outputRepositoryText,helper.getRepositoryText());

    }
}
