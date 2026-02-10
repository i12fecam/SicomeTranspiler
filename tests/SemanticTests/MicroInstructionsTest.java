package SemanticTests;

import Internals.Errors.ErrorEnum;
import Internals.MicroInstructionEnum;
import Internals.MicroInstructionTypeEnum;
import Runner.Runner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static Runner.ObjetiveConfig.repertorio;
import static org.junit.jupiter.api.Assertions.*;

public class MicroInstructionsTest {
    //private final Runner helper = new Runner();
    @Test
    @DisplayName("Comprueba que no haya dos instrucciones de mismo argumento con el mismo nombre.")
    public void INSTRUCCION_MISMO_NOMBRE(){
        String inputText = """
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){}
                instruccion1(Value){}
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_MISMO_NOMBRE));
    }
    @Test
    @DisplayName("Comprueba que no haya dos instrucciones de diferente argumento con el mismo nombre.")
    void INSTRUCCION_MISMO_NOMBRE2(){
        String inputText = """
            Lógica{
                 inc ->  INCR
                 rtn -> RTN

            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){}
                instruccion1(Var){}
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_MISMO_NOMBRE));
    }

    @Test
    @DisplayName("Comprueba que señala correctamente cuando la microinstrucción no está escrita correctamente.")
    void MICROINSTRUCCION_NO_RECONOCIDA1(){
        String inputText = """
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
            Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                |inc| GOR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_NO_RECONOCIDA));
    }

    private static Stream<String> provideValidMicroInstructions() {
        return Arrays.stream(MicroInstructionEnum.values())
                .filter(microInstructionEnum ->
                            !microInstructionEnum.needsArgument
                            && microInstructionEnum.getType() != MicroInstructionTypeEnum.cable)
                .map(microInstructionEnum ->
                        microInstructionEnum.inputName);

    }


    @ParameterizedTest
    @DisplayName("Comprueba que todas las microinstrucciones válidas sean reconocidas correctamente.")
    @MethodSource("provideValidMicroInstructions")
    void MICROINSTRUCCION_NO_RECONOCIDA2(String microinstruction){
        String inputText = String.format("""
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |inc| %s;
                }
            }\
            """,microinstruction);
        var helper = new Runner();
        assertDoesNotThrow(() -> helper.run(inputText, repertorio,null));

    }

    private static Stream<Arguments> provideInvalidMicroInstructionCombination() {
        return Arrays.stream(MicroInstructionEnum.values())
                .filter(a ->
                        a.getType() !=MicroInstructionTypeEnum.cable && !a.needsArgument)
                .flatMap(a ->
                        Arrays.stream(MicroInstructionEnum.values())
                        .filter(b -> b.getType() == a.getType())
                        .map(b -> Arguments.of(a.inputName,b.inputName))
                );

    }
    @ParameterizedTest
    @MethodSource("provideInvalidMicroInstructionCombination")
    @DisplayName("Comprueba que no haya dos microinstrucciones del mismo tipo en el mismo paso.")
    void MICROINSTRUCCION_INVALIDA1(String microinstruccion1,String microinstruccion2){
        String inputText = String.format("""
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                    |inc| PC->MAR;
                    |inc| M->GPR PC+1->PC;
                    |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                |inc| %s %s;
                }
            }\
            """,microinstruccion1,microinstruccion2);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        //helper.printErrors(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }
    private static Stream<String> provideInvalidValidMicroInstructions() {
        return Arrays.stream(MicroInstructionEnum.values())
                .filter(microInstructionEnum ->
                    microInstructionEnum.getType() == MicroInstructionTypeEnum.cable)
                .map(microInstructionEnum ->
                        microInstructionEnum.inputName);

    }
    @ParameterizedTest
    @MethodSource("provideInvalidValidMicroInstructions")
    @DisplayName("Comprueba que no haya microinstrucciones de tipo cable.")
    void MICROINSTRUCCION_INVALIDA2(String microinstruccion){
        String inputText = String.format("""
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                |inc| %s;
                }
            }\
            """,microinstruccion);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }

    @Test
    @DisplayName("Comprueba que no haya instrucciones con argumentos a la vez que una lógica de bifurcación que requiera argumento.")
    void MICROINSTRUCCION_INVALIDA3(){
        String inputText = """
            Lógica{
                 inc ->  INCR
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
                instruccion1(Value){
                |bif(0)| LOAD_SC(5);
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }




    @Test
    @DisplayName("Comprueba que las lógicas de bifurcacion que necesiten de argumento, lo tengan.")
    void ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO(){
        String inputText = """
            Lógica{
                 inc ->  INCR
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
                instruccion1(Value){
                |bif| GPR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO));
    }

    @Test
    @DisplayName("Comprueba que las lógicas de bifurcaciónes que tengan como argumento, una instrucción y un offset, no tenga un offset mayor que el nº de pasos de la instrucción.")
    void ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO2(){
        String inputText = """
            Lógica{
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
                instruccion1(Value){
                |inc| GPR->PC;
                |bif(instruccion2 ~ 0)| GPR->PC;
                }
                instruccion2(Value){
                |inc| GPR->PC;
                |bif(instruccion3 ~ 2)| GPR->PC; //instruccion 2 solo tiene dos pasos
                }
                instruccion3(Value){
                |inc| GPR->PC;
                |bif(instruccion1 ~ 0)| GPR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO));
    }

    @Test
    @DisplayName("Comprueba que las lógicas de bifurcaciónes que tengan como argumento, una instrucción y un offset, se refieran a instrucciones definidas.")
    void INSTRUCCION_NO_DEFINIDA(){
        String inputText = """
            Lógica{
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
                instruccion1(Value){
                |inc| GPR->PC;
                |bif(instruccion2 ~ 0)| GPR->PC;
                }
                instruccion2(Value){
                |inc| GPR->PC;
                |bif(instruccion4 ~ 2)| GPR->PC; //instruccion 4 no existe
                }
                instruccion3(Value){
                |inc| GPR->PC;
                |bif(instruccion1 ~ 0)| GPR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_NO_DEFINIDA));
    }


    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Comprueba que las microinstrucciones que necesiten de argumento, lo reciban.")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO(String argument){
        String inputText = String.format("""
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                |inc| LOAD_SC%s;
                }
            }\
            """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO));
    }

    //TODO Test sobre posible argumentos???

    @ParameterizedTest
    @ValueSource(strings = {"(0)", "(1)"})
    @DisplayName("Comprueba que las microinstrucciones que no necesiten de argumento,no lo reciban.")
    void MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO(String argument){
        String inputText = String.format("""
            Lógica{
                 inc ->  INCR
                 rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                |inc| GPR->PC%s;
                }
            }\
            """,argument);
        var helper = new Runner();
        assertDoesNotThrow( () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"256"})
    @DisplayName("Comprueba que el argumento pasado a LOAD SC no supere el límite de 8 bits")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO2(String argument){
        String inputText = String.format("""
            Lógica{
                inc -> INCR
                rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                    |inc| LOAD_SC(%s);
                }
            }\
            """,argument);
        var helper = new Runner();

        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO));
    }

    @Test
    @DisplayName("Comprueba que el uso de la rom por parte de los pasos de las instrucciones se ha superado (256 lineas)")
    void TAMANYO_ROM_SUPERADO(){
        //257
        String instrucciones = IntStream.range(1, 257+1)
                .mapToObj(i -> """
                       |inc| GPR->PC;
                       """)
                .reduce("",String::concat);

        String inputText = String.format("""
            Lógica{
                inc -> INCR
                rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    %s
                }
            }\
            """,instrucciones);
        var helper = new Runner();

        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.TAMANYO_ROM_SUPERADO));
    }

    @Test
    @DisplayName("Comprueba que el número de instrucciones no supere a 33, por el tamaño dedicado a instrucciones en la celdas de memoria (5 bits).")
    void NUMERO_INSTRUCCIONES_SUPERADO(){
        //instrucciones del 1 al 33
        String instrucciones = IntStream.range(1, 33+1)
                .mapToObj(i -> String.format("""
                        instruccion%s(Value){
                            |inc| GPR->PC;
                        }
                        """,i))
                .reduce("",String::concat);

        String inputText = String.format("""
            Lógica{
                inc -> INCR
                rtn -> RTN
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                %s
            }\
            """,instrucciones);
        var helper = new Runner();

        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.NUMERO_INSTRUCCIONES_SUPERADO));
    }


}
