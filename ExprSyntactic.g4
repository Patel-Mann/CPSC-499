parser grammar ExprSyntactic;

options {
    tokenVocab=ExprLexer;
}
prog: compilationUnit EOF;
identifier: IDENTIFIER;

qualifiedIdentifier: identifier (Dot identifier)*;

literal:  IntegerLiteral
         | FloatingPointLiteral
         | CharacterLiteral
         | StringLiteral
         | BooleanLiteral
         | NullLiteral;
//Section 15.26: Assignment Operators
expression: unaryExpr assignmentOperator expression | conditionalExpr ;

//Highest Prescedence _Expr = expression
//CI cha 6
conditionalExpr: logicalOrExpr Question conditionalExpr Colon conditionalExpr | logicalOrExpr;

logicalOrExpr: logicalAndExpr (ConditionalOR logicalAndExpr)*;

logicalAndExpr: equalityExpr (ConditionalAND equalityExpr)*;

equalityExpr: relationalExpr ((EqualTo | NotEqualTo) relationalExpr)*;

relationalExpr: additiveExpr((LessThan | GreaterThan | LessThanEqualTo | GreaterThanEqualTo) additiveExpr | InstanceOf type)*;

additiveExpr: multiplicativeExpr((Addition | Subtraction) multiplicativeExpr)*;
multiplicativeExpr: unaryExpr((Multiplication | Division | Remainder) unaryExpr)*;

unaryExpr:postfixExpr |(Addition | Subtraction | LogicalComplement | BitwiseComplement | Increment | Decrement) unaryExpr | parExpression type unaryExpr;

postfixExpr: primaryExpr (Increment| Decrement| Dot IDENTIFIER | SquareBracketLeft expression SquareBracketRight | arguments)*;

primaryExpr: parExpression
            | IntegerLiteral
            | FloatingPointLiteral
            | StringLiteral
            | CharacterLiteral
            | BooleanLiteral
            | NullLiteral
            | IDENTIFIER
            | This
            | Super (Dot IDENTIFIER)?
            | New type  arguments
            | primitiveType (SquareBracketLeft SquareBracketRight)* Dot Class
            | type Dot Class;


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

type: identifier (Dot identifier)* bracketsOpt | primitiveType;

statementExpression: expression;

constantExpression: expression;

identifierSuffix:  SquareBracketLeft SquareBracketRight bracketsOpt Dot Class
                |SquareBracketLeft expression SquareBracketRight
                |arguments
                |Dot (Class | This | Super arguments New innerCreator);

postfixOp:  Increment | Decrement;

primitiveType:  Byte | Short| Char | Int| Long| Float| Double| Boolean;
//Method arguments
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

statement:completeIf | If parExpression statementIncompleteIf;

statementIncompleteIf: block
                    | If parExpression completeIf Else statementIncompleteIf
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
//solve ambiguity over If()else. aka (Dangling else).
//this will do the longest check by looking for an else section, fincding the closest else!
completeIf: If parExpression completeIf Else completeIf | statementIncompleteIf;

catches: catchClause (catchClause)*;

catchClause: Catch ParenthesesLeft formalParameter ParenthesesRight block;

switchStatement: Switch parExpression CurlyBracketLeft CurlyBracketRight
               | Switch parExpression CurlyBracketLeft switchBlockStatementGroups? CurlyBracketRight;

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

compilationUnit: (Package qualifiedIdentifier Semicolon)? (importDeclaration)*(typeDeclaration)*;

importDeclaration: Import identifier (Dot identifier)* (Dot Multiplication)? Semicolon;

typeDeclaration: classOrInterfaceDeclaration | Semicolon;

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

qualifiedIdentifierList: qualifiedIdentifier (Comma qualifiedIdentifier)*;

formalParameters: ParenthesesLeft (formalParameter (Comma formalParameter)*)? ParenthesesRight;

formalParameter: (Final)? type variableDeclaratorId;

methodBody: block;




