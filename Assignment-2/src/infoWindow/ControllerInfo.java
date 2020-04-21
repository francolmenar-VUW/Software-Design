package infoWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ControllerInfo implements ActionListener{
	ModelInfo myModel;

	public ControllerInfo(ModelInfo m) {
		myModel = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton aux = (JButton)e.getSource();
		if(aux.getText() == "Back") myModel.goMenu();;
	}


}
