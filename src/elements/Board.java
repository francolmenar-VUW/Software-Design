package elements;

import java.util.ArrayList;

public class Board {
	int SizeX;
	int SizeY;
	ArrayList<ArrayList<? super Piece>> elements = new ArrayList<ArrayList<? super Piece>>();

	/*
	 * Create an empty Board
	 */
	public Board(int sizeX, int sizeY) {
		setSizeX(sizeX);
		setSizeY(sizeY);
		elements = new ArrayList<ArrayList<? super Piece>>();
	}


/*
 * Create a Board with the elements
 */
	public Board(int sizeX, int sizeY, ArrayList<ArrayList<? super Piece>> elements) {
		setSizeX(sizeX);
		setSizeY(sizeY);
		setElements(elements);

	}
	public Object getElement(int i, int j) {
		return elements.get(i).get(j);
	}
	public ArrayList<ArrayList<? super Piece>> getElements() {
		return elements;
	}

	public void setElements(ArrayList<ArrayList<? super Piece>> elements) {
		this.elements = elements;
	}

	public int getSizeX() {
		return SizeX;
	}
	public void setSizeX(int sizeX) {
		SizeX = sizeX;
	}
	public int getSizeY() {
		return SizeY;
	}
	public void setSizeY(int sizeY) {
		SizeY = sizeY;
	}


}
