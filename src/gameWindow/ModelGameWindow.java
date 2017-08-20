package gameWindow;

import java.util.ArrayList;
import java.util.Observable;

import elements.BoardSwordShield;
import elements.Piece;
import elements.PieceSwordShield;
import elements.Shield;
import elements.Sword;
import elements.Symbol;

public class ModelGameWindow extends Observable{
	private static final int sizeX = 10;//Dimensions of the board
	private static final int sizeY = 10;
	private static final int NPIECES = 24;
	private static final int [] ZERO = {8,1};
	private static final int [] ONE = {1,8};
	private static final int [] CREAT0 = {7, 2};
	private static final int [] CREAT1 = {2, 7};
	private BoardSwordShield board;

	private int mode;

	public ModelGameWindow() {
		setMode(2);//Game Window Mode
		board = createBoard();//I create the board
	}


	/**
	 * It changes to the menu window
	 */
	public void goMenu() {
		setMode(0);
		setChanged();
	    notifyObservers();
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	/**
	 * It creates a default Board for the game
	 */
	public static BoardSwordShield createBoard() {
		ArrayList<ArrayList<? super Piece>> elements = new ArrayList<ArrayList<? super Piece>>();//List of rows of pieces
		String [] cells = new String[5];//All the types of initial rows
		cells[0] = "????????//";//Down row
		cells[1] = "????????0/";//Yellow player face
		cells[2] = "??????????";//Empty cells
		cells[3] = "/1????????";//Green player face
		cells[4] = "//????????";//Top row
		int j;//aux for the cells
		int [] pos = {0,0};//Initial position of the board
		int [][] elem = new int [sizeX][sizeY];
		for(int i = 0; i < sizeY; i++) {//I go through all the rows of the board
			if(i == 0 || i == 1) j = i;//First or second row
			else if(i == sizeY-2) j = 3;//one before the last one
			else if(i == sizeY-1)j = 4;//last row
			else j = 2;//Any other row
			pos[1] = i;//The actual row
			elements.add(createListOfPieces(cells[j],pos, elem));//List of pieces
		}
		BoardSwordShield mesa = new BoardSwordShield(sizeX, sizeY, elements);//Board created
		return mesa;
	}

	/**
	* It creates a list of Pieces
	* It calls to the createPiece method
	*/
	public static ArrayList<? super Piece> createListOfPieces(String aux, int[] pos,int[][] elem) {
		ArrayList<? super Piece> object = new ArrayList<Piece>();//List of pieces
		for(int i = 0; i < sizeX; i++) {
			if(aux.charAt(i) == '?') {//empty cell
				object.add(createPiece(aux.charAt(i),pos, elem[i]));
			}
			else {//normal cell
				object.add(createPiece(aux.charAt(i),pos, elem[i]));
			}
			pos[0]++;
		}
		return object;
	}

	/**
	 * It creates a piece
	 *
	 * 0 null
	 * 1 Sword
	 * 2 Shield
	 *
	 */
	public static PieceSwordShield createPiece(char name, int[] pos, int[] elem) {
		ArrayList <? super Symbol> auxList = new ArrayList < Symbol> (4);//List of symbols for each piece
		if(name == '?' || name == '/' || name == '0' || name == '1') {
			for(int i = 0; i < 4; i++) {
				auxList.add(null);
			}
		}
		else {
			for(int i = 0; i < 4; i++) {
				switch(elem[i]) {
					case 1://Sword
						if(i % 2 != 0) {
							auxList.add(new Sword("-"));
						}
						else {
							auxList.add(new Sword("|"));
						}
						break;
					case 2://shield
						auxList.add(new Shield("#"));
						break;
					default:
						auxList.add(null);
						break;
				}
			}
		}
		PieceSwordShield piece = new PieceSwordShield(name, auxList, pos);
		return piece;
	}

	public BoardSwordShield getBoard() {
		return board;
	}

	public void setBoard(BoardSwordShield board) {
		this.board = board;
	}
}
