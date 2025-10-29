public class Test_0 {
    public int methodForLoop() {
        int val = 1;

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

    int result = this.methodForLoop();



}
