package initialMenuViewElements;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class InitialWindow extends Window{

	protected JPanel buttonPanel;//The panel which contains the buttons
	protected JButton infoButton;//The buttons of the center of the window
	protected JButton beginButton;
	protected JButton quitButton;

	public InitialWindow() {
		super();
		confMainFrame();//Create the frame
		confButtonPanel();//Create the ButtonPanel
		confButtons();//Create the buttons
	}

	/**
	 * It creates the basic configuration of the mainFrame
	 */
	public void confMainFrame() {
		mainFrame.setTitle("Swords & Shields");//common Frame name
		mainFrame.setSize(400, 400);//Size of the outer JFrame
	    mainFrame.setLayout(new GridLayout(3, 1));//I divide all the space in 3 regions
		mainFrame.setLocationRelativeTo(null);//It set it to the center
	}

	/**
	 * It creates the basic configuration of the ButtonPanel
	 */
	public void confButtonPanel() {
		buttonPanel = new JPanel();//I create the panel which be in the middle of the screen
		GridLayout layout = new GridLayout(0,3);//0 rows and 3 columns
	    layout.setHgap(10);//The gap between the elements in the GridLayout
	    layout.setVgap(10);
		buttonPanel.setLayout(layout);//I set the layout I created
	}

	/**
	 * I create the basic configuration of the Buttons
	 */
	public void confButtons() {
		infoButton = new JButton("Info");//I name the three buttons
		beginButton = new JButton("Begin");
		quitButton = new JButton("Quit");
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JButton getInfoButton() {
		return infoButton;
	}

	public void setInfoButton(JButton infoButton) {
		this.infoButton = infoButton;
	}

	public JButton getBeginButton() {
		return beginButton;
	}

	public void setBeginButton(JButton beginButton) {
		this.beginButton = beginButton;
	}

	public JButton getQuitButton() {
		return quitButton;
	}

	public void setQuitButton(JButton quitButton) {
		this.quitButton = quitButton;
	}
}
