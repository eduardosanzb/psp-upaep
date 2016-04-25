// File:         [File name]
// Created:      [yyyy/mm/dd creation date]
// Last Changed: $Date: 2000/08/09 15:15:25 $
// Author:       <A HREF="mailto:[Email address]">[Name]</A>
//
// License
// 
// History:
//  $Log: javaCodingStd.html,v $
//  Revision 1.1.1.1  2000/08/09 15:15:25  adamkmy
//  initial impound
//
import java.lang.*;
import java.util.*;
public class HistoricData {
    double[] w,x,y,z;
    int size;

    public HistoricData(){
      w = new double[100];
      x = new double[100];
      y = new double[100];
      z = new double[100];
      size = 0;
    }
    public void addProgram(double w, double x, double y, double z){
      this.w[size] = w;
      this.x[size] = x;
      this.y[size] = y;
      this.z[size] = z;
      size++;
    }
    public void addMultiplePrograms(double[] w, double[] x, double[] y, double[] z){
      if(w.length != x.length && y.length != x.length)
        throw new RuntimeException("Matrix is singular or nearly singular");
      for (int i = 0;i<w.length ;i++ ) {
        this.w[i] = w[i];
        this.x[i] = x[i];
        this.y[i] = y[i];
        this.z[i] = z[i];
        size++;
      }  
    }
    public double avgLetterAvg(char letter){
      switch(letter){
        case 'w':
            return avg(this.w);
          //break;
        case 'x':
          return avg(this.x);
          //break;
        case 'y':
          return avg(this.y);
          //break;
        case 'z':
          return avg(this.z);
          //break;
        default:
          throw new RuntimeException("Matrix is singular or nearly singular");
          //break;
      }
    }
    public double[] getLetterList(char letter){
      switch(letter){
        case 'w':
            return this.w;
          //break;
        case 'x':
          return this.x;
          //break;
        case 'y':
          return this.y;
          //break;
        case 'z':
          return this.z;
          //break;
        default:
          throw new RuntimeException("Matrix is singular or nearly singular");
          //break;
      }
    }
    private double avg(double[] list){
      double acum = 0;
      for (int i = 0; i < list.length; i++) {
        acum += list[i];
      }
      return acum;
    }

}
