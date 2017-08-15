package elements;

import java.util.ArrayList;

public class Player {
	ArrayList<? super Piece> pieces = new ArrayList<>();

	public ArrayList<? super Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<? super Piece> pieces) {
		this.pieces = pieces;
	}

	public Player(ArrayList<? super Piece> listP0) {
		super();
		this.pieces = listP0;
	}

}
