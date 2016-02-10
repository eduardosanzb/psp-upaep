package com.eduardosanzb.psp5;

// Imports for GUI
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class testGUI extends JFrame{
    //GUI Variables
    private String[] columns = {"n","x","dof","Expected p","Actual p"};
    private Object[][] data = {
      {"1","0 to x=1.1","9","0.35006", 1.0},
      {"2","0 to x=1.812","10","0.36757", 1.0},
      {"3","0 to x=2.750","30","0.49500", 1.0}
    } ;
    private JTable table;
    private JButton calculate;
    private JLabel test, expectedLabel, actualLabel;
    private JScrollPane scrollPane;
    

    public testGUI(){
      //We declare the size of our container JFRame
    this.setSize(450,200);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel thePanel = new JPanel();

    //We initialize our objects for the GUI
      //Create the table and add the correct size
    table = new JTable(data, columns);
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setFillsViewportHeight(true);
      //Button setup
    calculate = new JButton("Calcular!!");
    ListenForButton lForButton = new ListenForButton();
    calculate.addActionListener(lForButton);
      //labels setup
    test = new JLabel("TEST");
    expectedLabel = new JLabel("Expected Value");
    actualLabel = new JLabel("Actual Value");

    this.scrollPane = new JScrollPane(table);

    //Adding the elements to the frame
    thePanel.add(test);
    thePanel.add(expectedLabel);
    thePanel.add(actualLabel);

    thePanel.add(scrollPane);

    // modify the value of a cell
    table.getModel().setValueAt(2.4,1,4);

    thePanel.add(calculate);
    this.add(thePanel);
    this.setVisible(true);
    }

  private class ListenForButton implements ActionListener{
    public void actionPerformed(ActionEvent e){
      if(e.getSource() == calculate){

      }
    }
  }

  public static void main(String[] args) {
    testGUI var = new  testGUI();
  }
}





