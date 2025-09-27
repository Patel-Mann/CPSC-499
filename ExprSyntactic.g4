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

expression: expression1 (assignmentOperator expression1)?;

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
                  | SighnedRightShiftAssign
                  | UnsighnedRightShiftAssign;

type: identifier (Dot identifier)* bracketsOpt
     | basicType;

statementExpression: expression;

constantExpression: expression;

expression1: expression2 (expression1Rest)*;

expression1Rest: (Question expression Colon expression1)?;

expression2: expression3 (expression2Rest)?;

expression2Rest: (infixop expression3)*
                | expression3 INSTANCEOF type;

infixop:  ConditionalOR
        | ConditionalAND
        | BitwiseOR
        | BitwiseXOR
        | BitwiseAND
        | EqualTO
        | NotEqualTO
        | LessThan
        | GreterThan
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

expression3:  prefixOp expression3 // Recursion
            | (expr | type) expression3
            | primary (selector)* (postfixOP)*;

primary:  (expression)
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

arguments: ParenthesesLeft (expression (',' expression)*)? ParenthesesRight;

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
                |(identifier Colon)?;

localVariableDeclarationStatement: (Final)? type variableDeclarators;

statement: block
           | If parExpression statement (Else statement)?
           | For ParenthesesLeft forInitOp Semicolon (expression)? Semicolon forUpdateOpt ParenthesesRight statement
           | While parExpression statement
           | Do statement While parExpression Semicolon
           | Try block (catches | (catches)? Finally block)
           | Switch parExpression CurlyBracketLeft switchBlockStatementsGroups? CurlyBracketRight
           | Synchronized parExpression block
           | Return (expression)? Semicolon
           | Throw expression Semicolon
           | Break (identifier)? Semicolon
           | Continue (identifier)? Semicolon
           | Semicolon
           | expressionStatement
           | identifier Colon statement;

catches: catchClause (catchClause)*;

catchClause: Catch ParenthesesLeft formalParameter ParanthesesRight block;

switchBlockStatementGroups: (switchBlockStatementGroups)*;

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

variableDeclarators: variableDeclarators (Comma variableDeclarator)*;

variableDeclaratorsRest: variableDeclaratorsRest (Comma variableDeclarator)*;

constantDeclaratorRest: constantDeclaratorRest (Comma constantDeclarator)*;

variableDeclarator: identifier variableDeclaratorsRest;

constantDeclarator: identifier constantDeclaratorRest;

//Little git test
