package gameWindowViewElements;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LeftPanel extends BasicPanel{

	private JPanel board;//The board to play
	private JButton [][] pieces;//The pieces of the board

	public LeftPanel() {
		super();
		confBoard();
		pieces = new JButton[10][10];//The board's pieces
	}

	private void confBoard() {
		board = new JPanel();
		board.setLayout(new GridLayout(10,10));//I set the size of the board
	}

	public JPanel getBoard() {
		return board;
	}

	public void setBoard(JPanel board) {
		this.board = board;
	}

	public JButton[][] getPieces() {
		return pieces;
	}

	public void setPieces(JButton[][] pieces) {
		this.pieces = pieces;
	}

}
