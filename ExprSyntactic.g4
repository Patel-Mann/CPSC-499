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

identifierSuffix:  SquareBracketLeft(SquareBracketRight bracketsOpt Dot Class Division expression SquareBracketRight)
                 | arguments
                 |Dot ParanthesesLeft Class Divisor This Divisor Super arguments Divisor New innerCreator ParanthesesRight;
                 // confused about this need to ask prof

prefixOp:  Increment
         | Decrement
         | LogicalComplement
         | BitWiseComplement
         | Addition
         | Subtraction;

postfixOp:  Increment | Decrement;

selector: '.' identifier [arguments]
         | '.' This
         | '.' Super superSuffix
         | '.' New innerCreator
         | [expression];

superSuffix: arguments
            | '.' identifier [arguments];

basicType:  Byte
          | Short
          | Char
          | Int
          | Long
          | Float
          | Double
          | Boolean;

argumentsOpt: (arguments)?;

arguments: '(' (expression (',' expression)*)? ')';

bracketsOpt: ('[' ']')*; //ask prof.

creator: qualifiedIdentifier ( arrayCreatorRest | classCreatorRest);

innerCreator: identifier classCreatorRest;

arrayCreatorRest: '[' ( ']' bracketsOpt arrayInitializer | expression ']' {[expression]};