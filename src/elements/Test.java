package elements;

import java.util.ArrayList;
public class Test {
	public static final int sizeX = 10;//Dimensions of the board
	public static final int sizeY = 10;
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
		switch(name) {
		case '?'://empty cells
			for(int i = 0; i < 4; i++) {
				auxList.add(null);
			}
			break;
		case '/'://forbidden cells
			for(int i = 0; i < 4; i++) {
				auxList.add(null);
			}
			break;
		default:
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

	/*
	 * It creates a default Board for the game
	 */
	public static Board createBoard() {
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
		Board mesa = new Board(sizeX, sizeY, elements);//Board created
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

	public static void main(String[] args){
		Board mesa = createBoard();
		int [][][] pos = initMatrixPoints();
		char letter = 'a';
		ArrayList<PieceSwordShield> help = new ArrayList<>(10);
		int auxInt [] = {1,1,1,1};
		for(int i = 0; i < sizeX; i++) {
			help.add(createPiece(letter, pos[i][i], auxInt));
			mesa.addPiece(help.get(i), pos[i][i]);
			letter++;
		}
		System.out.println(mesa.toString());
	}
}