package gameWindowViewElements;

import javax.swing.JPanel;

public class BasicPanel {
	private JPanel panel;//A JPanel

	public BasicPanel() {
		panel = new JPanel();
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

}
