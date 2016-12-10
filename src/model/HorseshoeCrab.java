package model;

import java.io.Serializable;

public class HorseshoeCrab extends Entity implements Serializable{

	public HorseshoeCrab(int x, int y) {
		super(x, y);
		
	}
	

	// Don't use
	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.x = 2;
		
	}
	/**
	 * Getter for the attribute x.
	 * @return the current instance of x.
	 */
	public int getX() {
		return super.x;
	}
	/**
	 * Setter for the attribute x.
	 * @param x int The X coordinate of the blue crab on the JPanel.
	 */
	public void setX(int x) {
		super.x = x;
	}
	/**
	 * Getter for the attribute y.
	 * @return The current instance of y.
	 */
	public int getY() {
		return super.y;
	}
	/**
	 * Setter for the attribute y.
	 * @param y int The y coordinate of the blue crab on the JPanel.
	 */
	public void setY(int y) {
		super.y = y;
	}
}
