package model;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InfoWindow extends Window{
	protected JTextArea textArea;//The place where the text is going to be placed
	protected JScrollPane scrollPane;//The scroll panels of the text
	protected JPanel buttonPanel;//The panel which contains the button
	protected JButton backButton;//Button to go back


	public InfoWindow() {
		super();
		mainFrame.setTitle("Swords & Shields Info");//common Frame name
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(400, 400);//Size of the outer JFrame
	    mainFrame.setLayout(new GridLayout(3, 1));//I divide all the space in 3 regions
		mainFrame.setLocationRelativeTo(null);//It set it to the center

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		buttonPanel = new JPanel();
		backButton = new JButton();
	}

	public InfoWindow(JFrame mainFrame, JLabel headerLabel, JPanel controlPanel) {
		super(mainFrame, headerLabel, controlPanel);
		textArea = new JTextArea();
		scrollPane = new JScrollPane();
		buttonPanel = new JPanel();
		backButton = new JButton();
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
		backButton.addActionListener(new ActionListener() {//Back action

			@Override
			public void actionPerformed(ActionEvent e) {
				 mainFrame.dispose();
			}
		});
		buttonPanel.setBackground(Color.LIGHT_GRAY);//The panel where the button is going to be
	    GridLayout layout = new GridLayout(0,1);//Just one element in the layout
	    buttonPanel.setLayout(layout);//I set the layout I created
	    buttonPanel.add(backButton);//Button to buttonPanel
	    controlPanel.add(buttonPanel);//ButtonPanel to ControlPanel
		mainFrame.add(controlPanel);//ControlPanel to mainFrame
	}

	/**
	 * It creates the info window
	 */
	public void showInfoWindow() {
		headerLabel.setText("Info about the Swords and Shields");//Upper header
		mainFrame.add(headerLabel);
		arrangeScrollPane();//I create the ScrollPane
		arrangeBackButton();//I create the backButton
	    mainFrame.setVisible(true);
	}


}
