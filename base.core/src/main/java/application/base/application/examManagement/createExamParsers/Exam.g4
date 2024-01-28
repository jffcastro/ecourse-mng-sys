grammar Exam;

stat: INIT title SEPARATOR description SEPARATOR openDate SEPARATOR closeDate SEPARATOR language SEPARATOR header SEPARATOR structure INIT;

title: TEXT;

description: TEXT;

openDate: date;

closeDate: date;

date: NUM SLASH NUM SLASH NUM;

language: TEXT;

header: headerDescription SEPARATOR feedbackType SEPARATOR gradeType;

headerDescription: TEXT;

feedbackType: 'NONE' | 'ON_SUBMISSION' | 'AFTER_CLOSING';

gradeType: 'NONE' | 'ON_SUBMISSION' | 'AFTER_CLOSING';

structure : section+;

section: SECTIONOPEN TEXT SEPARATOR sectionStructure SECTIONCLOSE;

sectionStructure: question+;

question: INITQUESTION questiontype INITQUESTION;

questiontype: NUM SEPARATOR matching
        | NUM SEPARATOR missingWord
        | NUM SEPARATOR multipleChoice
        | NUM SEPARATOR numeric
        | NUM SEPARATOR short
        | NUM SEPARATOR trueOrFalse
        ;

quotation: NUM;

matching: 'MatchingQuestion' SEPARATOR matchingText SEPARATOR matchingAnswers SEPARATOR quotation;

matchingText: TEXT SEPARATOR matchingOption SEPARATOR matchingOption;

matchingOption: TEXT ',' matchingOption
    | TEXT
    ;

matchingAnswers: matchingAnswer ',' matchingAnswer
    | matchingAnswer
    ;

matchingAnswer: TEXT ARROW TEXT;



missingWord: 'MissingWordQuestion' SEPARATOR missingWordText SEPARATOR missingWordAnswer SEPARATOR quotation;

missingWordText: mwText SEPARATOR missingWordOptions;

mwText: GAPSPACE TEXT mwText
    | TEXT GAPSPACE mwText
    | TEXT GAPSPACE
    | GAPSPACE TEXT
    | TEXT GAPSPACE TEXT
    ;

missingWordOptions: missingWordOption ',' missingWordOption
    | missingWordOption;

missingWordOption: TEXT '-' missingWordOption
    | TEXT
    ;

missingWordAnswer: TEXT
    | TEXT ',' missingWordAnswer;



multipleChoice: 'MultipleChoiceQuestion' SEPARATOR multipleChoiceText SEPARATOR multipleChoiceAnswer SEPARATOR quotation;

multipleChoiceText: TEXT QUESTION? SEPARATOR multipleChoiceOption;

multipleChoiceOption: TEXT ',' multipleChoiceOption
    | TEXT
    ;

multipleChoiceAnswer: TEXT;



numeric: 'NumericQuestion' SEPARATOR numericText SEPARATOR numericAnswer SEPARATOR quotation;

numericText: TEXT QUESTION?;

numericAnswer: NUM;



short: 'ShortQuestion' SEPARATOR shortText SEPARATOR shortAnswer SEPARATOR quotation;

shortText: TEXT QUESTION? ;

shortAnswer: TEXT;



trueOrFalse: 'TrueFalseQuestion' SEPARATOR trueOrFalseText SEPARATOR trueOrFalseAnswer SEPARATOR quotation;

trueOrFalseText: TEXT QUESTION? ;

trueOrFalseAnswer: 'True'
    |'False'
    ;



INIT: '"' ;
INITQUESTION: '??' ;
SEPARATOR: ';' ;
SECTIONOPEN: '<<' ;
SECTIONCLOSE: '>>' ;
NUM: [0-9]+;
TEXT: [A-Za-z0-9 ]+;
SLASH: '/';
ARROW: '->';
GAPSPACE: '***';
QUESTION:'?';
WS : [ \t\r\n]+ -> skip ;