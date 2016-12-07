package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Gabion;

// I think build should be in GabionBuilder only, because it just adds to the collection
public class GabionTest {
	@Test
	public void changeHealthTest() {
		Gabion tester = new Gabion(0, 0, 0);
		tester.changeHealth(1);
		assertEquals(tester.getHealth(), 1);

	}
	
	@Test
	public void visibilityTest() {
		Gabion tester = new Gabion(0, 0, 0);
		
		assertTrue(tester.isVisible());
		tester.setVisible(false);
		assertFalse(tester.isVisible());
	}
}
