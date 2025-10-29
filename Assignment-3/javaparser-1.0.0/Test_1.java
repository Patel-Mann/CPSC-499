public class test_1{
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
  private int riskyOp(int input) Exception {
    if (input < 0){
      throw new Exception("Negative input");
    }
    return input + 5;
  }
  private int finalValue(int val){
    return val*2;
  }

int result = this.calculate(2);
}
