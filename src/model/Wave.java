package model;

import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Ley
 * 
 *
 */

public class Wave extends Entity implements Serializable{

	private int speed;

	
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
	 * Getter for the parameter speed.
	 * @return The current instance speed.
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Setter for the parameter speed.
	 * @param speed int Abstract integer that represents how fast an object moves on the JPanel.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * Getter for the parameter x.
	 * @return The current instance x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setter for the parameter x.
	 * @param x int X coordinate of a wave on the JPanel.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter for the parameter y.
	 * @return The current instance y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter for the parameter y.
	 * @param y int Y coordinate of a wave on the JPanel.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	
	
}
