package gameWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import elements.BoardSwordShield;
import elements.PieceSwordShield;
import gameWindowViewElements.ElementPanel;
import gameWindowViewElements.GameWindow;
import gameWindowViewElements.RoundButton;
import initialMenu.ControllerInitial;
import initialMenu.ModelInitial;
import initialMenu.ViewInitial;

public class ViewGameWindow extends JComponent implements Observer{
	private static final long serialVersionUID = 1L;
	private ControllerGameWindow controller;
	private ModelGameWindow myModel;//The view
	private GameWindow game;

	public ViewGameWindow(ModelGameWindow m, ControllerGameWindow c) {
		myModel = m;//Model
	    myModel.addObserver(this);
	    this.setFocusable(true);
	    controller = c;//controller
	    game = new GameWindow();//Game window
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
		arrangePieces();//I create the elements of the green player
		game.getMainFrame().setVisible(true);
	}

	/**
	 * It creates the toolBar
	 */
	private void arrangeToolBar() {
		game.getToolBar().getSurrender().addActionListener(controller);//Listener to Surrender
		game.getMainFrame().add(game.getToolBar().getControlButtons(),BorderLayout.PAGE_START);//ToolBar to the JFrame
	}

	/**
	 * It creates the Board and it is the responsible of inserting it into the other panel
	 */
	private void arrangeBoard() {
		createBoardPieces();

		for(int i = 0; i < myModel.getBoard().getSizeX(); i++) {
			for(int j = myModel.getBoard().getSizeY() -1; j >= 0; j--) {
				game.getMain().getBoardPanel().getPieces()[j][i].setName(j + " " + i);
				game.getMain().getBoardPanel().getPieces()[j][i].setPreferredSize(new Dimension(39,39));
				game.getMain().getBoardPanel().getBoard().add(game.getMain().getBoardPanel().getPieces()[j][i]);
			}
		}

		game.getMain().getBoardPanel().getPanel().add(game.getMain().getBoardPanel().getBoard());//Board to left Panel

		game.getMain().getPanel().add(game.getMain().getBoardPanel().getPanel());//Left panel to Main panel
		game.getMain().getPanel().add(game.getMain().getPiecesSide().getPanel());//Right panel to Main panel

		game.getMainFrame().add(game.getMain().getPanel());//I add the Main panel to the window
	}

	/**
	 * It creates initially all the pieces of the board
	 */
	private void createBoardPieces() {
		BoardSwordShield boardGame =  myModel.getBoard();
		PieceSwordShield aux;//Auxiliary to get the name of the piece
		for(int j = 0; j < myModel.getBoard().getSizeY(); j++) {
			for(int i = 0; i < myModel.getBoard().getSizeX(); i++) {
				aux = (PieceSwordShield) boardGame.getElements().get(j).get(i);//I get the name of the piece
				game.getMain().getBoardPanel().getPieces()[j][i] = new JButton();
				if(aux.getName() == '?') {//Empty cell
					if(j == 2 && i == 7) {//Yellow creation
						game.getMain().getBoardPanel().getPieces()[j][i].setBackground(Color.YELLOW);
					}
					else if(j ==  7 && i == 2) {//Green creation
						game.getMain().getBoardPanel().getPieces()[j][i].setBackground(Color.GREEN);
					}
					else if((j % 2 != 0  && i % 2 != 0) || (j % 2 == 0 && i % 2 == 0)){//White spaces
						game.getMain().getBoardPanel().getPieces()[j][i].setBackground(Color.WHITE);
						}
						else {//Gray spaces
							game.getMain().getBoardPanel().getPieces()[j][i].setBackground(Color.GRAY);
						}
				}
				else if(aux.getName() == '/') {//Forbidden cell
					game.getMain().getBoardPanel().getPieces()[j][i].setBackground(Color.LIGHT_GRAY);//White spaces
				}
				else if(aux.getName() == '0'){//Yellow face
					try {
						ImageIcon icon = new ImageIcon("images/YellowFace.png");
						game.getMain().getBoardPanel().getPieces()[j][i].setIcon(icon);
					} catch (Exception e) {
					    e.printStackTrace();
					  }
				}
				else {//Green face
					try {
						ImageIcon icon = new ImageIcon("images/GreenFace.png");
						game.getMain().getBoardPanel().getPieces()[j][i].setIcon(icon);
					} catch (Exception e) {
					    e.printStackTrace();
					  }
				}
			}
		}
	}

	/**
	 * It creates the pieces to be created and the deleted ones of btoh players
	 */
	private void arrangePieces() {

		piecesToCreate(game.getMain().getPiecesSide().getGreenElementPanel(), Color.GREEN);//I create the pieces that the green player can create
		piecesToCreate(game.getMain().getPiecesSide().getYellowElementPanel(), Color.YELLOW);//I create the pieces that the yellow player can create

		piecesEliminated(game.getMain().getPiecesSide().getGreenElementPanel(), Color.GREEN);
		piecesEliminated(game.getMain().getPiecesSide().getYellowElementPanel(), Color.YELLOW);


		game.getMain().getPiecesSide().getPanel().add(game.getMain().getPiecesSide().getGreenElementPanel().getPanel());//Player green panel to the left part of the Right panel
		game.getMain().getPiecesSide().getPanel().add(game.getMain().getPiecesSide().getYellowElementPanel().getPanel());//Player yellow panel to the right part of the Right panel

		game.getMain().getPanel().add(game.getMain().getPiecesSide().getPanel());//I add the right panel to the window
	}

	/**
	 * It creates the pieces that are eliminated of each player
	 * @param panel It is the Creation panel of the player
	 * @param color It is the player's color
	 */
	private void piecesEliminated(ElementPanel panel, Color color) {
		for(int j = 0; j < 6; j++) {
			for(int i = 0; i < 4; i++) {
				panel.getCemeteryPieces()[j][i] = new RoundButton(color);//I set the color of the player
				panel.getCemeteryPieces()[j][i].setBackground(Color.BLACK);//Default background of the pieces is black
				panel.getCemeteryPieces()[j][i].setPreferredSize(new Dimension(39,39));
				panel.getCemetery().add(panel.getCemeteryPieces()[j][i]);//New piece deleted to the cemetery
			}
		}
	}

	/**
	 * It creates the pieces that a player can create
	 * @param panel It is the Creation panel of the player
	 * @param color It is the color of the player
	 */
	private void piecesToCreate(ElementPanel panel, Color color) {
		for(int j = 0; j < 6; j++) {
			for(int i = 0; i < 4; i++) {
				panel.getPlayerPieces()[j][i] = new RoundButton(color);//I set the color of the player
				panel.getPlayerPieces()[j][i].setBackground(Color.BLACK);//Default background of the pieces is black
				panel.getPlayerPieces()[j][i].setPreferredSize(new Dimension(39,39));
				panel.getPiecesToCreate().add(panel.getPlayerPieces()[j][i]);//New piece to panel of creation
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
		game.getMainFrame().dispose();//I close the info window
		ModelInitial m1 = new ModelInitial();//model
		ControllerInitial c1 = new ControllerInitial(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewInitial(m1, c1));
	}
}
