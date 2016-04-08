// File:         Student.java
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
import java.util.ArrayList;

public class Student {
  /*    Student is a class to be an instance of a Student with his programs
   *
   *       Program program (Double proxySize, Double planAdded, Double actualAdded, Double actualTime)
   */

  private int numberOfPrograms;
  private ArrayList<Program> programsList;
  public double theProxySize;
  public String type = "Dynamic";

  public Student(){
    numberOfPrograms = 0;
    this.programsList = new ArrayList<Program>();
  }
  public Student(Program p){
    numberOfPrograms = 0;
    this.programsList = new ArrayList<Program>();
    this.appendProgram(p);
  }
  public Student(ArrayList<Program> list){
    numberOfPrograms = list.size();
    this.programsList = list;
  }

  /*METHODS SECTION*/
  public boolean isEmpty(){
    if(numberOfPrograms == 0){
      return false;
    }
    return true;
  }

  public void appendProgram(Program p){
    this.programsList.add(p);
    numberOfPrograms++;
  }

  public Program dropProgram(int index){
    Program temp = this.programsList.get(index);
    this.programsList.remove(index);
    numberOfPrograms--;
    return temp;
  }

  public void showProgramsInConsole(){
    int i = 1;
    for(Program p : this.programsList){
      System.out.println("Program number: " + i);
      System.out.println(p.getProxySize());
      System.out.println(p.getPlanAdded());
      System.out.println(p.getActualAdded());
      System.out.println(p.getActualTime());
      i++;
    }
  }
  public ArrayList<Double> getProxies(){
    ArrayList<Double> list = new ArrayList<Double>(numberOfPrograms);
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      list.add( this.programsList.get(i).getProxySize() );
    }
    return list;
  }
  public ArrayList<Double> getPlanSizes(){
    ArrayList<Double> list = new ArrayList<Double>(numberOfPrograms);
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      list.add( this.programsList.get(i).getPlanAdded() );
    }
    return list;
  }
  public ArrayList<Double> getActualSizes(){
    ArrayList<Double> list = new ArrayList<Double>(numberOfPrograms);
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      list.add( this.programsList.get(i).getActualAdded() );
    }
    return list;
  }
  public ArrayList<Double> getDevTimes(){
    ArrayList<Double> list = new ArrayList<Double>(numberOfPrograms);
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      list.add( this.programsList.get(i).getActualTime() );
    }
    return list;
  }

  public double proxyAvg(){
    double acum = 0.0;
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      acum += this.programsList.get(i).getProxySize();
    }
    return acum/numberOfPrograms;
  }
  public double planSizeAvg(){
    double acum = 0.0;
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      acum += this.programsList.get(i).getPlanAdded();
    }
    return acum/numberOfPrograms;
  }
  public double actualSizeAvg(){
    double acum = 0.0;
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      acum += this.programsList.get(i).getActualAdded();
    }
    return acum/numberOfPrograms;
  }
  public double devTimeAvg(){
    double acum = 0.0;
    for (int i = 0; i < numberOfPrograms ; i++ ) {
      acum += this.programsList.get(i).getActualTime();
    }
    return acum/numberOfPrograms;
  }

  
}
