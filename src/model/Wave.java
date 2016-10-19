package model;

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
	
	public void move() {
		this.setX((this.getX() - 1)*this.getSpeed());
	}
	
	public void tic() {
		this.move();
	}
	
	public boolean collided() {
		return false;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
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

	public boolean isCollided() {
		return collided;
	}

	public void setCollided(boolean collided) {
		this.collided = collided;
	}
	
}
