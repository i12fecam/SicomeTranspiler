package Internals.Errors;
import static Internals.Errors.ErrorLevelEnum.*;
public enum ErrorEnum {
    FALTA_BLOQUE_NECESARIO(FATAL_ERROR,"Falta bloque/s necesarios para el objetivo definido (%s)"),
    INSTRUCCION_MISMO_NOMBRE(FATAL_ERROR,"Ya existe otra instrucción con el mismo nombre (%s)"),
    VARIABLE_MISMO_NOMBRE(FATAL_ERROR,"Ya existe otra variable con el mismo nombre (%s)"),
    TAMANYO_VECTOR_INVALIDO(FATAL_ERROR,"El tamaño del vector definido es inválido (%s)"),
    INICIALIZACION_VECTOR_INVALIDA(FATAL_ERROR,"El numero de valores inicializados no coinciden con el tamaño del vector, o no es 1 para indicar valor predeterminado"),
    ETIQUETA_MISMO_NOMBRE(FATAL_ERROR,"Ya existe otra etiqueta con el mismo nombre (%s)"),
    MICROINSTRUCCION_NO_RECONOCIDA(FATAL_ERROR,"No se ha podido reconocer la instrucción (%s)"),
    MICROINSTRUCCION_INVALIDA(FATAL_ERROR,"La microinstrucción %s utilizada es inválida en este contexto (%s)"),
    BANDERA_NO_RECONOCIDA(FATAL_ERROR,"No se ha podido reconocer la bandera (%s)"),
    BANDERA_INVALIDA(FATAL_ERROR, "No esta permitida la bandera %s en este contexto"),
    NUMERO_LOGICA_BIFURCACION_SUPERADO(FATAL_ERROR,"Se ha superado el número máximo de lógicas de bifurcación permitida por la arquitectura (16)"),
    LOGICA_BIFURCACION_MISMO_NOMBRE(FATAL_ERROR,"Ya existe otra lógica de bifurcación con el mismo nombre (%s)"),
    LOGICA_BIFURCACION_NO_DEFINIDA(FATAL_ERROR,"Se ha querido utilizar una lógica de bifurcación que no se encuentra definida (%s)"),
    ARGUMENTO_USO_LOGICA_BIFURCACION_INVALIDO(FATAL_ERROR,"La lógica de bifurcación (%s) esperaba un argumento"),
    MICROINSTRUCCION_CON_ARGUMENTO_INVALIDO(FATAL_ERROR,"La microinstrucción  %s necesita de un argumento valido (%s)"),
    MICROINSTRUCCION_CON_ARGUMENTO_INNECESARIO(WARNING,"La microinstrucción %s tiene un argumento no necesario"),
    INSTRUCCION_NO_DEFINIDA(FATAL_ERROR,"La instrucción %s no se encuentra definida"),
    ARGUMENTO_DE_TIPO_VALOR_NO_ENCONTRADO(FATAL_ERROR,"La llamada a la instrucción %s requiere de tipo valor pero ha recibido un argumento invalido (%s)"),
    INDICE_ARGUMENTO_VECTOR_INVALIDO(FATAL_ERROR,"El índice del acceso al vector %s es inválido"),
    ARGUMENTO_DE_TIPO_VARIABLE_NO_ENCONTRADO(FATAL_ERROR,"La llamada a la instrucción %s requiere de tipo variable pero ha recibido un argumento invalido (%s)"),
    ARGUMENTO_DE_TIPO_DIRECCION_NO_ENCONTRADO(FATAL_ERROR,"La llamada a la instrucción %s requiere de tipo dirección pero ha recibido un argumento invalido (%s)"),
    ARGUMENTO_INSTRUCCION_INNECESARIO(FATAL_ERROR,"La llamada a la instrucción %s no requiere de argumentos pero ha recibido el argumento %s"),
    VARIABLE_NO_DEFINIDA(FATAL_ERROR,"La variable %s no se encuentra definida"),
    ETIQUETA_NO_DEFINIDA(FATAL_ERROR,"La etiqueta %s no se encuentra definida"),
    LOGICA_CONTROL_NO_EXHAUSTIVA(BIG_WARNING,"La combinación de banderas definidas está mal formada (%s)"),
    PASO_COMPLEJO_NO_EXHAUSTIVO(BIG_WARNING,"La combinación de banderas definidas esta mal formada (%s)"),
    ESPACIO_MEMORIA_SUPERADO(BIG_WARNING,"La definición del programa (variables + llamadas a instrucciones) supera el máximo de espacio de memoria de la computadora"),
    VALOR_VARIABLE_NO_VALIDO(FATAL_ERROR,"El valor de la variable %s supera el tamaño máximo de la celda de memoria (16bits)"),
    VALOR_ARGUMENTO_LITERAL_NO_VALIDO(FATAL_ERROR,"El valor del argumento literal pasado supera el tamaño máximo de la celda de memoria (11bits)"),

    DEFINICION_INSTRUCCION_VACIA(WARNING,"La instrucción no tiene definido ningún paso"),
    TAMANYO_ROM_SUPERADO(FATAL_ERROR,"El uso de la ROM por parte de los pasos de las instrucciones se ha superado (256 lineas)"),
    NUMERO_INSTRUCCIONES_SUPERADO(FATAL_ERROR,"El número de instrucciones máximo se ha superado de 32, por el tamaño dedicado a instrucciones en la celdas de memoria (5 bits) "),
    ANTLR4(ANTLR4_FATAL_ERROR,"%s");
    public final ErrorLevelEnum level;
    public final String msgFormat;
    ErrorEnum(ErrorLevelEnum level, String msg){
        this.level = level;
        this.msgFormat = msg;
    }
}
