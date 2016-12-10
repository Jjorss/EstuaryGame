package model;

import java.io.Serializable;
import java.util.Random;

public class ClumpOfOysters extends Entity implements Serializable{

	private boolean isVisible = true;
	
	private int numOfOystersInClump= 1;
	
	public ClumpOfOysters(int x, int y) {
		super(x, y);

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getNumOfOystersInClump() {
		return numOfOystersInClump;
	}

	public void setNumOfOystersInClump(int numOfOystersInClump) {
		this.numOfOystersInClump = numOfOystersInClump;
	}


	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisiable) {
		this.isVisible = isVisiable;
	}

		
}