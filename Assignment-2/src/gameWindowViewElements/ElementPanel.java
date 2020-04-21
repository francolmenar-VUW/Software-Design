package gameWindowViewElements;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ElementPanel extends BasicPanel{

	private JPanel piecesToCreate; //Pieces to be created
	private RoundButton [][] playerPieces;
	private JPanel Cemetery;//Pieces already deleted
	private RoundButton[][] CemeteryPieces;

	public ElementPanel() {
		super();
		confElementPanel();
		confPiecesToCreate();
		confCementery();
		this.getPanel().add(piecesToCreate);//Pieces to create to Element panel
		this.getPanel().add(Cemetery);//Cemetery to create to Element panel
	}

	/**
	 * It creates the basic configuration of the Cemetery
	 */
	private void confCementery() {
		Cemetery = new JPanel();//Pieces already deleted
		CemeteryPieces = new RoundButton[6][4];
	}

	/**
	 * It creates the basic configuration of the Pieces to create
	 */
	private void confPiecesToCreate() {
		piecesToCreate = new JPanel() ; //Pieces to be created
		playerPieces = new RoundButton[6][4];
	}

	private void confElementPanel() {
		this.getPanel().setLayout(new GridLayout(2,0));	//I divide the ElementPanel in 2 rows
	}

	public JPanel getPiecesToCreate() {
		return piecesToCreate;
	}

	public void setPiecesToCreate(JPanel piecesToCreate) {
		this.piecesToCreate = piecesToCreate;
	}

	public RoundButton[][] getPlayerPieces() {
		return playerPieces;
	}

	public void setPlayerPieces(RoundButton[][] playerPieces) {
		this.playerPieces = playerPieces;
	}

	public JPanel getCemetery() {
		return Cemetery;
	}

	public void setCemetery(JPanel cemetery) {
		Cemetery = cemetery;
	}

	public JButton[][] getCemeteryPieces() {
		return CemeteryPieces;
	}

	public void setCemeteryPieces(RoundButton[][] cemeteryPieces) {
		CemeteryPieces = cemeteryPieces;
	}


}
