package elements;

import java.util.ArrayList;

public class Player {
	ArrayList<? super Piece> pieces = new ArrayList<>();
	ArrayList<? super Piece> piecesDeleted = new ArrayList<>();

	public Player(ArrayList<? super Piece> listP0) {
		super();
		this.pieces = listP0;
		piecesDeleted = new ArrayList<>();
	}

	public ArrayList<? super Piece> getPiecesDeleted() {
		return piecesDeleted;
	}

	public void setPiecesDeleted(ArrayList<? super Piece> piecesDeleted) {
		this.piecesDeleted = piecesDeleted;
	}

	public ArrayList<? super Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<? super Piece> pieces) {
		this.pieces = pieces;
	}

}
