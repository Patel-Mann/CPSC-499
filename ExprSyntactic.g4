parser grammar ExprSyntactic;

options {
    tokenVocab=ExprLexer;
}

identifier: IDENTIFIER;

qualifiedIdentifier: identifier {'.' identifier};

literal:  IntegerLiteral
         | FloatingPointLiteral
         | CharacterLiteral
         | StringLiteral
         | BooleanLiteral
         | NullLiteral;

expression: expression1 [assignmentOperator expression1];

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

type: identifier {'.' identifier} bracketsOpt
     | basicType;

statementExpression: expression;

constantExpression: expression;

expression1: expression2 [expression1Rest];

expression1Rest: ['?' expression ':' expression1];

expression2: expression3 [expression2Rest];

expression2Rest: {infixop expression3}
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
            | primary {selector} {postfixOP};

primary:  (expression)
        | This [arguments]
        | Super superSuffix
        | literal
        | New creator
        | identifier {'.' identifier} [identifierSuffix]
        | basicType bracketsOpt '.'Class
        | Void '.' Class;

identifierSuffix:  '['(']' bracketsOpt '.' Class '/' expression ']')
                 | arguments
                 |'.' '(' Class '/' This '/' Super arguments '/' New innerCreator ')';
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

argumentsOpt: [arguments];

arguments: '(' [expression {',' expression}] ')';

bracketsOpt: {'[' ']'}; //ask prof.

creator: qualifiedIdentifier ( arrayCreatorRest | classCreatorRest);

innerCreator: identifier classCreatorRest;

arrayCreatorRest: '[' ( ']' bracketsOpt arrayInitializer | expression ']' {[expression]};