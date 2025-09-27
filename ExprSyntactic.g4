parser grammar ExprSyntactic;

options {
    tokenVocab=ExprLexer;
}

identifier: IDENTIFIER;

qualifiedIdentifier: identifier (Dot identifier)*;

literal:  IntegerLiteral
         | FloatingPointLiteral
         | CharacterLiteral
         | StringLiteral
         | BooleanLiteral
         | NullLiteral;

//expression: assignmentExpression;//expression1 (assignmentOperator expression1)?;


//testy test
// Top
expression
  : assignmentExpression
  ;

// Assignment is right-assoc and restricts LHS
assignmentExpression
  : leftHandSide assignmentOperator assignmentExpression
  | conditionalExpression
  ;

// ?: binds looser than ||
conditionalExpression
  : conditionalOrExpression
    (Question expression Colon conditionalExpression)?
  ;

// ||  (left-assoc)
conditionalOrExpression
  : conditionalAndExpression
    (ConditionalOR conditionalAndExpression)*
  ;

// &&  (left-assoc)
conditionalAndExpression
  : inclusiveOrExpression
    (ConditionalAND inclusiveOrExpression)*
  ;

// | ^ &  (left-assoc, each tier)
inclusiveOrExpression
  : exclusiveOrExpression
    (BitwiseOR exclusiveOrExpression)*
  ;

exclusiveOrExpression
  : andExpression
    (BitwiseXOR andExpression)*
  ;

andExpression
  : equalityExpression
    (BitwiseAND equalityExpression)*
  ;

// == !=
equalityExpression
  : relationalExpression
    ((EqualTo | NotEqualTo) relationalExpression)*
  ;

// < > <= >=  and  instanceof
relationalExpression
  : shiftExpression
    ((LessThan | GreaterThan | LessThanEqualTo | GreaterThanEqualTo) shiftExpression)*
  | shiftExpression InstanceOf type
  ;

// << >> >>>
shiftExpression
  : additiveExpression
    ((LeftShift | SignedRightShift | UnsignedRightShift) additiveExpression)*
  ;

// + -
additiveExpression
  : multiplicativeExpression
    ((Addition | Subtraction) multiplicativeExpression)*
  ;

// * / %
multiplicativeExpression
  : unaryExpression
    ((Multiplication | Division | Remainder) unaryExpression)*
  ;

// ++x --x +x -x and the rest
unaryExpression
  : (Increment | Decrement) unaryExpression
  | (Addition | Subtraction) unaryExpression
  | unaryExpressionNotPlusMinus
  ;

unaryExpressionNotPlusMinus
  : (BitwiseComplement | LogicalComplement) unaryExpression
  | castExpression
  | postfixExpression
  ;

// (T)expr
castExpression
  : ParenthesesLeft primitiveType ParanthesesRight unaryExpression
  | ParenthesesLeft referenceType ParanthesesRight unaryExpressionNotPlusMinus
  ;

// post-inc/dec over your suffix-based primary
postfixExpression
  : primary (Increment | Decrement)*
  ;

//end test
assignmentOperator: Assignment
                  | AddAssign
                  | SubtractAssign
                  | MultiplyAssign
                  | DivideAssign
                  | BitwiseANDAssign
                  | BitwiseORAssign
                  | BitwiseXORAssign
                  | RemainderAssign
                  | LeftShiftAssign
                  | SignedRightShiftAssign
                  | UnsignedRightShiftAssign;

type: identifier (Dot identifier)* bracketsOpt
     | basicType;

statementExpression: expression;

constantExpression: expression;

//expression1: expression2 (expression1Rest)?;
//
//expression1Rest: Question expression Colon expression1;
//
//expression2: expression3 expression2Rest?;
//
//expression2Rest: (infixop expression3)+
//                | InstanceOf type;
//
//infixop:  ConditionalOR
//        | ConditionalAND
//        | BitwiseOR
//        | BitwiseXOR
//        | BitwiseAND
//        | EqualTo
//        | NotEqualTo
//        | LessThan
//        | GreaterThan
//        | LessThanEqualTo
//        | GreaterThanEqualTo
//        | LeftShift
//        | SignedRightShift
//        | UnsignedRightShift
//        | Addition
//        | Subtraction
//        | Multiplication
//        | Division
//        | Remainder;
//
//expression3:  prefixOp expression3 // Recursion
//            | ParenthesesLeft type ParenthesesRight expression3
//            | primary (selector)* (postfixOp)*;

//primary:  (expression)
//        | This (arguments)?
//        | Super superSuffix
//        | literal
//        | New creator
//        | identifier (Dot identifier)* (identifierSuffix)?
//        | basicType bracketsOpt Dot Class
//        | Void Dot Class;

//TEST
primary: primaryAtom primarySuffix*
  | New creator                             ;

primaryAtom: ParenthesesLeft expression ParenthesesRight
  | This
  | literal
  | qualifiedIdentifier
  | qualifiedIdentifier Dot This
  | basicType bracketsOpt Dot Class
  | Void Dot Class
  | Super;

// All selectors / postfix pieces (no recursion back to primary)
primarySuffix: Dot Identifier arguments?
  | SquareBracketLeft expression SquareBracketRight
  | Dot Class;
////END test

identifierSuffix:  SquareBracketLeft SquareBracketRight bracketsOpt Dot Class //Case []...'.'class
                |SquareBracketLeft expression SquareBracketRight //arr[5]
                |arguments
                |Dot (Class | This | Super arguments New innerCreator);

prefixOp:  Increment
         | Decrement
         | LogicalComplement
         | BitWiseComplement
         | Addition
         | Subtraction;

postfixOp:  Increment | Decrement;

selector: Dot identifier (arguments)?
         | Dot This
         | Dot Super superSuffix
         | Dot New innerCreator
         | SquareBracketLeft expression SquareBracketRight;

superSuffix: arguments
          | Dot identifier (arguments)?;
//primitives
basicType:  Byte
          | Short
          | Char
          | Int
          | Long
          | Float
          | Double
          | Boolean;
//TODO: REVIEW ANYTHING BEFORE THIS POINT FOR POTENTIAL CONFUSSION ON TERMINAL SYMBOLS AND GRAMMAR
argumentsOpt: (arguments)?;

arguments: ParenthesesLeft (expression (Comma expression)*)? ParenthesesRight;

bracketsOpt: (SquareBracketLeft SquareBracketRight)*;

creator: qualifiedIdentifier ( arrayCreatorRest | classCreatorRest);

innerCreator: identifier classCreatorRest;

arrayCreatorRest: SquareBracketLeft SquareBracketRight bracketsOpt arrayInitializer
| SquareBracketLeft expression SquareBracketRight (SquareBracketLeft expression SquareBracketRight)*;
classCreatorRest: arguments (classBody)?;

arrayInitializer: CurlyBracketLeft (variableInitializer(Comma variableInitializer)*(Comma)?)? CurlyBracketRight;

variableInitializer: arrayInitializer
|expression;

parExpression: ParenthesesLeft expression ParenthesesRight;

block: CurlyBracketLeft blockStatements CurlyBracketRight;

blockStatements: (blockStatement)*;

blockStatement: localVariableDeclarationStatement
                |classOrInterfaceDeclaration
                |(identifier Colon)? statement;

localVariableDeclarationStatement: (Final)? type variableDeclarators;

statement: block
           | If parExpression statement (Else statement)?
           | For ParenthesesLeft forInit? Semicolon (expression)? Semicolon forUpdate? ParenthesesRight statement
           | While parExpression statement
           | Do statement While parExpression Semicolon
           | Try block (catches | (catches)? Finally block)
           | Switch parExpression CurlyBracketLeft switchBlockStatementGroups? CurlyBracketRight
           | Synchronized parExpression block
           | Return (expression)? Semicolon
           | Throw expression Semicolon
           | Break (identifier)? Semicolon
           | Continue (identifier)? Semicolon
           | Semicolon
           //| expressionStatement
           | identifier Colon statement;

catches: catchClause (catchClause)*;

catchClause: Catch ParenthesesLeft formalParameter ParenthesesRight block;

switchBlockStatementGroups: (switchBlockStatementGroup)*;

switchBlockStatementGroup: switchLabel blockStatements;

switchLabel: Case constantExpression Colon
           | Default Colon;
moreStatementExpressions: (Comma statementExpression)*;

forInit: statementExpression moreStatementExpressions
       | (Final)? type variableDeclarators;

forUpdate: statementExpression moreStatementExpressions;

modifiersOpt: (modifier)*;

modifier: Public
        | Protected
        | Private
        | Static
        | Abstract
        | Final
        | Native
        | Synchronized
        | Transient
        | Volatile
        | Strictfp;

variableDeclarators: variableDeclarator (Comma variableDeclarator)*;

variableDeclaratorsRest: variableDeclaratorRest (Comma variableDeclarator)*;

constantDeclaratorsRest: constantDeclaratorRest (Comma constantDeclarator)*;

variableDeclarator: identifier variableDeclaratorsRest;

constantDeclarator: identifier constantDeclaratorRest;

variableDeclaratorRest: bracketsOpt (Assignment variableInitializer)?;

constantDeclaratorRest: bracketsOpt Assignment variableInitializer;

variableDeclaratorId: identifier bracketsOpt;

compilationUnit: (Package qualifiedIdentifier Semicolon)? (importDeclaration)*;

importDeclaration: Import identifier (Dot identifier)* (Dot Multiplication)? Semicolon;

typeDeclaration: classOrInterfaceDeclaration Semicolon;

classOrInterfaceDeclaration: modifiersOpt (classDeclaration | interfaceDeclaration);

classDeclaration: Class identifier (Extends type)? (Implements typeList)? classBody;

interfaceDeclaration: Interface identifier (Extends typeList)? interfaceBody;

typeList: type (Comma type)*;

classBody: CurlyBracketLeft (classBodyDeclaration)* CurlyBracketRight;

interfaceBody: CurlyBracketLeft (interfaceBodyDeclaration)* CurlyBracketRight;

classBodyDeclaration: Semicolon
                    | (Static)? block
                    | modifiersOpt memberDecl;
memberDecl: methodOrFieldDecl
           | Void identifier methodDeclaratorRest
           | identifier constructorDeclaratorRest
           | classOrInterfaceDeclaration;

methodOrFieldDecl: type identifier methodOrFieldRest;

methodOrFieldRest: variableDeclaratorRest
                  |methodDeclaratorRest;

interfaceBodyDeclaration: Semicolon
                        | modifiersOpt interfaceMemberDecl;

interfaceMemberDecl: interfaceMethodOrFieldDecl
                   | Void identifier voidInterfaceMethodDeclaratorRest
                   | classOrInterfaceDeclaration;

interfaceMethodOrFieldDecl: type identifier interfaceMethodOrFieldRest;

interfaceMethodOrFieldRest: constantDeclaratorRest Semicolon
                           | interfaceMethodDeclaratorRest;

methodDeclaratorRest: formalParameters bracketsOpt (Throws qualifiedIdentifierList)?(methodBody | Semicolon);

voidMethodDeclaratorRest: formalParameters (Throws qualifiedIdentifierList)? (methodBody | Semicolon);

interfaceMethodDeclaratorRest: formalParameters bracketsOpt (Throws qualifiedIdentifierList)? Semicolon;

voidInterfaceMethodDeclaratorRest: formalParameters (Throws qualifiedIdentifierList)?;

constructorDeclaratorRest: formalParameters (Throws qualifiedIdentifierList)? methodBody;

qualifiedIdentifierList: qualifiedIdentifier (Semicolon qualifiedIdentifier)*;

formalParameters: ParenthesesLeft (formalParameter (Comma formalParameter)*)? ParenthesesRight;

formalParameter: (Final)? type variableDeclaratorId;

methodBody: block;

//Things not defined within the final chapter of JSL2
expressionStatement: statementExpression Semicolon;

asssignmentExpression: conditionalExpression
                     | assignment;
assignment: leftHandSide assignmentOperator assignmentExpression;

leftHandSide: expressionName
  | fieldAccess
  | primary SquareBracketLeft expression SquareBracketRight(SquareBracketLeft expression SquareBracketRight)*;

//conditionalExpression: conditionalOrExpression
//                     | conditionalOrExpression Question expression Colon conditionalExpression;
//
// conditionalOrExpression:
//     conditionalAndExpression
//     |conditionalOrExpression ConditionalOr conditionalAndExpression;
// //Conditional And (&&)
// conditionalAndExpression:
//     inclusiveOrExpression
//     |conditionalAndExpression ConditionalAnd inclusiveOrExpression;
// inclusiveOrExpression:
//     exclusiveOrExpression
//     inclusiveOrExpression BitwiseOR exclusiveOrExpression;
////Assignment operators
//assignmentExpression:conditionalExpression
//                    |assignment;
//
// relationalExpression:
//     shiftExpression
//     |relationalExpression LessThan shiftExpression
//     |relationalExpression GreaterThan shiftExpression
//     |relationalExpression LessOrEqual shiftExpression
//     |relationalExpression GreaterOrEqual shiftExpression
//     |relationalExpression InstanceOf referenceType;

//ShiftExpression:
//shiftExpression: additiveExpression
//               |shiftExpression LeftShift additiveExpression
//               |shiftExpression SignedRightShift additiveExpression
//               |shiftExpression UnsignedRightShift additiveExpression;
////Additive Operators
//additiveExpression: multiplicativeExpression
//                   | additiveExpression Addition multiplicativeExpression
//                   | additiveExpression Subtraction multiplicativeExpression;
//
////Multiplicative Operators
//multiplicativeExpression: unaryExpression
//                        | multiplicativeExpression Multiplication unaryExpression
//                        | multiplicativeExpression Division unaryExpression
//                        | multiplicativeExpression Remainder unaryExpression;
////Unary Operators
//unaryExpression: preIncrementExpression
//                |preDecrementExpression
//                |Addition unaryExpression
//                |Subtraction unaryExpression
//                |unaryExpressionNotPlusMinus;

preIncrementExpression: Increment unaryExpression;
preDecrementExpression: Decrement unaryExpression;
//unaryExpressionNotPlusMinus: postfixExpression
//                            |BitwiseComplement unaryExpression
//                            |LogicalComplement unaryExpression
//                            | castExpression;
//castExpression: ParenthesesLeft primitiveType ParanthesesRight unaryExpression
//               | ParenthesesLeft referenceType ParanthesesRight unaryExpressionNotPlusMinus;
//Primitive types
primitiveType: numericType
             | Boolean;
referenceType: classOrInterfaceType
            | arrayType;
classOrInterfaceType: classType
                    | interfaceType;
classType: typeName;
interfaceType: typeName;
//Determining meaning of Name
typeName: identifier
        | packageOrTypeName Dot Identifier;
packageOrTypeName: Identifier
                 | packageOrTypeName Dot identifier;
arrayType: type SquareBracketLeft SquareBracketRight;
numericType: integralType
            | floatingPointType;
integralType: Byte | Short | Int | Long | Char;
floatingPointType: Float | Double;
//postfix expressions
//postfixExpression: primary
//                | expressionName
//                | postIncrementExpression
//                | postDecrementExpression;
postfixBase: primary | expressionName ;
//postfixExpression  : postfixBase (Increment | Decrement)* ;
//postIncrementExpression: postfixExpression Increment;
//postDecrementExpression: postfixExpression Decrement;

// //Equality Operators
// equalityExpression:
//     relationalExpression
//     |equalityExpression EqualEqual relationalExpression
//     |equalityExpression NotEqualTo relationalExpression;

 //Bitwise and Logical Operators
// andExpression:
//     equalityExpression
//     |andExpression BitwiseAND andExpression;
// exclusiveOrExpression:
//     andExpression
//     |exclusiveOrExpression BitwiseXOR andExpression;
expressionName: Identifier (Dot Identifier)*;
fieldAccess
  : primary Dot Identifier
  | Super Dot Identifier
  | typeName Dot Super Dot Identifier
  ;

classInstanceCreationExpression: New classOrInterfaceType ParenthesesLeft argumentsOpt ParanthesesRight classBody
                                | primary New identifier ParenthesesLeft argumentsOpt ParanthesesRight classBody;

methodInvocation: methodName arguments
  | primary Dot Identifier arguments
  | Super Dot Identifier arguments
  | typeName Dot Super Dot Identifier arguments;
methodName
  : Identifier (Dot Identifier)*;

//arguments: ParenthesesLeft argumentList? ParanthesesRight;

argumentList: expression (Comma expression)*;






