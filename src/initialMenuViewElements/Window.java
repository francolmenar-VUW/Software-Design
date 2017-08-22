package initialMenuViewElements;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window {

	protected JFrame mainFrame;//Window frame
	protected JLabel headerLabel;//Header in the center
	protected JPanel controlPanel;//The main panel

	public Window() {
		mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
