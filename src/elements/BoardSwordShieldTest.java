package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardSwordShieldTest {

	@Test
	public void testIsEmptyRow() {
		BoardSwordShield mesa = Game.createBoard();
		assertFalse("The row is not empty", mesa.isEmptyRow(1));
	}

	@Test
	public void testIsEmptyElement() {
		BoardSwordShield mesa = Game.createBoard();
		assertFalse("The element is not empty", mesa.isEmptyElement(1,1));
		}

	@Test
	public void testExists() {
		BoardSwordShield mesa = Game.createBoard();
		int [] pos = {1,1};
		int [] elem = {0,0,0,0};
		PieceSwordShield piece = Game.createPiece('a', pos, elem);
		mesa.addPiece(piece, pos);
		assertNotNull(mesa.exists('a'));
	}

	@Test
	public void testMove() {
		BoardSwordShield mesa = Game.createBoard();
		int [] pos = {1,1};
		int [] elem = {0,0,0,0};
		PieceSwordShield piece = Game.createPiece('a', pos, elem);
		mesa.addPiece(piece, pos);
		assertTrue(mesa.move('a', "up"));
	}

	@Test
	public void testMove1() {
		BoardSwordShield mesa = Game.createBoard();
		int [] pos = {1,1};
		int [] elem = {0,0,0,0};
		PieceSwordShield piece = Game.createPiece('a', pos, elem);
		mesa.addPiece(piece, pos);
		mesa.move('a', "up");
		PieceSwordShield pieceAux = (PieceSwordShield) mesa.elements.get(pos[1]+1).get(pos[0]);
		assertArrayEquals('a', pieceAux.getName());
	}

	private boolean assertArrayEquals(char name, char name2) {
		if(name == name2) return true;
		else return false;

	}

	@Test
	public void testRemoveElement() {
		BoardSwordShield mesa = Game.createBoard();
		int [] pos = {1,1};
		int [] elem = {0,0,0,0};
		PieceSwordShield piece = Game.createPiece('a', pos, elem);
		mesa.addPiece(piece, pos);
		mesa.removeElement(pos);
		PieceSwordShield pieceAux = (PieceSwordShield) mesa.elements.get(pos[1]).get(pos[0]);
		assertArrayEquals('?', pieceAux.getName());
		}

	@Test
	public void testAddPiece() {
		BoardSwordShield mesa = Game.createBoard();
		int [] pos = {1,1};
		int [] elem = {0,0,0,0};
		PieceSwordShield piece = Game.createPiece('a', pos, elem);
		mesa.addPiece(piece, pos);
		PieceSwordShield pieceAux = (PieceSwordShield) mesa.elements.get(pos[1]).get(pos[0]);
		assertArrayEquals('a', pieceAux.getName());
		}

}
