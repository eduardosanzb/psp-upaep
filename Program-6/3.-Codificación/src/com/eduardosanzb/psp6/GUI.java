// File:         GUI.java
// Created:      03/04/2016 
// Last Changed: $Date: 2016/02/03 09:11:25 $
// Author:       <A HREF="mailto:eduardosanzb@gmail.com">Eduardo Sanchez</A>
//
// License MIT GNU
// 
// History:
//  $Log: javaCodingStd.html,v $
//  Revision 1.0  03/04/2016 10:02:25  esb
//  initial impound
package com.eduardosanzb.psp6;

// Common Imports
import java.lang.*;
import java.util.*;
import java.text.*;
// GUI imports
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class GUI extends JFrame{
  //  GUI is a class to create the Interface for the user
  //  gui GUI ()
  //  This class dont have any error or event handlers
  // GUI var = new GUI( );
  //    var GUI = new var.GUI()

  //GUI Variables
    private String[] columns = {"n","p","dof","Expected X","Actual X"};
    private Object[][] data = {
      {"1","0.20","6","0.55338", 0},
      {"2","0.45","15","1.75305", 0},
      {"3","0.495","4","4.60409", 0}
    };
    private JTable table;
    private JButton calculate;
    private JLabel test, expectedLabel, actualLabel;
    private JScrollPane scrollPane;

    public GUI() {
      //We declare the size of our container JFRame
      this.setSize(400,200);
      this.setLocationRelativeTo(null);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel thePanel = new JPanel();

      //We initialize our objects for the GUI
      //Table setup
      this.table = new JTable(data, columns);
      table.setPreferredScrollableViewportSize(table.getPreferredSize());
      table.setFillsViewportHeight(true);

      //Button setup
      this.calculate = new JButton("Calcular!!");
      ListenerForButton lForButton = new ListenerForButton();
      calculate.addActionListener(lForButton);

      //Add the scroll pane
      this.scrollPane = new JScrollPane(table);

      //labels setup
      this.test = new JLabel("TEST");
      this.expectedLabel = new JLabel("Expected Value");
      this.actualLabel = new JLabel("Actual Value");
      //Adding the elements to the frame
      thePanel.add(test);
      thePanel.add(expectedLabel);
      thePanel.add(actualLabel);
      thePanel.add(scrollPane);
      thePanel.add(calculate);

      //Add the panel to the Fram and put it visible
      this.add(thePanel);
      this.setVisible(true);
    }

    public static void main(String[] args) {
        
        GUI theGui = new GUI();

    }

  private class ListenerForButton implements ActionListener {
    //  ListenerForButton is a class to perform the actual calculations.
    //  lForButton ListenerForButton ()
    //  This class trigger the whole search algorithm that
    //    is the purpose of this program
    // ListenerForButton var = new ListenerForButton(  );
    //    ListenerForButton something = var.actionPerformed()
    
    SearchXValue searcher0;
    SearchXValue searcher1;
    SearchXValue searcher2;

    public void actionPerformed(ActionEvent e){
      /*
     * Strategy:
     *    1. Initialize each of the searchers for each test case
     *    2. Change the value in the table for each row
     */

      if(e.getSource() == calculate){
        searcher0 = new SearchXValue(0.20, 6);
        searcher1 = new SearchXValue(0.45, 15);
        searcher2 = new SearchXValue(0.495, 4);

        table.getModel().setValueAt(searcher0.search(), 0, 4);
        table.getModel().setValueAt(searcher1.search(), 1, 4);
        table.getModel().setValueAt(searcher2.search(), 2, 4);
      }
    }
  }    
}

class SearchXValue {
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

        System.out.println("The errorCalculated is: " + errorCalculated );

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

class CalculationP {
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

/* REUSE CODE */
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

  public TDistribution(double x, double dof){
    
    this.x = x;
    this.dof = dof;
  }

  public TDistribution(){
    this.x = 0;
    this.dof = 0;
  }

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