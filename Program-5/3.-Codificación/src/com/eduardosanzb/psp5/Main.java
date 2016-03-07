// File:         Main.java
// Created:      2016/02/03 
// Last Changed: $Date: 2016/02/03 09:11:25 $
// Author:       <A HREF="mailto:eduardosanzb@gmail.com">Eduardo Sanchez</A>
//
// License MIT GNU
// 
// History:
//  $Log: javaCodingStd.html,v $
//  Revision 1.0  2016/02/03 09:11:25  esb
//  initial impound
package com.eduardosanzb.psp5;

//Common imports
import java.lang.*;
import java.util.*;
import java.io.*;
import java.text.*;
// Imports for GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends JFrame {
  //  This is gonna be the GUI for the program
  //  Main()

  //Logic Variables
    private Simpson s10;
    private Simpson s20;
    private static final double E = 0.00001;
  //GUI Variables
    private String[] columns = {"n","x","dof","Expected p","Actual p"};
    private Object[][] data = {
      {"1","0 to x=1.1","9","0.35006", 0},
      {"2","0 to x=1.812","10","0.36757", 0},
      {"3","0 to x=2.750","30","0.49500", 0}
    };
    private JTable table;
    private JButton calculate;
    private JLabel test, expectedLabel, actualLabel;
    private JScrollPane scrollPane;

  public Main(){
    
    //We declare the size of our container JFRame
    this.setSize(400,400);
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
    ListenForButton lForButton = new ListenForButton();
    calculate.addActionListener(lForButton);

    //labels setup
    this.test = new JLabel("TEST");
    this.expectedLabel = new JLabel("Expected Value");
    this.actualLabel = new JLabel("Actual Value");

    //Add the scroll pane
    this.scrollPane = new JScrollPane(table);

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

  public double auxCalc(double x, double dof, int row){
    s10 = new Simpson(10, E, x, dof);
    //s10.calculateP();
    s20 = new Simpson(20, E, x, dof);
    if ( ( s20.calculateP() - s10.calculateP() ) < E )  {
        table.getModel().setValueAt( s20.calculateP(),row,4 );
        return s20.calculateP();
    } else {
      System.out.println("Error calculating the P");
      return 0;
    }
  }

private class ListenForButton implements ActionListener{

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == calculate){
      //auxCalc(0.5,6,0);
      //auxCalc(1.1812,10,1);
      //auxCalc(2.750,30,2);
    }
  }

  public double auxCalc(double x, double dof, int row){
    s10 = new Simpson(10, E, x, dof);
    //s10.calculateP();
    s20 = new Simpson(20, E, x, dof);
    if ( ( s20.calculateP() - s10.calculateP() ) < E )  {
        table.getModel().setValueAt( s20.calculateP(),row,4 );
        return s20.calculateP();
    } else {
      System.out.println("Error calculating the P");
      return 0;
    }
  }
}

  public static void main(String[] args) {
    System.out.println("The test case:");
    System.out.println("The error of acceptance is: 0.00001");
    System.out.println("The P value is: 0.20");
    System.out.println("The expected X is: 0.55338");
    Main test = new Main();
    Scanner sc = new Scanner(System.in);
    double val = 1;
    double d = 0.5;
    double pVal = test.auxCalc(val,6,0);
    double eCalc = 0.20 - pVal;
    String eFlag = "lol";
    Boolean flaggy = true;
    while(flaggy){
      System.out.println("********");
      eCalc = 0.20 - pVal;
      d = ( Math.signum(eCalc) == Math.signum(1) )? d: d/2;
      val = (pVal < 0.20)? val + d: val - d; 
      pVal = test.auxCalc(val,6,0);
      System.out.println("The x value is: " + val);
      System.out.println("The p value is: " + pVal);
      System.out.println("Error value: " + Math.floor(eCalc));
      String flag = pVal < 0.20 ?"Add":"Subtract";
      System.out.println("Add or subtract? " + flag);

      DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(4);
        System.out.println(df.format(eCalc));
        System.out.println(eCalc);
        eFlag = df.format(eCalc);
      Boolean mmm = Math.floor(eCalc) == 0.0001;
      System.out.println("The errors are wequal? " + mmm);
      System.out.println("The errors are the same sign?" + ( Math.signum(eCalc) == Math.signum(1) ) );
      flaggy = eFlag.equals(".0001") ? false: true;
      System.out.println("The flag is:" + eFlag + "So the bool shoudl be: " + (eFlag.equals(".0001")));
      //sc.nextLine();
    }
  }
    
}


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