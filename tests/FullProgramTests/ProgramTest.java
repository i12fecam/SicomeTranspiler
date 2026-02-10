package FullProgramTests;

import Analysis.LogicException;
import Runner.Runner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {

    private final Runner helper = new Runner();




    @Test
    void usingMarks(){
        String inputText = """
                @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    halt(){
                    }
                    jmp(Dir){
                    
                    }
                }
                Variables{
                    a(9) = {0s0};
                }
                Programa{
                    MARK inicio;
                        jmp inicio;
                    MARK medio;
                        jmp medio;
                    MARK final;
                        jmp final;
                        halt;
                }
               """;



        String outputProgramText = """
                0 0
                1 0
                2 0
                3 0
                4 0
                5 0
                6 0
                7 0
                8 0
                @
                9
                @
                jmp 9
                jmp A
                jmp B
                halt
                @
                """;

        Runner helper = new Runner();
        helper.run(inputText);


        helper.printErrorsToErr(true);
        assertEquals(outputProgramText,helper.getProgramText());
    }

}
