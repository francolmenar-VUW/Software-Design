package elements;

import java.util.ArrayList;

public class Player {
	ArrayList<PieceSwordShield> pieces = new ArrayList<>();

	public ArrayList<PieceSwordShield> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<PieceSwordShield> pieces) {
		this.pieces = pieces;
	}

	public Player(ArrayList<PieceSwordShield> pieces) {
		super();
		this.pieces = pieces;
	}

}
