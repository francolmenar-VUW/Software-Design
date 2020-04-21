package infoWindow;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import infoWindowViewElements.InfoWindow;
import initialMenu.ControllerInitial;
import initialMenu.ModelInitial;
import initialMenu.ViewInitial;

public class ViewInfo extends JComponent implements Observer{
	private static final long serialVersionUID = 1L;
	private InfoWindow info;//The infoWindow object

	ControllerInfo controller;
	ModelInfo myModel;//The view

	public ViewInfo (ModelInfo m, ControllerInfo c) {
		myModel = m;//Model
	    myModel.addObserver(this);
	    this.setFocusable(true);
	    controller = c;//Controller
	    info = new InfoWindow();//I create the window
		createGUI();
	}

	@Override
	public void update(Observable o, Object arg) {createGUI();}

	private void createGUI() {
		switch(myModel.getMode()) {
		case 1: showInfoWindow();return;
		case 0: showMenuWindow();return;
		}
	}

	/**
	 * It closes the info Window and creates the menu Window
	 */
	private void showMenuWindow() {
		info.getMainFrame().dispose();//I close the info window
		ModelInitial m1 = new ModelInitial();//model
		ControllerInitial c1 = new ControllerInitial(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewInitial(m1, c1));
	}

	/**
	 * It creates the info window
	 */
	private void showInfoWindow() {
		info.getHeaderLabel().setText("Info about the Swords and Shields");//I initializes the header
		info.getMainFrame().add(info.getHeaderLabel());
		arrangeScrollPane();//I create the ScrollPane
		arrangeBackButton();//I create the backButton
		info.getMainFrame().setVisible(true);
	}

	/**
	 * It reads a text file and return it as a String
	 * @param path is the name of the text file
	 * @param encoding The encoding used
	 * @return The String with the content of the text file
	 * @throws IOException
	 */
	static String readFile(String path, Charset encoding)
	   		  throws IOException
	   		{
	   		  byte[] encoded = Files.readAllBytes(Paths.get(path));//I read the content of the file
	   		  return new String(encoded, encoding);//I return the String
	   		}

	 /**
	  * It puts the ScrollPane in the screen
	  */
	public void arrangeScrollPane() {
	    String text = "";
	    try {
	    text = readFile("Info.txt",StandardCharsets.UTF_8);//Reading the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	    info.getTextArea().append(text);//The txt to the textArea
		info.getMainFrame().add(info.getScrollPane());//I add the scrollPane to the center of the Frame
	 }

	/**
	 * It puts the backButton in the screen
	 */
	public void arrangeBackButton() {
		info.getBackButton().addActionListener(controller);
		info.getButtonPanel().setBackground(Color.LIGHT_GRAY);//The panel where the button is going to be
	    info.getButtonPanel().add(info.getBackButton());//Button to buttonPanel
	    info.getControlPanel().add(info.getButtonPanel());//ButtonPanel to ControlPanel
		info.getMainFrame().add(info.getControlPanel());//ControlPanel to mainFrame
	}
}
