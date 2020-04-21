package gameWindow;

import java.util.ArrayList;
import java.util.Observable;

import elements.BoardSwordShield;
import elements.Piece;
import elements.PieceSwordShield;
import elements.Player;
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

	private Player player1;
	private static final char PLAYER1CHAR = 'a';
	private static final int PLAYER1 = 1;

	private Player player2;
	private static final int PLAYER2 = 2;
	private static final char PLAYER2CHAR = 'A';

	private int mode;

	public ModelGameWindow() {
		player1 = new Player(createAllPieces(PLAYER1CHAR));//I create the players and their initial pieces
		player2 = new Player(createAllPieces(PLAYER2CHAR));
		board = createBoard();//I create the board
		setMode(2);//Game Window Mode
	}

	/**
	 * It changes to the menu window
	 */
	public void goMenu() {
		setMode(0);
		setChanged();
	    notifyObservers();
	}

	/**
	 * It creates a default Board for the game
	 * @return The board created is returned
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
	 * It creates a list of pieces
	 * @param aux It is the String which have the information of the cells(if it is empty or is a piece)
	 * @param pos The position of the first piece
	 * @param elem The array of elements to create the pieces
	 * @return The list of Pieces
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
	 * It creates a PieceSwordShield
	 * @param name The name of the piece
	 * @param pos The position of the piece
	 * @param elem The symbols which is going to have the piece
	 * @return The PieceSwordShield itself
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

	/**
	 * It creates all the pieces that the player has at the beginning of the game
	 * @param letter The letter of the first piece
	 * @return A list with all the pieces is returned
	 */
	public static ArrayList<? super Piece> createAllPieces(char letter){
		int [] pos = {-1,-1};//Default value to not been created
		ArrayList<? super Piece> object = new ArrayList<Piece>(NPIECES);//List of pieces
		for(int j = 0; j < 6; j++) {//one sword more each time
			int [] elem = {0,0,0,0};//Reset the elements
			if(j != 0 && j != 5) {//I check the base case of no swords
				for(int z = 0; z < j; z++) {
					if(z == 0)elem[0] = 1;//one sword more
					else elem[4-z] = 1;
				}
			}
			else if(j == 5) {//special case of swords
				elem[0] = 1;
				elem[2] = 1;
			}
			object.add(createPiece(letter,pos, elem));//Elements with no shields
			letter++;
			for(int i = 0; i < 5 ; i++) {//All the ball and shields permutations in the clock wise
				if(i < 4 && elem[i] != 1) {
					if(j == 5 && i == 1) elem[3] = 2;//special case
					else if(j == 5 && i == 3) elem[1] = 2;//special case
					else elem[i] = 2;//shield
					object.add(createPiece(letter,pos, elem));
					letter++;
				}
			}
			if(elem[1] != 1 && elem[3] != 1 && elem[2] != 1) {//I check for the special case
				if(elem[0] != 1) elem[0] = 0;//nothing
				elem[1] = 2;//shield
				if(elem[2] != 1) elem[2] = 0;//nothing
				elem[3] = 2;//shield
				object.add(createPiece(letter,pos, elem));
				letter++;
			}
		}
		int [][] elem = {{1,0,2,0}, {1,0,0,2},{1,0,2,2},{1,0,2,1}};//Special cases
		for(int i = 0; i < elem.length; i++) {
			object.add(createPiece(letter,pos, elem[i]));
			letter++;
		}
		return object;
	}

	public BoardSwordShield getBoard() {
		return board;
	}

	public void setBoard(BoardSwordShield board) {
		this.board = board;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
