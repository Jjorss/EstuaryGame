package model;

public class Plants extends Wall{


	public Plants(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	int x;
	int y;
	int health;
	
	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		
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
	
	
}
