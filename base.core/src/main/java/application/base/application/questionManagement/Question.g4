grammar Question;

question: INITQUESTION questionType SEPARATOR quotation INITQUESTION;

quotation : NUM;

questionType: matching
        | missingWord
        | multipleChoice
        | numeric
        | short
        | trueOrFalse
        ;



matching: 'MatchingQuestion' SEPARATOR matchingText SEPARATOR matchingAnswers;

matchingText: TEXT SEPARATOR matchingOptions SEPARATOR matchingOptions;

matchingOptions: TEXT ',' matchingOptions
    | TEXT
    ;

matchingAnswers: matchingAnswer ',' matchingAnswer
    | matchingAnswer;

matchingAnswer: TEXT ARROW TEXT;



missingWord: 'MissingWordQuestion' SEPARATOR missingWordText SEPARATOR missingWordAnswer;

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



multipleChoice: 'MultipleChoiceQuestion' SEPARATOR multipleChoiceText SEPARATOR multipleChoiceAnswer;

multipleChoiceText: TEXT QUESTION? SEPARATOR multipleChoiceOption;

multipleChoiceOption: TEXT ',' multipleChoiceOption
    | TEXT
    ;

multipleChoiceAnswer: TEXT
    | TEXT ',' multipleChoiceAnswer;



numeric: 'NumericQuestion' SEPARATOR numericText SEPARATOR numericAnswer;

numericText: TEXT QUESTION? ;

numericAnswer: NUM ;



short: 'ShortQuestion' SEPARATOR shortText SEPARATOR shortAnswer;

shortText: TEXT QUESTION? ;

shortAnswer: TEXT;



trueOrFalse: 'TrueFalseQuestion' SEPARATOR trueOrFalseText QUESTION? SEPARATOR trueOrFalseAnswer;

trueOrFalseText: TEXT QUESTION? ;

trueOrFalseAnswer: 'True'
    |'False'
    ;




INITQUESTION: '??';
QUESTION: '?';
SEPARATOR: ';';
NUM: [0-9]+ ;
TEXT: [A-Za-z0-9 ]+ ;
ARROW: '->' ;
GAPSPACE: '***' ;
WS : [ \t\r\n]+ -> skip ;
