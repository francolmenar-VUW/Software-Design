package gui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.GrassLand;
import javax.swing.Timer;

public class Controller implements KeyListener {
  GrassLand myModel;
  public Controller(GrassLand m) {myModel = m;
  time();}
  public void keyPressed(KeyEvent e) {}
  public void keyReleased(KeyEvent e) {}
  public void keyTyped(KeyEvent e) {
    switch (e.getKeyChar()) {
      case 'w': myModel.moveWheat(0, -1); return;
      case 'a': myModel.moveWheat(-1, 0); return;
      case 's': myModel.moveWheat(0, 1); return;
      case 'd': myModel.moveWheat(1, 0); return;
    }
  }
  public void time() {
	  Timer timer = new Timer(50,(e)->myModel.ping());
	  timer.start();
  }

}
