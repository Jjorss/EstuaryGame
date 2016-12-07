package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ClumpOfOysters;

public class ClumpOfOystersTest {
	@Test
	public void numberOfOystersTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		assertTrue(3 <= tester.getNumOfOystersInClump() || tester.getNumOfOystersInClump() <= 5);
	}
	@Test
	public void moveTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		int oldX = tester.getX(); int oldY = tester.getY();
		tester.move(); //doesn't move
		assertEquals(tester.getX(), oldX);
		assertEquals(tester.getY(), oldY);
	}
	@Test(expected=UnsupportedOperationException.class)
	public void exceptionTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
	    tester.move();
	}
	
	@Test
	public void visibilityTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		assertTrue(tester.isVisible());
		tester.setVisible(false);
		assertFalse(tester.isVisible());
	}
	
	public void collectTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		assertFalse(tester.isCollected());
		tester.setCollected(true);
		assertTrue(tester.isCollected());
	}
}
