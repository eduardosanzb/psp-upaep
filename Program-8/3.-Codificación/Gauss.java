import java.lang.*;

public class Gauss {
    private static final double EPSILON = 1e-10;
    private double[][] A;
    private double[] b;
    private double[] results;
    public Gauss(HistoricData data){
      double[] w = data.getLetterList('w');
      double[] x = data.getLetterList('x');
      double[] y = data.getLetterList('y');
      double[] z = data.getLetterList('z');
      double[][] matrix = {
        {data.size, sumOfList(w), sumOfList(x), sumOfList(y)},
        {sumOfList(w), sumOfProduct(w,w), sumOfProduct(w,x), sumOfProduct(w,y)},
        {sumOfList(x), sumOfProduct(w,x), sumOfProduct(x,x), sumOfProduct(x,y)},
        {sumOfList(y), sumOfProduct(w,y), sumOfProduct(x,y), sumOfProduct(y,y)}
      };
      double [] values = {sumOfList(z), sumOfProduct(w,z), sumOfProduct(x,z), sumOfProduct(y,z)};
      
      this.A = matrix;
      this.b = values;
      this.results = lsolve();

    }
  public double sumOfList(double[] list){
    double sum = 0;
    for (double i : list) {
      sum += i;
    }
    return sum;
  }
  public double sumOfProduct(double[] a, double[] b){
    double sum = 0;
    if(a.length != b.length)
      throw new RuntimeException("Matrix is singular or nearly singular");

    for(int i = 0; i < a.length; i++){
      sum += a[i]*b[i];
    }
    return sum;
  } 
  // Gaussian elimination with partial pivoting
  public double[] lsolve() {
      int N  = b.length;

      for (int p = 0; p < N; p++) {

          // find pivot row and swap
          int max = p;
          for (int i = p + 1; i < N; i++) {
              if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                  max = i;
              }
          }
          double[] temp = A[p]; A[p] = A[max]; A[max] = temp;
          double   t    = b[p]; b[p] = b[max]; b[max] = t;

          // singular or nearly singular
          if (Math.abs(A[p][p]) <= EPSILON) {
              throw new RuntimeException("Matrix is singular or nearly singular");
          }

          // pivot within A and b
          for (int i = p + 1; i < N; i++) {
              double alpha = A[i][p] / A[p][p];
              b[i] -= alpha * b[p];
              for (int j = p; j < N; j++) {
                  A[i][j] -= alpha * A[p][j];
              }
          }
      }

      //print the matrix solved
      // for (int i=0;i<4 ;i++ ) {
      //     for (int j = 0;j<4 ;j++ ) {
      //         System.out.print(A[i][j] + ", ");
      //     }
      //     System.out.println();
      // }

      // back substitution
      double[] x = new double[N];
      for (int i = N - 1; i >= 0; i--) {
          double sum = 0.0;
          for (int j = i + 1; j < N; j++) {
              sum += A[i][j] * x[j];
          }
          x[i] = (b[i] - sum) / A[i][i];
      }
      return x;
  }

  public double beta0(){
    return this.results[0];
  }
  public double beta1(){
    return this.results[1];
  }
  public double beta2(){
    return this.results[2];
  }
  public double beta3(){
    return this.results[3];
  }

 

}
