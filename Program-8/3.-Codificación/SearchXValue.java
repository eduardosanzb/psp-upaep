// Common Imports
import java.lang.*;
import java.util.*;
import java.text.*;

public class SearchXValue {
  //  SearchXValue is a class to serve as the calculation of the P value
  //  double SearchXValue (double p, double dof)
  //  This class trigger The whole search Algorithm, with the proper
  //    calculations.
  // SearchXValue var = new SearchXValue( p,  dof );
  //    double xValue = var.search()

  //    var.setX(1);
  //    double theX = var.getX();
  //    var.setP(1);
  //    double theP = var.getP();
  //    var.setDof(1);
  //    double theDof = var.getDof();

  private double p;
  private double dof;
  private double x = 1.0;
  private double d = 0.5;
  private double eCalc;
  private double eVal = 0.0001;
  private CalculationP calculationObject;

  public SearchXValue(double p, double dof) {
    /*
     * Strategy:
     *    1. Initialize the variables
     */
    this.p = p;
    this.dof = dof;
  }

  public double search() {
    /*
     * Strategy:
     *    1. Start with a trial value of x (for example, 1.0).
     *    2. Make an initial integral and test to see if it gives the proper value; if not, continue.
     *    3. If it is too low, add d = 0.5 to trial x.
     *    4. If it is too high, subtract d = 0.5 from trial x.
     *    5. Integrate again and test if the result is within an acceptable error; if not, continue.
     *    6. If too low, adjust d; add d to trial x.
     *    7. If too high, adjust d; subtract d from trial x.
     *    8. Recycle at 5.
     * For the D adjusting will be:
     *    1. As long as the tests for the error of the result give the same sign of the error, leave d unchanged.
     *    2. Whenever the sign of the error changes, divide d by 2.
     *    3. Verify that D will not be 0, so if this happens I will set it to 2.
     */

    calculationObject = new CalculationP(this.x, this.eVal, this.dof);
    double theValue = calculationObject.calculate();
    Boolean flagForSearchX = true;
    if(theValue == this.p) {
      //We already finnish because We found the X value for the P
      return this.x;
    } else {
      while(flagForSearchX){
        this.x = (theValue < this.p) ? this.x+d : this.x-d;  //Calibrating the X value
        calculationObject.setX(this.x);
        theValue = calculationObject.calculate();
        this.eCalc = this.p - theValue;
        // We are going to format our error to then compare to the original one
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(4);
        String errorCalculated = df.format(this.eCalc);

        //System.out.println("The errorCalculated is: " + errorCalculated );

        if (errorCalculated.equals(".0001")) {
          // We found a X value that will give us an approximate P value
          //return this.x;
          flagForSearchX = false;
        } else {
          this.d = Math.signum(eCalc) == Math.signum(1) ? this.d: this.d/2;
          this.d = (this.d == 0) ? 2 : this.d;
        }
      }
      return this.x;
    }

  } 
  public double getX(){
    return this.x;
  }
  public void setP( double x ){
    this.x = x;
  }
  public double getP(){
    return this.x;
  }
  public void setX( double x ){
    this.x = x;
  }
  public double getDof(){
    return this.dof;
  }
  public void setDof(double dof){
    this.dof = dof;
  }
  
}