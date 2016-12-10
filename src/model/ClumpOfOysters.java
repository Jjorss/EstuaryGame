package model;

import java.io.Serializable;
import java.util.Random;

public class ClumpOfOysters extends Entity implements Serializable{

	private boolean isVisible = true;
	private boolean collected = false;
	
	private int numOfOystersInClump= 1;
	
	public ClumpOfOysters(int x, int y) {
		super(x, y);

	}
	/** 
	 * Override function from abstract class Entity. This function shouldn't be call
	 * and if it is will throw an UnsupportedOperationException.
	 */
	@Override
	public void move() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	/**
	 * int x in x,y position getter.
	 * @return int x
	 */
	public int getX() {
		return x;
	}
	/**
	 * int x in x,y position setter
	 * @param int y
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * int y in x,y position getter.
	 * @return int y
	 */
	public int getY() {
		return y;
	}

	/**
	 * int y in x,y position setter.
	 * @param int y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * int numOfOystersInClump getter.
	 * @return int numOfOystersInClump
	 */
	public int getNumOfOystersInClump() {
		return numOfOystersInClump;
	}
	/**
	 * int numOfOystersInClump setter.
	 * @param int numOfOystersInClump - desired number of oysters in clump
	 */
	public void setNumOfOystersInClump(int numOfOystersInClump) {
		this.numOfOystersInClump = numOfOystersInClump;
	}

	/**
	 * boolean isVisible getter. True if ClumpOfOysters are visible.
	 * @return boolean isVisible
	 */
	public boolean isVisible() {
		return isVisible;
	}
	/**
	 * boolean isVisible setter. True if ClumpOfOysters are visible
	 * @param boolean isVisiable - desired visibility; true if ClumpOfOysters are visible
	 */
	public void setVisible(boolean isVisiable) {
		this.isVisible = isVisiable;
	}
	/**
	 * boolean collected getter. True if the clump of oysters have been collected.
	 * @return boolean collected - true if the clump of oysters have been collected
	 */
	public boolean isCollected() {
		return collected;
	}
	/**
	 * boolean collected setter. True if the clump of oysters have been collected.
	 * @param boolean collected - true if the clump of oysters have been collected
	 */
	public void setCollected(boolean collected) {
		this.collected = collected;
	}

}