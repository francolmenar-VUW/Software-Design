package initialMenu;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import gameWindow.ControllerGameWindow;
import gameWindow.ModelGameWindow;
import gameWindow.ViewGameWindow;
import infoWindow.ControllerInfo;
import infoWindow.ModelInfo;
import infoWindow.ViewInfo;

public class ViewInitial extends JComponent implements Observer{
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;
	private ControllerInitial controller;
	private ModelInitial myModel;//The view


	public ViewInitial(ModelInitial m, ControllerInitial c) {
		myModel = m;
	    myModel.addObserver(this);
	    this.setFocusable(true);

	    controller = c;

		mainFrame = new JFrame();//I initializes the base JFrame
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400, 400);//Size of the outer JFrame
	    mainFrame.setLayout(new GridLayout(3, 1));//I divide all the space in 3 regions
		mainFrame.setLocationRelativeTo(null);//It set it to the center

		controlPanel = new JPanel();//Control panel
		createGUI();
	}

	/**
	 * It manages the calls to the methods to create the windows
	 */
	private void createGUI() {
		switch(myModel.getMode()) {
		case 0: showMenuWindow();return;
		case 1: showInfoWindow();return;
		case 2: showGameWindow(); return;
		case -1: closeWindow();return;
		}
	}

	/**
	 * It closes the menu Window and creates the Game Window
	 */
	private void showGameWindow() {
		closeWindow();
		ModelGameWindow m1 = new ModelGameWindow();//model
		ControllerGameWindow c1 = new ControllerGameWindow(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewGameWindow(m1, c1));
	}

	/**
	 * I close the window
	 */
	private void closeWindow() {
		mainFrame.dispose();
	}

	/**
	 * I call to the createGUI to change the window
	 */
	@Override
	public void update(Observable o, Object arg) {createGUI();}



	/**
	 * It creates the Menu Window
	 */
	private void showMenuWindow() {
		mainFrame.setTitle("Swords & Shields");//Frame Name

		headerLabel = new JLabel("Are you ready to play?", SwingConstants.CENTER);//I initializes the header
		headerLabel.setSize(350,100);//Size of the label
		mainFrame.add(headerLabel);//I add the header label to the Frame

		arrangeControlPanel();//I order the buttons
		mainFrame.setVisible(true);
	}

	/**
	 * It order the buttons in the right position
	 */
	public void arrangeControlPanel() {
		JPanel buttonPanel = new JPanel();//I create the panel which be in the middle of the screen
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setSize(300,300);

		JButton infoButton = new JButton("Info");//I create the three buttons
		infoButton.addActionListener(controller);
		JButton beginButton = new JButton("Begin");//I name the three buttons
		beginButton.addActionListener(controller);
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(controller);

	    GridLayout layout = new GridLayout(0,3);//0 rows and 3 columns
	    layout.setHgap(10);//The gap between the elements in the GridLayout
	    layout.setVgap(10);

		buttonPanel.setLayout(layout);//I set the layout I created
		buttonPanel.add(infoButton);//I add the buttons to the middle panel
		buttonPanel.add(beginButton);
		buttonPanel.add(quitButton);

	    controlPanel.add(buttonPanel);//Finally I insert the panel in the center panel
		mainFrame.add(controlPanel);//I add the control panel too
	}

	/**
	 * It closes the menu Window and creates the info Window
	 */
	private void showInfoWindow() {
		closeWindow();
		ModelInfo m1 = new ModelInfo();//model
		ControllerInfo c1 = new ControllerInfo(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewInfo(m1, c1));
	}

}
//https://docs.oracle.com/javase/tutorial/uiswing/events/mouselistener.html
//http://www.austintek.com/mvc/