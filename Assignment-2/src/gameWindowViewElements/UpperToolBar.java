package gameWindowViewElements;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class UpperToolBar {
	private JToolBar controlButtons;//Upper toolBar
	private JButton undo;//The buttons of the toolBar
	private JButton pass;
	private JButton surrender;

	public UpperToolBar() {
		confButtons();
		confControlButtons();
	}

	/**
	 * It creates the basic configuration of the Buttons
	 */
	private void confButtons() {
		undo = new JButton("Undo");//I create the buttons
		pass = new JButton("Pass");
		surrender = new JButton("Surrender");
	}

	/**
	 * It creates the basic configuration of the ControlButtons
	 */
	private void confControlButtons() {
		controlButtons = new JToolBar();//ToolBar and its buttons
		controlButtons.add(undo);//I put the buttons in the toolBar
		controlButtons.add(pass);
		controlButtons.add(surrender);
	}

	public JToolBar getControlButtons() {
		return controlButtons;
	}

	public void setControlButtons(JToolBar controlButtons) {
		this.controlButtons = controlButtons;
	}

	public JButton getUndo() {
		return undo;
	}

	public void setUndo(JButton undo) {
		this.undo = undo;
	}

	public JButton getPass() {
		return pass;
	}

	public void setPass(JButton pass) {
		this.pass = pass;
	}

	public JButton getSurrender() {
		return surrender;
	}

	public void setSurrender(JButton surrender) {
		this.surrender = surrender;
	}
}
