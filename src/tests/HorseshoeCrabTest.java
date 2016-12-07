package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.HorseshoeCrab;

public class HorseshoeCrabTest {
	@Test
	public void moveTest() {
		HorseshoeCrab tester = new HorseshoeCrab(0, 0);
		tester.move();
		assertEquals(tester.getX(), 2);
		assertEquals(tester.getY(), 0);
	}
}
