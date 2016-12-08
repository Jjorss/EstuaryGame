package model;

import java.io.Serializable;

public abstract class Entity implements Serializable{
	int x;
	int y;
	int health;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		health = 100;
	}
	
	abstract public void move();
}
