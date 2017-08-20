package initialMenu;

import java.util.Observable;

public class ModelInitial extends Observable{
	private int mode;

	public ModelInitial() {
		setMode(0);//Initial Window Mode
	}

	/**
	 * It changes to the info window
	 */
	public void goInfo() {
		setMode(1);
		setChanged();
	    notifyObservers();
	}

	/**
	 * It changes to the menu window
	 */
	public void goMenu() {
		setMode(0);
		setChanged();
	    notifyObservers();
	}

	/**
	 * It closes the window
	 */
	public void exit() {
		setMode(-1);
		setChanged();
	    notifyObservers();
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	/**
	 * It changes to the game window
	 */
	public void goGame() {
		setMode(2);
		setChanged();
	    notifyObservers();
	}

}
