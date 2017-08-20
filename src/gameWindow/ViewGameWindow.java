package gameWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import elements.BoardSwordShield;
import elements.PieceSwordShield;
import initialMenu.ControllerInitial;
import initialMenu.ModelInitial;
import initialMenu.ViewInitial;

public class ViewGameWindow extends JComponent implements Observer{
	private static final long serialVersionUID = 1L;

	private JFrame mainFrame;

	private JToolBar controlButtons;//Upper toolBar
	private JButton undo;//The buttons of the toolBar
	private JButton pass;
	private JButton surrender;
	private JButton [][] pieces;
	private JPanel board;
	private JPanel auxPanel;

	private JPanel leftPanel;
	private JPanel rightPanel;


	private ControllerGameWindow controller;
	private ModelGameWindow myModel;//The view

	public ViewGameWindow(ModelGameWindow m, ControllerGameWindow c) {
		myModel = m;
	    myModel.addObserver(this);
	    this.setFocusable(true);

	    controller = c;

		mainFrame = new JFrame("Swords & Shields");//I initializes the base JFrame
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 800);//Size of the outer JFrame
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setLocationRelativeTo(null);//It set it to the center

		controlButtons = new JToolBar();//ToolBar and its buttons
		undo = new JButton();
		pass = new JButton();
		surrender = new JButton();

		pieces = new JButton[10][10];//The board's pieces

		auxPanel = new JPanel();//I initializes the auxiliary panel
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		board = new JPanel();
		createGUI();
	}

	/**
	 * It manages the calls to the methods to create the windows
	 */
	private void createGUI() {
		switch(myModel.getMode()) {
		case 0: showMenuWindow();return;
		case 2: showGameWindow();return;
		}
	}

	/**
	 * It creates the Game Window
	 */
	private void showGameWindow() {
		arrangeToolBar();//I create the toolBar
		arrangeBoard();//I create the table
		mainFrame.setVisible(true);
	}

	/**
	 * It creates the toolBar
	 */
	private void arrangeToolBar() {
		undo.setText("Undo");//I create the buttons
		pass.setText("Pass");
		surrender.setText("Surrender");
		surrender.addActionListener(controller);

		controlButtons.add(undo);//I put the buttons in the toolBar
		controlButtons.add(pass);
		controlButtons.add(surrender);
		mainFrame.add(controlButtons,BorderLayout.PAGE_START);//ToolBar to the JFrame
	}


	/**
	 * It creates the Board and it is the responsible of inserting it into the other panel
	 */
	private void arrangeBoard() {
		auxPanel.setLayout(new GridLayout(1, 2));//I divide the space into 2 sides: right and left

		board.setLayout(new GridLayout(10,10));//I set the size of the board
		createPieces();

		for(int i = 0; i < myModel.getBoard().getSizeX(); i++) {
			for(int j = myModel.getBoard().getSizeY() -1; j >= 0; j--) {
				pieces[j][i].setName(j + " " + i);
				pieces[j][i].setPreferredSize(new Dimension(39,39));
				board.add(pieces[j][i]);
			}
		}

		leftPanel.add(board);

		auxPanel.add(leftPanel);
		auxPanel.add(rightPanel);

		mainFrame.add(auxPanel);//I add the auxiliary panel to the window
	}

	/**
	 * It creates initially all the pieces of the board
	 */
	private void createPieces() {
		BoardSwordShield boardGame =  myModel.getBoard();
		PieceSwordShield aux;//Auxiliary to get the name of the piece
		for(int j = 0; j < myModel.getBoard().getSizeY(); j++) {
			for(int i = 0; i < myModel.getBoard().getSizeX(); i++) {
				aux = (PieceSwordShield) boardGame.getElements().get(j).get(i);//I get the name of the piece
				pieces[j][i] = new JButton();
				if(aux.getName() == '?') {//Empty cell
					if(j == 2 && i == 7) {//Yellow creation
						pieces[j][i].setBackground(Color.YELLOW);
					}
					else if(j ==  7 && i == 2) {//Green creation
						pieces[j][i].setBackground(Color.GREEN);
					}
					else if((j % 2 != 0  && i % 2 != 0) || (j % 2 == 0 && i % 2 == 0)){//White spaces
							pieces[j][i].setBackground(Color.WHITE);
						}
						else {//Gray spaces
							pieces[j][i].setBackground(Color.GRAY);
						}
				}
				else if(aux.getName() == '/') {//Forbidden cell
					pieces[j][i].setBackground(Color.LIGHT_GRAY);//White spaces
				}
				else if(aux.getName() == '0'){//Yellow face
					try {
						ImageIcon icon = new ImageIcon("images/YellowFace.png");
						pieces[j][i].setIcon(icon);
					} catch (Exception e) {
					    e.printStackTrace();
					  }
				}
				else {//Green face
					try {
						ImageIcon icon = new ImageIcon("images/GreenFace.png");
						pieces[j][i].setIcon(icon);
					} catch (Exception e) {
					    e.printStackTrace();
					  }
				}
			}
		}
	}

	/**
	 * I call to the createGUI to change the window
	 */
	@Override
	public void update(Observable o, Object arg) {createGUI();}

	/**
	 * It closes the info Window and creates the menu Window
	 */
	private void showMenuWindow() {
		mainFrame.dispose();//I close the info window
		ModelInitial m1 = new ModelInitial();//model
		ControllerInitial c1 = new ControllerInitial(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewInitial(m1, c1));
	}
}
