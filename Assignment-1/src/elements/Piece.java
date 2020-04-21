package elements;


public class Piece {
	int position[] = new int[2];// position{x,y}

	public Piece(int position[]){
		setPosition(position);
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}
}
