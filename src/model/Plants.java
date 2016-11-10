package model;

public class Plants extends Wall{

	int x1;
	int x2;
	int x3;
	int y1;
	int y2;
	int y3;
	int health = 3;

	public Plants(int x, int y) {
		super(x, y);
		this.y = y;
		this.x = x;
		// TODO Auto-generated constructor stub
	}
	
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
