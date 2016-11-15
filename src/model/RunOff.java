package model;

public class RunOff extends Entity{
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
	private boolean isVisable = true;
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
	 * Decreases the x value of the object on the grid by 1 * speed
	 */
	@Override
	public void move() {
		this.setX(this.getX() + this.getSpeed());
		//System.out.println(this.getX());
	}


	/**
	 * Getter
	 * 
	 * @return the current instance speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Setter
	 * 
	 * @param speed
	 *            int that sets speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Getter
	 * 
	 * @return the current instance X
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setter
	 * 
	 * @param x
	 *            int that sets the x value
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Getter
	 * 
	 * @return the current instance Y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setter
	 * 
	 * @param y
	 *            int that sets the y value
	 */
	public void setY(int y) {
		this.y = y;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}


}
