package gameWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControllerGameWindow implements ActionListener{
	ModelGameWindow myModel;

	public ControllerGameWindow(ModelGameWindow m) {
		myModel = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton aux = (JButton)e.getSource();
		if(aux.getText() == "Surrender") myModel.goMenu();
	}
}
