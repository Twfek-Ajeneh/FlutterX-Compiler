parser grammar XFlutterParser;

options { tokenVocab=XFlutterLexer; }

// Program
//==================================================
program: (classDeclaration)* EOF;


// Statement
//==================================================
statement: nonSemiColonStatement | semiColonStatement;

nonSemiColonStatement: ifStatement
                     | whileStatement
                     ;

semiColonStatement: initVariable
                     | mutateVariable
                     | printStatement
                     | pushStatement
                     | popStatement
                     | stateStatement
                    ;


// Init variable
//==================================================
type: TYPE;

initVariable: type IDENTIFIER EQ expression SC;

mutateVariable: IDENTIFIER EQ expression SC;


// Functions
//===================================================

// Build Function
buildFunctionSignature: WIDGET BUILD OP CP;

buildFunctionBlock: OBC (statement)* RETURN_ widget SC CBC;

buildFunctionDeclaration: buildFunctionSignature buildFunctionBlock;

// Normal Function
functionSignature: type IDENTIFIER parameters;

functionBlock: OBC (statement)* (RETURN_ expression SC)? CBC;

functionDeclaration: functionSignature functionBlock;

// Paramters
parameters: OP CP
          | OP parameter (C parameter)* CP;

parameter: type IDENTIFIER;


// Class Declaration:
//===================================================

classDeclaration: CLASS_ IDENTIFIER classDeclarationBlock;
classDeclarationBlock: OBC (initVariable)* functionDeclaration*  buildFunctionDeclaration CBC;

// Function Call:
//===================================================
functionCall: IDENTIFIER arguments;

arguments: OP CP
         | OP namedArguments CP;

namedArguments: namedArgument (C namedArgument)*;

namedArgument: IDENTIFIER CO expression;


// Param Call:
//===================================================
paramCall: GETPARAM OP KEY CO expression CP;


// Expressions:
//===================================================
expression: conditionalExpression
          | operationExpression
          ;

operationExpression : OP operationExpression CP #perensOp
          | left = operationExpression op = (ST | SL | PC | PL | MINUS) right = operationExpression #numberOp
          | IDENTIFIER #identifierOp
          | literal #literalOp
          | functionCall #funOp
          | paramCall #paramOp
          ;

conditionalExpression: left = conditionalExpression op = (AA | PP) right = conditionalExpression #logicalExp
                   | left = operationExpression op = (EE | GT | LT | LTE | GTE | NE) right = operationExpression #comparisonExp
                   | value = (TRUE_ | FALSE_) #booleanExp
                   ;
//===================================================


// Literals:
//===================================================

literal: lit = (NUMBER | STRING | FALSE_ | TRUE_ | NULL_ | VOID);

//===================================================


// Statements:
//===================================================
ifStatement: IF_ OP conditionalExpression CP statementsBlock (ELSE_ statementsBlock)?;

whileStatement: WHILE_ OP conditionalExpression CP statementsBlock;

statementsBlock: OBC (statement)* CBC;
//===================================================

// PrintStatement:
//===================================================
printStatement: PRINT expression SC;
//===================================================

// NavStatement:
//===================================================
pushStatement: PUSH_ functionCall SC;
popStatement: POP_ SC;
//===================================================


// StateStatement:
//===================================================
stateStatement: STATE_ SC;
//===================================================

// Anonymous Function:
//===================================================
anonymousFunctionDeclaration: OP CP anonymousFunctionBlock;
anonymousFunctionBlock: OBC (statement)* CBC;
//===================================================


// Widgets:
//===================================================
widget: row | column | center | text | container | image | scaffold | button | sizedBox;

row: ROW OP width C height C mainAxisAlignment C crossAxisAlignment C margin C padding C children CP;

column: COLUMN OP width C height C mainAxisAlignment C crossAxisAlignment C margin C padding C children CP;

center: CENTER OP width C height C margin C padding C child CP;

text: TEXT OP width C height C margin C padding C textAttr C textAlignment C fontSize C fontWeight C color CP;

container: CONTAINER OP width C height C margin C padding C child C color CP;

image: IMAGE OP width C height C margin C padding C link CP;

button: BUTTON OP width C height C margin C padding C textAttr C onPress C color C enabled CP;

scaffold: SCAFFOLD OP width C height C margin C padding C appBarAttr C body CP;

sizedBox: SIZEDBOX OP width C height C margin C padding C color CP;

appBar: APPBAR OP width C height C margin C padding C color C leading C trailing C centerAttr CP;

// Widgets Attributes:
//===================================================
child: CHILD CO widget;

body: BODY CO widget;

children: CHILDREN CO OB widget (C widget)* CB;

width: WIDTH CO expression;

height: HEIGHT CO expression;

color: COLOR CO value = (RED | GREEN | BLUE | WHITE | BLACK | YELLOW | ORANGE | GRAY);

textAttr: TEXT_ CO expression;

textAlignment: TEXTALIGNMENT CO value = (CENTER_ | START | END);

fontSize: FONTSIZE CO expression;

fontWeight: FONTWEIGHT CO value = (LIGHT | REGULAR | MEDIUM | BOLD);

link: LINK CO expression;

onPress: ONPRESS CO anonymousFunctionDeclaration;

mainAxisAlignment: MAINAXISALIGNMENT CO value = (CENTER_ | START | END | SPACEBETWEEN | SPACEAROUND | SPACEEVENLY);
crossAxisAlignment: CROSSAXISALIGNMENT CO value = (CENTER_ | START | END);

enabled: ENABLED CO expression;

margin: MARGIN CO expression;
padding: PADDING CO expression;

leading: LEADING CO widget;
trailing: TRAILING CO widget;

centerAttr: CENTER_ CO widget;

appBarAttr: APPBAR_ CO appBar;