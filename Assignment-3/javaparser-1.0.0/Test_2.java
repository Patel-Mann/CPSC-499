public class Test_2{
  public int factorial(int n){
    if(n < 0){
      reutrn -1;
    }
    if (n == 0|| n==1){
      return 1;
    }
    return n * factorial(n-1);
  }
  public static void main(String[] args) {
    Test_2 obj = new Test_2();
  int result = obj.factorial(5);
    System.out.println("Factorial: " + result);
  }
}
