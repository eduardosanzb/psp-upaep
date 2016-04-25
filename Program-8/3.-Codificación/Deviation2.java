import java.lang.*;

public class Deviation2 {
  double wk, xk, yk;
  Gauss betas;
  int N;
  double sum;
  double firstElement;
  
  public Deviation2(HistoricData data, double[] inputs, Gauss g){
    this.betas = g;
    this.wk = inputs[0];
    this.xk = inputs[1];
    this.yk = inputs[2];
    N = data.size;
    this.sum = 0;
    this.firstElement  = 1/ ((double)N - 4) ;
    double[] w = data.getLetterList('w');
    double[] x = data.getLetterList('x');
    double[] y = data.getLetterList('y');
    double[] z = data.getLetterList('z');  

    for (int i = 0; i < N; i++) {
      double a = betas.beta1()*w[i];
      //System.out.println("a " + a);
      double b = betas.beta2()*x[i];
      //System.out.println("b " + b);
      double c = betas.beta3()*y[i];
      //System.out.println("c " + c);
      double after = z[i] - betas.beta0() - a - b - c;
      after = Math.pow(after,2);
      //System.out.println("after " + after);
      this.sum +=  after;
    }
  }
  
  public double result(){
    //System.out.println(this.firstElement * this.sum);
    return Math.sqrt(this.firstElement * this.sum);
  }

}

