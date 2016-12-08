package model;

import java.io.Serializable;

public abstract class Wall implements Serializable{
	
	int x;
	int y;
	int health;
	
	public Wall(int x, int y) {
		this.x = x;
		this.y = y;
		health = 100;
	}
	
	abstract public void changeHealth(int newHealth);
}
