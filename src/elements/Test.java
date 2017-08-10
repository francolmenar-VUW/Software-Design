package elements;

import java.awt.List;
import java.util.ArrayList;

public class Test {
	/*
	* It creates a list of Pieces
	* It calls to the createPiece method
	*/
	public static ArrayList<? super Piece> createListOfPieces(int number,char aux, int[] pos,int[][] elem) {
		ArrayList<? super Piece> object = new ArrayList<Piece>();//List of pieces
		if(aux == '?') {
			for(int i = 0; i < number; i++) {
				object.add(createPiece(aux,pos, elem[i]));
				pos[0]++;
			}
		}
		for(int i = 0; i < number; i++) {
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

	public static void main(String[] args){
		//int SizeY = 3;
		int SizeX = 4;
		ArrayList<ArrayList<? super Piece>> elements = new ArrayList<ArrayList<? super Piece>>();//List of rows of pieces
		char aux = '?';
		int [][] elem = {{1,1,2,1},{1,1,2,0},{1,1,2,1},{1,1,2,1}};
		int [][] elem1 = new int [4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(j != 2) {
					elem1[i][j] = 1;
				}
			}
		}
		int [] pos = {1,1};
		ArrayList<? super Piece> object = createListOfPieces(SizeX, aux,pos, elem);//List of pieces
		ArrayList<? super Piece> object1 = createListOfPieces(SizeX, aux,pos, elem1);//List of pieces
		elements.add(object);
		elements.add(object1);
		Board mesa = new Board(4, 2, elements);//Board of 4x4
		//mesa.addPiece((PieceSwordShield) object.get(1), pos);
		System.out.println(mesa.toString());
	}
}