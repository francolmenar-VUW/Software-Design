package model;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	protected JFrame mainFrame;
	protected JLabel headerLabel;
	protected JPanel controlPanel;

	public Window(JFrame mainFrame, JLabel headerLabel, JPanel controlPanel) {
		super();
		mainFrame.setTitle("Swords & Shields");//common Frame name
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame = mainFrame;
		this.headerLabel = headerLabel;
		this.controlPanel = controlPanel;
	}
	public Window() {
		mainFrame = new JFrame();
		headerLabel = new JLabel("",JLabel.CENTER);//I predefined the labels to be in the center
		controlPanel = new JPanel();
	}
	public JFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	public JLabel getHeaderLabel() {
		return headerLabel;
	}
	public void setHeaderLabel(JLabel headerLabel) {
		this.headerLabel = headerLabel;
	}
	public JPanel getControlPanel() {
		return controlPanel;
	}
	public void setControlPanel(JPanel controlPanel) {
		this.controlPanel = controlPanel;
	}
}
