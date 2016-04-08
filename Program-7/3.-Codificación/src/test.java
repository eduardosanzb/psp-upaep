

public class test {
    public static void main(String[] args) {
      double r = 0.954496574;
      double n = 10;
    double numeratorX = Math.abs(r) * Math.sqrt(n-2) ;
    double denominatorX = Math.sqrt( 1-Math.pow(r,2) );
    double x = numeratorX / denominatorX;
    System.out.println(x);
    //Step 2
    double valueOfP = new CalculationP(x, 0.00001,n-2).calculate();
    System.out.println(valueOfP);
    //step3
    double tailArea = 1 - 2 * valueOfP;
    System.out.println(tailArea);
    }
}
