package model;

import java.io.Serializable;

public class ConcreteWalls extends Wall implements Serializable{
	
	private boolean isVisible = true;

	public ConcreteWalls(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public int getX(){
		return super.x;
	}
	
	public int getY() {
		return super.y;
	}
	
	
}
