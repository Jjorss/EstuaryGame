package model;

public class Gabion extends Wall {
	
	
	public Gabion(int x, int y) {
		super(x, y);
		
	}
	
	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		super.health = newHealth;		
	}
	
	public int getHealth() {
		return super.health;
	}
}
