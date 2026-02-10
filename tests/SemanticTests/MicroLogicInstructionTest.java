package SemanticTests;

import Internals.Errors.ErrorEnum;
import Internals.FlagEnum;
import Runner.Runner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static Runner.ObjetiveConfig.repertorio;
import static Runner.ObjetiveConfig.logica;
import static org.junit.jupiter.api.Assertions.*;

public class MicroLogicInstructionTest {
    //private final Runner helper = new Runner();
    @Test
    @DisplayName("Comprueba que no haya dos lógicas de bifurcación con el mismo nombre.")
    public void LOGICA_BIFURCACION_MISMO_NOMBRE(){
        String inputText = """
            Lógica{
                 inc ->  INCR
                 inc -> BIF
            }
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, logica,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.LOGICA_BIFURCACION_MISMO_NOMBRE));
    }
    @Test
    @DisplayName("Comprueba que no se ha superado el número máximo de lógicas de bifurcación permitida por la arquitectura (16).")
    public void NUMERO_LOGICA_BIFURCACION_SUPERADO(){
        String inputText = """
            Lógica{
                 inc ->  INCR
                 inc2 -> RTN
                 inc3 -> INCR
                 inc4 -> INCR
                 inc5 -> INCR
                 inc6 -> INCR
                 inc7 -> BIF
                 inc8 -> INCR
                 inc9 -> INCR
                 inc10 -> INCR
                 inc11 -> INCR
                 inc12 -> INCR
                 inc13 -> INCR
                 inc14 -> INCR
                 inc15 -> INCR
                 inc16 -> INCR
                 bif -> {
                    F : BIF
                    !F : INCR
                 }
            }
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, logica,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.NUMERO_LOGICA_BIFURCACION_SUPERADO));
    }

    @Test
    @DisplayName("Comprueba que señala correctamente cuando la bandera no este escrita correctamente.")
    void BANDERA_NO_RECONOCIDA1(){
        String inputText = """
            Lógica{
                 inc ->  INCR
                 bif_if_flag -> {
                    A : BIF
                    A : INCR
                 }
                 rtn -> RTN
            }
            """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText, logica,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.BANDERA_NO_RECONOCIDA));
    }

    private static Stream<String> provideValidFlags() {
        return Arrays.stream(FlagEnum.values())
                .map(flag ->
                        flag.inputName);

    }

    @Test
    @DisplayName("Comprueba que no se haga referencia a la misma bandera varias veces en la misma condición.")
    void LOGICA_CONTROL_NO_EXHAUSTIVA(){
        String inputText ="""
            Lógica{
                 inc ->  INCR
                 bif_if_flag -> {
                    F !F: BIF
                    !F: INCR
                 }
                 rtn -> RTN
            }
            
            """;
        var helper = new Runner();
        assertDoesNotThrow(() -> helper.run(inputText, logica,null));
        helper.printErrorsToErr(true);
        helper.containsErrorEnum(ErrorEnum.LOGICA_CONTROL_NO_EXHAUSTIVA);
    }

    @Test
    @DisplayName("Comprueba que no haya dos condiciones iguales definidas")
    void LOGICA_CONTROL_NO_EXHAUSTIVA2(){
        String inputText ="""
            Lógica{
                 inc ->  INCR
                 bif_if_flag -> {
                    F Zsc: BIF
                    F: INCR
                    !F: INCR
                 }
                 rtn -> RTN
            }
            
            """;
        var helper = new Runner();
        assertDoesNotThrow(() -> helper.run(inputText, logica,null));
        helper.printErrorsToErr(true);
        helper.containsErrorEnum(ErrorEnum.LOGICA_CONTROL_NO_EXHAUSTIVA);
    }

    @Test
    @DisplayName("Comprueba que no haya condiciones sin definir")
    void LOGICA_CONTROL_NO_EXHAUSTIVA3(){
        String inputText ="""
            Lógica{
                 inc ->  INCR
                 bif_if_flag -> {
                    F : BIF
                 }
                 rtn -> RTN
            }
            
            """;
        var helper = new Runner();
        assertDoesNotThrow(() -> helper.run(inputText, logica,null));
        helper.printErrorsToErr(true);
        helper.containsErrorEnum(ErrorEnum.LOGICA_CONTROL_NO_EXHAUSTIVA);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Ovf"})
    @DisplayName("Comprueba que no se utiliza bandera prohibidas en microprogramado")
    void BANDERA_INVALIDA(String argument){
        String inputText = String.format("""
            Lógica{
                inc -> INCR
                rtn -> RTN
                instruccion_prohibida -> {
                    %s: INCR
                    !%s: INCR
                }
            }
            @Micro
            Instrucciones {
                Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |rtn| GPR[OP]->OPR GPR[AD]->MAR;
                }
                instruccion1(Value){
                    
                }
            }\
            """,argument,argument);
        var helper = new Runner();

        assertThrows(RuntimeException.class, () -> helper.run(inputText, repertorio,null));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.BANDERA_INVALIDA));
    }

}
