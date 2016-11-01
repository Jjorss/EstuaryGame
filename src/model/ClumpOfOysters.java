package model;

import java.util.Random;

public class ClumpOfOysters extends Entity{

	private boolean isVisible = true;
	private int numOfOystersInClump= 0;
	private int minimum = 3;
	private int maximum = 5;
	
	public ClumpOfOysters(int x, int y) {
		super(x, y);
		this.spawn();
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	public void spawn() {
		Random rand = new Random();
		this.numOfOystersInClump = rand.nextInt((maximum - minimum) + 1) + minimum;
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