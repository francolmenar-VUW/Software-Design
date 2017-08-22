package gameWindowViewElements;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import initialMenuViewElements.Window;

public class GameWindow extends Window{
	private UpperToolBar toolBar;
	private MainPanel main;

	public GameWindow() {
		super();
		confMainFrame();//Frame of the Game Window
		confMainPanel();//Main panel of the window
		toolBar = new UpperToolBar();//ToolBar
	}

	/**
	 * It creates the basic configuration of the MainPanel
	 */
	private void confMainPanel() {
		main = new MainPanel();//Initialize MainPanel
		main.getPanel().setLayout(new GridLayout(1, 2));//I divide the space into 2 sides: right and left
	}

	/**
	 * It creates the basic configuration of the MainFrame
	 */
	private void confMainFrame() {
		mainFrame = new JFrame("Swords & Shields");//I initializes the base JFrame
		mainFrame.setSize(800, 800);//Size of the outer JFrame
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setLocationRelativeTo(null);//It set it to the center
	}

	public UpperToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(UpperToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public MainPanel getMain() {
		return main;
	}

	public void setMain(MainPanel main) {
		this.main = main;
	}
}
