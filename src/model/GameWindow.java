package model;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class GameWindow extends Window{

	protected JToolBar controlButtons;//Upper toolBar
	protected JButton undo;//The buttons of the toolBar
	protected JButton pass;
	protected JButton surrender;
	protected JPanel board;

	public GameWindow() {
		super();
		mainFrame.setTitle("Swords & Shields");//common Frame name
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 800);//Size of the outer JFrame
	    mainFrame.setLayout(new BorderLayout());
		mainFrame.setLocationRelativeTo(null);//It set it to the center

		controlButtons = new JToolBar();
		undo = new JButton();
		pass = new JButton();
		surrender = new JButton();
	}

	/**
	 * It creates the toolBar
	 */
	private void arrangeToolBar() {
		undo.setText("Undo");//I create the buttons
		pass.setText("Pass");
		surrender.setText("Surrender");

		controlButtons.add(undo);//I put the buttons in the toolBar
		controlButtons.add(pass);
		controlButtons.add(surrender);
		mainFrame.add(controlButtons,BorderLayout.PAGE_START);//ToolBar to the JFrame
	}
	public void showGameWindow() {
		arrangeToolBar();//I create the toolBar
		mainFrame.setVisible(true);

	}



}
