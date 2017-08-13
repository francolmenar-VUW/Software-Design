package elements;

import java.util.ArrayList;
public class Test {
	public static final int sizeX = 10;//Dimensions of the board
	public static final int sizeY = 10;
	public static final int NPIECES = 24;
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

	public static void main(String[] args){
		BoardSwordShield mesa = createBoard();
		int [][][] pos = initMatrixPoints();
		char letter = 'a';
		ArrayList<PieceSwordShield> help = new ArrayList<>(10);
		int auxInt [] = {1,1,1,1};
		for(int i = 0; i < sizeX; i++) {
			help.add(createPiece(letter, pos[i][i], auxInt));
			mesa.addPiece(help.get(i), pos[i][i]);
			letter++;
		}
		int [] origin = {0,0};
		mesa.removeElement(origin);;
		System.out.println("\t    BOARD\n");
		System.out.println(mesa.toString());
		System.out.println("\n\t\tTO CREATE\n");
		ArrayList<? super Piece> object = createAllPieces('a');
		System.out.println(printListOfPieces(object, NPIECES));
		PieceSwordShield pra = createPiece('w', origin, auxInt);
		mesa.addPiece(pra, origin);
		System.out.println("\t    BOARD\n");
		System.out.println(mesa.toString());
	}
}
