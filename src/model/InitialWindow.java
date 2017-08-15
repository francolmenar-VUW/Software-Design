package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


//public class Test implements Runnable{
public class InitialWindow{
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;

	/*
	 * Constructor
	 */
	public InitialWindow() {
		createGUI();
		}

	/*
	 * I create the base initial window
	 */
	private void showInitialWindow() {
		headerLabel.setText("Are you ready to play?");//Upper header

		JPanel panel = new JPanel();//I create the panel which be in the middle of the screen
	    panel.setBackground(Color.LIGHT_GRAY);
	    panel.setSize(300,300);
	    GridLayout layout = new GridLayout(0,3);//0 rows and 3 columns
	    layout.setHgap(10);//The gap between the elements in the GridLayout
	    layout.setVgap(10);

		JButton infoBotton = new JButton("Info");//Three bottoms
		infoBotton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				InitialWindow window = new InitialWindow();
				window.showInfoWindow();
			}
		});
		JButton beginBotton = new JButton("Begin");
		JButton quitBotton = new JButton("Quit");

	    panel.setLayout(layout);//I set the layout I created
	    panel.add(infoBotton);//I add the buttons to the middle panel
	    panel.add(beginBotton);
	    panel.add(quitBotton);

	    controlPanel.add(panel);//Finally I insert the panel in the center panel
	    mainFrame.setVisible(true);
	}

	/*
	 * I show the information window
	 */
	protected void showInfoWindow() {
		headerLabel.setText("Info about the Swords and Shields");//Upper header
		//headerLabel.setText("");//?
		//http://www.programcreek.com/java-api-examples/javax.swing.JEditorPane
	    mainFrame.setVisible(true);
	}


	/*
	 * It creates the base of the window
	 */
	public void createGUI() {
		mainFrame = new JFrame("Swords and Shields");//Upper frame
		mainFrame.setSize(400, 400);//Size of the outer JFrame
	    mainFrame.setLayout(new GridLayout(3, 1));//I divide all the space in 3 regions
		mainFrame.setLocationRelativeTo(null);//It set it to the center
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		headerLabel = new JLabel("",JLabel.CENTER);//I predefined the labels to be in the center
		headerLabel.setSize(350,100);//Size of the label
	    controlPanel = new JPanel();//Init controlPanel

		mainFrame.add(headerLabel);//I add the header label to the Frame
		mainFrame.add(controlPanel);//I add the control panel too

		mainFrame.setVisible(true);
	}

	public static void main(String[] args){
		InitialWindow window = new InitialWindow();
		window.showInitialWindow();
	}

}
