parser grammar ExprParser;

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

expression
    : expression1 (assignmentOperator expression1)?
    ;

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
     | basicType bracketsOpt;

statementExpression: expression;

constantExpression: expression;

expression1: expression2 (Question expression Colon expression1)?;

expression2: expression3 (expression2Rest)?;

expression2Rest: (infixop expression3)+
                | InstanceOf type;

infixop:  ConditionalOR
        | ConditionalAND
        | BitwiseOR
        | BitwiseXOR
        | BitwiseAND
        | EqualTo
        | NotEqualTo
        | LessThan
        | GreaterThan
        | LessThanEqualTo
        | GreaterThanEqualTo
        | LeftShift
        | SignedRightShift
        | UnsignedRightShift
        | Addition
        | Subtraction
        | Multiplication
        | Division
        | Remainder;

expression3:  prefixOp expression3
            | ParenthesesLeft type ParenthesesRight expression3
            | primary postfixExpression*;

postfixExpression: selector | postfixOp;

primary:  ParenthesesLeft expression ParenthesesRight
        | This (arguments)?
        | Super superSuffix
        | literal
        | New creator
        | identifier (Dot identifier)* (identifierSuffix)?
        | basicType bracketsOpt Dot Class
        | Void Dot Class;

identifierSuffix:  SquareBracketLeft SquareBracketRight bracketsOpt Dot Class //Case []...'.'class
                |SquareBracketLeft expression SquareBracketRight //arr[5]
                |arguments
                |Dot (Class | This | Super arguments | New innerCreator);

prefixOp:  Increment
         | Decrement
         | LogicalComplement
         | BitwiseComplement
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

argumentsOpt: (arguments)?;

arguments: ParenthesesLeft (expression (Comma expression)*)? ParenthesesRight;

bracketsOpt: (SquareBracketLeft SquareBracketRight)*;

creator: qualifiedIdentifier ( arrayCreatorRest | classCreatorRest);

innerCreator: identifier classCreatorRest;

arrayCreatorRest: SquareBracketLeft SquareBracketRight bracketsOpt arrayInitializer
                | SquareBracketLeft expression SquareBracketRight (SquareBracketLeft expression SquareBracketRight)* bracketsOpt;

classCreatorRest: arguments (classBody)?;

arrayInitializer: CurlyBracketLeft (variableInitializer(Comma variableInitializer)*(Comma)?)? CurlyBracketRight;

variableInitializer: arrayInitializer
|expression;

parExpression: ParenthesesLeft expression ParenthesesRight;

block: CurlyBracketLeft blockStatements CurlyBracketRight;

blockStatements: (blockStatement)*;

blockStatement: localVariableDeclarationStatement
                |classOrInterfaceDeclaration
                |labeledStatement
                |statement;

labeledStatement: identifier Colon statement;

localVariableDeclarationStatement: (Final)? type variableDeclarators Semicolon;

statement: block
           | If parExpression statement (Else statement)?
           | For ParenthesesLeft forInit? Semicolon (expression)? Semicolon forUpdate? ParenthesesRight statement
           | While parExpression statement
           | Do statement While parExpression Semicolon
           | Try block catches
           | Try block Finally block
           | Try block catches Finally block
           | switchStatement
           | Synchronized parExpression block
           | Return (expression)? Semicolon
           | Throw expression Semicolon
           | Break (identifier)? Semicolon
           | Continue (identifier)? Semicolon
           | statementExpression Semicolon
           | Semicolon;

catches: catchClause+;

catchClause: Catch ParenthesesLeft formalParameter ParenthesesRight block;

switchStatement: Switch parExpression CurlyBracketLeft CurlyBracketRight
               | Switch parExpression CurlyBracketLeft switchBlockStatementGroups CurlyBracketRight;

switchBlockStatementGroups: switchBlockStatementGroup+;

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

variableDeclarator: identifier variableDeclaratorRest;

constantDeclarator: identifier constantDeclaratorRest;

variableDeclaratorRest: bracketsOpt (Assignment variableInitializer)?;

constantDeclaratorRest: bracketsOpt Assignment variableInitializer;

variableDeclaratorId: identifier bracketsOpt;

compilationUnit: (Package qualifiedIdentifier Semicolon)? (importDeclaration)* (typeDeclaration)*;

importDeclaration: Import identifier (Dot identifier)* (Dot Multiplication)? Semicolon;

typeDeclaration: classOrInterfaceDeclaration
               | Semicolon;

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

methodOrFieldRest: variableDeclaratorRest Semicolon
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

voidInterfaceMethodDeclaratorRest: formalParameters (Throws qualifiedIdentifierList)? Semicolon;

constructorDeclaratorRest: formalParameters (Throws qualifiedIdentifierList)? methodBody;

qualifiedIdentifierList: qualifiedIdentifier (Comma qualifiedIdentifier)*;

formalParameters: ParenthesesLeft (formalParameter (Comma formalParameter)*)? ParenthesesRight;

formalParameter: (Final)? type variableDeclaratorId;

methodBody: block;