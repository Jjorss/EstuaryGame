package tests;

import org.junit.Test;

import model.HorseshoeCrab;

public class HorseshoeCrabTest {
	@Test
	public void moveTest() {
		HorseshoeCrab tester = new HorseshoeCrab();
		int oldX = tester.getX();
		int oldY = tester.getY();
		tester.move();
		assertTrue(tester.getX()!=oldX || tester.getY()!=oldY);
	}
}
