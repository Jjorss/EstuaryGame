package tests;

import org.junit.Test;

import model.Gabion;

// I think build should be in GabionBuilder only, because it just adds to the collection
public class GabionTest {
	@Test
	public void buildTest() {
		Gabion tester = new Gabion();
		tester.build();
//		assertEquals();
	}
	public void changeHealthTest() {
		Gabion tester = new Gabion();
		int oldHealth = tester.getHealth();
		tester.changeHealth(1);
		assertEquals(tester.getHealth(), oldHealth+1);
		
		oldHealth = tester.getHealth();
		tester.changeHealth(-1);
		assertEquals(tester.getHealth(), oldHealth-1);
		
		oldHealth = tester.getHealth();
		tester.changeHealth(200);
		assertEquals(tester.getHealth(), oldHealth+200);
		
		oldHealth = tester.getHealth();
		tester.changeHealth(-200);
		assertEquals(tester.getHealth(), oldHealth-200);
	}
}
