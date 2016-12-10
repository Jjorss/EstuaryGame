package model;

import static org.junit.Assert.*;

import org.junit.Assert;

import model.Plants;

import org.junit.Test;

public class PlantsTest {

	@Test
	public void test() {
		Plants plantatron = new Plants(0, 0, true);
		
		int prevHealth = plantatron.getHealth();
		
		plantatron.changeHealth(90);
		
		Assert.assertTrue(prevHealth != plantatron.getHealth());
	}
	
	@Test
	public void testY() {
		Plants p = new Plants(0, 0, true);
		p.setY(52);
		assertTrue(p.getY() == 52);
	}
	@Test
	public void testX() {
		Plants p = new Plants(0, 0, true);
		p.setX(63);
		assertTrue(p.getX() == 63);
	}
	@Test
	public void testVisible() {
		Plants p = new Plants(0, 0, true);
		p.setVisible(false);
		assertTrue(!p.isVisible());
	}
	
	public void testMaxHealth() {
		Plants p = new Plants(0, 0, true);
		assertTrue(p.getMaxHealth() == 200);
	}

}
