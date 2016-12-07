package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ClumpOfOysters;

public class ClumpOfOystersTest {
		
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
	@Test
	public void collectTest() {
		ClumpOfOysters tester = new ClumpOfOysters(0, 0);
		assertFalse(tester.isCollected());
		tester.setCollected(true);
		assertTrue(tester.isCollected());
	}
}
