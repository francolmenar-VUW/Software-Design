package gameWindowViewElements;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import elements.Shield;
import elements.Sword;
import elements.Symbol;

public class RoundButton extends JButton {
	private static final long serialVersionUID = 1L;
	private static final int RADIX = 18;
	private Color color;
	private ArrayList<? super Symbol> object = new ArrayList<Symbol>(4);

	public RoundButton(Color color, ArrayList<? super Symbol> object) {
		super();
		this.color = color;
		this.object = object;
	}

	/**
	 * It paints a specific piece with the symbols of object and the desired color
	 *
	 */
	protected void paintComponent(Graphics g){
	    super.paintComponent(g);
	    g.setColor(color);
	    g.fillOval(0, 0, RADIX*2, RADIX*2);
	    g.setColor(Color.RED);//Line color
	    for(int i = 0; i < object.size(); i++) {
	    	if(object.get(i) instanceof Sword) {//We print a Sword
	    		drawSword(g, i);//I draw the sword
	    	}
	    	else if(object.get(i) instanceof Shield) {//We print the Shield
	    		drawShield(g,i);//I draw a shield
	    	}
	    }
	}

	/**
	 * It draws the Shield
	 * @param g The Graphic
	 * @param i The position of Shield
	 */
	private void drawShield(Graphics g, int i) {
		switch(i) {
		case 0:
			g.fillRect(0,0 , RADIX*2, 4);
			break;
		case 1:
			g.fillRect(RADIX*2-2, 0, 4, RADIX*2+3);
			break;
		case 2:
			g.fillRect(0, RADIX*2-1, RADIX*2, RADIX*2);
			break;
		case 3:
			g.fillRect(0, 0, 4, RADIX*2+3);
			break;
		}
	}

	/**
	 * It draws the Swords
	 * @param g The Graphic
	 * @param i The position of Sword
	 */
	private void drawSword(Graphics g, int i) {
		switch(i) {
		case 0:
			g.fillRect(RADIX-2, 0, 4, RADIX);
			break;
		case 1:
			g.fillRect(RADIX, RADIX-2, RADIX, 4);
			break;
		case 2:
			g.fillRect(RADIX-2, RADIX, 4, RADIX*2);
			break;
		case 3:
			g.fillRect(0, RADIX-2, RADIX, 4);
			break;
		}
	}

	/**
	 * It paints the border of the circle
	 */
	public void paintBorder(Graphics g){
	    g.setColor(color);
	}


	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<? super Symbol> getObject() {
		return object;
	}

	public void setObject(ArrayList<? super Symbol> object) {
		this.object = object;
	}
}
