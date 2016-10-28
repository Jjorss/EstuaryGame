package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ClumpOfOysters;

public class ClumpOfOystersTest {
	@Test
	public void numberOfOystersTest() {
		ClumpOfOysters tester = new ClumpOfOysters();
		tester.spawn();
		assertTrue(3 <= tester.getNumOfOystersInClump() || tester.getNumOfOystersInClump() <= 5);
		
		ClumpOfOysters tester2 = new ClumpOfOysters();
		tester2.spawn();
		tester2.spawn();
		tester2.spawn();
		assertTrue(3 <= tester2.getNumOfOystersInClump() || tester2.getNumOfOystersInClump() <= 5);
	}
	@Test
	public void moveTest() {
		ClumpOfOysters tester = new ClumpOfOysters();
		int oldX = tester.getX(); int oldY = tester.getY();
		tester.move(); //doesn't move
		assertEquals(tester.getX(), oldX);
		assertEquals(tester.getY(), oldY);
	}
	@Test(expected=UnsupportedOperationException.class)
	public void exceptionTest() {
		ClumpOfOysters tester = new ClumpOfOysters();
	    tester.move();
	}
}
