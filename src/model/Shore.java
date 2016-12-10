package model;

import java.io.Serializable;

public class Shore extends Entity implements Serializable{

	private final int maxHealth = 100;
	private int health = maxHealth;
	private int x = 0;
	private int y = 0;
	private int erodeAmount = 13;

	public Shore(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method should not be called.
	 */
	@Override
	public void move() {
		throw new UnsupportedOperationException();

	}
	/**
	 * Decreases the attribute health by the attribute erodeAmount.
	 * @return boolean Always returns true.
	 */
	public boolean erode() {
		// amount to decrement health by
		this.health = health - erodeAmount;
		System.out.println("Shore Health: " + this.health);
		// erode shore
		//this.setX(this.getX() - 10);
		return true;
	}

	/**
	 * Getter for the attribute health.
	 * @return int The current instance of health.
	 */

	public int getHealth() {
		return health;
	}
	
	/**
	 * Getter for the attribute x.
	 * @return the current instance of x.
	 */

	public int getX() {
		return x;
	}
	/**
	 * Setter for the attribute x.
	 * @param x int The x coordinate of the shore on the JPanel.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter for the attribute y.
	 * @return the current instance of y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter for the attribute y.
	 * @param y int The y coordinate of the shore on the JPanel.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Getter for the attribute maxHealth.
	 * @return the current instance of maxHealth.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}


}
