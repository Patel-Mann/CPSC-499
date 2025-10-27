public class Test {
    public void methodOne() {
        class Helper234 {
            int val = 1;
            int compute() {
                for (int i = 0; i < 10; i++) {
                    val += 1;
                    if (val == 5) {
                        val = 5;
                    } else if (val < 5) {
                        val = 3;
                    }
                }
                return val;
            }
        }

        // Example of using Helper234 inside methodOne
        Helper234 helper = new Helper234();
        int result = helper.compute();
        System.out.println("Result: " + result);
    }

    void methodTwo() {
        System.out.println("Hello");
    }
}