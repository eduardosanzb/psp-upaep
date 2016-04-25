import java.lang.*;

public class MainZ {
  
  
    public static void main(String[] args) {
      System.out.println();
      HistoricData data = new HistoricData();

      double[] inputs = {185,150,45};
      double[] w1 = {345, 168, 94, 187, 621, 255};
      double[] x1 = {65, 18, 0, 185, 87, 0};
      double[] y1 = {23, 18, 0, 98, 10, 0};
      double[] z1 = {31.4, 14.6, 6.4, 28.3, 42.1, 15.3};
      data.addMultiplePrograms(w1, x1, y1, z1);
      Gauss gauss = new Gauss(data);
      Range range = new Range(data, inputs, gauss);
      double z = gauss.beta0() + (inputs[0]*gauss.beta1() ) + (inputs[1]*gauss.beta2() ) + (inputs[2]*gauss.beta3() );
      System.out.println("Test 1");
      System.out.println("-----------------");
      System.out.println("Beta0: " + gauss.beta0());
      System.out.println("Beta1: " + gauss.beta1());
      System.out.println("Beta2: " + gauss.beta2());
      System.out.println("Beta3: " + gauss.beta3());
      System.out.println("Projected Hours: " + z);
      System.out.println("Range: " + range.result());
      System.out.println("UPI (70%): " + (range.result() + z) );
      System.out.println("LPI (70%): " + (z - range.result()) );


      System.out.println();
      HistoricData data2 = new HistoricData();

      double[] inputs2 = {650,3000,155};
      double[] w2 = {1142, 863, 1065, 554, 983, 256};
      double[] x2 = {1060, 995, 3205, 120, 2896, 485};
      double[] y2 = {325, 98, 23, 0, 120, 88};
      double[] z2 = {201, 98, 162, 54, 138, 61};
      data2.addMultiplePrograms(w2, x2, y2, z2);
      Gauss gauss2 = new Gauss(data2);
      Range range2 = new Range(data2, inputs2, gauss2);
      double tz2 = gauss2.beta0() + (inputs2[0]*gauss2.beta1() ) + (inputs2[1]*gauss2.beta2() ) + (inputs2[2]*gauss2.beta3() );
      System.out.println("Test 2");
      System.out.println("-----------------");
      System.out.println("Beta0: " + gauss2.beta0());
      System.out.println("Beta1: " + gauss2.beta1());
      System.out.println("Beta2: " + gauss2.beta2());
      System.out.println("Beta3: " + gauss2.beta3());
      System.out.println("Projected Hours: " + tz2);
      System.out.println("Range: " + range2.result());
      System.out.println("UPI (70%): " + (range2.result() + tz2) );
      System.out.println("LPI (70%): " + (tz2 - range2.result()) );


    }
}
