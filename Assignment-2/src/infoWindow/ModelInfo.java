package infoWindow;

import java.util.Observable;

public class ModelInfo extends Observable{
	private int mode;

	public ModelInfo() {
		setMode(1);//Info Window Mode
	}

	/**
	 * It changes to the menu window
	 */
	public void goMenu() {
		setMode(0);
		setChanged();
	    notifyObservers();
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
