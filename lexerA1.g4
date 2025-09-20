lexer grammar lexerA1;

// Literals
IntigerLiteral: ;

FloatingPointLiteral: ;

CharacterLiteral: '\'' SingleCharacter '\'' | '\'' EscapeSequence '\'';

fragment SingleCharacter:
    [^'\\];

StringLiteral: '"' StringCharacters? '"';

fragment StringCharacters:
    StringCharacter+;

fragment StringCharacter:
    [^"\\] | EscapeSequence;

fragment EscapeSequence:
    '\\' ('u0008' | 'u0009' | 'u000a' | 'u000c' | 'u000d' | 'u0022' | 'u0027' | 'u005c' | OctalEscape);

fragment OctalEscape:
    '\\' OctalDigit | '\\' OctalDigit OctalDigit | '\\' ZeroToThree OctalDigit OctalDigit;

fragment OctalDigit:
    [0-7];

fragment ZeroToThree:
    [0-3];

BooleanLiteral: 'true' | 'false' ;

NullLiteral: 'null';
// Keywords
Public : 'public';
Protected: 'protected';
Private: 'private';
Static: 'static';
Abstract: 'abstract';
Final: 'final';
Native: 'native';
Synchronized: 'synchronized';
Transient: 'transient';
Volatile: 'volatile';
Strictfp: 'strictfp';
Assert: 'Assert';
