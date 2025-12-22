## Termanology
1. Forward Slice => All statements that could be affected if you change a given statement.
2. AIS = Actualy Impacted Set (Manully calcualte the expected outcome)
3. EIS = Estimated Impacted Set (The set extracted from the outcome of the program)


### Run
```bash
chmod +x run.sh
# Show all dependencies for a file
./run.sh examples/Example1.java
# Analyze impact of line 3
./run.sh examples/Example1.java 3
```

# Testing
## Test1 simple data flow
### Line 3 (int x = 5;)
AIS = {3, 4, 5, 6}

```bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int x = 5 ;

=== IMPACTED LINES ===
  Line 3
  Line 4
  Line 5
  Line 6

Total: 4 lines impacted
```
1. TP: {3, 4, 5, 6} |TP| = 4
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0


### Line 4 (int y = x + 2;)
AIS = {4,5,6}

```bash
=== Impact Analysis for Line 4 ===

Statement(s) at line 4:
  2: int y = x + 2 ;

=== IMPACTED LINES ===
  Line 4
  Line 5
  Line 6

Total: 3 lines impacted
```
1. TP: {4, 5, 6} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

***

## Test2 Control Dependency
### Change at Line 5 (if (x > 5))
AIS = {5, 6, 8, 9}

```bash
=== Impact Analysis for Line 5 ===

Statement(s) at line 5:
  3: if ( x > 5 )

=== IMPACTED LINES ===
  Line 5
  Line 8
  Line 9

Total: 3 lines impacted
```
1. TP: {5, 8, 9} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {6} |FN| = 1
4. TN: {} |TN| = 0

### Change at Line 3 (int x = 10;)
AIS = {3, 5, 6, 8, 9}
```bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int x = 10 ;

=== IMPACTED LINES ===
  Line 3
  Line 5
  Line 6
  Line 8
  Line 9

Total: 5 lines impacted
```
1. TP: {3, 5, 6, 8, 9} |TP| = 5
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0


***

## Test3 Simple Loop
### Change at Line 3 (int sum = 0;)
AIS = {3, 6, 8}
```Bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int sum = 0 ;

=== IMPACTED LINES ===
  Line 3
  Line 6
  Line 8

Total: 3 lines impacted
```
1. TP: {3, 6, 8} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 5 (for loop heading)
AIS = {5, 6, 8}
```Bash
=== Impact Analysis for Line 5 ===

Statement(s) at line 5:
  3: for ( i = 0 ; i < 5 ; i ++ )
  4: block

=== IMPACTED LINES ===
  Line 5
  Line 6
  Line 8

Total: 3 lines impacted
```
1. TP: {5, 6, 8} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

***

## Test4 If-Else Branch
### Change at Line 5 (if (x > 5))
AIS = {5, 6, 8, 10, 11}
```Bash
=== Impact Analysis for Line 5 ===

Statement(s) at line 5:
  4: block
  3: if ( x > 5 )

=== IMPACTED LINES ===
  Line 5
  Line 6
  Line 7
  Line 8
  Line 10
  Line 11

Total: 6 lines impacted
```
1. TP: {5, 6, 7, 8, 10, 11} |TP| = 6
2. FP: {} |FP| = 0
3. FN: {7} |FN| = 1
4. TN: {} |TN| = 0

### Change at Line 6 (y = x + 10;)
AIS = {6, 10, 11}
```bash
=== Impact Analysis for Line 6 ===

Statement(s) at line 6:
  5: y = x + 10 ;

=== IMPACTED LINES ===
  Line 6

Total: 1 lines impacted
```
1. TP: {6} |TP| = 1
2. FP: {} |FP| = 0
3. FN: {10, 11} |FN| = 2
4. TN: {} |TN| = 0

***

## Test5 Multiple Uses va
### Change at Line 3 (int a = 5;)
AIS = {3, 4, 5, 6, 7}
```Bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int a = 5 ;

=== IMPACTED LINES ===
  Line 3
  Line 4
  Line 5
  Line 6
  Line 7

Total: 5 lines impacted
```
1. TP: {3, 4, 5, 6, 7} |TP| = 5
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 4 (int b = a + 2;)
AIS = {4, 6, 7}
```Bash
=== Impact Analysis for Line 4 ===

Statement(s) at line 4:
  2: int b = a + 2 ;

=== IMPACTED LINES ===
  Line 4
  Line 6
  Line 7

Total: 3 lines impacted
```
1. TP: {4, 6, 7} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

***

## Test6 Nested Control
### Change at Line 5 (if (x > 5))
AIS = {5, 6, 7, 10, 11}
```bash
=== Impact Analysis for Line 5 ===

Statement(s) at line 5:
  4: block
  3: if ( x > 5 )

=== IMPACTED LINES ===
  Line 5
  Line 6
  Line 7
  Line 10
  Line 11

Total: 5 lines impacted
```
1. TP: {5, 6, 7, 10, 11} |TP| = 5
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 6 (if (x > 8))
AIS = {6, 7, 10, 11}
```bash
=== Impact Analysis for Line 6 ===

Statement(s) at line 6:
  5: if ( x > 8 )
  6: block

=== IMPACTED LINES ===
  Line 6
  Line 7
  Line 10
  Line 11

Total: 4 lines impacted
```
1. TP: {6, 7, 10, 11} |TP| = 4
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

***

## Test7 While Loop
### Change at Line 5 (while (count < 5))
AIS = {5, 6, 7, 9}
```bash
=== Impact Analysis for Line 5 ===

Statement(s) at line 5:
  3: while ( count < 5 )
  4: block

=== IMPACTED LINES ===
  Line 5
  Line 6
  Line 7
  Line 9

Total: 4 lines impacted
```
1. TP: {5, 6, 7, 9} |TP| = 4
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 3 (int count = 0;)
AIS = {3, 5, 6, 7, 9}
```bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int count = 0 ;

=== IMPACTED LINES ===
  Line 3
  Line 5
  Line 6
  Line 7
  Line 9

Total: 5 lines impacted
```
1. TP: {3, 5, 6, 7, 9} |TP| = 5
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0
***

## Test8 Reassignment
### Change at Line 3 (int x = 5;)
AIS = {3, 4, 7}
```bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int x = 5 ;

=== IMPACTED LINES ===
  Line 3
  Line 4
  Line 7

Total: 3 lines impacted
```
1. TP: {3, 4, 7} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 5 (x = 10;)
AIS = {5, 6, 8}
```bash
=== Impact Analysis for Line 5 ===

Statement(s) at line 5:
  3: x = 10 ;

=== IMPACTED LINES ===
  Line 5
  Line 6
  Line 8

Total: 3 lines impacted
```
1. TP: {5, 6, 8} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

***

## Test9 No Impact
### Change at Line 4 (int y = 10;)
AIS = {4, 6, 8}
```bash
=== Impact Analysis for Line 4 ===

Statement(s) at line 4:
  2: int y = 10 ;

=== IMPACTED LINES ===
  Line 4
  Line 6
  Line 8

Total: 3 lines impacted
```
1. TP: {4, 6, 8} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 3 (int x = 5;)
AIS = {3, 5, 7}
```bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int x = 5 ;

=== IMPACTED LINES ===
  Line 3
  Line 5
  Line 7

Total: 3 lines impacted
```
1. TP: {3, 5, 7} |TP| = 3
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

***

## Test10 Complex Expression
### Change at Line 3 (int a = 2;)
AIS = {3, 6, 7, 8, 9}
```bash
=== Impact Analysis for Line 3 ===

Statement(s) at line 3:
  1: int a = 2 ;

=== IMPACTED LINES ===
  Line 3
  Line 6
  Line 7
  Line 8
  Line 9

Total: 5 lines impacted
```
1. TP: {3, 6, 7, 8, 9} |TP| = 5
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0

### Change at Line 8 (if (x > 5))
AIS = {8, 9}

```bash
=== Impact Analysis for Line 8 ===

Statement(s) at line 8:
  6: if ( x > 5 )
  7: block

=== IMPACTED LINES ===
  Line 8
  Line 9

Total: 2 lines impacted
```
1. TP: {8, 9} |TP| = 2
2. FP: {} |FP| = 0
3. FN: {} |FN| = 0
4. TN: {} |TN| = 0
