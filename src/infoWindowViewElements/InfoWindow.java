package infoWindowViewElements;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import initialMenuViewElements.Window;

public class InfoWindow extends Window{
	protected JTextArea textArea;//The place where the text is going to be placed
	protected JScrollPane scrollPane;//The scroll panels of the text
	protected JPanel buttonPanel;//The panel which contains the button
	protected JButton backButton;//Button to go back


	public InfoWindow() {
		super();
		confInfoFrame();
		confTextArea();
		confButtonPanel();

		scrollPane = new JScrollPane(textArea);
		backButton = new JButton("Back");
	}

	private void confButtonPanel() {
		buttonPanel = new JPanel();
		GridLayout layout = new GridLayout(0,1);//Just one element in the layout
	    buttonPanel.setLayout(layout);//I set the layout I created
	}

	/**
	 * It creates the basic configuration of the textArea
	 */
	private void confTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
	}

	/**
	 * It creates the basic configuration of the InfoFrame
	 */
	private void confInfoFrame() {
		mainFrame.setTitle("Swords & Shields Info");//common Frame name
		mainFrame.setSize(400, 400);//Size of the outer JFrame
	    mainFrame.setLayout(new GridLayout(3, 1));//I divide all the space in 3 regions
		mainFrame.setLocationRelativeTo(null);//It set it to the center
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
}
