# How shalt thou compile, with patience and practice
1. Compile thy code
    `javac -cp .:javaparser-1.0.0.jar:./lib/jgrapht-core-1.5.2.jar:./lib/jgrapht-io-1.5.2.jar:./lib/apfloat-1.10.1.jar:./lib/jheaps-0.14.jar CFGGraph/*.java LCA_JP1_0_0.java`

    `javac -cp .:javaparser-1.0.0.jar:./lib/* CFGGraph/*.java LCA_JP1_0_0.java`
2. Now move forth to victory
    `java -cp .:javaparser-1.0.0.jar:./lib/jgrapht-core-1.5.2.jar:./lib/jgrapht-io-1.5.2.jar:./lib/  apfloat-1.10.1.jar:./lib/jheaps-0.14.jar LCA_JP1_0_0 Test.java`
   
    `java -cp .:javaparser-1.0.0.jar:./lib/* LCA_JP1_0_0 Test.java`
3. PS: On Windows, replace `:` with `;` and `/` with `\` in paths


Node 1 (ENTRY): Created FIRST, so it gets ID = 1
Node 2 (EXIT): Created SECOND, so it gets ID = 2
Then we build the rest of the method body starting from node 3

Why we need them:
ENTRY marks where the method starts (before any code executes)
EXIT marks where the method ends (after return statements)
Helps with analysis - clear start/end points for control flow
```java
    if (val == 5) { // node 8
        val = 6;    // node 10
    }               // node 9: MERGE_IF
```
**Node 9 (MERGE_IF) → edge 14**:
- Node 9 is where the FIRST if statement merges (after `val = 6;`)
- After the merge, control flow continues to the NEXT statement
