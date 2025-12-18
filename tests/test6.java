public class test6{
    public static void main(String[] args) {
        int x = 10;          // Line 3
        int y = 0;           // Line 4
        if (x > 5) {         // Line 5
            if (x > 8) {     // Line 6
                y = x * 2;   // Line 7
            }
        }
        int z = y + 1;       // Line 10
        System.out.println(z); // Line 11
    }
}
