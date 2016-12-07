package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ClumpOfOysters;
import model.ConcreteWalls;


public class ConcreteWallsTest {
	
	public void exceptionTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
	    tester.move();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void healthTest(){
		ConcreteWalls tester = new ConcreteWalls(0, 0);
		tester.changeHealth(1);		
	}
	
}
