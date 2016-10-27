package model;

public class HorseshoeCrab extends Entity {


	
	public HorseshoeCrab(int x, int y) {
		super(x, y);
		
	}
	
	
	@Override
	public void move() {
		// TODO Auto-generated method stub
		super.x = 2;
		
	}

	public static void main (String[] args) {
		HorseshoeCrab h = new HorseshoeCrab(0,0);
		System.out.println(h.x);
		h.x+=1;
		System.out.println(h.x);
		
	}
	
}
