package model;

import java.io.Serializable;

public class Plants extends Wall implements Serializable{

	private int x;
	private int y;
	private int maxHealth = 200;
	private int health = maxHealth;
	private boolean isVisible = false;

	public Plants(int x, int y, boolean isVisible) {
		super(x, y);
		this.y = y;
		this.x = x;
		this.isVisible = isVisible;
		// TODO Auto-generated constructor stub
	}
	// we don't use this function i tink
	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		this.health = newHealth;
	}
	/**
	 * Getter for the attribute health.
	 * @return The current instance of health.
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Setter for the attribute health.
	 * @param health int The health of the plants.
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * Getter for the attribute y.
	 * @return The current instance of y.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter for the attribute y.
	 * @param y int The Y coordinate of the plant on the JPanel.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Getter for the attribute x.
	 * @return The current instance of x.
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setter for the attribute x.
	 * @param x int The X coordinate of the plant on the JPanel.
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter for the attribute isVisible.
	 * @return The current instance of isVisible.
	 */
	public boolean isVisible() {
		return isVisible;
	}
	/**
	 * Setter for the attribute isVisible.
	 * @param isVisible boolean Determines if the plant is being painted on the JPanel.
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	/**
	 * Getter for the attribute maxHealth.
	 * @return The current instance of maxHealth.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	/**
	 * Setter for the attribute maxHealth.
	 * @param maxHealth int Maximum health of the plants.
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
}
