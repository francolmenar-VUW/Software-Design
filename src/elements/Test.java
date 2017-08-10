package elements;

import java.awt.List;
import java.util.ArrayList;

public class Test {
	/*
	* It creates a list of Pieces
	* It calls to the createPiece method
	*/
	public static ArrayList<? super Piece> createListOfPieces(int sizeX,char aux, int[] pos,int[][] elem) {
		ArrayList<? super Piece> object = new ArrayList<Piece>();//List of pieces
		if(aux == '?') {
			for(int i = 0; i < sizeX; i++) {
				object.add(createPiece(aux,pos, elem[i]));
				pos[0]++;
			}
		}
		for(int i = 0; i < sizeX; i++) {
			object.add(createPiece(aux,pos, elem[i]));
			aux = (char)(aux+1);
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

	public static Board createBoard(int SizeX, int SizeY) {
		ArrayList<ArrayList<? super Piece>> elements = new ArrayList<ArrayList<? super Piece>>();//List of rows of pieces
		char aux = '?';//Empty cells
		int [] pos = {0,0};
		int [][] elem = new int [SizeX][SizeY];
		for(int i = 0; i < SizeX; i++) {//mal
			for(int j = 0; j < SizeY; j++) {
				elem[i][j] = 0;
			}
		}
		for(int i = 0; i < SizeY; i++) {
			pos[1] = i;
			elements.add(createListOfPieces(SizeX, aux,pos, elem));//List of pieces
		}
		Board mesa = new Board(SizeX, SizeY, elements);//Board of 10x10
		return mesa;
	}

	public static void main(String[] args){
		int sizeX = 5;
		int sizeY = 5;
		Board mesa = createBoard(sizeX,sizeY);
		int [][][] pos = new int [sizeX][sizeY][2];
		for(int i = 0; i < sizeY; i++) {//matrix of points
			for(int j = 0; j < sizeX; j++) {
				pos[j][i][0] = j;
				pos[j][i][1] = i;
			}
		}
		char letter = 'a';
		ArrayList<PieceSwordShield> help = new ArrayList<>(10);
		int auxInt [] = {1,1,1,1};
		for(int i = 0; i < sizeX; i++) {
			help.add(createPiece(letter, pos[i][i], auxInt));
			mesa.addPiece(help.get(i), pos[i][i]);
			letter++;
		}
		//System.out.println(mesa.toString() + "\n\n\n\n");
		System.out.println(mesa.toString());
	}
}