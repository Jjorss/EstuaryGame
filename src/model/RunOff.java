package model;

import java.io.Serializable;

public class RunOff extends Entity implements Serializable{
	/**
	 * 
	 * @author Jackson Jorss
	 * @author Jael Flaquer
	 * @author Ben Clark
	 * @author Robert Ley
	 * 
	 *
	 */

	private int speed;
	private int x;
	private int y;
	private int rowNum;

	public RunOff(int speed, int x, int y, int rowNum) {
			super(x,y);
			this.x = x;
			this.y = y;
			this.speed = speed;
			this.setRowNum(rowNum);
	}

	/**
	 * Decreases the x value of the object on the grid by the speed attribute.
	 */
	@Override
	public void move() {
		this.setX(this.getX() + this.getSpeed());
		//System.out.println(this.getX());
	}


	/**
	 * Getter for the attribute speed.
	 * @return the current instance of speed.
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setter for the attribute speed.
	 * @param speed int Abstract integer that modifies how fast run off moves across the JPanel.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Getter for the attribute x.
	 * @return The current instance of x. 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter for the attribute x.
	 * @param x int The X coordinate of the run off on the JPanel.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter for the attribute y.
	 * @return the current instance y.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter for the attribute y
	 * @param y int The y coordinate of the run off on the JPanel.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Getter for the attribute rowNum.
	 * @return The current instance of rowNum.
	 */
	public int getRowNum() {
		return rowNum;
	}
	/**
	 * Setter for the attribute rowNum
	 * @param rowNum int The row number that the run off is in.
	 */
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}


}
