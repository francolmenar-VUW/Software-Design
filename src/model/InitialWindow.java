package model;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InitialWindow extends Window{

	protected JPanel buttonPanel;//The panel which contains the buttons
	protected JButton infoButton;//The buttons of the center of the window
	protected JButton beginButton;
	protected JButton quitButton;

	public InitialWindow() {
		super();
		mainFrame.setTitle("Swords & Shields");//common Frame name
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonPanel = new JPanel();//I create the panel which be in the middle of the screen
		infoButton = new JButton();
		beginButton = new JButton();
		quitButton = new JButton();
	}

	public InitialWindow(JFrame mainFrame, JLabel headerLabel, JPanel controlPanel) {
		super(mainFrame, headerLabel, controlPanel);
		buttonPanel = new JPanel();//I create the panel which be in the middle of the screen
		infoButton = new JButton();
		beginButton = new JButton();
		quitButton = new JButton();
	}


	/**
	 * It creates the Initial Window
	 */
	public void createGUI() {
		mainFrame.setSize(400, 400);//Size of the outer JFrame
	    mainFrame.setLayout(new GridLayout(3, 1));//I divide all the space in 3 regions
		mainFrame.setLocationRelativeTo(null);//It set it to the center

		headerLabel.setSize(350,100);//Size of the label
		headerLabel.setText("Are you ready to play?");//Upper header
		mainFrame.add(headerLabel);//I add the header label to the Frame

		arrangeControlPanel();//I order the buttons
		mainFrame.setVisible(true);
	}

	/**
	 * It order the buttons in the right position
	 */
	public void arrangeControlPanel() {
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setSize(300,300);

	    GridLayout layout = new GridLayout(0,3);//0 rows and 3 columns
	    layout.setHgap(10);//The gap between the elements in the GridLayout
	    layout.setVgap(10);

		infoButton.setText("Info");//I name the three buttons
		beginButton.setText("Begin");
		quitButton.setText("Quit");

		infoButton.addActionListener(new ActionListener() {//I the button is pressed, the InfoWindow is created

			@Override
			public void actionPerformed(ActionEvent e) {
				InfoWindow window = new InfoWindow();
				window.showInfoWindow();//I show the InfoWindow
			}
		});
		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 mainFrame.dispose();
			}
		});
		beginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GameWindow window = new GameWindow();
				window.showInfoWindow();//I show the InfoWindow
				mainFrame.dispose();
			}
		});

		buttonPanel.setLayout(layout);//I set the layout I created
		buttonPanel.add(infoButton);//I add the buttons to the middle panel
		buttonPanel.add(beginButton);
		buttonPanel.add(quitButton);

	    controlPanel.add(buttonPanel);//Finally I insert the panel in the center panel
		mainFrame.add(controlPanel);//I add the control panel too
	}
}
