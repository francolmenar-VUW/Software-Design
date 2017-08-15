package elements;

import java.util.ArrayList;
import java.util.Scanner;

import exceptions.InvalidPieceException;
public class Game {
	public static final int sizeX = 10;//Dimensions of the board
	public static final int sizeY = 10;
	public static final int NPIECES = 24;
	public static final int [] ZERO = {8,1};
	public static final int [] ONE = {1,8};
	public static final int [] CREAT0 = {7, 2};
	public static final int [] CREAT1 = {2, 7};

	/*
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

	/*
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

	public static void printLineOfPiece(int length, ArrayList<? super Piece> object) {
		for(int i = 0; i < length; i++) {
			System.out.println(object.get(i).toString());
		}
	}



	public static String auxiliaryToString(int column, int symbolIndex, PieceSwordShield aux, int columns) {
		String message = "";//The String which will be returned
		Object retrieve = aux.getObject().get(symbolIndex);//I choose the symbol of the piece that I want
		if(retrieve instanceof Sword) {//I check if it is a sword or a shield
			Sword retrieve1 = (Sword) retrieve;
			message += retrieve1.getShape();//I copy the shape to the string
		}
		else if(retrieve instanceof Shield) {//shield
			Shield retrieve1 = (Shield) retrieve;//auxiliary object to make the casting
			message += retrieve1.getShape();
		}
		else {//null
			message += " ";
		}
		return message;
	}


	public static String printListOfPieces(ArrayList<? super Piece> elements, int columns) {
		String message = "";//The String which will be returned
		PieceSwordShield aux;//Auxiliary to get the name of the piece
			for(int j = 0; j < 3; j++) {//I go line by line creating the string
				for(int z = 0; z < columns; z++) {//I go through all the columns
					aux = (PieceSwordShield) elements.get(z);//I get the name
					if(aux.getName() == '?') {
						message += "   ";//Empty cell
					}
					else if(aux.getName() == '/') {
						message += "///";//Empty cell
					}
					else if(j == 1) {//middle row
						message += auxiliaryToString(z,3, aux, columns);//I print the symbols
						message += aux.getName();
						message += auxiliaryToString(z,j, aux,columns);
					}
					else {
						message += " " + auxiliaryToString(z,j, aux, columns) + " ";//I print the symbols
					}
				}
				message += "\n";
			}
		return message;
	}

	/*
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

	/*
	 * It initializes a Matrix of points, each point having the X and Y coordinates
	 */
	public static int[][][] initMatrixPoints(){
		int [][][] pos = new int [sizeX][sizeY][2];
		for(int i = 0; i < sizeY; i++) {//matrix of points
			for(int j = 0; j < sizeX; j++) {//Initialize the pos array
				pos[j][i][0] = j;
				pos[j][i][1] = i;
			}
		}
		return pos;
	}

	public static ArrayList<? super Piece> createAllPieces(char letter){
		int [] pos = {-1,-1};//Defaul value to not been created
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

	public static int turn(Player player, BoardSwordShield mesa, int id) {
		PieceSwordShield pieceOne = (PieceSwordShield) mesa.elements.get(ONE[1]).get(ONE[0]);
		PieceSwordShield pieceZero = (PieceSwordShield) mesa.elements.get(ZERO[1]).get(ZERO[0]);
		ArrayList <? super Symbol> auxList = new ArrayList < Symbol> (4);//List of symbols for each piece
		for(int i = 0; i < 4; i++) {
			auxList.add(null);
		}
		if(pieceOne.getName() == '?') {
			pieceOne.setName('1');
			try {
				pieceOne.setObject(auxList);
			} catch (InvalidPieceException e) {
				e.printStackTrace();
			}
			mesa.addPiece(pieceOne, ONE);
		}
		if(pieceZero.getName() == '?') {
			pieceZero.setName('0');
			try {
				pieceZero.setObject(auxList);
			} catch (InvalidPieceException e) {
				e.printStackTrace();
			}
			mesa.addPiece(pieceZero, ZERO);
		}
		System.out.println("\t    BOARD\n");//print the board
		System.out.println(mesa.toString());
		System.out.println("\n\n\tPIECES TO CREATE PLAYER " + id + "\n");//print the pieces
		System.out.println(printListOfPieces(player.getPieces(), player.getPieces().size()));
		if(id == 0)	return 1;
		else return 0;
	}

	/*
	* It creates a new piece in the creation cell of a ccertain player
	*/
	public static boolean create(char letter,Player p1, BoardSwordShield mesa, int id) {
		PieceSwordShield object;
		int [] aux;
		if(id == 0) {//To know who is the player
			PieceSwordShield pieceZero = (PieceSwordShield) mesa.elements.get(CREAT0[1]).get(CREAT0[0]);
			aux = CREAT0;
			if(pieceZero.getName() != '?')return false;
		}
		else {
			PieceSwordShield pieceOne = (PieceSwordShield) mesa.elements.get(CREAT1[1]).get(CREAT1[0]);
			aux = CREAT1;
			if(pieceOne.getName() != '?')return false;
		}
		for(int i = 0; i < p1.getPieces().size(); i++) {//I go through all the pieces to be created by the player
			object = (PieceSwordShield) p1.getPieces().get(i);
			if(object.getName() == letter) {//I check if the piece can be created
					if(id == 0) object.setPosition(CREAT0);//I choose the right position to create the piece
					else object.setPosition(CREAT1);
					mesa.addPiece(object, aux);
					p1.pieces.remove(i);
					return true;
					}
		}
		return false;
	}


	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);//Reading from the keyword
		BoardSwordShield mesa = createBoard();//I create the board
		String scr;
		String [] scrArray;
		char [] auxChar;
		int aux = 0, i = 0,id = 0;
		ArrayList<? super Piece> listP0 = createAllPieces('a');//I create the pieces of both players
		Player p0 = new Player((listP0));//I create the players
		ArrayList<? super Piece> listP1 = createAllPieces('A');
		Player p1 = new Player(listP1);
		boolean pass;//The boolean for passing the turn
		while(i < 500) {//Just to execute it as many times as I want
			i++;
			pass = false;
			while(pass == false){
				if(id == 1) {//I check of who is the turn
					aux = turn(p1, mesa, id);
				}
				else aux = turn(p0, mesa, id);
				System.out.println("\n\nChoose an action to perform");
				scr = scanner.nextLine();//I read the action to be performed
				scrArray = scr.split(" ");
				if(scrArray.length == 3 || scrArray.length == 2 || scrArray.length == 1) {//Correct number of arguments
					if(scrArray.length >1) {//It is not a pass action
						auxChar = scrArray[1].toCharArray();//I convert the letter of the piece to char
						if(auxChar.length == 1) {//Correct letter introduced
							if(scrArray[0].equals("move") && scrArray.length == 3) {//Move command
								if(mesa.move(auxChar[0], scrArray[2]) == false) System.out.println("Wrong move command");
							}
							else if(scrArray[0].equals("create") && scrArray.length == 2) {//Create command
								if(id == 0) {
									if(create(auxChar[0], p0,  mesa, id) == false) {
										System.out.println("Creation bussy");
									}
								}
								else {
									if(create(auxChar[0], p1,  mesa, id) == false) {
										System.out.println("Creation bussy");
									}
								}
							}
						}
						else System.out.println("Not move");
						}
					else if(scrArray[0].equals("pass") && scrArray.length == 1) {//Pass action
						pass = true;
					}
					else System.out.println("Wrong letter introduced");
				}
				else System.out.println("Wrong number of arguments: Length " + scrArray.length);
			}
			id = aux;//I change the turn
		}
		scanner.close();
	}
}
