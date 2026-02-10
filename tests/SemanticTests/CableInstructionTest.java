package SemanticTests;

import Internals.Errors.ErrorEnum;
import Internals.FlagEnum;
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

public class CableInstructionTest {

    //private final Runner helper = new Runner();
    @Test
    @DisplayName("Comprueba que no haya dos instrucciones de distinto argumento con el mismo nombre.")
    public void INSTRUCCION_MISMO_NOMBRE(){
        String inputText = """
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){}
                instruccion1(Var){}
            }
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);

        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_MISMO_NOMBRE));
    }

    @Test
    @DisplayName("Comprueba que no haya dos instrucciones de mismo argumento con el mismo nombre.")
    public void INSTRUCCION_MISMO_NOMBRE2(){
        String inputText = """
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){}
                instruccion1(){}
            }
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_MISMO_NOMBRE));
    }

    @Test
    @DisplayName("Comprueba que señala correctamente cuando la microinstrucción no está escrita correctamente en un paso simple.")
    void MICROINSTRUCCION_NO_RECONOCIDA1(){
        String inputText = """
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |SR+1->SR| GOR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_NO_RECONOCIDA));
    }

    @Test
    @DisplayName("Comprueba que señala correctamente cuando la microinstrucción no está escrita correctamente en un paso complejo.")
    void MICROINSTRUCCION_NO_RECONOCIDA2(){
        String inputText = """
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                {
                    F: |SR+1->SR| GOR->PC;
                    !F: |SR+1->SR|;
                }
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
    void MICROINSTRUCCION_NO_RECONOCIDA3(String microinstruction){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |SR+1->SR| %s;
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
                .map(a ->
                       Arguments.of(a.inputName,a.inputName)
                );

    }
    @ParameterizedTest
    @MethodSource("provideInvalidMicroInstructionCombination")
    @DisplayName("Comprueba que no haya dos microinstrucciones iguales en el mismo paso simple.")
    void MICROINSTRUCCION_INVALIDA1(String microinstruccion1,String microinstruccion2){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |SR+1->SR| %s %s;
                }
            }\
            """,microinstruccion1,microinstruccion2);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        //helper.printErrors(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMicroInstructionCombination")
    @DisplayName("Comprueba que no haya dos microinstrucciones iguales en el mismo paso complejo.")
    void MICROINSTRUCCION_INVALIDA2(String microinstruccion1,String microinstruccion2){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
            
                instruccion1(){
                {
                    F: |SR+1->SR| %s %s;
                    !F: |SR+1->SR|;
                }
                }
            }\
            """,microinstruccion1,microinstruccion2);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }
    private static Stream<String> provideCableMicroInstructions() {
        return Arrays.stream(MicroInstructionEnum.values())
                .filter(microInstructionEnum ->
                        microInstructionEnum.getType() == MicroInstructionTypeEnum.cable)
                .map(microInstructionEnum ->
                        microInstructionEnum.inputName);

    }
    private static Stream<String> provideNotCableMicroInstructions() {
        return Arrays.stream(MicroInstructionEnum.values())
                .filter(microInstructionEnum ->
                        microInstructionEnum.getType() != MicroInstructionTypeEnum.cable)
                .map(microInstructionEnum ->
                        microInstructionEnum.inputName);

    }
    @ParameterizedTest
    @MethodSource("provideCableMicroInstructions")
    @DisplayName("Comprueba que no haya instrucciones de tipo cable en la parte derecha del paso simple.")
    void MICROINSTRUCCION_INVALIDA3(String microinstruccion){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |SR+1->SR| %s;
                }
            }\
            """,microinstruccion);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }

    @ParameterizedTest
    @MethodSource("provideCableMicroInstructions")
    @DisplayName("Comprueba que no haya instrucciones de tipo cable en la parte derecha del paso complejo.")
    void MICROINSTRUCCION_INVALIDA4(String microinstruccion){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    {
                        F: |SR+1->SR| %s;
                        !F: |SR+1->SR|;
                    }
                }
            }\
            """,microinstruccion);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }

    @ParameterizedTest
    @MethodSource("provideNotCableMicroInstructions")
    @DisplayName("Comprueba que no haya instrucciones de tipo no cable en la parte izquierda del paso simple.")
    void MICROINSTRUCCION_INVALIDA5(String microinstruccion){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |%s| ;
                }
            }\
            """,microinstruccion);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }
    @ParameterizedTest
    @MethodSource("provideNotCableMicroInstructions")
    @DisplayName("Comprueba que no haya instrucciones de tipo no cable en la parte izquierda del paso complejo.")
    void MICROINSTRUCCION_INVALIDA6(String microinstruccion){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    {
                        F: |%s|;
                        !F: |SR+1->SR|;
                    }
                }
            }\
            """,microinstruccion);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_INVALIDA));
    }

    private static Stream<String> provideValidFlags() {
        return Arrays.stream(FlagEnum.values())
                .map(flag ->
                        flag.inputName);

    }
    @Test
    @DisplayName("Comprueba que señala correctamente cuando la bandera no esté escrita correctamente.")
    void BANDERA_NO_RECONOCIDA1(){
        String inputText = """
            
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                   {
                     A: |SR+1->SR| GPR->PC;
                     !A: |SR+1->SR| GPR->PC;
                   }
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.BANDERA_NO_RECONOCIDA));
    }

    @ParameterizedTest
    @MethodSource("provideValidFlags")
    @DisplayName("Comprueba que todas las banderas se reconozcan de forma correcta.")
    void BANDERA_NO_RECONOCIDA2(String flag){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    {
                     %s: |SR+1->SR| GPR->PC;
                     !%s: |SR+1->SR| GPR->PC;
                    }
                }
            }\
            """,flag,flag);
        var helper = new Runner();
        assertDoesNotThrow(() -> helper.run(inputText, repertorio,null));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Comprueba que las microinstrucciones en pasos simples que necesiten de argumento, lo reciban.")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO(String argument){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |SR+1->SR| LOAD_SC%s;
                }
            }\
            """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    @DisplayName("Comprueba que las microinstrucciones en pasos complejos que necesiten de argumento, lo reciban.")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO2(String argument){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                {
                    F: |SR+1->SR| LOAD_SC%s;
                    !F: |SR+1->SR| GPR->PC;
                }
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
    @DisplayName("Comprueba que las microinstrucciones en pasos simples que no necesitan de argumento, no lo reciban.")
    void MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO(String argument){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                |SR+1->SR| GPR->PC%s;
                }
            }\
            """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"(0)", "(1)"})
    @DisplayName("Comprueba que las microinstrucciones en pasos complejos que no necesitan de argumento, no lo reciban.")
    void MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO2(String argument){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                {
                F: |SR+1->SR| GPR->PC%s;
                !F: |SR+1->SR| GPR->PC;
                }
                }
            }\
            """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO));
    }
    @ParameterizedTest
    @ValueSource(strings = {"256"})
    @DisplayName("Comprueba que el argumento pasado a LOAD SC no supere el límite de 8 bits en paso simple")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO3(String argument){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    |SR+1->SR| LOAD_SC(%s);
                }
            }\
            """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"256"})
    @DisplayName("Comprueba que el argumento pasado a LOAD SC no supere el límite de 8 bits en paso complejo")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO4(String argument){
        String inputText = String.format("""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    {
                        F: |SR+1->SR| LOAD_SC(%s);
                        !F: |SR+1->SR|;
                    }
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
    @DisplayName("Comprueba que el argumento pasado a LOAD SR no supere el límite de pasos de la instrucción en la que está en paso simple")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO5(){
        String inputText ="""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                    |LOAD_SR(7)|; //el valor que se podrá poner sera 0 al 6 al tener 7 pasos
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO));
    }

    @Test
    @DisplayName("Comprueba que el argumento pasado a LOAD SR no supere el límite de pasos de la instrucción en la que está en paso complejo")
    void MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO6(){
        String inputText ="""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                    {
                        F:  |LOAD_SR(7)|; //el valor que se podrá poner sera 0 al 6 al tener 7 pasos
                        !F: |SR+1->SR|;
                    }
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                    |SR+1->SR| GPR->PC;
                }
            }\
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO));
    }

    @Test
    @DisplayName("Comprueba que el número de instrucciones no supere a 32, por el tamaño dedicado a instrucciones en la celdas de memoria (5 bits).")
    void NUMERO_INSTRUCCIONES_SUPERADO(){
        //
        String instrucciones = IntStream.range(1, 33+1)
                .mapToObj(i -> String.format("""
                        instruccion%s(){
                            |SR+1->SR| GPR->PC;
                        }
                        """,i))
                .reduce("",String::concat);

        String inputText = String.format("""

            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
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

    @Test
    @DisplayName("Comprueba que no se haga referencia a la misma bandera varias veces en la misma condición.")
    void PASO_COMPLEJO_NO_EXHAUSTIVO(){
        String inputText ="""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    |SR+1->SR| GPR->PC;
                    {
                        F !F:  |SR+1->SR|;
                        !F: |SR+1->SR|;
                    }
                }
            }\
            """;
        var helper = new Runner();
        assertDoesNotThrow(() ->helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.PASO_COMPLEJO_NO_EXHAUSTIVO));
    }
    @Test
    @DisplayName("Comprueba que no haya dos condiciones iguales definidas.")
    void PASO_COMPLEJO_NO_EXHAUSTIVO2(){
        String inputText ="""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    |SR+1->SR| GPR->PC;
                    {
                        F Zsc:  |SR+1->SR|;
                        F: |SR+1->SR|;
                        !F: |SR+1->SR|;
                    }
                }
            }\
            """;
        var helper = new Runner();
        assertDoesNotThrow(() -> helper.run(inputText, repertorio,null) );

        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.PASO_COMPLEJO_NO_EXHAUSTIVO));
    }

    @Test
    @DisplayName("Comprueba que no haya condiciones sin definir.")
    void PASO_COMPLEJO_NO_EXHAUSTIVO3(){
        String inputText ="""
            @Cable
            Instrucciones {
                Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(){
                    |SR+1->SR| GPR->PC;
                    {
                        F Zsc:  |SR+1->SR|;
                    }
                }
            }\
            """;
        var helper = new Runner();
        assertDoesNotThrow(() ->helper.run(inputText, repertorio,null));

        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.PASO_COMPLEJO_NO_EXHAUSTIVO));
    }


}
