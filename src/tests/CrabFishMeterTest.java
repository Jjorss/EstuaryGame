package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CrabFishMeter;

public class CrabFishMeterTest {
	
	@Test
	public void test(){
		CrabFishMeter tester = new CrabFishMeter();
		
		tester.setPhLevels(0);
		int ph1 = tester.getPhLevels();
		tester.changePhLevels(1);
		int ph2 = tester.getPhLevels();
		
		tester.setMood(0);
		int mood1 = tester.getMood();
		tester.changeMood(1);
		int mood2 = tester.getMood();
		
		tester.setSize(0);
		int size1 = tester.getSize();
		tester.changeSize(1);
		int size2 = tester.getSize();
		

		assertTrue(ph1 != ph2);
		assertTrue(mood1 != mood2);
		assertTrue(size1 != size2);
		
	}
	
}
