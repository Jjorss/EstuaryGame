package model;

import java.io.Serializable;

public class ConcreteWalls extends Wall implements Serializable{
	

	public ConcreteWalls(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Override function from abstract class Wall. This function should not be call
	 * and if it is, will throw an UnsupportedOperationException.
	 */
	@Override
	public void changeHealth(int newHealth) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		
	}

	public int getX(){
		return super.x;
	}
	/**
	 * concreteWalls y location
	 * @return int y
	 */
	public int getY() {
		return super.y;
	}
	
	
}
