package model;

import java.io.Serializable;

public class Shore extends Entity implements Serializable{

	private int maxHealth = 100;
	private int health = maxHealth;
	private int numberOfHorseshoeCrabs = 0;
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
	 * Decreases the parameter health by the parameter erodeAmount.
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

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getNumberOfHorseshoeCrabs() {
		return numberOfHorseshoeCrabs;
	}

	public void setNumberOfHorseshoeCrabs(int numberOfHorseshoeCrabs) {
		this.numberOfHorseshoeCrabs = numberOfHorseshoeCrabs;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

}
