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
		initBoard();
	}

	public void initBoard() {
		for(int i = 0; i < getSizeY(); i++) {
			elements.add(null);
		}

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

	/*
	 * It adds a piece into a certain point of the board
	 * @param position[0] is the X axis
	 * @param position[1] is the Y axis
	 * @param object is the piece we want to add
	 *
	 */
	public void addPiece(PieceSwordShield object, int[] position) {
		int aux = position[1];
		elements.get(position[1]).remove(aux);
		elements.get(position[1]).add(aux, object);
	}

	/*
	 * It checks if one row is empty or not
	 * @param row is the desired row to check
	 * @return true if it empty and false otherwise
	 */
	public boolean isEmptyRow(int row) {
		if(elements.get(row) == null) return true;
		else return false;
	}

	/*
	 * It checks if one element is empty or not
	 * @param row is the row of the element
	 * @param column is the column of the element
	 * @return true if it empty and false otherwise
	 */
	public boolean isEmptyElement(int row, int column) {
		if(isEmptyRow(row) == true) return true;
		else if(elements.get(row).get(column) == null) return true;
		else return false;
	}

	/*
	 * It makes all the checks necessary in order to know what symbol is it
	 */
	public String auxiliaryToString(int row, int column, int symbolIndex) {
		String message = "";//The String which will be returned
		if(isEmptyElement(row, column)) {//There is no element
			message += " ";
		}
		else {//There is an element
			PieceSwordShield aux = (PieceSwordShield) elements.get(row).get(column);//I get the piece I want to print
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
		}

		return message;
	}


	/*
	* It prints the board as it is required in the assignment
	*
	*/
	public String toString() {
		String message = "";//The String which will be returned
		PieceSwordShield aux;//Auxiliary to get the name of the piece
		for(int i = SizeY-1; i >= 0; i--) {//I go through all the rows
			for(int j = 0; j < 3; j++) {//I go line by line creating the string
				for(int z = 0; z < SizeX; z++) {//I go through all the columns
					aux = (PieceSwordShield) elements.get(i).get(z);//I get the name
					if(aux.getName() == '?') {
						message += "   ";//Empty cell
					}
					else if(j == 1) {//middle row
						message += auxiliaryToString(i,z,3);//I print the symbols
						if(isEmptyElement(i, z) == false) {//If there is no symbol we cannot get any name
							aux = (PieceSwordShield) elements.get(i).get(z);//I get the name
							message += aux.getName();
						}
						else message += " ";
						message += auxiliaryToString(i,z,j);
					}
					else {
						message += " " + auxiliaryToString(i,z,j) + " ";//I print the symbols
					}
				}
				message += "\n";
			}
		}
		return message;
	}





}
