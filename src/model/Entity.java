package model;

public abstract class Entity {
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
