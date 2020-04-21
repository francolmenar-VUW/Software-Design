package gameWindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import elements.BoardSwordShield;
import elements.PieceSwordShield;
import elements.Player;
import gameWindowViewElements.ElementPanel;
import gameWindowViewElements.GameWindow;
import gameWindowViewElements.LeftPanel;
import gameWindowViewElements.RightPanel;
import gameWindowViewElements.RoundButton;
import initialMenu.ControllerInitial;
import initialMenu.ModelInitial;
import initialMenu.ViewInitial;

public class ViewGameWindow extends JComponent implements Observer{
	public static final int PLAYER1 = 1;
	public static final Color PLAYER1COLOR = Color.YELLOW;
	public static final int PLAYER2 = 2;
	public static final Color PLAYER2COLOR = Color.GREEN;

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
		game.getToolBar().getSurrender().addActionListener(controller);//Listener to Surrender Button
		game.getMainFrame().add(game.getToolBar().getControlButtons(),BorderLayout.PAGE_START);//ToolBar to the JFrame
	}

	/**
	 * It creates the Board and it is the responsible of inserting it into the other panel
	 */
	private void arrangeBoard() {
		createBoardPieces();
		JPanel board = returnLeftPanel().getBoard();//The board where I am inserting the piece
		JPanel leftPanel = returnLeftPanel().getPanel();//The Left panel where the board is going to be
		JPanel rightPanel = returnRightPanel().getPanel();//The Right panel where are the pieces to create

		for(int i = 0; i < myModel.getBoard().getSizeX(); i++) {//I go through the board
			for(int j = myModel.getBoard().getSizeY() -1; j >= 0; j--) {
				JButton piece = returnPieceOfTheBoard(j, i);//The piece I am using
				piece.setName(j + " " + i);
				piece.setPreferredSize(new Dimension(39,39));
				board.add(piece);
			}
		}
		leftPanel.add(board);//Board to left Panel

		game.getMainPanel().getPanel().add(leftPanel);//Left panel to Main panel
		game.getMainPanel().getPanel().add(rightPanel);//Right panel to Main panel

		game.getMainFrame().add(game.getMainPanel().getPanel());//I add the Main panel to the window
	}


	/**
	 * It creates initially all the pieces of the board
	 */
	private void createBoardPieces() {
		BoardSwordShield boardGame =  myModel.getBoard();//The board
		PieceSwordShield aux;//Auxiliary to get the name of the piece
		for(int j = 0; j < myModel.getBoard().getSizeY(); j++) {
			for(int i = 0; i < myModel.getBoard().getSizeX(); i++) {
				aux = (PieceSwordShield) boardGame.getElements().get(j).get(i);//I get the name of the piece
				JButton piece = new JButton();//The piece of the board that I am going to create
				if(aux.getName() == '?') {//Empty cell
					newPieceIntoEmptyCell(piece, j, i);
				}
				else if(aux.getName() == '/') {//Forbidden cell
					newPieceIntoForbiddenCell(piece);
				}
				else {//Any face
					String url = "";
					if(aux.getName() == '0'){//Yellow face
						url = "images/YellowFace.png";
					}
					else {//Green face
						url = "images/GreenFace.png";
					}
					newPieceIntoFaceCell(piece, url);
				}
			game.getMainPanel().getBoardPanel().getPieces()[j][i] = piece;//I save the new piece
			}
		}
	}

	/**
	 * I create the Face piece
	 * @param piece The piece to be created
	 * @param url Where the image is stored
	 */
	private void newPieceIntoFaceCell(JButton piece, String url) {
		try {
			ImageIcon icon = new ImageIcon(url);
			piece.setIcon(icon);
		} catch (Exception e) {
		    e.printStackTrace();
		  }
	}

	/**
	 * It creates a forbidden cell
	 * @param piece The piece to be created
	 */
	private void newPieceIntoForbiddenCell(JButton piece) {
		piece.setBackground(Color.LIGHT_GRAY);//White spaces
	}

	/**
	 * It creates a new Piece in the board into an empty place
	 * @param piece The piece to be created
	 * @param j row
	 * @param i column
	 */
	private void newPieceIntoEmptyCell(JButton piece, int j, int i) {
		if(j == 2 && i == 7) {//Yellow creation
			piece.setBackground(Color.YELLOW);
		}
		else if(j ==  7 && i == 2) {//Green creation
			piece.setBackground(Color.GREEN);
		}
		else if((j % 2 != 0  && i % 2 != 0) || (j % 2 == 0 && i % 2 == 0)){//White spaces
			piece.setBackground(Color.WHITE);
			}
			else {//Gray spaces
				piece.setBackground(Color.GRAY);
			}
	}



	/**
	 * It creates the pieces to be created and the deleted ones of btoh players
	 */
	private void arrangePieces() {
		ElementPanel yellowPanel = returnPlayerPanel(PLAYER1);//Yellow Player panel with the creation and cemetery pieces
		ElementPanel greenPanel = returnPlayerPanel(PLAYER2);
		RightPanel rightPanel = returnRightPanel();

		piecesToCreate(yellowPanel, PLAYER1COLOR, myModel.getPlayer1());//I create the pieces that the yellow player can create
		piecesToCreate(greenPanel, PLAYER2COLOR, myModel.getPlayer2());//I create the pieces that the green player can create

		rightPanel.getPanel().add(yellowPanel.getPanel());//Player yellow panel to the left part of the Right panel
		rightPanel.getPanel().add(greenPanel.getPanel());//Player green panel to the right part of the Right panel

		game.getMainPanel().getPanel().add(rightPanel.getPanel());//I add the right panel to the window
	}


	/**
	 * It creates the pieces that are eliminated of each player
	 * @param playerPanel It is the Creation panel of the player
	 * @param color It is the player's color
	 */
	/*
	private void piecesEliminated(ElementPanel playerPanel, Color color) {
		for(int j = 0; j < 6; j++) {
			for(int i = 0; i < 4; i++) {
				getCemeteryPieces()[j][i]
				RoundButton piece = returnPlayerPieceDeleted(playerPanel, j, i);//The actual piece
				panel.getCemeteryPieces()[j][i] = new RoundButton(color);//I set the color of the player
				panel.getCemeteryPieces()[j][i].setBackground(Color.BLACK);//Default background of the pieces is black
				panel.getCemeteryPieces()[j][i].setPreferredSize(new Dimension(39,39));
				panel.getCemetery().add(panel.getCemeteryPieces()[j][i]);//New piece deleted to the cemetery
			}
		}
	}
	*/


	/**
	 * It creates the pieces that a player can create
	 * @param playerPanel It is the Creation panel of the player
	 * @param color It is the color of the player
	 * @param player
	 */
	private void piecesToCreate(ElementPanel playerPanel, Color color, Player player) {
		int count = 0;
		for(int j = 0; j < 6; j++) {
			for(int i = 0; i < 4; i++) {
				if(count >= player.getPieces().size()) { return;}//There is no more pieces to create
				PieceSwordShield aux = (PieceSwordShield) player.getPieces().get(count);
				RoundButton piece = returnPlayerPiece(playerPanel, j, i);//The actual piece
				piece = new RoundButton(color, aux.getObject());//I create the piece
				piece.setBackground(Color.BLACK);//Default background of the pieces is black
				piece.setPreferredSize(new Dimension(39,39));
				playerPanel.getPiecesToCreate().add(piece);//New piece to panel of creation
				count++;
			}
		}
	}

	/**
	 * It returns a specific Piece of the board
	 * @param j the row
	 * @param i the column
	 * @return The JButton which is the piece we are looking for
	 */
	private JButton returnPieceOfTheBoard(int j, int i) {
		return game.getMainPanel().getBoardPanel().getPieces()[j][i];
	}

	/**
	 * It returns the Left Panel which contains the Board
	 * @return the Left Panel
	 */
	private LeftPanel returnLeftPanel() {
		return game.getMainPanel().getBoardPanel();
	}

	/**
	 * It returns the Right Panel which contains all the Player pieces
	 * @return the Right Panel
	 */
	private RightPanel returnRightPanel() {
		return game.getMainPanel().getPiecesSide();
	}

	/**
	 *It returns the Panel of a certain player
	 * @param playerId It represents if we want the panel of the player 1 or 2
	 * @return It is returned the Panel of the desired Player
	 */
	private  ElementPanel returnPlayerPanel(int playerId) {
		if(playerId == 1) {return game.getMainPanel().getPiecesSide().getYellowElementPanel();}//Yellow Player
		else { return game.getMainPanel().getPiecesSide().getGreenElementPanel();}//Green Player
	}

	/**
	 * It returns a specific piece to create of a player
	 * @param panel It is the Panel where the pieces are. It is passed the yellow one or the green depending of the player
	 * @param j It is the row
	 * @param i It is the column
	 * @return The Piece to create of the player as a RoundButton
	 */
	private RoundButton returnPlayerPiece(ElementPanel panel, int j, int i) {
		return panel.getPlayerPieces()[j][i];
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
