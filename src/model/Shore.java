package model;

public class Shore extends Entity {

	private int health = 100;
	private int numberOfHorseshoeCrabs = 0;
	private int x;
	private int y;
	
	public Shore(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void move() {
		throw new UnsupportedOperationException();
		
	}
	public void erode(){
		// amount to decrement health by
		this.health = health - 10;
		// erode shore
		this.setX(this.getX() - 10);		
	}
	public void grow(){
		
	}
	public void spawnHorseshoeCrab(){
		
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public int getNumberOfHorseshoeCrabs() {
		return numberOfHorseshoeCrabs;
	}


	public void setNumberOfHorseshoeCrabs(int numberOfHorseshoeCrabs) {
		this.numberOfHorseshoeCrabs = numberOfHorseshoeCrabs;
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

	
	
}
