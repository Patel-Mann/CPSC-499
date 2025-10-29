public class Test {
    public int methodForLoop() {
        int val = 1;

        for (int i = 0; i < 10; i++) {
            val += 1;
            if (val == 5) {
                val = 5;
                val = 6;
            } else if(val < 5) {
                val = 3;
            }
            if(1 == 1 && 2 == 2) {return 0;} else {return 1;}


        }
        return val;
    }

    int result = this.methodForLoop();



}