package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.BlueCrab;

public class BlueCrabTests {
	@Test
	public void moveTest() {
		BlueCrab tester = new BlueCrab(0, 0);
		tester.move();
		assertEquals(tester.getX(), 2);
		assertEquals(tester.getY(), 0);
	}
	@Test
	public void testX() {
		BlueCrab tester = new BlueCrab(0,0);
		tester.setX(9);
		assertTrue(tester.getX() == 9);
	}
	
	@Test
	public void testY() {
		BlueCrab tester = new BlueCrab(0,0);
		tester.setY(22);
		assertTrue(tester.getY() == 22);
	}
}
