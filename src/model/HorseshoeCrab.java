package model;

import java.io.Serializable;

public class HorseshoeCrab extends Entity implements Serializable{

	public HorseshoeCrab(int x, int y) {
		super(x, y);
		
	}
	

	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.x = 2;
		
	}
	
	public int getX() {
		return super.x;
	}
	
	public void setX(int x) {
		super.x = x;
	}
	
	public int getY() {
		return super.y;
	}

	public void setY(int y) {
		super.y = y;
	}
}
