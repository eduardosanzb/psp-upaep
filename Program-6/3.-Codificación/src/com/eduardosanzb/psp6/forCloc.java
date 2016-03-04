class Simpson{
  //  Simpson is a class that calculate the area under a function from 0 to x
  //  double Simpson (double num_segs, double e, double x, double dof)
  //  No error handlig
  //  Simpson with10 = New Simpson(10, 0.00001, 1.1, 9);
  //    double result = with10.calculateP();
  // Please add the error handling.
  // Ver 1.0 2016/02/03 09:11:25  esb

  private double num_segs;  //Number of segments of the Sum.
  private double W;       
  private double errorVar;  
  private double dof;
  private double x;

  public Simpson( double num_segs, double errorVar, double x, double dof){
    this.num_segs = num_segs;
    this.errorVar = errorVar;
    this.x = x;
    this.dof = dof;
    this.W = x / num_segs;
  }

  public double calculateP(){
    /*
     * Strategy:
     *    1. Create a tDistribution for f0 = new tDistribution(0, dof);
     *    2. Create a tDistribution for fx = new tDistribution(x, dof);
     *    3. return f0.calculateP() + fx.calculateP() + sumEven() + sumOdd;
     */

    // 1. && 2.-
    TDistribution f0 = new TDistribution(0, this.dof);
    TDistribution fx = new TDistribution(this.x, this.dof);

    return (W/3) * ( f0.calculateDistribution() + fx.calculateDistribution() + this.sumEven() + this.sumOdd() );
  }

  private double sumEven(){
    /*
     * Strategy:
     *    1. Create the vars of t & acum
     *    2. set the dof in t
     *    3. Cycle for the even numbers
     *    4. Set x in t
     *    5. Acum the calcDistribution
     *    6. Return the acum * 4
     */

    //1.-
    TDistribution t = new TDistribution();
    double acum = 0;

    //2.-
    t.setDof( this.dof );

    //3.-
    for(int i = 1; i <= (num_segs-1); i+=2){

      //4.-
      t.setX( i * W );

      //5.-
      
      acum += t.calculateDistribution();
    }

    //6.-
    //System.out.println(acum);
    return 4 * acum;
  }

  private double sumOdd(){
    /*
     * Strategy:
     *    1. Create the vars of t & acum
     *    2. set the dof in t
     *    3. Cycle for the even numbers
     *    4. Set x in t
     *    5. Acum the calcDistribution
     *    6. Return the acum * 4
     */

    //1.-
    TDistribution t = new TDistribution();
    double acum = 0;

    //2.-
    t.setDof( this.dof );

    //3.-
    for(int i = 2; i <= (num_segs-2); i+=2){

      //4.-
      t.setX( i * W );

      //5.-
      acum += t.calculateDistribution();
    }

    //6.-
    return 2 * acum;
  }

  public double getNumSegs(){
    return this.num_segs;
  }
  public void setNumSegs(double num_segs){
    this.num_segs = num_segs;
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