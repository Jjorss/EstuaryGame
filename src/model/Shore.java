package model;

import java.io.Serializable;

public class Shore extends Entity implements Serializable{

	private final int maxHealth = 100;
	private int health = maxHealth;
	private int x = 0;
	private int y = 0;

	public Shore(int x, int y) {
		super(x, y);
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		throw new UnsupportedOperationException();

	}

	public boolean erode() {
		// amount to decrement health by
		this.health = health - 13;
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


}
