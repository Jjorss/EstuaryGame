package tests;

import static org.junit.Assert.*;


import org.junit.Test;

import model.Shore;

public class ShoreTest {


	@Test(expected=UnsupportedOperationException.class)
	public void testMove() {
		Shore shore = new Shore(50, 0);
		shore.move();
	}
	
	@Test
	public void testErrode() {
		Shore shore = new Shore(50, 0);
		int prevX = shore.getX();
		int prevY = shore.getY();
		int prevHealth = shore.getHealth();
		// collision happened
		// shore.errode was called
		shore.errode(90);
		// shore moved back
		assertTrue(shore.getX() < prevX);
		//shore didn't move vertical
		assertTrue(shore.getY() == prevY);
		// shore's health changed
		assertTrue(shore.getHealth() < prevHealth);
	}
	
	@Test
	public void testGrow() {
		Shore shore = new Shore(50, 0);
		int prevX = shore.getX();
		int prevY = shore.getY();
		int prevHealth = shore.getHealth();
		// Enough time passed where the shore.grow method was called
		shore.grow();
		// show moved forwards
		if (shore.getHealth() < 100) {
			assertTrue(shore.getX() > prevX);
		} else {
			// shore doesn't move because it is at it's maximum length bc health is full
			assertTrue(shore.getX() == prevX);
		}
		//shore doesn't move vertically
		assertTrue(shore.getY() == prevY);
		
		//shores health grows with the length
		assertTrue(shore.getHealth() > prevHealth);
		// shores health cannot go pass 100
		assertTrue(shore.getHealth() <= 100);
	}
	
	@Test
	public void testSpawnHorseshoeCrab() {
		Shore shore = new Shore(50, 0);
		int prevCrabs = shore.getNumberOfHorseshoeCrabs();
		// something 'good' happened where shore.spawnHorseShoeCrab was called
		shore.spawnHorseshoeCrab();
		// horseshoe crabs should increase
		assertTrue(shore.getNumberOfHorseshoeCrabs() > prevCrabs);
	}

}
