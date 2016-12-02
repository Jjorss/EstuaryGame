package model;

public class Plants extends Wall{

	private int x;
	private int y;
	private int health = 300;
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
	
	
}
