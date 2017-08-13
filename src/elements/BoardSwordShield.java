package elements;

import java.util.ArrayList;

import exceptions.InvalidPieceException;

public class BoardSwordShield extends Board{

	public BoardSwordShield(int sizeX, int sizeY) {
		super(sizeX, sizeY);
	}

	public BoardSwordShield(int sizeX, int sizeY, ArrayList<ArrayList<? super Piece>> elements) {
		super(sizeX, sizeY, elements);
	}

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

	public PieceSwordShield exists(char letter) {
		PieceSwordShield object;
		for(int i = 0; i < getSizeY(); i++) {
			for(int j = 0; j < getSizeX(); j++) {
				object = (PieceSwordShield) elements.get(i).get(j);
				if(object.getName() == letter) return object;
			}
		}
		return null;
	}



	/*
	 * I move a piece in the given direction
	 */
	public void move(char letter, String direction) {
		PieceSwordShield object, toMove;
		int [] pos = new int [2];
		if((object = exists(letter)) != null) {//I check if the piece exists
			switch(direction) {
				case "up":
					pos[0] = object.getPosition()[0];
					pos[1] = object.getPosition()[1] + 1;//Upper position
					break;
				case "down":
					pos[0] = object.getPosition()[0];
					pos[1] = object.getPosition()[1] - 1;//Down position
					break;
				case "right":
					pos[0] = object.getPosition()[0] + 1;//Right position
					pos[1] = object.getPosition()[1];
					break;
				case "left":
					pos[0] = object.getPosition()[0] - 1;//Left position
					pos[1] = object.getPosition()[1];
					break;
				default:

					break;
			}
			toMove = (PieceSwordShield) elements.get(pos[1]).get(pos[0]);
			if(toMove.getName() == '?') {//There is no piece in the new position
				toMove.setName(object.getName());
				try {
					toMove.setObject(object.getObject());
				} catch (InvalidPieceException e) {
					e.printStackTrace();
				}
				addPiece(toMove, pos);
				removeElement(object.getPosition());//I remove the element in the old position
			}
		}
	}

	public void removeElement(int[] position) {
		int aux = position[0];
		PieceSwordShield object = (PieceSwordShield) elements.get(position[1]).get(position[0]);
		object.name = '?';
		elements.get(position[1]).remove(aux);
		elements.get(position[1]).add(aux, object);
	}

	/*
	 * It adds a piece into a certain point of the board
	 * @param position[0] is the X axis
	 * @param position[1] is the Y axis
	 * @param object is the piece we want to add
	 *
	 */
	public void addPiece(PieceSwordShield object, int[] position) {
		int aux = position[0];
		elements.get(position[1]).remove(aux);
		elements.get(position[1]).add(aux, object);
	}


	/*
	 * It makes all the checks necessary in order to know what symbol is it
	 */
	public String auxiliaryToString(int row, int column, int symbolIndex) {
		String message = "";//The String which will be returned
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
		return message;
	}


	/*
	* It prints the board as it is required in the assignment
	*
	*/
	public String toString() {
		String message = "";//The String which will be returned
		PieceSwordShield aux;//Auxiliary to get the name of the piece
		for(int i = getSizeY()-1; i >= 0; i--) {//I go through all the rows
			for(int j = 0; j < 3; j++) {//I go line by line creating the string
				for(int z = 0; z < getSizeX(); z++) {//I go through all the columns
					aux = (PieceSwordShield) elements.get(i).get(z);//I get the name
					if(aux.getName() == '?') {
						message += "   ";//Empty cell
					}
					else if(aux.getName() == '/') {
						message += "///";//Empty cell
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
