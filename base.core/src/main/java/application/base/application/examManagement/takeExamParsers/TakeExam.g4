grammar TakeExam;

stat: INIT structure INIT;

structure: section+;

section: TEXT TWO_DOTS sectionStructure;

sectionStructure: question+;

question: NUM DOT questionAnswer SEPARATOR;

questionAnswer: TEXT
    | TEXT ARROW questionAnswer
    | questionAnswer ',' questionAnswer
    | TEXT ',' questionAnswer
    | NUM;

INIT: '"' ;
INITQUESTION: '??' ;
SEPARATOR: ';' ;
NUM: [0-9]+;
TEXT: [A-Za-z0-9]+;
ARROW: '->';
SECTIONOPEN: '<<' ;
SECTIONCLOSE: '>>' ;
TWO_DOTS: ':';
DOT: '.';
WS : [ \t\r\n]+ -> skip ;