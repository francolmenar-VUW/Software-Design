package gameWindowViewElements;

public class MainPanel extends BasicPanel{
	private LeftPanel BoardPanel;
	private RightPanel PiecesSide;

	public MainPanel() {
		super();
		BoardPanel = new LeftPanel();
		PiecesSide = new RightPanel();
	}

	public LeftPanel getBoardPanel() {
		return BoardPanel;
	}

	public void setBoardPanel(LeftPanel boardPanel) {
		BoardPanel = boardPanel;
	}

	public RightPanel getPiecesSide() {
		return PiecesSide;
	}

	public void setPiecesSide(RightPanel piecesSide) {
		PiecesSide = piecesSide;
	}
}
