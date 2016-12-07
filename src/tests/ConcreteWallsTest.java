package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ConcreteWalls;


public class ConcreteWallsTest {
	
	@Test
	public void healthTest(){
		ConcreteWalls tester = new ConcreteWalls(0, 0);
		
		tester.setHealth(0);
		int health1 = tester.getHealth();
		tester.changeHealth(1);
		int health2 = tester.getHealth();
		
		assertTrue(health1 == health2);
		
	}
	@Test
	public void visibilityTest(){
		ConcreteWalls tester = new ConcreteWalls(0, 0);
		
		assertTrue(tester.isVisible());
		tester.setVisible(false);
		assertFalse(tester.isVisible());
	}
}
