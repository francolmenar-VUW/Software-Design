package gameWindowViewElements;

import java.awt.*;
import javax.swing.*;

public class RoundButton extends JButton {
	private static final long serialVersionUID = 1L;
	private Color color;

	public RoundButton(Color color)	{
	    this.color=color;
	}

	protected void paintComponent(Graphics g){
    super.paintComponent(g);
    g.setColor(color);
    g.fillOval(0, 0, 35, 35);
    }

	public void paintBorder(Graphics g){
	    g.setColor(color);
	    g.drawOval(0,0, 35, 35);
	}
}
