lexer grammar ExprLexer;

// UNICODE  --------------------------------------------------------------------------------------------------------------
UnicodeInputCharacter: UnicodeEscape | RawInputCharacter;

fragment UnicodeEscape: '\\' UnicodeMarker HexDigit HexDigit HexDigit HexDigit;

fragment UnicodeMarker: 'u'+;


fragment RawInputCharacter:;
    //not complete need to ask question from prof.

// LINE TERMINATORS  -----------------------------------------------------------------------------------------------------------------------
LineTerminator: '\n' | '\r' | '\r\n';

InputCharacter: [^\n\r];

//WHITE SPACE  ----------------------------------------------------------------------------------------------------------------------------------------
WhiteSpace: ' ' | '\t' | '\f' | LineTerminator;

//COMMENTS  --------------------------------------------------------------------------------------------------------------------------------------------------
Comment: TraditionalComment | EndOfLineComment;

fragment TraditionalComment:
    '/*' NotStar CommentTail;

fragment EndOfLineComment:
    '//' CharactersInLine? LineTerminator;

fragment CommentTail: '*' CommentTailStar | NotStar ;

fragment CommentTailStar:
    '/' | '*' CommentTailStar | NotStarNotSlash CommentTail;

fragment NotStar:
    [^*] | LineTerminator;

fragment NotStarNotSlash:
    [^*/] | LineTerminator;

fragment CharactersInLine: InputCharacter
                         | InputCharacter;



// KEYWORDS  --------------------------------------------------------------------------------------------------------
Abstract: 'abstract';
Assert: 'assert';
Boolean: 'boolean';
Break: 'break';
Byte: 'byte';
Case: 'case';
Catch: 'catch';
Char: 'char';
Class: 'class';
Const: 'const';
Continue: 'continue';
Default: 'default';
Do: 'do';
Double: 'double';
Else: 'else';
Extends: 'extends';
Final: 'final';
Finally: 'finally';
Float: 'float';
For: 'for';
Goto: 'goto';
If: 'if';
Implements: 'implements';
Import: 'import';
InstanceOf: 'instanceof';
Int: 'int';
Interface: 'interface';
Long: 'long';
Native: 'native';
New: 'new';
Package: 'package';
Private: 'private';
Protected: 'protected';
Public: 'public';
Return: 'return';
Short: 'short';
Static: 'static';
Strictfp: 'strictfp';
Super: 'super';
Switch: 'switch';
Synchronized: 'synchronized';
This: 'this';
Throw: 'throw';
Throws: 'throws';
Transient: 'transient';
Try: 'try';
Void: 'void';
Volatile: 'volatile';
While: 'while';

// LITERALS  -------------------------------------------------------------------------------------------------------------------
IntegerLiteral: DecimalIntegerLiteral | HexIntegerLiteral | OctalIntegerLiteral;

fragment DecimalIntegerLiteral:
    DecimalNumeral IntegerTypeSuffix?;

fragment HexIntegerLiteral:
    HexNumeral IntegerTypeSuffix?;

fragment OctalIntegerLiteral:
    OctalNumeral IntegerTypeSuffix?;

fragment IntegerTypeSuffix:
    [lL];

fragment DecimalNumeral:
    '0' | NonZeroDigit Digits?;

fragment HexNumeral:
    '0' 'x' HexDigits | '0' 'X' HexDigits;

fragment HexDigits:
    HexDigit | HexDigit HexDigits;

fragment HexDigit:
    [0-9a-fA-F];

FloatingPointLiteral: Digits '.' Digits? ExponentPart? FloatTypeSuffix?
                     | '.' Digits ExponentPart? FloatTypeSuffix?
                     | Digits ExponentPart FloatTypeSuffix?
                     | Digits ExponentPart? FloatTypeSuffix;

fragment ExponentPart:
    ExponentIndicator SignedInteger;

fragment ExponentIndicator:
    [eE];

fragment SignedInteger:
    Sign? Digits;

fragment Digits:
    Digit+;

fragment Digit:
    '0' | NonZeroDigit;

fragment NonZeroDigit:
    [1-9];

fragment Sign:
    [+-];

fragment FloatTypeSuffix:
    [fFdD];

CharacterLiteral: '\'' SingleCharacter '\'' | '\'' EscapeSequence '\'';

fragment SingleCharacter:
    [^'\\];

StringLiteral: '"' StringCharacters? '"';

fragment StringCharacters:
    StringCharacter+;

fragment StringCharacter:
    SingleCharacter | EscapeSequence;

fragment EscapeSequence:
    '\\' ('u0008' | 'u0009' | 'u000a' | 'u000c' | 'u000d' | 'u0022' | 'u0027' | 'u005c' | OctalEscape);

fragment OctalEscape:
    '\\' OctalDigit | '\\' OctalDigit OctalDigit | '\\' ZeroToThree OctalDigit OctalDigit;

fragment OctalNumeral:
    '0' OctalDigits;

fragment OctalDigits:
    OctalDigit;

fragment OctalDigit:
    [0-7];

fragment ZeroToThree:
    [0-3];

BooleanLiteral: 'true' | 'false' ;

NullLiteral: 'null';

//SEPRATORS  -------------------------------------------------------------------------------------------------------------------------
ParenthesesLeft: '(';
ParenthesesRight: ')';
CurlyBracketLeft: '{';
CurlyBracketRight: '}';
SquareBracketLeft: '[';
SquareBracketRight: ']';
Semicolon: ';';
Comma: ',';
Dot: '.';

//OPERATORS  ---------------------------------------------------------------------------------------------------------------------------------------
Assignment: '=';
GreterThan: '>';
LessThan: '<';
LogicalComplement: '!';
BitwiseComplement: '~';
Question: '?';
Colon: ':';
EqualTo: '==';
LessThanEqualTo: '<=';
GreaterThanEqualTo: '>=';
NotEqualTo: '!=';
ConditionalAND: '&&';
ConditionalOR: '||';
Increment: '++';
Decrement: '--';
Addition: '+';
Subtraction: '-';
Multiplication: '*';
Division: '/';
BitwiseAND: '&';
BitwiseOR: '|';
BitwiseXOR: '^';
Remainder: '%';
LeftShift: '<<';
SignedRightShift: '>>';
UnsignedRightShift: '>>>';
AddAssign: '+=';
SubtractAssign: '-=';
MultiplyAssign: '*=';
DivideAssign: '/=';
BitwiseANDAssign: '&=';
BitwiseORAssign: '|=';
BitwiseXORAssign: '^=';
RemainderAssign: '%=';
LeftShiftAssign: '<<=';
SignedRightShiftAssign: '>>=';
UnsignedRightShiftAssign: '>>>=';


//IDENTIFIERS  -------------------------------------------------------------------------------------------------------------------------------------------
IDENTIFIER: JavaLetter JavaLetterOrDigit;

fragment JavaLetter:
    [a-zA-Z_$];

fragment JavaLetterOrDigit:
    JavaLetter | [0-9];
