package elements;

import java.util.ArrayList;

import exceptions.InvalidPieceException;

public class PieceSwordShield extends Piece{
								  							 	//     0
	ArrayList<? super Symbol> object = new ArrayList<Symbol>(4);     //   3    1
	char name;				 							 //     2

/* Constructor of the class PieceSwordShield
 *
 */
	  public PieceSwordShield(char name, ArrayList<? super Symbol> auxList,int pos []) {
		  super(pos);//coordinate of the piece
		  setName(name);
		  try {
		  setObject(auxList);}
		  catch(InvalidPieceException e) {
			  System.out.println(e.getMessage());
		  }
	  }


	public ArrayList<? super Symbol> getObject() {
		return object;
	}

	public void setObject(ArrayList<? super Symbol> auxList) throws InvalidPieceException{
		for(int i = 0; i < 4; i++) {//I check that there is sword or shields objects or null
			if( !(auxList.get(i) instanceof Sword) && !(auxList.get(i) instanceof Shield) &&  (auxList.get(i) != null)){
				throw new InvalidPieceException();
			}
		}
		this.object = auxList;
	}


	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}



	@Override
	public String toString() {
		String[] aux = new String [4];
		for(int i = 0; i < 4; i++) {
		Object retrieve = object.get(i);
			if(retrieve instanceof Sword) {
				Sword retrieve1 = (Sword) retrieve;
				aux[i] = retrieve1.getShape();
			}
			else if(retrieve instanceof Shield) {
				Shield retrieve1 = (Shield) retrieve;
				aux[i] = retrieve1.getShape();
			}
			else {
				System.out.println("Estamos jodidos");
			}
		}
		return " " + aux[0] + "\n" +
				aux[3] + getName() + aux[1] +
				"\n " + aux[2];
	}


}
