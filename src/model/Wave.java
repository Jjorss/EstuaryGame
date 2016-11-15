package model;

import java.awt.geom.Rectangle2D;

/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Ley
 * 
 *
 */

public class Wave extends Entity{

	private int speed;
	private boolean isVisable = true;

	
	public Wave(int speed, int x, int y) {
		super(x,y);
		this.speed = speed;
	}
	/**
	 * Decreases the x value of the object on the grid by 1 * speed
	 */
	@Override
	public void move() {
		this.setX(this.getX() - this.getSpeed());
	}
	
	/**
	 * Getter
	 * @return the current instance speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Setter
	 * @param speed int that sets speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * Getter
	 * @return the current instance X
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setter
	 * @param x int that sets the x value
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter
	 * @return the current instance Y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter
	 * @param y int that sets the y value
	 */
	public void setY(int y) {
		this.y = y;
	}
	public boolean isVisable() {
		return isVisable;
	}
	public void setVisable(boolean isVisable) {
		this.isVisable = isVisable;
	}

	
}
