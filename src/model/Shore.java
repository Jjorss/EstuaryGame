package model;

public class Shore extends Entity {

	private int maxHealth = 100;
	private int health = maxHealth;
	private int numberOfHorseshoeCrabs = 0;
	private int x = 0;
	private int y = 0;
	private int hits = 0;
	
	public Shore(int x, int y) {
		super(x,y);
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void move() {
		throw new UnsupportedOperationException();
		
	}
	public boolean erode(){
		this.hits++;
		if (this.hits == 2) {
			// amount to decrement health by
			this.health = health - 25;
			System.out.println("Shore Health: " + this.health);
			// erode shore
			this.setX(this.getX() - 10);
			this.hits = 0;
			return true;
		} else {
			return false;
		}
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


	public int getMaxHealth() {
		return maxHealth;
	}


	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	
	
}
