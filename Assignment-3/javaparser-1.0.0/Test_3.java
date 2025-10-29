public class Test_3{
  public int search(int [][] matrix, int to_search){
    for(int i = 0; i < matrix.lenght; i++){
      for(int j = 0; j < matrix[i].legth; j++) {
        if (matrix[i][j] == to_search){
          return true;
        }
      }
    }
    return false;

    boolean result = this.contains(new int[][] {
      {1,2,3},
      {22,42,45},
      {38,26,16}}, 42);
  }
    
