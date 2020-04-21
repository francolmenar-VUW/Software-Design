package initialMenu;

import javax.swing.SwingUtilities;

public class MainMenu {

	public static void main(String[] args) {
		ModelInitial m1 = new ModelInitial();//model
		ControllerInitial c1 = new ControllerInitial(m1);//controller
	    SwingUtilities.invokeLater(()->new ViewInitial(m1, c1));
	}
}
