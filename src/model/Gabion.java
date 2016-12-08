package model;

import java.io.Serializable;

public class Gabion extends Wall implements Serializable{
	private boolean isVisible = true;
	private int health = 3;
	private int rowNum;
	public Gabion(int x, int y, int rowNum) {
		super(x, y);
		this.rowNum = rowNum;
		
	}
	
	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		this.health = newHealth;
	}
	
	public int getHealth() {
		return this.health;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	public int getRowNum() {
		return this.rowNum;
	}
	public void setHealth(int h) {
		this.health = h;
	}
}
