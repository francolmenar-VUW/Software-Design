package elements;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameTest {



	@Test
	public void testCreatePiece() {
		int [] pos = {1,1};
		int [] elem = {0,0,0,0};
		assertNotNull(Game.createPiece('a', pos, elem));
		}

	@Test
	public void testCreateBoard() {
		BoardSwordShield mesa = Game.createBoard();
		assertNotNull(mesa);
	}

	@Test
	public void testCreateAllPieces() {
		ArrayList<? super Piece> listP0 = Game.createAllPieces('a');
		assertNotNull(listP0);}

	public void testCreateAllPieces1() {
		ArrayList<? super Piece> listP0 = Game.createAllPieces('a');
		assertTrue(assertIntEqual(listP0.size(), Game.NPIECES));}


	private boolean assertIntEqual(int size, int npieces) {
		if(size == npieces) return true;
		else return false;
	}
}
