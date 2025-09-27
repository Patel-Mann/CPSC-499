parser grammar syntactic;

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
