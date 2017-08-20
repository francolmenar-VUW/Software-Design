package infoWindow;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import initialMenu.ControllerInitial;
import initialMenu.ModelInitial;
import initialMenu.ViewInitial;

public class ViewInfo extends JComponent implements Observer{
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JPanel controlPanel;

	protected JTextArea textArea;//The place where the text is going to be placed
	protected JScrollPane scrollPane;//The scroll panels of the text
	protected JPanel buttonPanel;//The panel which contains the button
	protected JButton backButton;//Button to go back

	ControllerInfo controller;
	ModelInfo myModel;//The view

	public ViewInfo (ModelInfo m, ControllerInfo c) {
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
		mainFrame.dispose();//I close the info window
		ModelInitial m1 = new ModelInitial();//model
		ControllerInitial c1 = new ControllerInitial(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewInitial(m1, c1));
	}

	/**
	 * It creates the info window
	 */
	private void showInfoWindow() {
		mainFrame.setTitle("Swords & Shields Info");//common Frame name

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		buttonPanel = new JPanel();
		backButton = new JButton();


		headerLabel = new JLabel("Info about the Swords and Shields", SwingConstants.CENTER);//I initializes the header
		mainFrame.add(headerLabel);
		arrangeScrollPane();//I create the ScrollPane
		arrangeBackButton();//I create the backButton
	    mainFrame.setVisible(true);
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
		textArea.setEditable(false);
	    String text = "";
	    try {
	    text = readFile("Info.txt",StandardCharsets.UTF_8);//Reading the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	    textArea.append(text);//The txt to the textArea
		mainFrame.add(scrollPane);//I add the scrollPane to the center of the Frame
	 }

	/**
	 * It puts the backButton in the screen
	 */
	public void arrangeBackButton() {
		backButton.setText("Back");
		backButton.addActionListener(controller);
		buttonPanel.setBackground(Color.LIGHT_GRAY);//The panel where the button is going to be
	    GridLayout layout = new GridLayout(0,1);//Just one element in the layout
	    buttonPanel.setLayout(layout);//I set the layout I created
	    buttonPanel.add(backButton);//Button to buttonPanel
	    controlPanel.add(buttonPanel);//ButtonPanel to ControlPanel
		mainFrame.add(controlPanel);//ControlPanel to mainFrame
	}
}
