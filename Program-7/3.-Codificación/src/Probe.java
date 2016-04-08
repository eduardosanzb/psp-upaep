import java.lang.*;
import java.util.ArrayList;


public class Probe {

  public Probe(){
  }

  public double calculateBeta0(double beta1, ArrayList<Double> xList, ArrayList<Double> yList){
    double xAvg = sumatoryOfList(xList) / xList.size();
    double yAvg = sumatoryOfList(yList) / yList.size();
    return yAvg - (beta1 * xAvg);
  }
  public double calculateBeta1( ArrayList<Double> xList, ArrayList<Double> yList){
    double xAvg = sumatoryOfList(xList) / xList.size();
    double yAvg = sumatoryOfList(yList) / yList.size();
    double numerator = sumatoryOfList( multiplyLists(xList, yList) ) - (xList.size() * xAvg * yAvg);
    double denominator = sumatoryOfList( multiplyLists(xList, xList) ) - (xList.size() * xAvg * xAvg);
    return numerator / denominator;
  }
  public double calculateR( ArrayList<Double> xList, ArrayList<Double> yList){
    double numerator = ( xList.size() * ( sumatoryOfList( multiplyLists(xList, yList) ) ) ) - ( sumatoryOfList(xList) *sumatoryOfList(yList) );

    double denominator1 = xList.size() * sumatoryOfList(multiplyLists(xList,xList)) - (Math.pow(sumatoryOfList(xList),2) );
    double denominator2 = yList.size() * sumatoryOfList(multiplyLists(yList,yList)) - (Math.pow(sumatoryOfList(yList),2) );
    double denominator = denominator1 * denominator2;
    return numerator / Math.sqrt(denominator);
  }
  public double calculateY(double beta0, double beta1, double xk){

    return beta0 + beta1 * xk;
  }
  public double calculateTailArea(double r, ArrayList<Double> list){
    /*  Strategy:
     *  1. Calculate X with the formula value
     *  2. Calculate P for that x with a dof = n-2
     *  3. Now we can return the calculation of the tailArea as 1*2*P
     */
    //Step 1
    double numeratorX = Math.abs(r) * Math.sqrt(list.size()-2) ;
    double denominatorX = Math.sqrt( 1-Math.pow(r,2) );
    double x = numeratorX / denominatorX;
    //System.out.println(x);
    //Step 2
    double valueOfP = new CalculationP(x, 0.00001,list.size()-2).calculate();
    //System.out.println(valueOfP);
    //step3
    double tailArea = 1 - 2 * valueOfP;
    return tailArea;
  }
  public double sigmaValue(ArrayList<Double> xList, ArrayList<Double> yList, double beta0, double beta1){
    /*  Strategy:
     *  1. Calculathe the first part of the sqrt (1/n-2)
     *  2. Caculate the value of the summatory and pow it to 2
     *  3. Return the sqr of the multiplication of the above
     */
    //step 1
    //System.out.println(beta0);
    //System.out.println(beta1);
    double n = (double)xList.size();
    double first = (1/(n-2));
    //step2
    double acum = 0;
    for (int i = 0; i < yList.size(); i++) {
      acum +=  yList.get(i) - beta0 - beta1 * xList.get(i);
    }
    double second = Math.pow(acum,2);
    //step3
    //System.out.println("first: " + first);
    //System.out.println("second: " + second);
    return Math.sqrt(first * second);

  }

  public double getSigma(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1){
    ArrayList<Double> cal = new ArrayList<Double>();
    for (int i = 0; i < xArrayList.size(); i++){
      cal.add(yArrayList.get(i) - b0 - b1 * xArrayList.get(i));
    }
    return Math.sqrt(multiple(cal,cal) / (xArrayList.size() - 2));
  }     
  //End
  //Start
  public double getRange(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList,double b0, double b1,double xk){
    double range = new SearchXValue(0.35, xArrayList.size() - 2).search() * getSigma(xArrayList, yArrayList, b0, b1);
    ArrayList<Double> cal = new ArrayList<Double>();
    for (int i = 0; i < xArrayList.size(); i++){
      cal.add(xArrayList.get(i) - getAverage(xArrayList));
    }   
    return range * Math.sqrt( 1 + (1.0 / xArrayList.size()) + ((xk - getAverage(xArrayList)) * (xk - getAverage(xArrayList)) / multiple(cal,cal)));
  }
  public double calculateRange(ArrayList<Double> xList, ArrayList<Double> yList, double beta0, double beta1, double xk){
    /*  Strategy:
     *  1. Calculate the value of x for p=0.35 and dof = n-2
     *  2. Calculate Sigma value
     *  3. Calculate the the sqrt value of the formula
     *  4. Return the multiplication of all the above
     */
    //step1
    double n = (double)xList.size();
    double valueOfX = new SearchXValue(0.35, (n-2)).search();
    //step 2
    double sigma = sigmaValue(xList, yList, beta0, beta1);
    //step 3
    double xAvg = sumatoryOfList(xList) / n;

    // double part1 = (1)+(1/(n-2));
    // System.out.println(part1);
    // double part2 = Math.pow( (xk-xAvg), 2) / Math.pow(sumatoryOfList(xList,xAvg),2);
    // System.out.println("***"+sumatoryOfList(xList,xAvg));
    // double value = Math.sqrt(part1 + part2);
    // System.out.println(valueOfX + "*" + sigma + "*" + value);
    // //step4
    // return valueOfX * sigma * value;



    ArrayList<Double> cal = new ArrayList<Double>();
    for (int i = 0; i < xList.size(); i++){
      cal.add(xList.get(i) - xAvg);
    }   
    return (valueOfX*sigma) * Math.sqrt( 1 + (1.0 / xList.size()) + ((xk - xAvg) * (xk - xAvg) / sumatoryOfList(multiplyLists(cal,cal)) ));
  }


  /*COMMON FUNCTION TOOLS FOR MODIFY AND PROCESS LISTS AND SO*/
  public ArrayList<Double> multiplyLists (ArrayList<Double> aList, ArrayList<Double> bList){
    ArrayList<Double> tempList = new ArrayList<Double>();
    if(aList.size() == bList.size()){
      for (int i = 0; i < aList.size() ; i++) {
        double temp = aList.get(i) * bList.get(i);
        tempList.add(i,temp);
      }

      return tempList;
    }
    return null;
  }
  public double sumatoryOfList (ArrayList<Double> list){
    double acum = 0;
    for (double value : list) {
      acum += value;
    }
    return acum;
  }
  public double sumatoryOfList(ArrayList<Double> list, double val){
    double acum = 0;
    for(double value : list){
      acum += value - val;
    }
    return acum;
  }

  //Start
  public double multiple(ArrayList<Double> xArrayList, ArrayList<Double> yArrayList){
    double cal = 0;
    for (int i = 0; i < xArrayList.size(); i++){
      cal += xArrayList.get(i)*yArrayList.get(i);
    }
    return cal;
  }
  //End
//Start
  public double getAverage(ArrayList<Double> cal){
    double sumFloat = 0;
    double averageFloat = 0;
    for (int i = 0; i < cal.size(); i++){
      sumFloat += cal.get(i);
    }
    averageFloat = sumFloat / cal.size();
    return averageFloat;
  }
  //End

    
}
