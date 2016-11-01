package model;

public class ConcreteWalls extends Wall {
	
	private boolean isVisible;

	public ConcreteWalls(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		
	}
	
	public int getHealth() {
		return super.health;
	}
	
	public void setHealth(int health) {
		super.health = health;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
}
