
class TDistribution {
  //  TDistribution helps out to calculate the distribution of the x value
  //  double TDistribution (double x, double dof)
  //  No error handlig int his version
  // Tdistribution newT = new TDistribution( 1.1, 9 );
  //    double p = newT.calculatedistribution()
  // Plaease improve error hndlign in the next versions
  // Ver 1.0 2016/02/03 09:11:25  esb
  private double x;
  private double dof;
  private static final double PI = 3.141516;

  

  private double gamma( double x){
    /*
     * Strategy:
     *    FIRST check if is an integer... Dafuq with the instructions!!! =(
     *    1. Pre kickback with x == 1
     *    2. Kickback with x = .5
     *    3. Recursive call
     */
    if ((x == Math.floor(x)) && !Double.isInfinite(x)) {
    // integral type
      if( x == 1){
        return 1;
      } else {
        return (x-1) * gamma(x-1);
      }
    } else {
        if (x == 1){
        return 1 * gamma(0.5);
      } else if( x == 0.5){
        return Math.sqrt(PI);
      } else {
        return (x-1) * gamma(x-1);
      }
    }
    

  }

  public double calculateDistribution(){
    /*
     * Strategy:
     *    1. Calc 1+(pow(x,2) / dof)
     *    2. calc pow( (ans), -((dof+1)/2))
     *    3. calc g((dof+1)/2)   /  pow(dof*PI,2) * g(dof/2)
     *    4. return value of (2.-)*(3.-)
     */

    //1.-

    double itemBase = 1 + ( Math.pow(this.x,2)/this.dof );
    

    //Auxiliar exponent var for 2.-
    double itemExponent = (-1)*( (dof+1) / 2 );
    
    //2.-
    double itemComplete = Math.pow( itemBase, itemExponent);

    //3.-
    double auxDenominator = Math.pow( (dof*PI),0.5 );
    
    double itemObelus = ( gamma( (dof+1)/2 ) ) / (auxDenominator * gamma(dof/2) );
    
    //4.-
    return itemComplete * itemObelus;
  }


}
