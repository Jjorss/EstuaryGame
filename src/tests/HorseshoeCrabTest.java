package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.HorseshoeCrab;

public class HorseshoeCrabTest {
	@Test
	public void moveTest() {
		HorseshoeCrab tester = new HorseshoeCrab(0, 0);
		int oldX = tester.getX();
		int oldY = tester.getY();
		tester.move();
		assertTrue(tester.getX()!= oldX || tester.getY()!= oldY);
	}
}
