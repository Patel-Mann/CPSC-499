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


//TODO: sections 4 to 9

//Primitive Types
type:
    primitiveType dims? //Handle int[] or just int
    |name dims?;

primitiveType:
    primitiveNumericType
    |Boolean;

primitiveNumericType:
    primitiveIntegralType
    |primitiveFloatingPointType;

primitiveIntegralType:
    Byte
    |Short
    |Int
    |Long
    |Char;

primitiveFloatingPointType:
    Float
    |Double;

dims:
    (LBracket RBracket)*;

name:
    Identifier (Dot Identifier)*;
// Operators 15.20 Relational Operators
relationalExpression:
    shiftExpression
    |relationalExpression LessThan ShiftExpression
    |relationalExpression GreaterThan ShiftExpression
    |relationalExpression LessOrEqual ShiftExpression
    |relationalExpression GreaterOrEqual ShiftExpression
    |relationalExpression InstanceOf ReferenceType;

//Equality Operators

equalityExpression:
    relationalExpression
    |equalityExpression EqualEqual relationalExpression
    |equalityExpression NotEqualTo relationalExpression;

//Bitwise and Logical Operators
andExpression:
    equalityExpression
    |andExpression BitwiseAND andExpression;
exclusiveOrExpression:
    andExpression
    |exclusiveOrExpression BitwiseXOR andExpression;
inclusiveOrExpression:
    exclusiveOrExpression
    inclusiveOrExpression BitwiseOR exclusiveOrExpression;
//Conditional And (&&)
conditionalAndExpression:
    inclusiveOrExpression
    |conditionalAndExpression ConditionalAnd inclusiveOrExpression;
//conditional Or (||)
conditionalOrExpression:
    conditionalAndExpression
    |conditionalOrExpression ConditionalOr conditionalAndExpression;
//conditional Operator (?)
conditionalExpression:
    conditionalOrExpression
    |conditionalOrExpression QuestionMark exp Colon conditionalOrExpression;

//Assignment operators
assignmentExpression:
    conditionalExpression
    |assignment;
assignment:
    leftHandSide assignmentOperator assignmentExpression;
leftHandSide:
    expressionName;
    |fieldAccess
    |arrayAccess;
assignmentOperator:
    Assignment | MultiplyAssign | DivideAssign | RemainderAssign | AddAssign | SubtractAssign | LeftShiftAssign
    | SighnedRightShiftAssign | BitwiseANDAssign | BitwiseXORAssign | BitwiseORAssign;

//Determining Meaning of a Name (Section 6.5)
expressionName:
    name;
    |ambiguousName Dot name;
