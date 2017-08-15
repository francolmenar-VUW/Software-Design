package model;


import java.io.*;
import java.util.*;
import java.security.*;
import javax.xml.bind.DatatypeConverter;
import java.lang.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import javax.swing.*;

public class SwingLayoutDemo {
   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JLabel msglabel;

   public SwingLayoutDemo(){
      prepareGUI();
   }
   public static void main(String[] args){
      SwingLayoutDemo swingLayoutDemo = new SwingLayoutDemo();
      swingLayoutDemo.showGridLayoutDemo();
   }
   private void prepareGUI(){
      mainFrame = new JFrame("Java SWING Examples");//Upper frame
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));//for the different JLabels

      headerLabel = new JLabel("",JLabel.CENTER );//I center the header and the status
      statusLabel = new JLabel("",JLabel.CENTER);//Status center too
      statusLabel.setSize(350,100);


      mainFrame.addWindowListener(new WindowAdapter() {//close the window?
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }
      });
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);//First header
      mainFrame.add(controlPanel);//Second control
      mainFrame.add(statusLabel);//Last one status
      mainFrame.setVisible(true);
   }
   private void showGridLayoutDemo(){
      headerLabel.setText("Layout in action: GridLayout");
      statusLabel.setText("Payo");

      JPanel panel = new JPanel();//I create the base panel
      panel.setBackground(Color.darkGray);
      panel.setSize(300,300);
      GridLayout layout = new GridLayout(0,3);//0 rows and 3 columns
      layout.setHgap(10);//The gap between the elements in the GridLayout
      layout.setVgap(10);

      panel.setLayout(layout);//I set the layout I created
      panel.add(new JButton("Button 1"));//I add the buttons to the panel
      panel.add(new JButton("Button 2"));
      panel.add(new JButton("Button 3"));
      panel.add(new JButton("Button 4"));
      panel.add(new JButton("Button 5"));
      controlPanel.add(panel);//I add to the control pane(the second one) the JPanel i have created
      mainFrame.setVisible(true);
   }
}