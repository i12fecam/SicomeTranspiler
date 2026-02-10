package SemanticTests;

import Internals.Errors.ErrorEnum;
import Runner.Runner;
import Runner.ObjetiveConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgramTest {

    //private final Runner helper = new Runner();
    @Test
    @DisplayName("Comprueba que no se pueda definir dos variables con el mismo nombre.")
    void VARIABLE_MISMO_NOMBRE(){
        String inputText = """
        @Cable
        Instrucciones {
        Fetch {
            |SR+1->SR| PC->MAR;
            |SR+1->SR| M->GPR PC+1->PC;
            |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
        }
        instruccion1(){}
        }
        Variables{
        variableRepetida = 0s0;
        variableRepetida = 0s5;
        }
        Programa{
        instruccion1;
        }
        """;

        Runner helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText));
        helper.printErrorsToErr(true);
        assertTrue(helper
        .containsErrorEnum(ErrorEnum.VARIABLE_MISMO_NOMBRE));

    }
    @Test
    @DisplayName("Comprueba que no se pueda definir vectores de tamaño 0.")
    void TAMANYO_VECTOR_INVALIDO1(){
        String inputText = """
        @Cable
        Instrucciones {
        Fetch {
            |SR+1->SR| PC->MAR;
            |SR+1->SR| M->GPR PC+1->PC;
            |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
        }
        instruccion1(){}
        }
        Variables{
        vector(0) = { 0s1 };
        }
        Programa{
        instruccion1;
        }
        """;

        Runner helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.TAMANYO_VECTOR_INVALIDO));

    }


    @Test
    @DisplayName("Comprueba que no se pueda definir vectores de tamaño 1.")
    void TAMANYO_VECTOR_INVALIDO2(){
        String inputText = """
        @Cable
        Instrucciones {
        Fetch {
            |SR+1->SR| PC->MAR;
            |SR+1->SR| M->GPR PC+1->PC;
            |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
        }
        instruccion1(){}
        }
        Variables{
        vector(1) = { 0s1 };
        }
        Programa{
        instruccion1;
        }
        """;

        Runner helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.TAMANYO_VECTOR_INVALIDO));

    }

    @Test
    @DisplayName("Comprueba que no se pueda inicializar el vector con un número diferente de valores al tamaño del vector.")
    void INICIALIZACION_VECTOR_INVALIDA1(){
        String inputText = """
        @Cable
        Instrucciones {
        Fetch {
            |SR+1->SR| PC->MAR;
            |SR+1->SR| M->GPR PC+1->PC;
            |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
        }
        instruccion1(){}
        }
        Variables{
        vector(3) = {0s2,0s2};
        }
        Programa{
        instruccion1;
        }
        """;

        Runner helper = new Runner();
        assertThrows(RuntimeException.class, () -> helper.run(inputText));
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INICIALIZACION_VECTOR_INVALIDA));

    }
    @Test
    @DisplayName("Comprueba que no se pueda escribir dos etiquetas con el mismo nombre.")
    void ETIQUETA_MISMO_NOMBRE(){
        String inputText = """
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(){}
                }
                Variables{}
                Programa{
                MARK label1;
                instruccion1;
                MARK label1;
                instruccion1;
                }
               """;

        Runner helper = new Runner();
        var ex = assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ETIQUETA_MISMO_NOMBRE));

    }

    @Test
    @DisplayName("Comprueba si la instrucción no está definida en cableado.")
    void INSTRUCCION_NO_DEFINIDA1(){
        String inputText = """
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(){}
                }
                Variables{
                }
                Programa{
                    instruccion2;
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_NO_DEFINIDA));
    }

    @Test
    @DisplayName("Comprueba si la instrucción no está definida en microprogramado.")
    void INSTRUCCION_NO_DEFINIDA2(){
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
                    instruccion1(){}
                }
                Variables{}
                Programa{
                    instruccion2;
                }
               """;
        var helper = new Runner();

        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INSTRUCCION_NO_DEFINIDA));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "","etiqueta1","variable1","vector1(1)"})
    @DisplayName("Comprueba que las llamadas a funciones de tipo value solo reciban valores literales.")
    void ARGUMENTO_DE_TIPO_VALOR_NO_ENCONTRADO(String argument){
        String inputText = String.format("""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Value){}
                }
                Variables{
                    variable1 = 0s1;
                    vector1(2) = {0s1};
                }
                Programa{
                    MARK etiqueta1;
                    instruccion1 %s;
                }
               """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ARGUMENTO_DE_TIPO_VALOR_NO_ENCONTRADO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"","etiqueta1","0s5"})
    @DisplayName("Comprueba que las llamadas a funciones de tipo variable solo reciban variables.")
    void ARGUMENTO_DE_TIPO_VARIABLE_NO_ENCONTRADO(String argument){
        String inputText = String.format("""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Var){}
                }
                Variables{
                    variable1 = 0s1;
                    vector1(2) = {0s1};
                }
                Programa{
                    MARK etiqueta1;
                    instruccion1 %s;
                }
               """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ARGUMENTO_DE_TIPO_VARIABLE_NO_ENCONTRADO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"","variable1","vector1(1)","0s5"})
    @DisplayName("Comprueba que las llamadas a funciones de tipo dirección solo reciban etiquetas.")
    void ARGUMENTO_DE_TIPO_DIRECCION_NO_ENCONTRADO(String argument){
        String inputText = String.format("""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Dir){}
                }
                Variables{
                    variable1 = 0s1;
                    vector1(2) = {0s1};
                }
                Programa{
                    MARK etiqueta1;
                    instruccion1 %s;
                }
               """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ARGUMENTO_DE_TIPO_DIRECCION_NO_ENCONTRADO));
    }



    @ParameterizedTest
    @ValueSource(strings = {"variable1","vector1(1)","0s5"})
    @DisplayName("Comprueba que las llamadas a funciones que no necesiten de argumento, no se le pase.")
    void ARGUMENTO_INSTRUCCION_INNECESARIO(String argument){
        String inputText = String.format("""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(){}
                }
                Variables{
                    variable1 = 0s1;
                    vector1(2) = {0s1};
                }
                Programa{
                    MARK etiqueta1;
                    instruccion1 %s;
                }
               """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ARGUMENTO_INSTRUCCION_INNECESARIO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"variable2","vector2(0)"})
    @DisplayName("Comprueba que las variables usadas estén definidas.")
    void VARIABLE_NO_DEFINIDA(String argument){
        String inputText = String.format("""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Var){}
                }
                Variables{
                    variable1 = 0s1;
                }
                Programa{
                    MARK etiqueta1;
                    instruccion1 %s;
                }
               """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.VARIABLE_NO_DEFINIDA));
    }

    @Test
    @DisplayName("Comprueba que las etiquetas usadas estén definidas.")
    void ETIQUETA_NO_DEFINIDA(){
        String inputText = """
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Dir){}
                }
                Variables{}
                Programa{
                    MARK etiqueta1;
                    instruccion1 etiqueta3;
                    MARK etiqueta2;
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ETIQUETA_NO_DEFINIDA));
    }
    @ParameterizedTest
    @ValueSource(strings = {"4","5"})
    @DisplayName("Comprueba que el índice del vector utilizado no supere el tamaño del vector.")
    void INDICE_ARGUMENTO_VECTOR_INVALIDO(String argument){
        String inputText = String.format("""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Var){}
                }
                Variables{
                    vector(4) = {0s1};
                }
                Programa{
                    instruccion1 vector(%s);
                }
               """,argument);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.INDICE_ARGUMENTO_VECTOR_INVALIDO));
    }
    @Test
    @DisplayName("Comprueba si el espacio ocupado por las variables y las instrucciones no supere 2048")
    void ESPACIO_MEMORIA_SUPERADO(){

        String inputText = """
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Var){}
                }
                Variables{
                    vector(2047) = {0s0};
                }
                Programa{
                    instruccion1 vector(5);
                    instruccion1 vector(200);

                }
               """;
        var helper = new Runner();

            helper.run(inputText);

        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.ESPACIO_MEMORIA_SUPERADO));
    }
    //TODO comprobar tb para complemento a 2
    @ParameterizedTest
    @ValueSource(strings = {"0s65535","0x10000","0b10.0000.0000.0000.0000"})
    @DisplayName("Comprueba si el valor de la variable supera el límite válido (16bits)")
    void VALOR_VARIABLE_NO_VALIDO(String valorVariable){
        String inputText = String.format( """
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(){}
                }
                Variables{
                   variable = %s;
                }
                Programa{
                }
               """,valorVariable);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.VALOR_VARIABLE_NO_VALIDO));
    }
    //TODO comprobar tb para complemento a 2
    @ParameterizedTest
    @ValueSource(strings = {"0s2048","0x800","0b1000.0000.0000"})
    @DisplayName("Comprueba si el valor de la variable supera el límite válido (11bits)")
    void VALOR_ARGUMENTO_LITERAL_NO_VALIDO(String value){
        String inputText = String.format( """
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Value){}
                }
                Variables{
                }
                Programa{
                instruccion1 %s;
                }
               """,value);
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.VALOR_ARGUMENTO_LITERAL_NO_VALIDO));
    }



    @Test
    @DisplayName("Comprueba si no está definido el bloque de instrucciones cuando se eliga analizar instrucciones en microprogramado")
    void FALTA_BLOQUE_NECESARIO(){
        String inputText ="""
               Lógica{
                    inc -> INCR
                    bif -> BIF
                    ret -> RTN
                }
                Variables{
                }
                Programa{
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.repertorio,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }

    @Test
    @DisplayName("Comprueba si no está definido el bloque de instrucciones cuando se eliga analizar todo en microprogramado")
    void FALTA_BLOQUE_NECESARIO2(){
        String inputText ="""
               Lógica{
                    inc -> INCR
                    bif -> BIF
                    ret -> RTN
                }
//               @Micro
//                Instrucciones {
//                    Fetch {
//                        |inc| PC->MAR;
//                        |inc| M->GPR PC+1->PC;
//                        |ret| GPR[OP]->OPR GPR[AD]->MAR;
//                    }
//                    instruccion1(Value){}
//                }
                Variables{
                }
                Programa{
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.todo,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }

    @Test
    @DisplayName("Comprueba si no está definido el bloque de variables y programa cuando se eliga analizar todo en microprogramado")
    void FALTA_BLOQUE_NECESARIO3(){
        String inputText ="""
               Lógica{
                    inc -> INCR
                    bif -> BIF
                    ret -> RTN
                }
               @Micro
                Instrucciones {
                    Fetch {
                        |inc| PC->MAR;
                        |inc| M->GPR PC+1->PC;
                        |ret| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Value){}
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.todo,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }

    @Test
    @DisplayName("Comprueba que no se pueda analizar solo lógica si es un programa en cableado")
    void FALTA_BLOQUE_NECESARIO4(){
        String inputText ="""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Value){}
                }
               
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.logica,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }


    @Test
    @DisplayName("Comprueba si no está definido el bloque de instrucciones cuando se eliga analizar todo en cableado")
    void FALTA_BLOQUE_NECESARIO5(){
        String inputText ="""
               
                Variables{
                }
                Programa{
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.todo,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }

    @Test
    @DisplayName("Comprueba si no está definido el bloque de instrucciones cuando se eliga analizar instrucciones en cableado")
    void FALTA_BLOQUE_NECESARIO6(){
        String inputText ="""
               
                Variables{
                }
                Programa{
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.repertorio,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }

    @Test
    @DisplayName("Comprueba si no está definido el bloque de programa y variable cuando se eliga analizar todo en cableado")
    void FALTA_BLOQUE_NECESARIO7(){
        String inputText ="""
               @Cable
                Instrucciones {
                    Fetch {
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    instruccion1(Value){}
                }
               """;
        var helper = new Runner();
        assertThrows(RuntimeException.class, () -> {
            helper.run(inputText, ObjetiveConfig.todo,null);
        });
        helper.printErrorsToErr(true);
        assertTrue(helper
                .containsErrorEnum(ErrorEnum.FALTA_BLOQUE_NECESARIO));
    }



}
