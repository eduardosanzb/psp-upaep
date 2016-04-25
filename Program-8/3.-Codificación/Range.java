
import java.lang.*;
public class Range {
  private SearchXValue x;
  private Deviation2 sigma;
  private Gauss gauss;
  private double result;
  private double wk, xk, yk;

  public Range(HistoricData data, double[] inputs, Gauss gauss){
    double[] w = data.getLetterList('w');
    double[] x = data.getLetterList('x');
    double[] y = data.getLetterList('y');
    double[] z = data.getLetterList('z');
    double wAvg = data.avgLetterAvg('w');
    double xAvg = data.avgLetterAvg('x');
    double yAvg = data.avgLetterAvg('y');
    int N = data.size;

    this.x = new SearchXValue(0.35,data.size-4);
    //System.out.println("The x value is: " + this.x.search());
    this.sigma = new Deviation2(data, inputs, gauss);
    //System.out.println("The sigma value is: " + sigma.result());
    double wSum = 0;
    double xSum = 0;
    double ySum = 0;
    for (int i = 0; i < data.size; i++) {
      wSum += Math.pow(w[i]-wAvg,2);
      xSum += Math.pow(x[i]-xAvg,2);
      ySum += Math.pow(y[i]-yAvg,2);
    }
    


    double part3 = 1 + (1/N) +( Math.pow(inputs[0]-wAvg,2) / wSum) + ( Math.pow(inputs[1]-xAvg,2) / xSum) + ( Math.pow(inputs[2]-yAvg,2) / ySum);

    this.result = this.x.search() * sigma.result() * Math.sqrt( part3 ) ;

  }

  public double result (){
    return this.result;
  }
}
