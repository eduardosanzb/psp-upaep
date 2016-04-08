import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;
import java.text.*;

public class OutputWindow extends JFrame {
  private JTable table1, table2;
  private JScrollPane scrollPane1, scrollPane2;
  private Student student;

  private String[] columns = {"Test","Parameter","Expected Val","Actual Val"};

    public OutputWindow(Student student){
      Probe probe = new Probe();
      this.student = student;
      double xk = student.theProxySize;
      ArrayList<Double> proxy = student.getProxies();
      ArrayList<Double> actual = student.getActualSizes();
      ArrayList<Double> plan = student.getPlanSizes();
      ArrayList<Double> develop = student.getDevTimes();
      double ra = probe.calculateR(proxy, actual);
      double rb = probe.calculateR(proxy, develop);
      double r2a = Math.pow(ra,2);
      double r2b = Math.pow(rb,2);
      double taila =probe.calculateTailArea( ra, plan);
      //NumberFormat formatter = new DecimalFormat("0.####E0");
      //taila = Double.parseDouble(formatter.format(taila));
      //System.out.println(formatter.format(taila));
      double tailb =probe.calculateTailArea( rb, proxy);
      double beta0a = probe.calculateBeta0(probe.calculateBeta1(proxy, actual),proxy,actual);
      double beta0b = probe.calculateBeta0(probe.calculateBeta1(proxy, develop),proxy,develop);
      double beta1a = probe.calculateBeta1(proxy, actual);
      double beta1b = probe.calculateBeta1(proxy, develop);
      double ya = probe.calculateY(beta0a, beta1a, xk);
      double yb = probe.calculateY(beta0b, beta1b, xk);
      //double rangea = probe.calculateRange(proxy, actual, beta0a, beta1a, xk);
      double rangea = probe.getRange(proxy, actual, beta0a, beta1a, xk);
      double rangeb = probe.getRange(proxy, develop, beta0b, beta1b, xk);
      //double rangeb = probe.calculateRange(proxy, develop, beta0b, beta1b, xk);
      double upia = ya + rangea;
      double upib = yb + rangeb;
      double lpia = ya - rangea;
      double lpib = yb - rangeb;

      
      this.setSize(400,400);
      if(student.type == "static"){
        
         Object[][] test1 = {
          {"Test 1","r","0.954496574", ra},
          {"","r2","0.91106371",r2a},
          {"","tail area","1.77517E-05",taila},
          {"","beta 0","-22.55253275",beta0a},
          {"","beta 1","1.727932426",beta1a},
          {"","y","644.4293838",ya},
          {"","range","230.0017197",rangea},
          {"","UPI","874.4311035",upia},
          {"","LPI","414.427664",lpia}
        };
         Object[][] test2 = {
          {"Test 2","r","0.933306898",rb},
          {"","r2","0.871061766",r2b},
          {"","tail area","7.98203E-05",tailb},
          {"","beta 0","-4.038881575",beta0b},
          {"","beta 1","0.16812665",beta1b},
          {"","y","60.85800528",yb},
          {"","range","27.55764748",rangeb},
          {"","UPI","88.41565276",upib},
          {"","LPI","33.3003578",lpib}
        };
        buildGui(test1,test2,student);
      }
      else{
         Object[][] test3 = {
          {"Test 3","r","n/a",ra},
          {"","r2","n/a",r2a},
          {"","tail area","n/a",taila},
          {"","beta 0","n/a",beta0a},
          {"","beta 1","n/a",beta1a},
          {"","y","n/a",ya},
          {"","range","n/a",rangea},
          {"","UPI","n/a",lpia},
          {"","LPI","n/a",upia}
        };
         Object[][] test4 = {
          {"Test 4","r","n/a",rb},
          {"","r2","n/a",r2a},
          {"","tail area","n/a",tailb},
          {"","beta 0","n/a",beta0b},
          {"","beta 1","n/a",beta1b},
          {"","y","n/a",yb},
          {"","range","n/a",rangeb},
          {"","UPI","n/a",lpib},
          {"","LPI","n/a",upib}
        };
        buildGui(test3, test4, student);
      }

      this.setVisible(true);

      

    }
  private void buildGui(Object[][] data1, Object[][] data2, Student student){
    table1 = new JTable(data1, columns);
    table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
    table1.setFillsViewportHeight(true);
    table2 = new JTable(data2, columns);
    table2.setPreferredScrollableViewportSize(table2.getPreferredSize());
    table2.setFillsViewportHeight(true);
    scrollPane1 = new JScrollPane(table1);
    scrollPane2 = new JScrollPane(table2);
    JPanel panel = new JPanel();
    panel.add(scrollPane1);
    panel.add(scrollPane2);
    this.add(panel);
  }
}
