package model;

public class ClumpOfOysters extends Entity{

	private boolean isVisiable = true;
	
	public ClumpOfOysters(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

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

	public boolean isVisiable() {
		return isVisiable;
	}

	public void setVisiable(boolean isVisiable) {
		this.isVisiable = isVisiable;
	}

	
}
