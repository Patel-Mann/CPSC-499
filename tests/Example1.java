public class Example1 {
    public void test() {
        int x = 5;
        int y = x + 10;
        int z = y * 2;
        int result = z + x;
    }
    public void test1(int a) {
        int x = 5;
        int y = 0;

        if (a > 10) {
            y = x + a;
        } else {
            y = x - a;
        }

        int result = y * 2;
    }
    public void test2() {
        int sum = 0;
        int i = 0;

        while (i < 10) {
            sum = sum + i;
            i = i + 1;
        }
    }
}
