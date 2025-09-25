parser grammar ExprParser;

options {
    tokenVocab=ExprLexer;
}

// Class Parser rules
prog : class EOF;

class:(Public | Private | Protected ) Class ID SLPAREN method+ SRPAREN;

//Method Decloration
method: Public Static Void ID LPAREN arg* RPAREN SLPAREN methodCode SRPAREN
        | Private Static Void ID SLPAREN methodCode SRPAREN
        | Protected Static Void ID SLPAREN methodCode SRPAREN
        ;

methodCode: systemOutPrint* forloop*
          ;

systemOutPrint
    : SYSTEM DOT OUT DOT PRINT LPAREN data RPAREN SEMI;

forloop: For LPAREN arg RPAREN SLPAREN methodCode SRPAREN;

arg: String arraydef* ID
   | exp SEMI exp SEMI exp
   ;

exp: define
    | ID GreterEql INTE
    | ID INCMENT
    ;
define :  Int ID EQL INTE;
arraydef: SqLPAREN (data T)* SqRPAREN;
data: STRING ;