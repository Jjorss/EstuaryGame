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
	
	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		this.health = newHealth;
		
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	
}
