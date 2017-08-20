package model;

import java.awt.Polygon;
import java.util.*;

public class GrassLand extends Observable {
  public static Random r = new Random();
  public final float maxSize = 100;
  private Wheat w = new Wheat(50, 50);
  private List<Pig> pigs = new ArrayList<>();
  { pigs.add(new Pig(0, r.nextInt((int) maxSize))); }

  public List<Pig> getPigs() { return new ArrayList<>(pigs); }

  public Wheat getWheat() { return w; }

  public void moveWheat(int x, int y) {
    if (x > 1 || x < -1 || y > 1 || y < -1)
      throw new Error("Invalid Command");
    w.x =inMargin(w.x+x * w.speed);
    w.y =inMargin(w.y+y * w.speed);
    ping();
  }

  private float inMargin(float val) {
    return Math.max(0, Math.min(val,maxSize));
  }

  public void ping() {
    if (r.nextInt(10) == 0) { pigs.add(new Pig(0, r.nextInt((int) maxSize))); }
    for (Pig p : pigs) { p.ping(); }
    setChanged();
    notifyObservers();
  }


  class Item {//inner class: know about GrassLand
    public Item(float x, float y) { this.x = x; this.y = y; }
    float x; public float getX() { return x; }//no setter
    float y; public float getY() { return y; }
  }

  public class Pig extends Item {
	float radiux = 10;
    public Pig(float x, float y) { super(x, y); }
    public final float speed = 0.5f;
    public void ping() {
      if( (x > w.x) && containsX(x, y, false) == false){  x -= speed; }
      if( (x < w.x) && containsX(x, y , true) == false){ x += speed; }
      if( (y > w.y) && containsY(y, false) == false){ y -= speed; }
      if( (y < w.y) && containsY(y, true) == false){ y += speed; }
    }
    boolean containsX(float x,float y, boolean up) {
        for (Pig p : pigs) {
        	if(up == true) {//derecha
        		if( (p.getX() - radiux < x + speed) && (p.getX() > x) &&
        				(y <= p.getY()) && (y >= p.getY() - radiux)) {
            		return true;
            	}
        	}
        	else {//left
        		if( (p.getX() - radiux*2 < x + speed) && (p.getX() < x) &&
        				(y <= p.getY()) && (y >= p.getY() - radiux)) {
        			return true;
        		}
        	}
        }
    	return false;
    }
    boolean containsY(float y, boolean up) {
        for (Pig p : pigs) {
        	if(up == true) {//up
        		if( (p.getY() - radiux < y + speed) && (p.getY() > y) &&
        				(x <= p.getX()) && (x >= p.getX() - radiux)) {
        			return true;
        		}
        	}
        	else {//Down
        		if( (p.getY()  > y + speed) && (p.getY() < y)&&
        				(x <= p.getX()) && (x >= p.getX() - radiux)) {
        			return true;
        		}
        	}
        }
    	return false;
    }
  }

  public class Wheat extends Item {
    public Wheat(float x, float y) { super(x, y); }
    public final float speed = 2;
  }
}