grammar Cable;
import Base;

instructionBlockCable: '@Cable' 'Instrucciones''{' fetchDefinitionCable instructionCable+ '}';

instructionCable: IDENTIFIER '(' arg=('Value'|'Dir'|'Var')? ')' nSteps=DECNUMBER? '{' stepCable* '}';

fetchDefinitionCable: 'Fetch' '{' stepCable+ '}';

stepCable: '|' linstr=permissibleLOADSRMicroIntr '|' rinstr+=microIntr* ';'  #simpleCableStep
    | '{'conditionalStepCable+ '}'                         #conditionalCableStepBlock
    ;

conditionalStepCable: flags+=FLAG+ ':' '|' linstr=permissibleLOADSRMicroIntr '|' rinstr+=microIntr* ';' ;
