// Java 1.4 Grammar Reference File
// This file demonstrates Java language constructs available in Java 1.4

// 1. Package declaration
package com.example.grammar;

// 2. Import statements
import java.util.*;
import java.io.*;
import java.net.*;

/**
 * 3. Class-level documentation comment
 * Java 1.4 grammar demonstration class
 */
public class Java14GrammarReference {

    // 4. Class-level fields with various modifiers
    public static final String CONSTANT = "CONSTANT_VALUE";
    private static int staticCounter = 0;
    protected volatile boolean flag = false;
    transient String transientField;
    final Vector finalVector = new Vector();

    // 5. Instance initializer block
    {
        finalVector.addElement("initialized");
        System.out.println("Instance initializer executed");
    }

    // 6. Static initializer block
    static {
        staticCounter = 10;
        System.out.println("Static initializer executed");
    }

    // 7. Constructors
    public Java14GrammarReference() {
        this("default");
    }

    public Java14GrammarReference(String value) {
        super(); // Call to superclass constructor
        this.transientField = value;
    }

    // 8. Method declarations with various signatures
    public void basicMethod() {
        System.out.println("Basic method");
    }

    private static synchronized Object genericMethod(Object parameter) {
        return parameter;
    }

    protected final void finalMethod() throws IOException, InterruptedException {
        // Method that throws exceptions
    }

    public abstract void abstractMethod(); // Would be in abstract class

    public native void nativeMethod(); // Native method declaration

    // 9. Method with array parameter (varargs not available in 1.4)
    public void arrayMethod(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

    // 10. Main method demonstrating various language constructs
    public static void main(String[] args) {

        // 11. Variable declarations and initializations
        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = 3.14f;
        double d = 3.141592653589793;
        char c = 'A';
        boolean bool = true;
        String str = "Hello World";

        // 12. Array declarations
        int[] intArray = new int[10];
        int[][] twoDArray = {{1, 2}, {3, 4}};
        String[] stringArray = {"one", "two", "three"};

        // 13. Object creation (using pre-1.5 collections)
        Java14GrammarReference obj = new Java14GrammarReference("example");
        Vector vector = new Vector();
        Hashtable hashtable = new Hashtable();

        // 14. Anonymous class
        Runnable anonymousClass = new Runnable() {
            public void run() {
                System.out.println("Anonymous class method");
            }
        };

        // 15. Conditional statements
        if (bool) {
            System.out.println("If statement");
        } else if (i > 0) {
            System.out.println("Else if statement");
        } else {
            System.out.println("Else statement");
        }

        // 16. Ternary operator
        String result = (i > 0) ? "positive" : "non-positive";

        // 17. Switch statement
        switch (c) {
            case 'A':
                System.out.println("Case A");
                break;
            case 'B':
            case 'C':
                System.out.println("Case B or C");
                break;
            default:
                System.out.println("Default case");
        }

        // 18. Loops
        // For loop
        for (int j = 0; j < 10; j++) {
            System.out.println("For loop: " + j);
        }

        // Traditional iteration (no enhanced for loop in 1.4)
        for (int k = 0; k < stringArray.length; k++) {
            System.out.println("Array iteration: " + stringArray[k]);
        }

        // Iterator usage
        Iterator iterator = vector.iterator();
        while (iterator.hasNext()) {
            System.out.println("Iterator: " + iterator.next());
        }

        // While loop
        int counter = 0;
        while (counter < 5) {
            System.out.println("While loop: " + counter);
            counter++;
        }

        // Do-while loop
        do {
            System.out.println("Do-while loop: " + counter);
            counter--;
        } while (counter > 0);

        // 19. Exception handling
        try {
            int division = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception caught");
        } catch (Exception e) {
            System.out.println("General exception caught");
        } finally {
            System.out.println("Finally block executed");
        }

        // 20. Manual resource management (no try-with-resources in 1.4)
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("test.txt");
            // Use the stream
        } catch (IOException e) {
            System.out.println("IO Exception");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.out.println("Error closing stream");
                }
            }
        }

        // 21. Assertions (introduced in 1.4)
        assert i > 0 : "i should be positive";

        // 22. Synchronized block
        synchronized (obj) {
            System.out.println("Synchronized block");
        }

        // 23. Operators
        // Arithmetic operators
        int sum = i + b;
        int difference = i - b;
        int product = i * b;
        int quotient = i / b;
        int remainder = i % b;

        // Assignment operators
        i += 5;
        i -= 3;
        i *= 2;
        i /= 2;
        i %= 3;

        // Increment/Decrement operators
        i++;
        ++i;
        i--;
        --i;

        // Comparison operators
        boolean equal = (i == b);
        boolean notEqual = (i != b);
        boolean greater = (i > b);
        boolean less = (i < b);
        boolean greaterEqual = (i >= b);
        boolean lessEqual = (i <= b);

        // Logical operators
        boolean and = (bool && equal);
        boolean or = (bool || equal);
        boolean not = !bool;

        // Bitwise operators
        int bitwiseAnd = i & b;
        int bitwiseOr = i | b;
        int bitwiseXor = i ^ b;
        int bitwiseNot = ~i;
        int leftShift = i << 2;
        int rightShift = i >> 2;
        int unsignedRightShift = i >>> 2;

        // 24. instanceof operator
        if (obj instanceof Java14GrammarReference) {
            System.out.println("obj is instance of Java14GrammarReference");
        }

        // 25. Type casting
        double castToDouble = (double) i;
        int castToInt = (int) d;
        Object objCast = (Object) str;
        String strCast = (String) objCast;

        // 26. Method calls and chaining
        obj.basicMethod();
        str.toUpperCase().toLowerCase().trim();

        // 27. Access to nested classes
        OuterClass.NestedClass nested = new OuterClass.NestedClass();
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();

        // 28. Local class
        class LocalClass {
            void localMethod() {
                System.out.println("Local class method");
            }
        }
        LocalClass localObj = new LocalClass();
        localObj.localMethod();

        // 29. Working with collections (pre-generics)
        vector.addElement("string1");
        vector.addElement(new Integer(42));

        hashtable.put("key1", "value1");
        hashtable.put("key2", new Integer(100));

        // Manual casting required
        String retrievedString = (String) hashtable.get("key1");
        Integer retrievedInt = (Integer) hashtable.get("key2");

        // 30. Enumeration usage
        Enumeration enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("Enumeration: " + enumeration.nextElement());
        }
    }

    // 31. Nested classes
    static class NestedClass {
        private String nestedField = "nested";

        public void nestedMethod() {
            System.out.println("Nested class method");
        }
    }

    class InnerClass {
        void innerMethod() {
            // Can access outer class members
            System.out.println("Inner class accessing: " + transientField);
        }
    }

    // 32. Interface declaration
    public interface SampleInterface {
        // Interface fields (implicitly public static final)
        String INTERFACE_CONSTANT = "interface constant";

        // Abstract method (implicitly public abstract)
        void abstractInterfaceMethod();

        // Only abstract methods allowed in interfaces in Java 1.4
        String processData(Object data);
    }

    // 33. Interface implementation
    public class InterfaceImplementation implements SampleInterface {
        public void abstractInterfaceMethod() {
            System.out.println("Implementing abstract interface method");
        }

        public String processData(Object data) {
            return data.toString().toUpperCase();
        }
    }

    // 34. Abstract class
    public abstract class AbstractClass {
        protected String abstractField = "abstract field";

        // Concrete method in abstract class
        public void concreteMethod() {
            System.out.println("Concrete method in abstract class");
        }

        // Abstract method
        public abstract void abstractMethod();

        // Final method cannot be overridden
        public final void finalMethod() {
            System.out.println("Final method in abstract class");
        }
    }

    // 35. Extending abstract class
    public class ConcreteClass extends AbstractClass {
        public void abstractMethod() {
            System.out.println("Implementing abstract method");
        }

        // Overriding concrete method
        public void concreteMethod() {
            super.concreteMethod(); // Call parent method
            System.out.println("Overridden concrete method");
        }
    }

    // 36. Method with all access modifiers
    public void publicMethod() { }
    protected void protectedMethod() { }
    void packagePrivateMethod() { }
    private void privateMethod() { }

    // 37. Method with all possible modifiers available in 1.4
    public static final synchronized strictfp void methodWithAllModifiers() {
        System.out.println("Method with multiple modifiers");
    }

    // 38. Methods demonstrating method overloading
    public void overloadedMethod(int parameter) {
        System.out.println("Integer parameter: " + parameter);
    }

    public void overloadedMethod(String parameter) {
        System.out.println("String parameter: " + parameter);
    }

    public void overloadedMethod(int param1, String param2) {
        System.out.println("Multiple parameters: " + param1 + ", " + param2);
    }

    // 39. Method demonstrating all primitive parameter types
    public void primitiveParameters(boolean b, byte bt, short s, int i,
                                    long l, float f, double d, char c) {
        System.out.println("All primitive types as parameters");
    }

    // 40. Method demonstrating array parameters
    public void arrayParameters(int[] intArray, String[] stringArray,
                                Object[] objectArray) {
        System.out.println("Array parameters method");
    }

    // 41. Method with throws clause
    public void methodWithExceptions() throws IOException,
            IllegalArgumentException {
        throw new IOException("Example exception");
    }

    // 42. Finalize method
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalizing object");
    }
}