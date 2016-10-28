package model;

public class ClumpOfOysters extends Entity{

	int x;
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

	int y;
	int numOfOystersInClump;
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
	
	public void spawn() {
		
	}

	
}
