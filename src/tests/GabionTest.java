package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Gabion;

public class GabionTest {
	@Test
	public void changeHealthTest() {
		Gabion tester = new Gabion(0, 0, 0);
		tester.changeHealth(1);
		assertEquals(tester.getHealth(), 1);

	}
	
}
