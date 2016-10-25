package model;
/**
 * 
 * @author Jackson Jorss
 * @author Jael Flaquer
 * @author Ben Clark
 * @author Robert Lee
 * 
 *
 */

public class Wave {

	private int speed;
	private int x;
	private int y;
	private boolean collided;
	
	public Wave(int speed, int x, int y, boolean collided) {
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.collided = collided;
	}
	/**
	 * Decreases the x value of the object on the grid by 1 * speed
	 */
	public void move() {
		this.setX((this.getX() - 1)*this.getSpeed());
	}
	/**
	 * Calls all logical methods every tic of the game
	 */
	public void tic() {
		this.move();
	}
	
	/**
	 * Getter
	 * @return the current instance speed
	 */
	public int getSpeed() {
		return speed;
	}
	/**
	 * Setter
	 * @param speed int that sets speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	/**
	 * Getter
	 * @return the current instance X
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setter
	 * @param x int that sets the x value
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter
	 * @return the current instance Y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter
	 * @param y int that sets the y value
	 */
	public void setY(int y) {
		this.y = y;
	}

	
}
