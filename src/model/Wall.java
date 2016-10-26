package model;

public abstract class Wall {
	
	int x;
	int y;
	int health;
	
	abstract public void build();
	abstract public void changeHealth(int newHealth);
}
