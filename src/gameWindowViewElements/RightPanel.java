package gameWindowViewElements;

import java.awt.GridLayout;

public class RightPanel extends BasicPanel{
	private ElementPanel greenElementPanel;//The elements of the green player
	private ElementPanel yellowElementPanel;//The elements of the yellow player

	public RightPanel() {
		super();
		greenElementPanel = new ElementPanel();
		yellowElementPanel = new ElementPanel();
		confRightPanel();
		confElementPanel(greenElementPanel);
	}
	/**
	 * It creates the basic configuration of the Right Panel
	 */
	private void confRightPanel() {
		this.getPanel().setLayout(new GridLayout(0,2));//I divide the Right panel in 2 columns
	}
	/**
	 * It creates the basic configuration of the Pieces Panels
	 * @param element Is the panel which contains the pieces to be created and the cemetery
	 */
	private void confElementPanel(ElementPanel element) {
		greenElementPanel = new ElementPanel();//Initialize Main Panel
		yellowElementPanel = new ElementPanel();
	}
	public ElementPanel getGreenElementPanel() {
		return greenElementPanel;
	}
	public void setGreenElementPanel(ElementPanel greenElementPanel) {
		this.greenElementPanel = greenElementPanel;
	}
	public ElementPanel getYellowElementPanel() {
		return yellowElementPanel;
	}
	public void setYellowElementPanel(ElementPanel yellowElementPanel) {
		this.yellowElementPanel = yellowElementPanel;
	}
}
