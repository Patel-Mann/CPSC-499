// Comprehensive Java Grammar Reference File
// This file demonstrates most Java language constructs and grammar features

// 1. Package declaration
package com.example.grammar;

// 2. Import statements
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.io.*;
import java.nio.file.*;
import static java.lang.Math.*;
import static java.util.Collections.sort;

/**
 * 3. Class-level documentation comment
 * Comprehensive Java grammar demonstration class
 */
public class JavaGrammarReference {

    // 4. Class-level fields with various modifiers
    public static final String CONSTANT = "CONSTANT_VALUE";
    private static int staticCounter = 0;
    protected volatile boolean flag = false;
    transient String transientField;
    final List<String> finalList = new ArrayList<>();

    // 5. Instance initializer block
    {
        finalList.add("initialized");
        System.out.println("Instance initializer executed");
    }

    // 6. Static initializer block
    static {
        staticCounter = 10;
        System.out.println("Static initializer executed");
    }

    // 7. Constructors
    public JavaGrammarReference() {
        this("default");
    }

    public JavaGrammarReference(String value) {
        super(); // Call to superclass constructor
        this.transientField = value;
    }

    // 8. Method declarations with various signatures
    public void basicMethod() {
        System.out.println("Basic method");
    }

    private static synchronized <T> T genericMethod(T parameter) {
        return parameter;
    }

    protected final void finalMethod() throws IOException, InterruptedException {
        // Method that throws exceptions
    }

    public abstract void abstractMethod(); // Would be in abstract class

    public native void nativeMethod(); // Native method declaration

    // 9. Method with varargs
    public void varargsMethod(String... args) {
        for (String arg : args) {
            System.out.println(arg);
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

        // 13. Object creation
        JavaGrammarReference obj = new JavaGrammarReference("example");
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        // 14. Anonymous class
        Runnable anonymousClass = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous class method");
            }
        };

        // 15. Lambda expressions
        Runnable lambda = () -> System.out.println("Lambda expression");
        Function<String, Integer> stringToInt = Integer::parseInt;
        Consumer<String> printer = System.out::println;

        // 16. Method references
        list.forEach(System.out::println);

        // 17. Conditional statements
        if (bool) {
            System.out.println("If statement");
        } else if (i > 0) {
            System.out.println("Else if statement");
        } else {
            System.out.println("Else statement");
        }

        // 18. Ternary operator
        String result = (i > 0) ? "positive" : "non-positive";

        // 19. Switch statement (traditional)
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

        // 20. Switch expression (Java 14+)
        String dayType = switch (i % 7) {
            case 1, 2, 3, 4, 5 -> "Weekday";
            case 6, 7 -> "Weekend";
            default -> "Unknown";
        };

        // 21. Loops
        // For loop
        for (int j = 0; j < 10; j++) {
            System.out.println("For loop: " + j);
        }

        // Enhanced for loop (for-each)
        for (String element : stringArray) {
            System.out.println("Enhanced for: " + element);
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

        // 22. Exception handling
        try {
            int division = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic exception caught");
        } catch (Exception e) {
            System.out.println("General exception caught");
        } finally {
            System.out.println("Finally block executed");
        }

        // 23. Try-with-resources
        try (FileInputStream fis = new FileInputStream("test.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line = br.readLine();
        } catch (IOException e) {
            System.out.println("IO Exception in try-with-resources");
        }

        // 24. Assertions
        assert i > 0 : "i should be positive";

        // 25. Synchronized block
        synchronized (obj) {
            System.out.println("Synchronized block");
        }

        // 26. Operators
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

        // 27. instanceof operator
        if (obj instanceof JavaGrammarReference) {
            System.out.println("obj is instance of JavaGrammarReference");
        }

        // 28. Type casting
        double castToDouble = (double) i;
        int castToInt = (int) d;

        // 29. Generics
        List<Integer> integerList = new ArrayList<Integer>();
        Map<String, List<Integer>> complexGeneric = new HashMap<>();

        // 30. Wildcards
        List<? extends Number> wildcardList = new ArrayList<Integer>();
        List<? super Integer> superWildcard = new ArrayList<Number>();

        // 31. Annotations usage
        @SuppressWarnings("unchecked")
        List rawList = new ArrayList();

        // 32. Method calls and chaining
        obj.basicMethod();
        str.toUpperCase().toLowerCase().trim();

        // 33. Access to nested classes
        OuterClass.NestedClass nested = new OuterClass.NestedClass();
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();

        // 34. Local class
        class LocalClass {
            void localMethod() {
                System.out.println("Local class method");
            }
        }
        LocalClass localObj = new LocalClass();
        localObj.localMethod();
    }

    // 35. Nested classes
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

    // 36. Enum declaration
    public enum Status {
        ACTIVE("Active Status"),
        INACTIVE("Inactive Status"),
        PENDING("Pending Status");

        private final String description;

        Status(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        // Enum with methods
        public static Status fromString(String text) {
            for (Status status : Status.values()) {
                if (status.description.equalsIgnoreCase(text)) {
                    return status;
                }
            }
            return null;
        }
    }

    // 37. Interface declaration
    public interface SampleInterface {
        // Interface fields (implicitly public static final)
        String INTERFACE_CONSTANT = "interface constant";

        // Abstract method (implicitly public abstract)
        void abstractInterfaceMethod();

        // Default method (Java 8+)
        default void defaultMethod() {
            System.out.println("Default interface method");
        }

        // Static method in interface (Java 8+)
        static void staticInterfaceMethod() {
            System.out.println("Static interface method");
        }

        // Private method in interface (Java 9+)
        private void privateHelperMethod() {
            System.out.println("Private interface method");
        }
    }

    // 38. Functional interface
    @FunctionalInterface
    public interface CustomFunctionalInterface<T> {
        T process(T input);

        default void defaultProcessing() {
            System.out.println("Default processing");
        }
    }

    // 39. Record declaration (Java 14+)
    public record Person(String name, int age, String email) {
        // Compact constructor
        public Person {
            if (age < 0) {
                throw new IllegalArgumentException("Age cannot be negative");
            }
        }

        // Additional constructor
        public Person(String name, int age) {
            this(name, age, "unknown@example.com");
        }

        // Instance method in record
        public boolean isAdult() {
            return age >= 18;
        }

        // Static method in record
        public static Person createDefault() {
            return new Person("Unknown", 0);
        }
    }

    // 40. Text blocks (Java 15+)
    public static final String TEXT_BLOCK = """
        This is a text block
        that can span multiple lines
        and preserves formatting
        """;

    // 41. Pattern matching for instanceof (Java 16+)
    public static void patternMatchingExample(Object obj) {
        if (obj instanceof String s) {
            System.out.println("String length: " + s.length());
        } else if (obj instanceof Integer i) {
            System.out.println("Integer value: " + i);
        }
    }

    // 42. Sealed class declaration (Java 17+)
    public abstract sealed class Shape
            permits Circle, Rectangle, Triangle {
        abstract double area();
    }

    public final class Circle extends Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        double area() {
            return PI * radius * radius;
        }
    }

    public final class Rectangle extends Shape {
        private final double width, height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        double area() {
            return width * height;
        }
    }

    public non-sealed class Triangle extends Shape {
        private final double base, height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        double area() {
            return 0.5 * base * height;
        }
    }

    // 43. Method with complex generic bounds
    public static <T extends Comparable<T> & Serializable> void complexGenericMethod(T item) {
        System.out.println("Complex generic method: " + item);
    }

    // 44. Method demonstrating all access modifiers
    public void publicMethod() { }
    protected void protectedMethod() { }
    void packagePrivateMethod() { }
    private void privateMethod() { }

    // 45. Method with all possible modifiers
    public static final synchronized strictfp void methodWithAllModifiers() {
        System.out.println("Method with multiple modifiers");
    }
}