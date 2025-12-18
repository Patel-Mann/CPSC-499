## Termanology
1. Forward Slice => All statements that could be affected if you change a given statement.

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
TODO:
```

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
TP: {5, 8, 9} |TP| = 3
FP: {}        |FP| = 0
FN: {6}       |FN| = 1
Precision: 3 / (3 + 0) = 3/3 = 1.00 (100%)
Recall: 3 / (3 + 1) = 3/4 = 0.75 (75%)
Analysis:
- Perfect precision (no false alarms)
- Missed line 6 (the assignment inside the if-block)
- UNDER-APPROXIMATION

### Change at Line 3 (int x = 10;)
AIS = {3, 5, 6, 8, 9}

***

## Test3 Simple Loop
### Change at Line 3 (int sum = 0;)
AIS = {3, 6, 8}

### Change at Line 5 (for loop heading)
AIS = {5, 6, 8}

***

## Test4 If-Else Branch
### Change at Line 5 (if (x > 5))
AIS = {5, 6, 8, 10, 11}

### Change at Line 6 (y = x + 10;)
AIS = {6, 10, 11}

***

## Test5 Multiple Uses va
### Change at Line 3 (int a = 5;)
AIS = {3, 4, 5, 6, 7}

### Change at Line 4 (int b = a + 2;)
AIS = {4, 6, 7}

***

## Test6 Nested Control
### Change at Line 5 (if (x > 5))
AIS = {5, 6, 7, 10, 11}

### Change at Line 6 (if (x > 8))
AIS = {6, 7, 10, 11}

***

## Test7 While Loop
### Change at Line 5 (while (count < 5))
AIS = {5, 6, 7, 9}

### Change at Line 3 (int count = 0;)
AIS = {3, 5, 6, 7, 9}

***

## Test8 Reassignment
### Change at Line 3 (int x = 5;)
AIS = {3, 4, 7}

### Change at Line 5 (x = 10;)
AIS = {5, 6, 8}

***

## Test9 No Impact
### Change at Line 4 (int y = 10;)
AIS = {4, 6, 8}

### Change at Line 3 (int x = 5;)
AIS = {3, 5, 7}

***

## Test10 Complex Expression
### Change at Line 3 (int a = 2;)
AIS = {3, 6, 7, 8, 9}

### Change at Line 8 (if (x > 5))
AIS = {8, 9}

```bash
=== IMPACTED LINES ===
  Line 6
  Line 8
```
