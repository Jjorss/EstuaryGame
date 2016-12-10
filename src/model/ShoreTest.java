package model;

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
		int prevHealth = shore.getHealth();
		// collision happened
		// shore.errode was called
		shore.erode();
		// shore loses health
		assertTrue(shore.getHealth() < prevHealth);
	}
		
	@Test
	public void testX() {
		Shore s = new Shore(50, 0);
		s.setX(30);
		assertTrue(s.getX() == 30);
	}
	@Test
	public void testY() {
		Shore s = new Shore(50, 0);
		s.setY(70);
		assertTrue(s.getY() == 70);
	}
	
	@Test
	public void testMaxHealth() {
		Shore s = new Shore(50, 0);
		assertTrue(s.getMaxHealth() == 100);
	}
}
