// File:         Program.java
// Created:      [04/06/2016 creation date]
// Author:       <A HREF="mailto:[eduardosanzb@gmail.com]">[Eduardo Sanchez]</A>
//
// License MIT
// 
// History:
//  Revision 1.1.1.1  04/06/2016  esb
//  initial impound
//
import java.lang.*;

public class Program {
  /*    Program is a class to be an instance of a program of the student
   *
   *       Program program (Double proxySize, Double planAdded, Double actualAdded, Double actualTime)
   */

  private double proxySize, planAdded, actualAdded, actualTime;

    /*CONSTRUCTORS SECTION*/
    public Program(){}
    public Program( double proxySize, double planAdded, double actualAdded, double actualTime){
      this.proxySize = proxySize;
      this.planAdded = planAdded;
      this.actualAdded = actualAdded;
      this.actualTime = actualTime;
    }

    /*SET AND GET OF ALL ATTRIBUTES SECTION*/
    public void setProxySize(double val){
      this.proxySize = val;
    }
    public double getProxySize(){
      return this.proxySize;
    }
    public void setPlanAdded(double val){
      this.planAdded = val;
    }
    public double getPlanAdded(){
      return this.planAdded;
    }
    public void setActualAdded(double val){
      this.actualAdded = val;
    }
    public double getActualAdded(){
      return this.actualAdded;
    }
    public void setActualTime(double val){
      this.actualTime = val;
    }
    public double getActualTime(){
      return this.actualTime;
    }
}
