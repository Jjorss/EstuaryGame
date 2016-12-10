package model;

import java.io.Serializable;

public class ConcreteWalls extends Wall implements Serializable{
	
	private boolean isVisible = true;

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
	/**
	 * Visibility of the concreteWalls getter. True if concreteWalls are visible
	 * @return concreteWalls Visibility
	 */
	public boolean isVisible() {
		return isVisible;
	}
	/**
	 * Visibility of the concreteWalls setter. True if concreteWalls are visible
	 * @param boolean concreteWalls new Visibility
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	/**
	 * concreteWalls x location
	 * @return int x
	 */
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
