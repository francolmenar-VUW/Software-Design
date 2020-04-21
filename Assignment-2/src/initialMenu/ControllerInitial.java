package initialMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControllerInitial implements ActionListener {
	ModelInitial myModel;

	public ControllerInitial(ModelInitial m) {
		myModel = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton aux = (JButton)e.getSource();
		if(aux.getText() == "Quit") myModel.exit();
		else if(aux.getText() == "Info") myModel.goInfo();
		else if(aux.getText() == "Begin") myModel.goGame();
	}



}