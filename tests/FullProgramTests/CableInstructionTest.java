package FullProgramTests;

import Runner.Runner;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

import static Runner.ObjetiveConfig.repertorio;
import static org.junit.jupiter.api.Assertions.*;

public class CableInstructionTest {



    @Test
    public void fetchTest(){
        String inputText = """
                @Cable
                Instrucciones {
                    Fetch{
                        |SR+1->SR| PC->MAR;
                        |SR+1->SR| M->GPR PC+1->PC;
                        |SR+1->SR| GPR[OP]->OPR GPR[AD]->MAR;
                    }
                    
                    instruccion1(){
                     |SR+1->SR| PC+1->PC;
                     |LOAD_SR (START)| GPR->PC;
                    }
                }
                Variables{}
                Programa{}
                """;

        String outputRepositoryText = "instruccion1 false 2 q0\n";

        String outputLogicText = """
                $
                M->GPR:t1
                GPR(OP)->OPR:t2
                PC->MAR:t0
                GPR(AD)->MAR:t2
                PC+1->PC:t1 + t3·q0
                GPR->PC:t4·q0
                $
                SR+1->SR:t0 + t1 + t2 + t3·q0
                LOAD SR:t4·q0-0
                $
                """;

        Runner helper = new Runner();

        helper.run(inputText);

        System.out.println(helper.getLogicText());
        assertCableLogic(helper.getLogicText(),outputLogicText);

        assertEquals(outputRepositoryText,helper.getRepositoryText());
    }



    @Test
    void p3_2526(){
        String inputText = """
                @Cable
                Instrucciones{
                    Fetch{
                        |SR+1->SR| PC->MAR;
                         |SR+1->SR| M->GPR PC+1->PC;
                         |SR+1->SR| GPR[OP]->OPR;
                    }
                    
                    
                    /*
                    Carga del registro ACC con el contenido de la dirección de memoria
                    indicada
                    */
                    lda(Dir){
                        |SR+1->SR| GPR[AD]->MAR;
                        |SR+1->SR| M->GPR 0->ACC;
                        |LOAD_SR(START)| ACC+GPR->ACC;
                    }
                    
                    
                    /*Carga del registro QR con el contenido de la dirección de memoria
                      indicada */
                    ldq(Dir){
                        |SR+1->SR| GPR[AD]->MAR;
                        |SR+1->SR| M->GPR;
                        |LOAD_SR(START)| GPR->QR;
                    }
                    
                    
                    /* Almacena el contenido del registro ACC en la dirección de memoria
                       indicada.*/
                    sta(Dir){
                        |SR+1->SR| GPR[AD]->MAR;
                        |SR+1->SR| ACC->GPR;
                        |LOAD_SR(START)| GPR->M;
                    }
                    
                    
                    /* Calcula el valor absoluto del valor almacenado en la dirección de memoria
                       indicada y guarda el resultado en la misma dirección.
                        Si se utiliza el registro ACC,debe quedar como estaba. */
                    abs(Dir){
                        |SR+1->SR| GPR[AD]->MAR;
                        |SR+1->SR| M->QR;
                        //Aquí nos tenemos que ir a otro ciclo, porque no debo consultar Qs (bit más significativo de QR) y modificar QR en el mismo ciclo
                        {
                            Qs: |SR+1->SR|;
                            !Qs:|LOAD_SR(START)|;
                        }
                        |SR+1->SR| !QR+1->QR;
                        |LOAD_SR(START)| GPR->M;
                    }
                    
                    
                    /*Suma el valor inmediato codificado en la instrucción en los 11 bits menos
                      significativos de la propia instrucción (v) al contenido del acumulador. Se debe
                      implementar la instrucción haciendo rotaciones entre los registros F, ACC y QR; ya
                      que el valor inmediato puede ser positivo o negativo. El registro QR debe quedar
                      como estaba*/
                    addv11(Value){
                        |SR+1->SR| SP-1->SP LOAD_SC(2); //0
                        //El registro SR se puede cargar aquí o un poco más adelante, no importa. Lo pongo en la columna CONTROL porque es un regsitro del circuito de control. Si lo ponen en la columna de microoperaciones, no se tiene en cuenta.
                        |SR+1->SR| SP->MAR; //1
                        //Guardo QR en pila y "Libero" ya la posición del SP, si os fijáis, MAR ya no se toca en todo el algoritmo.
                        |SR+1->SR| QR->M SP+1->SP; //2
                        //Son dos buses diferentes, puedo llevar ACC a GPR y GPR a QR sin problema. Además, son dos decodificadores diferentes.
                        |SR+1->SR| GPR->QR ACC->GPR; //3
                        |SR+1->SR| ROL_F_ACC_QR SC-1->SC; //4
                        { //5
                            !Zsc:|LOAD_SR(4)|;
                            Zsc :|SR+1->SR| 0->ACC;
                        }
                        { //6
                            Qs  :|SR+1->SR| !ACC->ACC LOAD_SC(3);
                            !Qs :|SR+1->SR| LOAD_SC(3);
                        }
                        |SR+1->SR| ROR_F_ACC_QR SC-1->SC; //7
                        { //8
                            !Zsc :|LOAD_SR(10)|;
                            Zsc  :|SR+1->SR| 0->ACC;
                        }
                        //GPR tiene lo que había en ACC
                        |SR+1->SR| ACC+GPR->ACC QR->GPR; //9
                        //Aquí, GPR tiene el valor inmediato codificado en 16 bits. Restauro QR de la pila
                        |LOAD_SR(START)| ACC+GPR->ACC M->QR; //10
                    }
                    mul(Dir){
                        |SR+1->SR| GPR[AD]->MAR;//0
                        //Aquí ya tengo el multiplicando en GPR (BR y GPR es lo mismo, según la literatura se llama de una forma u otra). EL multiplicador ya está en QR.\s
                        |SR+1->SR| M->GPR;//1
                        //Como no se especifica lo contrario, esta instrucción borra lo que tenga el acumulador.
                        |SR+1->SR| 0->ACC 0->Qn+1 LOAD_SC(16);//2
                        {//3
                            !Qn !Qn+: |LOAD_SR(5)|;
                            !Qn Qn+: |SR+1->SR|;
                            Qn !Qn+: |LOAD_SR(6)|;
                            Qn Qn+: |LOAD_SR(6)|;
                        }
                        |LOAD_SR(6)| ACC+GPR->ACC;
                        |SR+1->SR| !GPR+1+ACC->ACC;
                        |SR+1->SR| SHR_F_ACC_QR SC-1->SC;
                        {
                            !Zsc: |LOAD_SR(3)|;
                            Zsc: |LOAD_SR(START)|;
                        }
                    }
                    
                    
                    
                    /*Realizar desplazamientos cíclicos a la izquierda del registro
                      conjunto F|ACC hasta detectar un 1 en el registro F. Guardar el número de
                      desplazamientos realizados en la dirección de memoria dir. Si no hubiese ningún 1 en
                      el acumulador, almacenar 0. Al finalizar, el registro acumulador y el registro QR
                      deben quedar como estaban*/
                    rol_1_f_acc(Dir){
                        |SR+1->SR| GPR[AD]->MAR;
                        |SR+1->SR| QR->M;
                        //Aquí compruebo si todos los bits de acumulador son 0, en cuyo caso escribo un 0 en memoria y me voy (evitando modificar QR)
                        {
                            !Zac: |LOAD_SR(6)| 0->QR;
                            Zac:  |SR+1->SR| 0->QR;
                        }
                        |SR+1->SR| 0->QR;
                        |SR+1->SR| M->GPR QR->M;
                        |LOAD_SR(START)| GPR->QR;
                        //Hago un bucle de 17 iteraciones para dejar el acumulador como estaba, ya que acc y f suman un registro de 16+1
                        |SR+1->SR| QR->GPR M->QR LOAD_SC(17);
                        //La primera salida del bucle la hago según F, ya que sé que, al menos un 1 hay.
                        |SR+1->SR| ROL_F_ACC GPR+1->GPR SC-1->SC;
                        {
                           !F: |LOAD_SR(7)|;
                            F: |SR+1->SR|;
                        }
                        //Cuando termino el primer bucle (he encontrado el 1), sigo iterando hasta que se complete la vuelta entera, esto hace que el ACC se quede inalterado.\s
                        |SR+1->SR| ROL_F_ACC SC-1->SC;
                        {
                            !Zsc: |LOAD_SR(9)|;
                             Zsc: |LOAD_SR(START)|;
                        }
                    }
                    
                    
                    
                    
                    /*suma el valor almacenado en las posiciones de memoria consecutivas dir
                    y dir+1 al contenido del registro compuesto Acc y QR*/
                    addaq(Var){
                        /*
                          Quiero sumar V(msb)|V(lsb) + M(msb)|V(lsb).
                          El problema es que tengo que sumar primero la parte menos signficativa (lsb),
                          porque si hay un acarreo lo tengo que sumar a la parte más significativa (msb)
                          
                          Acc y QR son V(msb) y V(lsb) respectivamente.
                          En la dirección dir tengo V(msb) y en dir+1 V(lsb)
                        */
                        |SR+1->SR| SP-1->SP;
                        |SR+1->SR| SP->MAR;
                        
                        |SR+1->SR| GPR->M SP-1->SP; //Pila : [ DIR M(msb) ]
                        |SR+1->SR| SP->MAR ACC->GPR;
                        |SR+1->SR| GPR->M QR->GPR 0->ACC; //Pila : [ DIR M(msb),  V(msb) ], Suma: 0, GPR: V(lsb)
                        |SR+1->SR| ACC+GPR->ACC SP+1->SP; //Suma: V(lsb), Pila:  : [ DIR M(msb),  V(msb) ]
                        |SR+1->SR| SP->MAR;
                        |SR+1->SR| M->GPR; //GPR: DIR M(msb)
                        |SR+1->SR| GPR+1->GPR; //GPR: DIR M(lsb)
                        |SR+1->SR| GPR[AD]->MAR;
                        |SR+1->SR| M->GPR; //GPR: M(lsb)
                        |SR+1->SR| ACC+GPR->ACC; //Suma: V(lsb) + M(lsb)
                        |SR+1->SR| ACC->GPR;
                        |SR+1->SR| GPR->QR 0->ACC SP->MAR; //Esa suma la llevo a QR y vuelvo a 0.
                        |SR+1->SR| ROL_F_ACC M->GPR; //Con ese ROL, Suma : 0 si no hay acarreo, 1 si hay acarreo.
                        |SR+1->SR| GPR[AD]->MAR; //GPR: DIR M(msb)
                        |SR+1->SR| M->GPR;  //GPR: M(msb);
                        |SR+1->SR| ACC+GPR->ACC SP-1->SP; //Suma: acarreo + M(msb), Pila:  : [ DIR M(msb),  V(msb) ]
                        |SR+1->SR| SP->MAR;
                        |SR+1->SR| M->GPR SP+1->SP; //GPR: V(msb). Pila:  : [ DIR M(msb),  V(msb) ]
                        |LOAD_SR(START)| ACC+GPR->ACC SP+1->SP; //Pila: Liberada
                    }
                    
                    
                    
                }
                
                Variables{
                        var1 = 0s0;
                        var2 = 0s1;
                    }
                    
                Programa{
                    addaq var1;
                }
                
                """;

        String outputRepositoryText = """
                lda true 3 q0
                ldq true 3 q1
                sta true 3 q2
                abs true 5 q3
                addv11 true 11 q4
                mul true 8 q5
                rol_1_f_acc true 11 q6
                addaq true 21 q7
                """;

        String outputLogicText = """
                $
                 QR->M:t5·q4 + t4·q6 + t7·q6
                 ROR_F-ACC_QR:t10·q4
                 QR'+1->QR:t6·q3
                 M->GPR:t1 + t4·q0 + t4·q1 + t4·q5 + t7·q6 + t10·q7 + t13·q7 + t17·q7 + t19·q7 + t22·q7
                 !ACC->ACC:t9·q4·Qs
                 GPR(OP)->OPR:t2
                 QR->GPR:t12·q4 + t9·q6 + t7·q7
                 PC->MAR:t0
                 GPR+ACC->GPR:t5·q0 + t12·q4 + t13·q4 + t7·q5 + t8·q7 + t14·q7 + t20·q7 + t23·q7
                 SP-1->SP:t3·q4 + t3·q7 + t5·q7 + t20·q7
                 ACC->GPR:t4·q2 + t6·q4 + t6·q7 + t15·q7
                 0->Qn+1:t5·q5
                 0->ACC:t4·q0 + t8·q4·Zsc + t11·q4·Zsc + t5·q5 + t7·q7 + t16·q7
                 0->QR:t5·q6·Zac' + t5·q6·Zac + t6·q6
                 GPR(AD)->MAR:t3·q0 + t3·q1 + t3·q2 + t3·q3 + t3·q5 + t3·q6 + t12·q7 + t18·q7
                 GPR'+1+ACC->ACC:t8·q5
                 ROL_F_ACC_QR:t7·q4
                 GPR->QR:t5·q1 + t6·q4 + t8·q6 + t16·q7
                 SP+1->SP:t5·q4 + t8·q7 + t22·q7 + t23·q7
                 SHR_F_ACC_QR:t9·q5
                 SP->MAR:t4·q4 + t4·q7 + t6·q7 + t9·q7 + t16·q7 + t21·q7
                 GPR+1->GPR:t10·q6 + t11·q7
                 M->QR:t4·q3 + t13·q4 + t9·q6
                 ROL_F_ACC:t10·q6 + t12·q6 + t17·q7
                 PC+1->PC:t1
                 GPR->M:t5·q2 + t7·q3 + t5·q7 + t7·q7
                 $
                 LOAD SC:t3·q4-2 + t9·q4·Qs-3 + t9·q4·Qs'-3 + t5·q5-16 + t9·q6-17
                 SC-1->SC:t7·q4 + t10·q4 + t9·q5 + t10·q6 + t12·q6
                 LOAD SR:t5·q0-0 + t5·q1-0 + t5·q2-0 + t5·q3·Qs'-0 + t7·q3-0 + t8·q4·Zsc'-6 + t11·q4·Zsc'-12 + t13·q4-0 + t6·q5·Qn'·Qn_plus'-7 + t6·q5·Qn·Qn_plus'-8 + t6·q5·Qn·Qn_plus-8 + t7·q5-8 + t10·q5·Zsc'-5 + t10·q5·Zsc-0 + t5·q6·Zac'-8 + t8·q6-0 + t11·q6·F'-9 + t13·q6·Zsc'-11 + t13·q6·Zsc-0 + t23·q7-0
                 SR+1->SR:t0 + t1 + t2 + t3·q0 + t4·q0 + t3·q1 + t4·q1 + t3·q2 + t4·q2 + t3·q3 + t4·q3 + t5·q3·Qs + t6·q3 + t3·q4 + t4·q4 + t5·q4 + t6·q4 + t7·q4 + t8·q4·Zsc + t9·q4·Qs + t9·q4·Qs' + t10·q4 + t11·q4·Zsc + t12·q4 + t3·q5 + t4·q5 + t5·q5 + t6·q5·Qn'·Qn_plus + t8·q5 + t9·q5 + t3·q6 + t4·q6 + t5·q6·Zac + t6·q6 + t7·q6 + t9·q6 + t10·q6 + t11·q6·F + t12·q6 + t3·q7 + t4·q7 + t5·q7 + t6·q7 + t7·q7 + t8·q7 + t9·q7 + t10·q7 + t11·q7 + t12·q7 + t13·q7 + t14·q7 + t15·q7 + t16·q7 + t17·q7 + t18·q7 + t19·q7 + t20·q7 + t21·q7 + t22·q7
                 $
                """;


        Runner helper = new Runner();
        try {
            helper.run(inputText, repertorio,null);

        } catch (RuntimeException e) {
            helper.printErrorsToErr(true);

        }
        System.err.println(inputText);


        //assertEquals(outputRepositoryText,helper.getRepositoryText());
        //assertEquals(outputLogicText,helper.getLogicText());
    }






    public void assertCableLogic(String received, String expected){
        String[] receivedSection = received.split("$");
        String[] expectedSection = expected.split("$");

        //Se comprueba sección 1
        {
            String[] lineReceivedSection = receivedSection[0].split("\n");
            String[] lineExpectedSection = expectedSection[0].split("\n");
            for (String lineExpected : lineExpectedSection) {
                assertTrue(Arrays.asList(lineReceivedSection).contains(lineExpected), "Linea no encontrada->" + " " + lineExpected);
            }
        }

       //Se comprueba sección 2
        {
            String[] lineReceivedSection = receivedSection[1].split("\n");
            String[] lineExpectedSection = expectedSection[1].split("\n");
            for (String lineExpected : lineExpectedSection) {
                assertTrue(Arrays.asList(lineReceivedSection).contains(lineExpected), "Linea no encontrada->" + " " + lineExpected);
            }
        }
    }
}
