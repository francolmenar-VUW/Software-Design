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

	/*
	 * It checks if a certain piece is in the board
	 */
	public PieceSwordShield exists(char letter) {
		PieceSwordShield object;
		for(int i = 0; i < getSizeY(); i++) {//I go through all the board
			for(int j = 0; j < getSizeX(); j++) {
				object = (PieceSwordShield) elements.get(i).get(j);
				if(object.getName() == letter) return object;//If i found it, it is returned
			}
		}
		return null;//The piece is not in the board
	}

	/*
	 * It calculates the position of the next move
	 */
	public int[] auxMove(String direction, PieceSwordShield object) {
		int [] pos = new int [2];
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
			System.out.println("Default");
			return null;
		}
		return pos;
	}


	/*
	 * I move a piece in the given direction
	 */
	public boolean move(char letter, String direction) {
		PieceSwordShield object, toMove;
		int [] pos = new int [2];
		if((object = exists(letter)) != null) {//I check if the piece exists
			if( (pos = auxMove(direction, object)) == null) {//I calculate the position that the piece has to have
				return false;
			}
			if(pos[0] > 9 || pos[0] < 0 || pos[1] > 9 || pos[1] < 0) {//Forbidden moves
				System.out.println("Forbiden move: Out of bounds \nPos (" + pos[0] + ", " + pos[1] + ")");
				return false;
			}
			else{//It does not go out of the board
				//System.out.println("Pos (" + pos[0] + ", " + pos[1] + ")");
				toMove = (PieceSwordShield) elements.get(pos[1]).get(pos[0]);
				if(toMove.getName() == '?' || toMove.getName() == '0' || toMove.getName() == '1') {//There is no piece in the new position
					toMove.setName(object.getName());
					try {
						toMove.setObject(object.getObject());
					} catch (InvalidPieceException e) {
						e.printStackTrace();
					}
					toMove.setPosition(pos);
					addPiece(toMove, pos);
					removeElement(object.getPosition());//I remove the element in the old position
					return true;
				}
				else if(toMove.getName() == '/' ) {//Forbidden moves
					System.out.println("Forbiden move /");
					return false;
				}
				else {//The piece moves another piece
					System.out.println("Enter");
					ArrayList<? super Piece> list = new ArrayList<Piece>();//List of pieces
					list.add(object);//I add the first piece to move
					list.add(toMove);//I add the following piece
					boolean out = false;
					boolean clear = false;
					boolean wall = false;
					while(out == false && clear == false && wall == false) {//I check if the piece is deleted or it pushed another one
						pos = auxMove(direction, (PieceSwordShield) list.get(list.size()-1));//I calculate the next position
						if(pos[0] > 9 || pos[0] < 0 || pos[1] > 9 || pos[1] < 0) {//Out of the board
							System.out.println("Out of the board");
							out = true;
						}
						else {//Inside the board
							PieceSwordShield aux = (PieceSwordShield) elements.get(pos[1]).get(pos[0]);
							if(aux.getName() == '?' || aux.getName() == '0' || aux.getName() == '1') {//The piece can be moved
								System.out.println("clear");
								clear = true;
							}
							else if(toMove.getName() == '/' ) {//It cannot be moved
								System.out.println("wall");
								wall = true;
							}
							else {//another piece
								System.out.println("another piece");
								list.add(aux);//another piece to be moved
							}
						}
					}
					if(out) {//The last piece moved is removed
						PieceSwordShield aux = (PieceSwordShield) list.get(list.size()-1);
						list.remove(list.size()-1);//I delete the element from the list
						removeElement(aux.getPosition());
						clear = true;//I have to move the rest of pieces
					}
					if(clear) {//I move all the pieces in the list
						for(int i = list.size()-1; i >= 0; i--) {
							PieceSwordShield aux = (PieceSwordShield) list.get(i);//I get the last element
							move(aux.getName(), direction);//I move the pieces
						}
					}
					if(wall) return true;//They cannot been moved
					return true;
				}
			}
		}
		else {//Piece does not exists
			System.out.println("Piece does not exists");
			return false;
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
