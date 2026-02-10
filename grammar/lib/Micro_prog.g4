grammar Micro_prog;
import Base;



statusLogicBlock: 'Lógica' '{' statusLogic+ '}' ;

statusLogic: name=IDENTIFIER '->' option=('INCR'|'BIF'|'RTN')? disable='DISABLE'? #simpleStatusLogic
           | name=IDENTIFIER '->' '{' statusLogicOption+ '}' #complexStatusLogic
           ;

statusLogicOption : flags+=FLAG+ ':' option=('INCR'|'BIF'|'RTN')? disable='DISABLE'? ;






instructionBlockMicro : '@Micro' 'Instrucciones''{' fetchDefinitionMicro instructionMicro+ '}';

fetchDefinitionMicro: 'Fetch' '{' stepMicro+ '}';

instructionMicro: IDENTIFIER '(' arg=('Value'|'Dir'|'Var')? ')' nSteps=DECNUMBER? '{' stepMicro* '}';

stepMicro: '|' biflogic=IDENTIFIER ('(' bifLogicArgument ')')? '|' instr+=microIntr* ';';

bifLogicArgument: instr=IDENTIFIER '~' arg=DECNUMBER #instructionBifLogicArgument
                | DECNUMBER #startBifLogicArgument
                ;