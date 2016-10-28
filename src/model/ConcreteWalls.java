package model;

public class ConcreteWalls extends Wall {
	int x;
	int y;
	private int health;
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		
	}

}
