import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InputWindow extends JFrame{
  private JTable table;
  private JScrollPane scrollPane;
  private Student student;
  private JTextField proxySize;
  private JLabel proxy, program;
  private JButton calculate;
  private String[] columns = {"Program #","Estimated Proxy Size","Plan Added And Modified","Actual Added","Actual Development Time"};
  private Object[][] data = {
          {"3",0,0,0,0},
          {"4",0,0,0,0},
          {"5",0,0,0,0},
          {"6",0,0,0,0}
        };

  public InputWindow(){
    this.setSize(300,300);
    //this.setLocationRelativeTo(null);
    //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    table = new JTable(data, columns);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setFillsViewportHeight(true);
    scrollPane = new JScrollPane(table);

    proxy = new JLabel("Estimated Poxy Size");
    program = new JLabel("from program 7");
    calculate = new JButton("Calculate!!!");
    proxySize = new JTextField(50);
    ButonListener lForButton = new ButonListener();
    calculate.addActionListener(lForButton);

    JPanel panel = new JPanel(new BorderLayout(15,15));

    Box box = Box.createHorizontalBox();
    box.add(proxy);
    box.add(proxySize);
    box.add(program);

    panel.add(box, BorderLayout.NORTH);
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(calculate, BorderLayout.SOUTH);
    this.add(panel);

    this.setVisible(true);

  }


  private class ButonListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        if(e.getSource() == calculate){
          
          student = new Student();
          student.theProxySize = Double.parseDouble(proxySize.getText());
          for(int i = 0; i < 4; i++){
            Program p = new Program();
            double prSize = Double.parseDouble(table.getModel().getValueAt(i,1).toString());
            double pSize = Double.parseDouble(table.getModel().getValueAt(i,2).toString());
            double aSize = Double.parseDouble(table.getModel().getValueAt(i,3).toString());
            double dSize = Double.parseDouble(table.getModel().getValueAt(i,4).toString());
            p.setProxySize(prSize);
            p.setPlanAdded(pSize);
            p.setActualAdded(aSize);
            p.setActualTime(dSize);
            student.appendProgram(p);
            System.out.println();
            System.out.println("*******" + i);
            student.showProgramsInConsole();
            
          }
          //student.showProgramsInConsole();
          OutputWindow output = new OutputWindow(student);
          //this.dismis();
        }
      }
  }
}
