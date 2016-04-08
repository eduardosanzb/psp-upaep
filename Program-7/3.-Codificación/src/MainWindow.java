import java.util.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;
import java.text.*;

public class MainWindow extends JFrame {
  JButton case1To2, case3To4;
  JLabel staticData, dynamicData;

  ArrayList<Program> staticPrograms;

  public MainWindow(){
    
    this.setSize(300,300);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ButtonListener listener = new ButtonListener();
    case1To2 = new JButton("Case 1 && 2");
    case1To2.setActionCommand("1");
    case1To2.addActionListener(listener);
    case3To4 = new JButton("Case 3 && 4");
    case3To4.setActionCommand("2");
    case3To4.addActionListener(listener);
    staticData = new JLabel("Static Data =(");
    dynamicData = new JLabel("<html>You can calculate<br> with your own data =)</html>");

    this.setLayout(new BorderLayout(10,10));
    Box leftPanel = Box.createVerticalBox();
    leftPanel.add(case1To2);
    leftPanel.add(staticData);

    Box rightPanel = Box.createVerticalBox();
    rightPanel.add(case3To4);
    rightPanel.add(dynamicData);

    this.add(leftPanel, BorderLayout.LINE_START);
    this.add(rightPanel, BorderLayout.LINE_END);
    this.setVisible(true);


  }
  private class ButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent e){
      int action = Integer.parseInt(e.getActionCommand());
      switch(action){
        case 1: //1&&2
          staticPrograms = new ArrayList<Program>();
          staticPrograms.add(new Program(130,163,186,15));
          staticPrograms.add(new Program(650,765,699,69.9));
          staticPrograms.add(new Program(99,141,132,6.5));
          staticPrograms.add(new Program(150,166,272,22.4));
          staticPrograms.add(new Program(128,137,291,28.4));
          staticPrograms.add(new Program(302,355,331,65.9));
          staticPrograms.add(new Program(95,136,199,19.4));
          staticPrograms.add(new Program(945,1206,1890,198.7));
          staticPrograms.add(new Program(368,433,788,38.8));
          staticPrograms.add(new Program(961,1130,1601,138.2));
          Student staticStudent = new Student(staticPrograms);
          staticStudent.type = "static";
          staticStudent.theProxySize = 386;
          OutputWindow output = new OutputWindow(staticStudent);
          //this.setVisible(false);
        break;
        case 2: //2&&3
          InputWindow input = new InputWindow();
        break;
      }
    }

  }

  public static void main(String[] args) {
    MainWindow w = new MainWindow();
  }
}
