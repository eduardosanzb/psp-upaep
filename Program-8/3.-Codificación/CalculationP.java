
public class CalculationP {
  //  CalculationP is a class to calculate Simpson values
  //  void CalculationP (double x, double eVal, double dof)
  //  This class trigger an error when you are a bad boy.
  // CalculationP var = new CalculationP( x, E, dof );
  //    double something = var.calculate()
  //    double xValue = var.search()

  //    var.setX(1);
  //    double theX = var.getX();
  //    var.setDof(1);
  //    double theDof = var.getDof();

  private Simpson s10;
  private Simpson s20;
  private double x;
  private double eVal = 0.0001;
  private double dof;

  public CalculationP(double x, double eVal, double dof) {
    /*
     * Strategy:
     *    1. Initialize the variables
     */
    this.x = x;
    this.eVal = eVal;
    this.dof = dof;
  }

  public double calculate() {
    /*
     * Strategy:
     *    1. Create the Simpson objects for 10 and 20 traces
     *    2. Calculate the Simpson value
     *    3. Compare the subtraction of 20 - 10 with the error value
     *    4. If true return value
     *    5. If false print the error
     */
    s10 = new Simpson(10, this.eVal, this.x, this.dof);
    s20 = new Simpson(10, eVal, x, dof);
    //System.out.println(s10.calculateP());
    // double 10P = s10.calculateP();
    // double 20P = s20.calculateP();

    if (s20.calculateP() - s10.calculateP() > eVal) {
      
      System.out.println("Error calculating the Simpson Value for: " + x);
    }
    
    return s20.calculateP();
  }

  public double getX(){
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