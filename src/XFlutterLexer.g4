lexer grammar XFlutterLexer;

AA: '&&';
C: ',';
CB: ']';
CBC: '}';
CO: ':';
CP: ')';
EE: '==';
EQ: '=';
GT: '>';
LT: '<';
LTE: '<=';
GTE: '>=';
MINUS: '-';
NE: '!=';
OB: '[';
OBC: '{';
OP: '(';
PC: '%';
PL: '+';
PP: '||';
SL: '/';
ST: '*';
SC: ';';
CLASS_: 'class';
ELSE_: 'else';
EXTENDS_: 'extends';
FALSE_: 'false';
IF_: 'if';
RETURN_: 'return';
TRUE_: 'true';
WHILE_: 'while';
BUILD: 'build';
WIDGET: 'Widget';
PRINT: 'print';
NULL_: 'null';

GETPARAM: 'getParam';
KEY: 'key';

PUSH_: 'push';
POP_: 'pop';

STATE_: 'state';

FONTSIZE: 'fontSize';

COLUMN: 'Column';
ROW: 'Row';
CONTAINER: 'Container';
BUTTON: 'Button';
IMAGE: 'Image';
SCAFFOLD: 'Scaffold';
TEXT: 'Text';
CENTER: 'Center';

SIZEDBOX: 'SizedBox';
APPBAR: 'AppBar';

ENABLED: 'enabled';

MAINAXISALIGNMENT: 'mainAxisAlignment';
CROSSAXISALIGNMENT: 'crossAxisAlignment';

CENTER_: 'center';
START: 'start';
END: 'end';

LEADING: 'leading';
TRAILING: 'trailing';

SPACEBETWEEN: 'spaceBetween';
SPACEAROUND: 'spaceAround';
SPACEEVENLY: 'spaceEvenly';

APPBAR_: 'appBar';

WIDTH: 'width';
HEIGHT: 'height';

LINK: 'link';
TEXT_: 'text';

COLOR: 'color';
BACKGROUND: 'background';
BODY: 'body';

MARGIN: 'margin';
PADDING: 'padding';

TEXTALIGNMENT: 'textAlignment';
FONTWEIGHT: 'fontWeight';

LIGHT: 'light';
MEDIUM: 'medium';
REGULAR: 'regular';
BOLD: 'bold';

ONPRESS: 'onPress';

RED: 'red';
BLUE: 'blue';
WHITE: 'white';
GREEN: 'green';
BLACK: 'black';
YELLOW: 'yellow';
ORANGE: 'orange';
GRAY: 'gray';

CHILD: 'child';
CHILDREN: 'children';

TYPE : INT | BOOLEAN | STRING_ | VOID;

INT: 'int';
BOOLEAN: 'boolean';
STRING_: 'string';
VOID: 'void';

NUMBER : DIGIT+ ( '.' DIGIT+ )?;
STRING : StringDQ | StringSQ | 'r\'' (~('\'' | '\n' | '\r'))* '\'' | 'r"' (~('"' | '\n' | '\r'))* '"';

IDENTIFIER : IDENTIFIER_START IDENTIFIER_PART*;

WHITESPACE : ( '\t' | ' ' | NEWLINE )+  -> skip;
COMMENT : '//' ~[\r\n]* -> skip;

ErrorChar : . ;

// TODO: Elaborate..
fragment StringDQ : '"' StringContentDQ*? '"';
fragment StringContentDQ : ~('\\' | '"' | '\n' | '\r' | '$') | '\\' ~('\n' | '\r') | StringDQ | '${' StringContentDQ*? '}';
fragment StringSQ : '\'' StringContentSQ*? '\'';
fragment StringContentSQ : ~('\\' | '\'' | '\n' | '\r' | '$') | '\\' ~('\n' | '\r') | StringSQ | '${' StringContentSQ*? '}';
fragment StringContentTDQ : ~('\\' | '"') | '"' ~'"' | '""' ~'"';
fragment StringContentTSQ : '\'' ~'\'' | '\'\'' ~'\'' | .;

fragment NEWLINE : '\n' | '\r' | '\r\n';

fragment IDENTIFIER_START : LETTER | '_' | '$';
fragment IDENTIFIER_PART : IDENTIFIER_START | DIGIT;

fragment LETTER : 'a' .. 'z' | 'A' .. 'Z';
fragment DIGIT : '0' .. '9';