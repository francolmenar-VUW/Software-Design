package initialMenu;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import gameWindow.ControllerGameWindow;
import gameWindow.ModelGameWindow;
import gameWindow.ViewGameWindow;
import infoWindow.ControllerInfo;
import infoWindow.ModelInfo;
import infoWindow.ViewInfo;
import initialMenuViewElements.InitialWindow;

public class ViewInitial extends JComponent implements Observer{
	private static final long serialVersionUID = 1L;

	private InitialWindow menu;

	private ControllerInitial controller;
	private ModelInitial myModel;//The view


	public ViewInitial(ModelInitial m, ControllerInitial c) {
		myModel = m;//Model
	    myModel.addObserver(this);
	    this.setFocusable(true);
	    controller = c;//Controller
	    menu = new InitialWindow();//Initial Window class
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
		menu.getMainFrame().dispose();
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
		menu.getHeaderLabel().setText("Are you ready to play?");//I put the text of the header
		menu.getHeaderLabel().setSize(350,100);//Size of the label
		menu.getMainFrame().add(menu.getHeaderLabel());//I add the header label to the Frame

		arrangeControlPanel();//I order the buttons
		menu.getMainFrame().setVisible(true);
	}

	/**
	 * It order the buttons in the right position
	 */
	public void arrangeControlPanel() {
		menu.getButtonPanel().setBackground(Color.LIGHT_GRAY);
		menu.getButtonPanel().setSize(300,300);

		menu.getInfoButton().addActionListener(controller);//I add the listeners to the buttons
		menu.getBeginButton().addActionListener(controller);
		menu.getQuitButton().addActionListener(controller);

		menu.getButtonPanel().add(menu.getInfoButton());//I add the buttons to the middle panel
		menu.getButtonPanel().add(menu.getBeginButton());
		menu.getButtonPanel().add(menu.getQuitButton());

	    menu.getControlPanel().add(menu.getButtonPanel());//Finally I insert the panel in the center panel
		menu.getMainFrame().add(menu.getControlPanel());//I add the control panel too
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