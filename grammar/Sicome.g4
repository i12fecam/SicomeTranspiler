grammar Sicome;
import Cable,Micro_prog;
program: instructionBlockCable (variablesBlock programBlock | programBlock variablesBlock)? #cableProgram
    | statusLogicBlock instructionBlockMicro? (variablesBlock programBlock | programBlock variablesBlock)? #microProgram
    ;



variablesBlock: 'Variables' '{' variableDeclaration* '}' ;
variableDeclaration: id=IDENTIFIER '=' value=MEMORYVALUE              ';'  #simpleVariableDeclaration
                   | id=IDENTIFIER '(' size=DECNUMBER ')'  '=' '{' value+=MEMORYVALUE (',' value+=MEMORYVALUE )* '}' ';' #vectorVariableDeclaration
                   ;

programBlock: 'Programa' '{' programLine* '}' ;
programLine: name=IDENTIFIER arg=instructionUseArgument ';'#instructionUse
            | 'MARK' label=IDENTIFIER  ';' #markUse
            ;
instructionUseArgument: var=IDENTIFIER ('('offset=DECNUMBER')')?
                      | num=MEMORYVALUE
                      |
                      ;


