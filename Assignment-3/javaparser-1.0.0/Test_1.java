public class Test_1{
  public int calculate(int x){
    switch(x){
      case 1: x += 10;
      break;
      case 2: x *= 2:
      break;
      default: x = -1;
    }
    try{
      x = riskyOp(x);
    } catch (Exception e){
      x = 0;
    }
    return finalValue(x);
  }
  int riskyOp(int input) throws Exception {
    if (input < 0){
      throw new Exception("Negative input");
    }
    return input + 5;
  }
  int finalValue(int val){
    return val*2;
  }
public static void main(String[] args){
  Test_1 obj = new Test_1();
int result = obj.calculate(2);
  System.out.println("Result: " + result);}
}
