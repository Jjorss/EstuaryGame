package tests;

import org.junit.Test;

import model.Oysters;

// it's unclear to me how spawn works
// by how much do the oysters increase when they spawn?
public class OystersTest {
	@Test
	public void numberOfOystersTest() {
		Oysters tester = new Oysters();
		int oldNumOyster = tester.getNumberOfOysters();
		tester.spawn();
		assertEquals(tester.getNumberOfOysters(), oldNumOyster+1);
		
		oldNumOyster = tester.getNumberOfOysters();
		tester.spawn();
		tester.spawn();
		tester.spawn();
		assertEquals(tester.getNumberOfOysters(), oldNumOyster+3);
	}
	public void moveTest() {
		Oysters tester = new Oysters();
		int oldX = tester.getX(); int oldY = tester.getY();
		tester.move(); //doesn't move
		assertEquals(tester.getX(), oldX);
		assertEquals(tester.getY(), oldY);
	}
	@Test(expected=UnsupportedOperationException.class)
	public void exceptionTest() {
		Oysters tester = new Oysters();
	    tester.move();
	}
}
