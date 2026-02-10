grammar Base;



fragment HEXNUMBER: '0x'[0-9a-fA-F]+ ;
fragment BINNUMBER: '0b'[01.]+;
fragment COMPLEMENT2NUMBER: '0c''-'?[0-9]+;
fragment SIGNMAGNITUDENUMBER: '0s''-'?[0-9]+;

MEMORYVALUE: HEXNUMBER|BINNUMBER|COMPLEMENT2NUMBER|SIGNMAGNITUDENUMBER;
DECNUMBER:[0-9]+;

FLAG: [!]?[A-Z][a-z]?[a-z+1]?;
IDENTIFIER: [a-z][a-zA-Z0-9_]*; //debe empezar por minuscula
MICRO_INSTR: [A-Z!01][a-zA-Z0-9+\-[\]>_@!]+;
microIntr: MICRO_INSTR ('(' arg=DECNUMBER ')')?;
permissibleLOADSRMicroIntr: MICRO_INSTR ('(' arg=(DECNUMBER|'START') ')')?;





LINE_COMMENT: '//' .*? '\r'? '\n' -> skip;
COMMENT : '/*' .*? '*/' -> skip ;

NEWLINE: '\r'? '\n' -> skip;
WS : [ \t]+ -> skip;