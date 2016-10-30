package tests;

import static org.junit.Assert.*;

import org.junit.Assert;

import model.Plants;

import org.junit.Test;

public class PlantsTest {

	@Test
	public void test() {
		Plants plantatron = new Plants(0, 0);
		
		int prevHealth = plantatron.getHealth();
		
		plantatron.changeHealth(90);
		
		Assert.assertTrue(prevHealth != plantatron.getHealth());
	}

}
